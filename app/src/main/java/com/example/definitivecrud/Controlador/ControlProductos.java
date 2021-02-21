package com.example.definitivecrud.Controlador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.definitivecrud.Modelos.Productos;
import com.example.definitivecrud.Modelos.Tienda;
import com.example.definitivecrud.Producto;
import com.example.definitivecrud.R;
import com.example.definitivecrud.fragments.Tiendas;

import java.util.List;

public class ControlProductos  extends RecyclerView.Adapter<ControlProductos.ViewHolder> {
    private List<Productos> lProductos;
    private Context context;
    //Constructor
    public ControlProductos( List<Productos> lProductos) {
        this.lProductos = lProductos;

    }

    @NonNull
    @Override
    public ControlProductos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        context = parent.getContext();
        ControlProductos.ViewHolder holder = new ControlProductos.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Productos productos = lProductos.get(position);
        holder.nomProducto.setText(productos.getNomProducto());
        holder.descripcionProducto.setText(productos.getDescripcion());
        holder.precioProducto.setText("Precio: " + productos.getPrecio() + "€");
        holder.cantidadProducto.setText("Stock: " + productos.getStock() + " Ud");

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
            //Intent intent = new Int
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FF03DAC5"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FF018786"));
        }

    }



    @Override
    public int getItemCount() {
        return lProductos.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nomProducto;
        public TextView cantidadProducto;
        public TextView precioProducto;
        public TextView descripcionProducto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomProducto= (TextView) itemView.findViewById(R.id.tvNombreProducto);
            cantidadProducto = (TextView) itemView.findViewById(R.id.tvStock);
            precioProducto =(TextView) itemView.findViewById(R.id.tvPrecio);
            descripcionProducto = (TextView) itemView.findViewById(R.id.tvDescripcion);
        }
    }
}
