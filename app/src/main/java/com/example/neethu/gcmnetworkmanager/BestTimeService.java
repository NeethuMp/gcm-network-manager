package com.example.neethu.gcmnetworkmanager;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

/**
 * Created by neethu on 9/3/16.
 */
public class BestTimeService extends GcmTaskService{
    private  static  final String TAG="bestTimeService";

    @Override
    public int onRunTask(TaskParams taskParams) {
        String taskId=taskParams.getExtras().getString(CodelabUtil.TASK_ID);
        boolean completed = CodelabUtil.makeNetworkCall();
        Log.d(TAG,"Oneoff sheduled call executed TaskID: "+taskId);
        Intent taskUpdateIntent = new Intent(CodelabUtil.TASK_UPDATE_FILTER);
        taskUpdateIntent.putExtra(CodelabUtil.TASK_ID, taskId);
        TaskItem taskItem = CodelabUtil.getTaskItemFromFile(this, taskId);
        if (taskItem == null) {
            return GcmNetworkManager.RESULT_FAILURE;
        }
        if (completed) {
            taskItem.setStatus(TaskItem.EXECUTED_STATUS);
        } else {
            taskItem.setStatus(TaskItem.FAILED_STATUS);
        }
        taskUpdateIntent.putExtra(CodelabUtil.TASK_STATUS, taskItem.getStatus());
        CodelabUtil.saveTaskItemToFile(this, taskItem);
        LocalBroadcastManager localBroadcastManager =
                LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(taskUpdateIntent);
        return GcmNetworkManager.RESULT_SUCCESS;

    }
}
