package com.mats.alertavecinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GrupoActivity extends AppCompatActivity {
    public static final String userId = "aas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);
        String userIdentify = getIntent().getStringExtra(userId);
    }
}
