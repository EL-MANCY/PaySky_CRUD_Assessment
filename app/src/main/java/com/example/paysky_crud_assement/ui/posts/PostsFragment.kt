package com.example.paysky_crud_assement.ui.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paysky_crud_assement.databinding.PayskyLayoutFragmentPostsBinding
import com.example.paysky_crud_assement.domain.models.Post
import com.example.paysky_crud_assement.ui.dialog.AddEditPostDialogFragment
import com.example.paysky_crud_assement.utils.launchCoroutine
import com.example.paysky_crud_assement.utils.observeLoadingState
import com.example.paysky_crud_assement.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(), OnPostClickListener {

    private lateinit var binding: PayskyLayoutFragmentPostsBinding
    private val postsViewModel: PostsViewModel by viewModels()
    private var postsList = mutableListOf<Post>()
    private val postsAdapter by lazy { PostsAdapter(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PayskyLayoutFragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postsViewModel.getPosts()
        observeStates()
        setListenere()
    }

    private fun observeStates() {
        observeLoadingState(postsViewModel.loading)

        launchCoroutine {
            postsViewModel.error.collect { errorMessage ->
                errorMessage?.let { showToast(it) }
            }
        }

        launchCoroutine {
            postsViewModel.postsResponse.collect { posts ->
                postsList.clear()
                postsList.addAll(posts)
                setupPostsAdapter(postsList)
            }
        }

        launchCoroutine {
            postsViewModel.deleteResponse.collect { isDeleted ->
                if (isDeleted) {
                    showToast("Post deleted successfully")
                    postsViewModel.getPosts()
                }
            }
        }
        launchCoroutine {
            postsViewModel.insertPostResponse.collect {
                showToast("Post Added successfully")
                postsViewModel.getPosts()

            }
        }
        launchCoroutine {
            postsViewModel.updatePostResponse.collect {
                showToast("Post Edited successfully")
                postsViewModel.getPosts()
            }
        }

    }

    private fun handleSearch(query: String?) {
        val filteredList = postsList.filter {
            it.userId.toString().contains(
                query.orEmpty(),
                ignoreCase = true
            ) || it.title.contains(
                query.orEmpty(),
                ignoreCase = true
            ) || it.body.contains(query.orEmpty(), ignoreCase = true)
        }
        postsAdapter.updatePosts(filteredList)
    }

    private fun setupPostsAdapter(posts: List<Post>) {
        postsAdapter.updatePosts(posts)
        binding.postsRv.adapter = postsAdapter
        binding.postsRv.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onEditPostClick(post: Post) {
        AddEditPostDialogFragment(post) { title, body ->
            postsViewModel.updatePost(post.id, post.copy(title = title, body = body))
        }.show(parentFragmentManager, "EditPostDialog")
    }

    override fun onDeletePostClick(post: Post) {
        postsViewModel.deletePost(post.id)
    }

    fun setListenere() {
        binding.fabAddItem.setOnClickListener {
            AddEditPostDialogFragment() { title, body ->
                postsViewModel.createPost(
                    Post(
                        (Math.random() * 10000).toInt(),
                        (Math.random() * 10000).toInt(),
                        title,
                        body
                    )
                )
            }.show(parentFragmentManager, "EditPostDialog")
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                handleSearch(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                lifecycleScope.launchWhenResumed {
                    handleSearch(query)
                }
                return true
            }
        })
    }
}
