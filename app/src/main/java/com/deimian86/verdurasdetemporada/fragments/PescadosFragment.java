package com.deimian86.verdurasdetemporada.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.deimian86.verdurasdetemporada.R;
import com.deimian86.verdurasdetemporada.adapters.PescadoAdapter;
import com.deimian86.verdurasdetemporada.entities.pescados.Pescado;
import com.deimian86.verdurasdetemporada.entities.pescados.PescadoMes;
import com.deimian86.verdurasdetemporada.utils.AppDatabase;
import com.deimian86.verdurasdetemporada.utils.BusProvider;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.List;

public class PescadosFragment extends Fragment {

    private String tag = this.getClass().getName();
    private RecyclerView rv;
    private List<Pescado> pescados = new ArrayList<>();
    private PescadoAdapter adapter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        rv = v.findViewById(R.id.rv);
        rv.setVisibility(View.INVISIBLE);
        progressBar = v.findViewById(R.id.progressBar);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        adapter = new PescadoAdapter(getContext(), pescados);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        loadPescados();
        return v;
    }

    @Subscribe
    public void dbReady(String created) {
        loadPescados();
    }

    private void loadPescados(){
        new LoadPescadosAsync().execute();
    }

    private void refreshAdapter(List<Pescado> pescadosTemp){
        pescados.clear();
        pescados.addAll(pescadosTemp);
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.INVISIBLE);
        rv.setVisibility(View.VISIBLE);
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

    private class LoadPescadosAsync extends AsyncTask<Void, Void, Void> {

        private List<Pescado> pescadosTemp;
        private AppDatabase db;


        private LoadPescadosAsync() {
            this.db = AppDatabase.getDatabase(getActivity());
        }

        @Override
        protected Void doInBackground(final Void... params) {
            pescadosTemp = db.pescadoDao().getAll();
            for (final Pescado v: pescadosTemp) {
                List<PescadoMes> verdurasMesList = db.pescadoMesDao().findMesesPorPescado(v.getId());
                for (PescadoMes vm: verdurasMesList) {
                    Log.d(tag, "v = " + v.getNombre() + " vm = "  + vm.getMesId());
                    v.getMeses().add(vm.getMesId());
                    if(vm.isMenorVenta()) {
                        v.getMesesMenos().add(vm.getMesId());
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(pescadosTemp.size() > 0) {
                refreshAdapter(pescadosTemp);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

}