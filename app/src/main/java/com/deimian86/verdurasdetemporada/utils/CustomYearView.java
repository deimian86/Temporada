package com.deimian86.verdurasdetemporada.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.Mes;

import java.util.ArrayList;
import java.util.List;

public class CustomYearView extends FrameLayout {

    private Context mContext;
    private ArrayList<TextView> txtMeses = new ArrayList<>();

    public CustomYearView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    public CustomYearView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    private void init(){

        inflate(mContext, R.layout.view_months, this);

        TextView txtMes01 = findViewById(R.id.txt_mes_01);
        txtMes01.setText(new Mes(Mes.ENERO).getNombreCortoFormateado());

        TextView txtMes02 = findViewById(R.id.txt_mes_02);
        txtMes02.setText(new Mes(Mes.FEBRERO).getNombreCortoFormateado());

        TextView txtMes03 = findViewById(R.id.txt_mes_03);
        txtMes03.setText(new Mes(Mes.MARZO).getNombreCortoFormateado());

        TextView txtMes04 = findViewById(R.id.txt_mes_04);
        txtMes04.setText(new Mes(Mes.ABRIL).getNombreCortoFormateado());

        TextView txtMes05 = findViewById(R.id.txt_mes_05);
        txtMes05.setText(new Mes(Mes.MAYO).getNombreCortoFormateado());

        TextView txtMes06 = findViewById(R.id.txt_mes_06);
        txtMes06.setText(new Mes(Mes.JUNIO).getNombreCortoFormateado());

        TextView txtMes07 = findViewById(R.id.txt_mes_07);
        txtMes07.setText(new Mes(Mes.JULIO).getNombreCortoFormateado());

        TextView txtMes08 = findViewById(R.id.txt_mes_08);
        txtMes08.setText(new Mes(Mes.AGOSTO).getNombreCortoFormateado());

        TextView txtMes09 = findViewById(R.id.txt_mes_09);
        txtMes09.setText(new Mes(Mes.SEPTIEMBRE).getNombreCortoFormateado());

        TextView txtMes10 = findViewById(R.id.txt_mes_10);
        txtMes10.setText(new Mes(Mes.OCTUBRE).getNombreCortoFormateado());

        TextView txtMes11 = findViewById(R.id.txt_mes_11);
        txtMes11.setText(new Mes(Mes.NOVIEMBRE).getNombreCortoFormateado());

        TextView txtMes12 = findViewById(R.id.txt_mes_12);
        txtMes12.setText(new Mes(Mes.DICIEMBRE).getNombreCortoFormateado());

        txtMeses.add(txtMes01);
        txtMeses.add(txtMes02);
        txtMeses.add(txtMes03);
        txtMeses.add(txtMes04);
        txtMeses.add(txtMes05);
        txtMeses.add(txtMes06);
        txtMeses.add(txtMes07);
        txtMeses.add(txtMes08);
        txtMeses.add(txtMes09);
        txtMeses.add(txtMes10);
        txtMeses.add(txtMes11);
        txtMeses.add(txtMes12);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void marcarMeses(List<Integer> meses, List<Integer> mesesMenos){
        desmarcarMeses();
        for(Integer i : meses){
            txtMeses.get(i - 1).setTextColor(getResources().getColor(android.R.color.white));
            txtMeses.get(i - 1).setAlpha(1.0f);
            if(!mesesMenos.contains(i)) {
                txtMeses.get(i - 1).setBackgroundResource(R.drawable.layout_rounded_active_accent);
            } else {
                txtMeses.get(i - 1).setBackgroundResource(R.drawable.layout_rounded_active_accent_light);
            }
        }
    }

    private void desmarcarMeses(){
        for(int i = 0; i < 12; i++){
            txtMeses.get(i).setTextColor(getResources().getColor(R.color.gris_inactivo_texto));
            txtMeses.get(i).setBackgroundResource(R.drawable.layout_rounded_unactive);
            txtMeses.get(i).setAlpha(0.6f);
        }
    }

}