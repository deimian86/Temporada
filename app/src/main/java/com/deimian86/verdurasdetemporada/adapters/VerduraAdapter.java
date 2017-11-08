package com.deimian86.verdurasdetemporada.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.Verdura;

import java.util.List;

public class VerduraAdapter extends RecyclerView.Adapter<VerduraAdapter.VerduraViewHolder>{

    private List<Verdura> data;

    public VerduraAdapter(List<Verdura> data){
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
    public void onBindViewHolder(VerduraViewHolder holder, int i) {
        holder.verduraNombre.setText(data.get(i).getNombre());
        // holder.verduraFoto.setImageResource(data.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}