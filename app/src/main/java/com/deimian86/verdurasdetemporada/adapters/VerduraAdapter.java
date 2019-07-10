package com.deimian86.verdurasdetemporada.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.verduras.Verdura;
import com.deimian86.verdurasdetemporada.utils.CustomYearView;

import java.util.ArrayList;
import java.util.List;

public class VerduraAdapter extends RecyclerView.Adapter<VerduraAdapter.VerduraViewHolder> implements Filterable {

    private String tag = this.getClass().getName();
    private List<Verdura> data;
    private List<Verdura> dataFiltered;
    private Context context;

    public VerduraAdapter(Context context, List<Verdura> data){
        this.context = context;
        this.data = data;
        this.dataFiltered = data;
    }

    public static class VerduraViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout cv;
        TextView lytNombre;
        ImageView lytFoto;
        CustomYearView mesesLayout;

        private VerduraViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            lytNombre = itemView.findViewById(R.id.lyt_detalle_nombre);
            lytFoto = itemView.findViewById(R.id.lyt_detalle_foto);
            mesesLayout = itemView.findViewById(R.id.lyt_meses);
        }
    }

    @Override
    public long getItemId(int position) {
        return dataFiltered.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return dataFiltered.size();
    }

    @Override
    public VerduraViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_detalle, viewGroup, false);
        VerduraViewHolder vh = new VerduraViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VerduraViewHolder holder, final int position) {

        // NOMBRE //

        holder.lytNombre.setText(dataFiltered.get(position).getNombre());

        // FOTO //

        int resId = context.getResources().getIdentifier(dataFiltered.get(position).getFondo(), "drawable", context.getPackageName());
        if(resId != 0) {

            Glide.with(context)
                    .load(resId)
                    .centerCrop()
                    .placeholder(android.R.color.white)
                    .into(holder.lytFoto);


        }
        // LISTADO DE MESES //

        holder.mesesLayout.marcarMeses(dataFiltered.get(position).getMeses(), dataFiltered.get(position).getMesesMenos());

        // CLICK LISTENER //

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, dataFiltered.get(position).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dataFiltered = data;
                } else {
                    List<Verdura> filteredList = new ArrayList<>();
                    for (Verdura row : data) {
                        if (row.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    dataFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataFiltered = (ArrayList<Verdura>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}