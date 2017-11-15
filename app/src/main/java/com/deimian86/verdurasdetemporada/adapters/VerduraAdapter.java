package com.deimian86.verdurasdetemporada.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.Mes;
import com.deimian86.verdurasdetemporada.entities.Verdura;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
        LinearLayout mesesLayout;

        private VerduraViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            verduraNombre = itemView.findViewById(R.id.verdura_nombre);
            verduraFoto = itemView.findViewById(R.id.verdura_foto);
            mesesLayout = itemView.findViewById(R.id.lyt_meses);
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
    public void onBindViewHolder(VerduraViewHolder holder, final int position) {

        // NOMBRE //

        holder.verduraNombre.setText(data.get(position).getNombre());

        // FOTO //

        int resId = context.getResources().getIdentifier(data.get(position).getFoto(), "drawable", context.getPackageName());
        Picasso.with(context)
                .load(resId)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.img_00)
                .error(R.drawable.img_00)
                .into(holder.verduraFoto);

        // LISTADO DE MESES //

        int monthCount = 1;
        for (int i = 0; i < holder.mesesLayout.getChildCount(); i++) {
            View v = holder.mesesLayout.getChildAt(i);

            Log.d("DEBUG", "name = " + context.getResources().getResourceName(v.getId()));

            if (v instanceof TextView) {
                ((TextView)v).setText(new Mes(monthCount++).getNombreCortoFormateado());
                ((TextView)v).setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            }
        }

        // CLICK LISTENER //

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, data.get(position).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}