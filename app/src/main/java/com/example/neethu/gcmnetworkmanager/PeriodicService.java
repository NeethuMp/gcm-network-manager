package com.example.neethu.gcmnetworkmanager;

import android.content.Intent;
import android.util.Log;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
/**
 * Created by neethu on 11/3/16.
 */
public class PeriodicService extends GcmTaskService {
    private static final String TAG ="PeriodicService";
    @Override
    public int onRunTask(TaskParams taskParams) {
        Log.i(TAG,"onRunTask: " + taskParams.getTag());
        return GcmNetworkManager.RESULT_SUCCESS;
    }
    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

}

