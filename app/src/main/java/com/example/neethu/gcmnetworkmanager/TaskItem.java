package com.example.neethu.gcmnetworkmanager;

/**
 * Created by neethu on 9/3/16.
 */
public class TaskItem {
    public   static  final String ONEOFF_TASK="oneoffTask";
    public static final String NOW_TASK="nowTask";
    public static final String EXECUTED_STATUS="executed";
    public static final String FAILED_STATUS="failed";
    public static final String PENDIND_STATUS="pending";
    private String mId;
    private String mType;
    private String mStatus;


    public TaskItem(String id, String label, String status) {
        mId = id;
        mType = label;
        mStatus = status;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getType() {
        return mType;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String stat) {
        this.mStatus = stat;
    }
}
