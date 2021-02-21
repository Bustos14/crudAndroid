package com.example.definitivecrud.Controlador;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.definitivecrud.Modelos.Tienda;
import com.example.definitivecrud.Producto;
import com.example.definitivecrud.R;
import com.example.definitivecrud.fragments.Tiendas;
import com.example.definitivecrud.nuevaTienda;

import java.util.List;

public class ControlTiendas extends RecyclerView.Adapter<ControlTiendas.ViewHolder> {

    private List<Tienda> lTienda;
    private Context context;
    Tiendas tiendas;
    //Constructor
    public ControlTiendas(Context context,List<Tienda> lTienda) {
        this.lTienda = lTienda;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item_tienda,parent,false);
       ViewHolder holder = new ViewHolder(v);
       tiendas = new Tiendas();
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tienda tienda = lTienda.get(position);

        holder.nomLocal.setText(tienda.getNomLocal());
        holder.tv_telefono.setText("Llámanos: "+tienda.getTelefono());
        holder.tv_tipo.setText(tienda.getTipo());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Opciones:");
                builder.setMessage("¿Qué quieres hacer?.");        // add the buttons
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Vas a Editar", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("Cancelar",null);
                builder.setNegativeButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Tiendas tiendas = new Tiendas();
                        tiendas.eliminarLocal(holder.nomLocal.getText().toString());
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Producto.class);
                i.putExtra("nomLocal", tienda.getNomLocal());
                context.startActivity(i);
            }
        });
        if(position%2==0){
            holder.itemView.setBackgroundColor(Color.parseColor("#FF03DAC5"));
        }else{
            holder.itemView.setBackgroundColor(Color.parseColor("#FF018786"));
        }
    }


    @Override
    public int getItemCount() {
        return lTienda.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nomLocal;
        public TextView tv_telefono;
        public TextView tv_tipo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomLocal= (TextView) itemView.findViewById(R.id.tvNombre);
            tv_telefono = (TextView) itemView.findViewById(R.id.tvtelfono);
            tv_tipo =(TextView) itemView.findViewById(R.id.tvtipo);
        }
    }
}


