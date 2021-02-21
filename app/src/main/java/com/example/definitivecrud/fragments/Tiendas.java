package com.example.definitivecrud.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.definitivecrud.Controlador.ControlTiendas;
import com.example.definitivecrud.Modelos.Tienda;
import com.example.definitivecrud.R;
import com.example.definitivecrud.nuevaTienda;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Tiendas extends Fragment {

    public Tiendas() {
        // Required empty public constructor
    }
    private RecyclerView recyclerView;
    private ControlTiendas controlTiendas;
    private List<Tienda> listaTiendas;
    Button addTienda;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_tiendas, container,false);
        recyclerView = view.findViewById(R.id.rvTiendas);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listaTiendas = new ArrayList<>();
        addTienda = view.findViewById(R.id.btn_addTienda);

        addTienda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             startActivity(new Intent(getContext(), nuevaTienda.class));
            }
        });
        leoLocales();
        return view;
    }


    public void eliminarLocal(String nomLocal){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Locales").child(nomLocal);
        reference.removeValue();
    }
    private void leoLocales(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Locales");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                listaTiendas.removeAll(listaTiendas);
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                   Tienda tienda = snapshot.getValue(Tienda.class);
                   listaTiendas.add(tienda);
                   controlTiendas = new ControlTiendas(getContext(),listaTiendas);
                   recyclerView.setAdapter(controlTiendas);
               }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}