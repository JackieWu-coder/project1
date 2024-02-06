package com.example.project1.ui.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project1.R;
import com.example.project1.model.Task;
import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.TaskViewHolder> {

    private final List<Task> tasks;
    private final OnTaskInteractionListener listener;

    public ToDoListAdapter(List<Task> tasks, OnTaskInteractionListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = tasks.get(position);
        holder.checkBoxCompleted.setChecked(currentTask.isCompleted());
        holder.textViewTitle.setText(currentTask.getTitle());

        holder.buttonEdit.setOnClickListener(v -> listener.onEditTask(currentTask, position));
        holder.buttonDelete.setOnClickListener(v -> listener.onDeleteTask(currentTask, position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBoxCompleted;
        TextView textViewTitle;
        Button buttonEdit, buttonDelete;

        TaskViewHolder(View itemView) {
            super(itemView);
            checkBoxCompleted = itemView.findViewById(R.id.checkbox_completed);
            textViewTitle = itemView.findViewById(R.id.textview_task_title);
            buttonEdit = itemView.findViewById(R.id.button_edit);
            buttonDelete = itemView.findViewById(R.id.button_delete);
            // Apply white background to buttons
            buttonEdit.setBackgroundColor(itemView.getContext().getResources().getColor(android.R.color.white));
            buttonDelete.setBackgroundColor(itemView.getContext().getResources().getColor(android.R.color.white));
            // Set text color for visibility
            buttonEdit.setTextColor(itemView.getContext().getResources().getColor(android.R.color.black));
            buttonDelete.setTextColor(itemView.getContext().getResources().getColor(android.R.color.black));
        }
    }

    public interface OnTaskInteractionListener {
        void onEditTask(Task task, int position);
        void onDeleteTask(Task task, int position);
    }
}
