package com.x1oto.kidtasker.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.x1oto.kidtasker.data.model.Task
import com.x1oto.kidtasker.databinding.ItemTaskBinding

class TaskAdapter(private val taskList: List<Task>, private val isParent: Boolean) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root){
        val title = binding.taskTitleTv
        val desc = binding.taskDescTv
        val reward = binding.taskRewardTv
        val button = binding.acceptBt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val currentTask = taskList[position]

        holder.title.text = currentTask.title
        holder.desc.text = currentTask.desc
        holder.reward.text = currentTask.reward.toString()

        if(isParent){
            holder.button.text = "Delete"
        }else{
            holder.button.text = "Accept"
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}