package com.example.paysky_crud_assement.utils

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.paysky_crud_assement.R
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun Fragment.showToast(message: String) {
    Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
}

fun Fragment.observeLoadingState(loading: StateFlow<Boolean?>) {
    val loadingDialog = Dialog(requireContext())
    loadingDialog.setCancelable(false)
    loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    loadingDialog.setContentView(R.layout.paysky_loading_dialog)

    lifecycleScope.launch {
        loading.collect {
            if (it != null) {
                if (it) {
                    if (!loadingDialog.isShowing)
                        loadingDialog.show()
                } else {
                    loadingDialog.dismiss()
                }
            }
        }
    }
}

fun Fragment.launchCoroutine(suspendedFunction: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            suspendedFunction()
        }
    }
}

fun Any.hasInternetConnection(application: Application): Boolean {
    val connectivityManager = getApplication(application).getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}

