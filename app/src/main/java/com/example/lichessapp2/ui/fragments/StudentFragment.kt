package com.example.lichessapp2.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.lichessapp2.R
import com.example.lichessapp2.ui.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_student.*

@AndroidEntryPoint
class StudentFragment : Fragment(R.layout.fragment_student) {

    private val viewModel : StudentViewModel by viewModels()
    val args : StudentFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val student = args.student
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(student.url)
        }
    }
}