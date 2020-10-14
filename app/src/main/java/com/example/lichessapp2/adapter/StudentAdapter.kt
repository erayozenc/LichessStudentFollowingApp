package com.example.lichessapp2.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lichessapp2.R
import com.example.lichessapp2.model.Student
import com.example.lichessapp2.util.TimeUtil
import kotlinx.android.synthetic.main.item_student_preview.view.*

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.cv_studentView.setOnClickListener {
                val position = adapterPosition
                val bundle = Bundle().apply {
                    putSerializable("student",differ.currentList[position])
                }
                itemView.findNavController().navigate(
                    R.id.action_allStudentFragment_to_studentFragment,
                    bundle
                )
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Student>(){
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_student_preview,
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = differ.currentList[position]
        holder.itemView.apply {
            tvName.text = student.profile?.firstName + " " + student.profile?.lastName
            tvLastSeen.text = TimeUtil.calculateLastSeenTime(student.seenAt)
            tvFIDERating.text = "Fide Rating = " + student.profile?.fideRating.toString()
            tvCountryAndLocation.text = student.profile?.country + " " + student.profile?.location
            tvCreatedAt.text = TimeUtil.calculateCreatedTime(student.createdAt)
        }
    }
}