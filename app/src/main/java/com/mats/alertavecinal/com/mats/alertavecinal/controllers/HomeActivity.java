package com.mats.alertavecinal.com.mats.alertavecinal.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mats.alertavecinal.GrupoActivity;
import com.mats.alertavecinal.R;
import com.mats.alertavecinal.com.mats.alertavecinal.controllers.AlertaFragment;
import com.mats.alertavecinal.com.mats.alertavecinal.controllers.GrupoFragment;
import com.mats.alertavecinal.com.mats.alertavecinal.controllers.PerfilFragment;
import com.mats.alertavecinal.com.mats.alertavecinal.modelos.Grupo;
import com.mats.alertavecinal.com.mats.alertavecinal.modelos.Usuario;
import com.mats.alertavecinal.com.mats.alertavecinal.modelos.Vecino;
import com.mats.alertavecinal.com.mats.alertavecinal.singleton.SingletonRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    public static final String user = "";
    private Intent intent;
    private RequestQueue queue;

    private TextView mTextMessage;
    private TextView message;

    private Bundle argsAlerta = new Bundle();
    private Bundle argsGrupo = new Bundle();

    private JSONArray mJsonArray;
    private Vecino vecino;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_alertas:
                    mTextMessage.setText(R.string.title_alertas);
                    selectedFragment = AlertaFragment.newInstance(argsAlerta);
                    break;
                case R.id.navigation_grupos:
                    mTextMessage.setText(R.string.title_grupos);
//                    String actionName = "android.intent.action.GRUPO";
//                    intent = new Intent(actionName);
//                    intent.putExtra(GrupoActivity.userId,user);
//                    startActivity(intent);
                    selectedFragment = GrupoFragment.newInstance(argsGrupo);
                    break;
                case R.id.navigation_perfil:
                    mTextMessage.setText(R.string.title_perfil);
                    selectedFragment = new PerfilFragment();

                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        queue = SingletonRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();
        mTextMessage = (TextView) findViewById(R.id.message);
        //recibo lo q me pasa RegisterActivity
        String us = getIntent().getStringExtra(user);
        Usuario user = new Usuario(us,"dddd");
        mTextMessage.setText(us);
        vecino = new Vecino("Alex","Moret",32323,323,new Date(),"a@a.com", user);

        // le paso args a Alerta Fragment;
        argsAlerta.putString("user",us);
        //le paso args a Grupo Fragment;
        argsGrupo.putString("user",us);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, AlertaFragment.newInstance(argsAlerta)).commit();
    }


}
