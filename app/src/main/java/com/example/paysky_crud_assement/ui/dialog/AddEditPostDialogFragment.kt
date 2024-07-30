package com.example.paysky_crud_assement.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.paysky_crud_assement.databinding.PayskyLayoutFragmentEditPostDialogBinding
import com.example.paysky_crud_assement.domain.models.Post

class AddEditPostDialogFragment(
    private val post: Post?=null,
    private val onSave: (String, String) -> Unit
) : DialogFragment() {

    private var _binding: PayskyLayoutFragmentEditPostDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PayskyLayoutFragmentEditPostDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Populate fields with existing post data
        binding.editTextTitle.setText(post?.title)
        binding.editTextBody.setText(post?.body)

        // Set up save button click listener
        binding.buttonSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val body = binding.editTextBody.text.toString()
            onSave(title, body)
            dismiss()
        }

        // Set up cancel button click listener
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
