package com.example.neethu.gcmnetworkmanager;

/**
 * Created by neethu on 9/3/16.
 */
public class TaskItem {
    private  static  final String ONEOFF_TASK="oneoffTask";
    private static final String NOW_TASK="nowTask";
    private static final String EXECUTED_STATUS="executed";
    private static final String FAILED_STATUS="failed";
    private static final String PENDIND_STATUS="pending";
    private String mID;
    private String mType;
    private String mStatus;


    public TaskItem(String id, String label, String status) {
        mID = id;
        mType = label;
        mStatus = status;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

}
