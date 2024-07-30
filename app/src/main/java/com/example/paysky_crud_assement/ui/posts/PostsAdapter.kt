package com.example.paysky_crud_assement.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paysky_crud_assement.databinding.PayskyLayoutPostItemBinding
import com.example.paysky_crud_assement.domain.models.Post
import com.example.paysky_crud_assement.utils.DiffUtilCallBack

class PostsAdapter(private val onPostClickListener: OnPostClickListener) :
    RecyclerView.Adapter<PostsAdapter.MyViewHolder>() {

    private var postsList = emptyList<Post>()

    inner class MyViewHolder(val binding: PayskyLayoutPostItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = PayskyLayoutPostItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = postsList[position]
        with(holder.binding) {
            textViewRepositoryTitle.text = post.title
            textViewRepositoryBody.text = post.body

            buttonEdit.setOnClickListener {
                onPostClickListener.onEditPostClick(post)
            }

            buttonDelete.setOnClickListener {
                onPostClickListener.onDeletePostClick(post)
            }
        }
    }

    override fun getItemCount(): Int = postsList.size

    fun updatePosts(newPostsList: List<Post>) {
        val diffUtilCallback = DiffUtilCallBack(postsList, newPostsList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        postsList = newPostsList
        diffResult.dispatchUpdatesTo(this)
    }
}
