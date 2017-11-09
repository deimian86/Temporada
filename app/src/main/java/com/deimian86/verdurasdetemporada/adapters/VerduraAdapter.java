package com.deimian86.verdurasdetemporada.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.Verdura;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VerduraAdapter extends RecyclerView.Adapter<VerduraAdapter.VerduraViewHolder>{

    private String tag = this.getClass().getName();
    private List<Verdura> data;
    private Context context;

    public VerduraAdapter(Context context, List<Verdura> data){
        this.context = context;
        this.data = data;
    }

    public static class VerduraViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView verduraNombre;
        ImageView verduraFoto;

        private VerduraViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            verduraNombre = itemView.findViewById(R.id.verdura_nombre);
            verduraFoto = itemView.findViewById(R.id.verdura_foto);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public VerduraViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_verdura, viewGroup, false);
        VerduraViewHolder vh = new VerduraViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VerduraViewHolder holder, final int i) {

        // NOMBRE //

        holder.verduraNombre.setText(data.get(i).getNombre());

        // FOTO //

        int resId = context.getResources().getIdentifier(data.get(i).getFoto(), "drawable", context.getPackageName());
        Picasso.with(context)
                .load(resId)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.img_00)
                .error(R.drawable.img_00)
                .into(holder.verduraFoto);

        // CLICK LISTENER //

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, data.get(i).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}