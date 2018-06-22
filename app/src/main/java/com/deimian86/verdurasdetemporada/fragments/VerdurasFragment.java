package com.deimian86.verdurasdetemporada.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.activities.MainActivity;
import com.deimian86.verdurasdetemporada.adapters.VerduraAdapter;
import com.deimian86.verdurasdetemporada.entities.Verdura;
import com.deimian86.verdurasdetemporada.entities.VerduraMes;
import java.util.ArrayList;
import java.util.List;

public class VerdurasFragment extends Fragment {

    private String tag = this.getClass().getName();
    private View v;
    private RecyclerView rv;
    private List<Verdura> verduras = new ArrayList<>();
    private VerduraAdapter adapter;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_content, container, false);
        rv = v.findViewById(R.id.rv);
        progressBar = v.findViewById(R.id.progressBar);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        adapter = new VerduraAdapter(getContext(), verduras);
        rv.setAdapter(adapter);
        loadVerduras();
        return v;
    }

    private void loadVerduras(){
        LiveData<List<Verdura>> verdurasLiveList = ((MainActivity) getActivity()).db.verduraDao().getAll();
        verdurasLiveList.observe(getActivity(), new Observer<List<Verdura>>() {
            @Override
            public void onChanged(@Nullable List<Verdura> verdurasTemp) {
                loadMesesPorVerdura(verdurasTemp);
            }
        });
    }

    private void loadMesesPorVerdura(final List<Verdura> verdurasTemp){

        for (final Verdura v: verdurasTemp) {
            LiveData<List<VerduraMes>> verdurasMesLiveList = ((MainActivity) getActivity()).db.verduraMesDao().findMesesPorVerdura(v.getId());
            verdurasMesLiveList.observe(getActivity(), new Observer<List<VerduraMes>>() {
                @Override
                public void onChanged(@Nullable List<VerduraMes> verdurasMesesTemp) {
                    for (VerduraMes vm: verdurasMesesTemp) {
                        Log.d(tag, "v = " + v.getNombre() + " vm = "  + vm.getMesId());
                        v.getMeses().add(vm.getMesId());
                    }
                    refreshAdapter(verdurasTemp);
                }
            });
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void refreshAdapter(List<Verdura> verdurasTemp){
        verduras.clear();
        verduras.addAll(verdurasTemp);
        adapter.notifyDataSetChanged();
    }

}