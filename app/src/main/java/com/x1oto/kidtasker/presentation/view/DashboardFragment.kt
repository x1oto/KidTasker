package com.x1oto.kidtasker.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.x1oto.kidtasker.databinding.FragmentDashboardBinding
import com.x1oto.kidtasker.presentation.adapters.TaskAdapter
import com.x1oto.kidtasker.presentation.status.Status
import com.x1oto.kidtasker.presentation.status.WaitingDialog
import com.x1oto.kidtasker.presentation.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<DashboardViewModel>()
    private var progressDialog: WaitingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        progressDialog = WaitingDialog(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDataAndStatus()

        binding.addTaskBt.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun setupDataAndStatus() {
        viewModel.fetchData()
        viewModel.dataStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.Loading -> progressDialog?.show()

                is Status.Success -> {
                    val isParent = viewModel.userRole.value ?: false
                    updateUI(isParent)
                    getTasksAndSetStatus()
                }

                is Status.Error -> {
                    Toast.makeText(requireContext(), status.error, Toast.LENGTH_SHORT).show()
                    progressDialog?.dismiss()
                }
            }
        }
    }

    private fun updateUI(isParent: Boolean) {
        if (isParent) {
            binding.taskRv.visibility = View.VISIBLE
            binding.addTaskBt.visibility = View.VISIBLE
        } else {
            binding.taskRv.visibility = View.VISIBLE
            binding.addTaskBt.visibility = View.GONE
        }
    }

    private fun getTasksAndSetStatus() {
        viewModel.fetchTasks()
        viewModel.taskStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.Loading -> progressDialog?.show()

                is Status.Success -> {
                    val taskList = viewModel.taskList.value ?: emptyList()
                    setupRecyclerView(taskList)
                    progressDialog?.dismiss()
                }

                is Status.Error -> {
                    Toast.makeText(requireContext(), status.error, Toast.LENGTH_SHORT).show()
                    progressDialog?.dismiss()
                }
            }
        }
    }

    private fun setupRecyclerView(taskList: List<com.x1oto.kidtasker.data.model.Task>) {
        val adapter = if (viewModel.userRole.value == true) {
            TaskAdapter(taskList, true)
        } else {
            TaskAdapter(taskList, false)
        }
        binding.taskRv.adapter = adapter
    }

    private fun showAddTaskDialog() {
        AddTaskDialog.show(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
