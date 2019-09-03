package com.deimian86.verdurasdetemporada.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.frutas.Fruta;
import com.deimian86.verdurasdetemporada.entities.mariscos.Marisco;
import com.deimian86.verdurasdetemporada.entities.pescados.Pescado;
import com.deimian86.verdurasdetemporada.entities.verduras.Verdura;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DialogDetailFood extends BottomSheetDialogFragment {

    private String titulo = "";
    private String descripcion = "";

    public static DialogDetailFood newInstance(Verdura verdura) {
        DialogDetailFood f = new DialogDetailFood();
        Bundle args = new Bundle();
        args.putSerializable("verdura", verdura);
        f.setArguments(args);
        return f;
    }

    public static DialogDetailFood newInstance(Pescado pescado) {
        DialogDetailFood f = new DialogDetailFood();
        Bundle args = new Bundle();
        args.putSerializable("pescado", pescado);
        f.setArguments(args);
        return f;
    }

    public static DialogDetailFood newInstance(Marisco marisco) {
        DialogDetailFood f = new DialogDetailFood();
        Bundle args = new Bundle();
        args.putSerializable("marisco", marisco);
        f.setArguments(args);
        return f;
    }

    public static DialogDetailFood newInstance(Fruta fruta) {
        DialogDetailFood f = new DialogDetailFood();
        Bundle args = new Bundle();
        args.putSerializable("fruta", fruta);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().getSerializable("verdura") != null) {
            Verdura food = (Verdura) getArguments().getSerializable("verdura");
            titulo = food.getNombre();
            descripcion = food.getDescripcion();
        } else if(getArguments().getSerializable("pescado") != null) {
            Pescado food = (Pescado) getArguments().getSerializable("pescado");
            titulo = food.getNombre();
            descripcion = food.getDescripcion();
        } else if(getArguments().getSerializable("marisco") != null) {
            Marisco food = (Marisco) getArguments().getSerializable("marisco");
            titulo = food.getNombre();
            descripcion = food.getDescripcion();
        } else if(getArguments().getSerializable("fruta") != null) {
            Fruta food = (Fruta) getArguments().getSerializable("fruta");
            titulo = food.getNombre();
            descripcion = food.getDescripcion();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);

        TextView txtTitulo = view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(titulo);

        TextView txtDescripcion = view.findViewById(R.id.txtDescripcion);
        txtDescripcion.setText(descripcion);

        return view;
    }

}
