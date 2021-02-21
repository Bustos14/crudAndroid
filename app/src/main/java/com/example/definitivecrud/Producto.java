package com.example.definitivecrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.definitivecrud.Controlador.ControlProductos;
import com.example.definitivecrud.Modelos.Productos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Producto extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ControlProductos controlProductos;
    private List<Productos> productoList;
    Button addProducto;
    TextView local;
    String nomLocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos_activity);
        //Inflamos
        recyclerView = findViewById(R.id.rvProductos);
        nomLocal = getIntent().getStringExtra("nomLocal");
        local = findViewById(R.id.product_nomTienda);
        local.setText(nomLocal);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        productoList = new ArrayList<>();
        addProducto = findViewById(R.id.btn_addProductos);
        addProducto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Producto.this, nuevoProducto.class);
                i.putExtra("nomLocal", nomLocal);
                startActivity(i);
            }
        });
        leoProducto(nomLocal);
    }
    public void eliminarLocal(String nomLocal){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Locales").child(nomLocal);
        reference.removeValue();
    }
    public void eliminarProducto(String nomProducto){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Productos").child(nomLocal+nomProducto);
        Toast.makeText(this,nomProducto, Toast.LENGTH_SHORT).show();
        reference.removeValue();
    }
    private void leoProducto(String nomLocal){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Productos");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                productoList.removeAll(productoList);
                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    Productos productos = snapshot.getValue(Productos.class);
                    if(productos.getNomLocal().equals(nomLocal)){
                        productoList.add(productos);
                    }
                    controlProductos = new ControlProductos(productoList);
                    recyclerView.setAdapter(controlProductos);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}
}