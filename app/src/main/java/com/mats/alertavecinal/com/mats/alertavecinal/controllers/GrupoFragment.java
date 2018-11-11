package com.mats.alertavecinal.com.mats.alertavecinal.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mats.alertavecinal.R;
import com.mats.alertavecinal.com.mats.alertavecinal.modelos.Grupo;
import com.mats.alertavecinal.com.mats.alertavecinal.modelos.Usuario;
import com.mats.alertavecinal.com.mats.alertavecinal.modelos.Vecino;
import com.mats.alertavecinal.com.mats.alertavecinal.singleton.SingletonRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class GrupoFragment extends Fragment {
    private View myInflatedView;
    private TextView tView;
    private ListView lViewGrupos;
    private JSONArray mJsonArray;
    private Vecino vecino;
    private ArrayList<Grupo> grupos = new ArrayList<>();


    public static GrupoFragment newInstance(Bundle arguments){
        GrupoFragment gf = new GrupoFragment();
        if (arguments != null) {
            gf.setArguments(arguments);

        } else {
            System.out.println("No hay argumentos para recibir en GrupoFragment");
        }
        return gf;
    }

    //El Fragment ha sido creado
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("on create");
        String us = getArguments().getString("user");
        Usuario user = new Usuario(us,"dddd");
        this.vecino = new Vecino("Alex","Moret",32323,323,new Date(),"a@a.com", user);
        obtenerGrupos();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myInflatedView = inflater.inflate(R.layout.fragment_grupo,container,false);
        System.out.println("on create view");
        lViewGrupos = myInflatedView.findViewById(R.id.lViewGrupo);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,vecino.getGrupos());
        System.out.println("Los grupos son:      "+grupos);
        lViewGrupos.setAdapter(adapter);
        return myInflatedView;
    }
    private void obtenerGrupos(){
        String url = "http://192.168.100.2:1337/api/grupo";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject r = response;
                            mJsonArray = response.getJSONArray("data");
                            for(int i=0; i<mJsonArray.length(); i++){
                                JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                                Grupo grupo = new Grupo(mJsonObject.getString("id"),
                                        mJsonObject.getString("nombre"),
                                        mJsonObject.getString("ubicacion"));
                                grupos.add(grupo);
                                System.out.println("El elemento grupo es:"+grupo);
                                System.out.println("El grupo es:"+grupos);
                            }
                        } catch (JSONException e) {
                            System.out.println("ERRRROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOORRR");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("aiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" +error);

            }
        });
        SingletonRequestQueue.getInstance(getContext()).addToRequestQueue(request);
    }
}
