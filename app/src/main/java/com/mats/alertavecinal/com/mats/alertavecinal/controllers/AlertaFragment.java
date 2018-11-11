package com.mats.alertavecinal.com.mats.alertavecinal.controllers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mats.alertavecinal.R;

public class AlertaFragment extends Fragment {
    private String user;
    private TextView titulo;

    public static AlertaFragment newInstance(Bundle arguments){
        AlertaFragment af = new AlertaFragment();
        if (arguments != null) {
            af.setArguments(arguments);

        } else {
            System.out.println("No hay argumentos para recibir en AlertaFragment");
        }
        return af;
    }

    //El Fragment ha sido creado
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myInflatedView = inflater.inflate(R.layout.fragment_alerta,container,false) ;
        titulo = myInflatedView.findViewById(R.id.textViewTitulo);
        user = getArguments().getString("user");
        titulo.setText("Bienvenido: "+user);
        return myInflatedView;
    }
}
