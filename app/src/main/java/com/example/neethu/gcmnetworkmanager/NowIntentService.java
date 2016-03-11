package com.example.neethu.gcmnetworkmanager;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by neethu on 9/3/16.
 */
public class NowIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public static final String TAG="nowIntentService";
    public NowIntentService() {
        super(TAG);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String taskId=intent.getStringExtra(CodelabUtil.TASK_ID);
        boolean completed = CodelabUtil.makeNetworkCall();
        Intent taskUpdateIntent = new Intent(CodelabUtil.TASK_UPDATE_FILTER);
        taskUpdateIntent.putExtra(CodelabUtil.TASK_ID, taskId);
        TaskItem taskItem = CodelabUtil.getTaskItemFromFile(this, taskId);
        if(taskItem==null)
        {
            return;
        }
        if(completed)
        {
            taskItem.setStatus(taskItem.EXECUTED_STATUS);
        }
        else {
            taskItem.setStatus(taskItem.FAILED_STATUS);
        }
        taskUpdateIntent.putExtra(CodelabUtil.TASK_STATUS, taskItem.getStatus());
        CodelabUtil.saveTaskItemToFile(this, taskItem);
        LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(taskUpdateIntent);
    }
}
























