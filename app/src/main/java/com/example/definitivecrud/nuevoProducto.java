package com.example.definitivecrud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class nuevoProducto extends AppCompatActivity {
    //Declaramos los botones
    private Button guardaDatos;
    private Button cancelar;
    private EditText etNomProducto;
    private EditText etPrecio;
    private EditText description;
    private EditText etStock;
    //Firebase
    FirebaseDatabase database;
    DatabaseReference myRef;
    String nomLocal;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newproducto);

        nomLocal = getIntent().getStringExtra("nomLocal");
        //Componentes necesarios para el registro
        guardaDatos = (Button) findViewById(R.id.successButton);
        cancelar = (Button) findViewById(R.id.cancelButton);
        //Variables texto
        etNomProducto = (EditText) findViewById(R.id.etNomProducto);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etStock = (EditText) findViewById(R.id.etCantidad);
        description = (EditText) findViewById(R.id.etDescripcion);
        guardaDatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(nuevoProducto.this, Producto.class);
                startActivity(goBack);
                finish();
            }
        });
        //Firebase auth
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

    }

    private void registrarUsuario() {

        //Obtenemos los datos de las casillas de texto
        String nomProducto = etNomProducto.getText().toString();
        String Precio = etPrecio.getText().toString();
        String Description = description.getText().toString();
        String Stock = etStock.getText().toString();
        //Comprobamos si han introducido email y contraseña
        if (nomProducto.isEmpty()) {
            Toast.makeText(this, "Introduzca el nombre del producto a registrar", Toast.LENGTH_SHORT).show();
        } else {
            if (Precio.isEmpty() && Description.isEmpty()) {
                Toast.makeText(this, "Debe ingresar precio y descripcion", Toast.LENGTH_SHORT).show();
            } else if (Stock.isEmpty()) {
                Stock = "0";
            }else{
                //Mapeo
                myRef = FirebaseDatabase.getInstance().getReference("Productos").child(nomLocal + nomProducto);
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("nomLocal", nomLocal);
                hashMap.put("nomProducto", nomProducto);
                hashMap.put("descripcion", Description);
                hashMap.put("precio", Precio);
                hashMap.put("stock", Stock);
                myRef.setValue(hashMap);
                //Acceso al perfil si es exitoso
                Intent acceso = new Intent(nuevoProducto.this, Producto.class);
                acceso.putExtra("nomLocal", nomLocal);
                startActivity(acceso);
                //Destruimos la actividad
                finish();
            };
        }
    }
}
