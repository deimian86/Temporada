package com.deimian86.verdurasdetemporada.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.activities.MainActivity;
import com.deimian86.verdurasdetemporada.adapters.FrutaAdapter;
import com.deimian86.verdurasdetemporada.entities.Fruta;
import com.deimian86.verdurasdetemporada.entities.FrutaMes;
import java.util.ArrayList;
import java.util.List;

public class FrutasFragment extends Fragment {

    private String tag = this.getClass().getName();
    private View v;
    private RecyclerView rv;
    private List<Fruta> frutas = new ArrayList<>();
    private FrutaAdapter adapter;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_content, container, false);
        setHasOptionsMenu(true);
        rv = v.findViewById(R.id.rv);
        progressBar = v.findViewById(R.id.progressBar);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        adapter = new FrutaAdapter(getContext(), frutas);
        rv.setAdapter(adapter);
        loadFrutas();
        return v;
    }

    private void loadFrutas(){
        LiveData<List<Fruta>> verdurasLiveList = ((MainActivity) getActivity()).db.frutaDao().getAll();
        verdurasLiveList.observe(getActivity(), new Observer<List<Fruta>>() {
            @Override
            public void onChanged(@Nullable List<Fruta> frutasTemp) {
                loadMesesPorFruta(frutasTemp);
            }
        });

    }

    private void loadMesesPorFruta(final List<Fruta> frutasTemp){

        for (final Fruta v: frutasTemp) {
            LiveData<List<FrutaMes>> verdurasMesLiveList = ((MainActivity) getActivity()).db.frutaMesDao().findMesesPorFruta(v.getId());
            verdurasMesLiveList.observe(getActivity(), new Observer<List<FrutaMes>>() {
                @Override
                public void onChanged(@Nullable List<FrutaMes> verdurasMesesTemp) {
                    for (FrutaMes vm: verdurasMesesTemp) {
                        Log.d(tag, "v = " + v.getNombre() + " vm = "  + vm.getMesId());
                        v.getMeses().add(vm.getMesId());
                        if(vm.isMenorVenta()) {
                            v.getMesesMenos().add(vm.getMesId());
                        }
                    }
                    refreshAdapter(frutasTemp);
                }
            });
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void refreshAdapter(List<Fruta> verdurasTemp){
        frutas.clear();
        frutas.addAll(verdurasTemp);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(tag,"onQueryTextSubmit: " + query);
                adapter.getFilter().filter(query);
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(tag,"SearchOnQueryTextChanged: " + s);
                adapter.getFilter().filter(s);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


}