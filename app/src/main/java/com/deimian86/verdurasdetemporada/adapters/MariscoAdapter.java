package com.deimian86.verdurasdetemporada.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.mariscos.Marisco;
import com.deimian86.verdurasdetemporada.utils.CustomYearView;
import com.deimian86.verdurasdetemporada.utils.bus.MessageEventMarisco;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;

public class MariscoAdapter extends RecyclerView.Adapter<MariscoAdapter.MariscoViewHolder> implements Filterable {

    private String tag = this.getClass().getName();
    private List<Marisco> data;
    private List<Marisco> dataFiltered;
    private Context context;

    public MariscoAdapter(Context context, List<Marisco> data){
        this.context = context;
        this.data = data;
        this.dataFiltered = data;
    }

    public static class MariscoViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout cv;
        TextView lytNombre;
        ImageView lytFoto;
        CustomYearView mesesLayout;

        private MariscoViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            lytNombre = itemView.findViewById(R.id.lyt_detalle_nombre);
            lytFoto = itemView.findViewById(R.id.lyt_detalle_foto);
            mesesLayout = itemView.findViewById(R.id.lyt_meses);
        }
    }

    @Override
    public int getItemCount() {
        return dataFiltered.size();
    }

    @Override
    public MariscoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lyt_detalle, viewGroup, false);
        MariscoViewHolder vh = new MariscoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MariscoViewHolder holder, final int position) {

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
                EventBus.getDefault().post(new MessageEventMarisco(dataFiltered.get(position)));
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
                    List<Marisco> filteredList = new ArrayList<>();
                    for (Marisco row : data) {
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
                dataFiltered = (ArrayList<Marisco>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}