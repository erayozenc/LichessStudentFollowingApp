package com.example.lichessapp2.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.lichessapp2.R
import com.example.lichessapp2.ui.StudentViewModel
import com.example.lichessapp2.util.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_student.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddStudentFragment : Fragment(R.layout.fragment_add_student) {

    private val viewModel : StudentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var job : Job? = null
        btnConfirm.setOnClickListener {
            job?.cancel()
            job = MainScope().launch {
                etStudentUsername?.let {
                    val studentUsername = etStudentUsername.text.toString()
                    viewModel.getStudentData(studentUsername)
                    Snackbar.make(view,"Saved Succesfully",Snackbar.LENGTH_SHORT)
                }
            }
        }

        viewModel.student.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success -> {
                    response.data?.let {
                        viewModel.saveStudent(it)

                        findNavController().navigate(R.id.action_addStudentFragment_to_allStudentFragment)
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(),"An error occured : ${response.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}