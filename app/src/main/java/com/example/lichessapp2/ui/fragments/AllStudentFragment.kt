package com.example.lichessapp2.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lichessapp2.R
import com.example.lichessapp2.adapter.StudentAdapter
import com.example.lichessapp2.ui.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_all_students.*


@AndroidEntryPoint
class AllStudentFragment : Fragment(R.layout.fragment_all_students) {

    private val viewModel: StudentViewModel by viewModels()
    private lateinit var studentAdapter : StudentAdapter
    private var menu : Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu,menu)
        this.menu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAllStudent ->{
                viewModel.deleteAllStudents()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_allStudentFragment_to_addStudentFragment)
        }

        viewModel.showAllStudents().observe(viewLifecycleOwner, Observer {
            studentAdapter.differ.submitList(it)
        })

    }

    private fun setupRecyclerView(){
        rvAllStudents.apply {
            studentAdapter = StudentAdapter()
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}