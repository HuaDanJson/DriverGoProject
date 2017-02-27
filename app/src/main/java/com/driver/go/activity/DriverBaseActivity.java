package com.driver.go.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.driver.go.base.Profile;
import com.driver.go.control.IntentManager;
import com.driver.go.db.DBConstants;
import com.driver.go.db.SQLiteManager;
import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.SharePreferenceUtil;
import com.driver.go.utils.Util;
import com.driver.go.utils.image.ImageLoader;

/**
 * Created by Administrator on 2016/11/5.
 */
public abstract class DriverBaseActivity extends FragmentActivity {
    public static int sOrderQuestionTotalNum = Profile.ORDER_TOTAL_ITEM;
    public SQLiteManager mSQLiteManager = null;
    public ImageLoader mImageLoader = null;
    protected final String ANSWER_A = "1";
    protected final String ANSWER_B = "2";
    protected final String ANSWER_C = "3";
    protected final String ANSWER_D = "4";


    public abstract void initView();
    public abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDB();
        initManager();
    }

    private void initManager() {
        mImageLoader = ImageLoader.getInstance();
    }


    private void initDB(){
        mSQLiteManager = SQLiteManager.getInstance();
        mSQLiteManager.createTables();
    }

    public boolean isOrderTableExist(){
        return mSQLiteManager.isOrderTableHasData();
    }

    public void addOrderQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.ORDER_EXAM_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }
    public void addRandomQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.RANDOM_EXAM_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    public void addWrongQuestionItem(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.WRONG_QUESTION_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    protected void saveCollectQuestion(QuestionItem q){
        mSQLiteManager.insertQuestion2Table(DBConstants.COLLECT_QUESTION_TABLE,q.getId(),q.getQuestion(),q.getAnswer(),q.getItem1(),q.getItem2(),q.getItem3(),q.getItem4(),q.getExplains(),q.getUrl());
    }

    public void finishActivity(Activity activity){
        IntentManager.finishActivity(activity);
    }

    public boolean hasInternet(){
        return Util.hasInternet();
    }

    protected void saveOrderQuestionIndex(int index){
        SharePreferenceUtil.saveOrderQuestionIndex(index);
    }

    protected int loadOrderQuestionIndex(){
       return SharePreferenceUtil.loadOrderQuestionIndex();
    }

    protected boolean checkCollected(int id){
        Cursor cursor = mSQLiteManager.queryCollectQuestionById(id);
        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSQLiteManager.closeDB();
    }
}
