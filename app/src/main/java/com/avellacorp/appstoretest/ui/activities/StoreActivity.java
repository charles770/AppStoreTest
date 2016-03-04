package com.avellacorp.appstoretest.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avellacorp.appstoretest.R;

public class StoreActivity extends MasterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
    }

    public static Intent getLaunchIntent(Context activityContext, String tvShowId) {

        return new Intent();

    }
}
