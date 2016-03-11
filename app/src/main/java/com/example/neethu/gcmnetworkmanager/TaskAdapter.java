package com.example.neethu.gcmnetworkmanager;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by neethu on 10/3/16.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private static final String TAG = "TaskAdapter";

    private List<TaskItem> mTaskItems;
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mLabelTextView;
        private final TextView mStatusTextView;
        private final Button mDeleteButton;

        public ViewHolder(View v) {
            super(v);
            mLabelTextView = (TextView) v.findViewById(R.id.taskLabel);
            mStatusTextView = (TextView) v.findViewById(R.id.taskStatus);
            mDeleteButton = (Button) v.findViewById(R.id.deleteButton);
        }
        public TextView getLabelTextView() {
            return mLabelTextView;
        }

        public TextView getStatusTextView() {
            return mStatusTextView;
        }

        public Button getDeleteButton() {
            return mDeleteButton;
        }
    }




    private class DeleteTask extends AsyncTask<TaskItem, Void, Integer> {

        private Context mContext;

        public DeleteTask(Context context) {
            mContext = context;
        }

        @Override
        protected Integer doInBackground(TaskItem... taskItems) {
            TaskItem taskItem = taskItems[0];
            CodelabUtil.deleteTaskItemFromFile(mContext, taskItems[0]);
            return getTaskItemPosition(taskItem.getId());
        }

        @Override
        protected void onPostExecute(Integer position) {
            if (position == -1) {
                return;
            }
            mTaskItems.remove(position.intValue());
            notifyItemRemoved(position);
        }
    }

    public TaskAdapter(List<TaskItem> taskItems) {
        mTaskItems = taskItems;
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_task,
                viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.getDeleteButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Log.d(TAG, position + " - " + mTaskItems.size());
                TaskItem taskItem = mTaskItems.get(position);
                Log.d(TAG, "Clicked button at position " + position);
                new DeleteTask(view.getContext()).execute(taskItem);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder viewHolder, final int position) {
        final TaskItem taskItem = mTaskItems.get(position);
        viewHolder.getLabelTextView().setText(taskItem.getType());
        viewHolder.getStatusTextView().setText(taskItem.getStatus());
    }

    @Override
    public int getItemCount() {
        return mTaskItems.size();
    }

    public void setTaskItems(List<TaskItem> taskItems) {
        mTaskItems.clear();
        mTaskItems.addAll(taskItems);
        notifyDataSetChanged();
    }

    public void addTaskItem(TaskItem taskItem) {
        mTaskItems.add(0, taskItem);
        notifyItemInserted(0);
    }

    private int getTaskItemPosition(String id) {
        for (int i = 0; i < mTaskItems.size(); i++) {
            TaskItem taskItem = mTaskItems.get(i);
            if (taskItem.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void updateTaskItemStatus(String id, String status) {
        for (int i = 0; i < mTaskItems.size(); i++) {
            TaskItem taskItem = mTaskItems.get(i);
            if (taskItem.getId().equals(id)) {
                taskItem.setStatus(status);
                notifyItemChanged(i);
                break;
            }
        }
    }
}
