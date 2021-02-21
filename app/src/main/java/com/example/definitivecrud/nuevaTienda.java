package com.example.definitivecrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class nuevaTienda extends AppCompatActivity {
    //Declaramos los botones
    private Button guardaDatos;
    private Button cancelar;
    private EditText etNomLocal;
    private EditText etTelefono;
    private EditText etTipo;
    //Firebase
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtienda_activity);

        //Componentes necesarios para el registro
        guardaDatos = (Button) findViewById(R.id.successButton);
        cancelar = (Button) findViewById(R.id.cancelButton);
        etNomLocal = (EditText) findViewById(R.id.etNomLocal);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etTipo = (EditText) findViewById(R.id.etTipo);
        guardaDatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                registrarLocal();
            }
           });
        cancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(nuevaTienda.this, MainActivity.class);
                startActivity(goBack);
                finish();
            }
        });
        //Firebase auth
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


    }

    private void registrarLocal() {

        //Obtenemos los datos de las casillas de texto
        String nombre = etNomLocal.getText().toString();
        String telefono = etTelefono.getText().toString();
        String tipo = etTipo.getText().toString();
        //Comprobamos si han introducido email y contraseña
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Introduce el nombre de su local", Toast.LENGTH_SHORT).show();
        } else {
            if (telefono.isEmpty() && tipo.isEmpty()) {
                Toast.makeText(this, "Debe ingresar un telefono de contacto y el tipo de local que quiere registrar", Toast.LENGTH_SHORT).show();
            } else {
                //Mapeo
                myRef = FirebaseDatabase.getInstance().getReference("Locales").child(nombre);
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("nomLocal", nombre);
                hashMap.put("telefono", telefono);
                hashMap.put("tipo", tipo);
                myRef.setValue(hashMap);
                //Acceso al perfil si es exitoso
                Intent acceso = new Intent(nuevaTienda.this, MainActivity.class);
                startActivity(acceso);
                //Destruimos la actividad
                finish();
                };
            }
        }
}
