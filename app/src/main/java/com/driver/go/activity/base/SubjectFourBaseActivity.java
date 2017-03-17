package com.driver.go.activity.base;

import android.os.Bundle;

import com.driver.go.db.SubjectFourSQLiteBehavior;
import com.driver.go.db.SubjectOneSQLiteBehavior;

/**
 * Created by malijie on 2017/3/17.
 */

public class SubjectFourBaseActivity extends DriverBaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    public void initView() {

    }

    public void initData() {
        mSQLiteManager.setSubjectBehavior(new SubjectFourSQLiteBehavior());
    }
}