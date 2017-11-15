package com.deimian86.verdurasdetemporada.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.Mes;

public class CustomYearView extends View {

    private View mView;
    private Context mContext;

    public CustomYearView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.view_months, null);

        TextView txt1 = mView.findViewById(R.id.txt_mes_01);
        txt1.setText(new Mes(1).getNombreCortoFormateado());
        txt1.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));

    }
}