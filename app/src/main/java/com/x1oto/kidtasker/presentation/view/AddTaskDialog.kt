package com.x1oto.kidtasker.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.x1oto.kidtasker.databinding.DialogAddTaskBinding

class AddTaskDialog : DialogFragment() {

    private var _binding: DialogAddTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object{
        fun show(fragment: Fragment){
            AddTaskDialog().show(fragment.childFragmentManager, "addTaskDialog")
        }
    }
}