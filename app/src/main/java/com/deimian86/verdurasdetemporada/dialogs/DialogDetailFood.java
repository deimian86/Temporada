package com.deimian86.verdurasdetemporada.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;

import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.entities.frutas.Fruta;
import com.deimian86.verdurasdetemporada.entities.mariscos.Marisco;
import com.deimian86.verdurasdetemporada.entities.pescados.Pescado;
import com.deimian86.verdurasdetemporada.entities.verduras.Verdura;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DialogDetailFood extends BottomSheetDialogFragment {

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);
        return view;
    }

}
