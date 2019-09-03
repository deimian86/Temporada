package com.deimian86.verdurasdetemporada.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
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
import com.deimian86.verdurasdetemporada.entities.frutas.Fruta;
import com.deimian86.verdurasdetemporada.entities.frutas.FrutaMes;
import com.deimian86.verdurasdetemporada.utils.AppDatabase;
import com.deimian86.verdurasdetemporada.utils.RequestCodes;
import com.deimian86.verdurasdetemporada.utils.bus.MessageEventFruta;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class FrutasFragment extends Fragment {

    private String tag = this.getClass().getName();
    private RecyclerView rv;
    private List<Fruta> frutas = new ArrayList<>();
    private FrutaAdapter adapter;
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
        adapter = new FrutaAdapter(getContext(), frutas);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        loadFrutas();
        return v;
    }

    @Subscribe
    public void getMessage(String message) {
        if(message.equals(RequestCodes.DB_CREATED)) loadFrutas();
    }

    @Subscribe
    public void getMessageObject(MessageEventFruta fruta) {
        ((MainActivity)getActivity()).showDetailBottomDialog(fruta.getFruta());
    }

    private void loadFrutas(){
        new LoadFrutasAsync().execute();
    }

    private void refreshAdapter(List<Fruta> verdurasTemp){
        frutas.clear();
        frutas.addAll(verdurasTemp);
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
                adapter.getFilter().filter(s);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private class LoadFrutasAsync extends AsyncTask<Void, Void, Void> {

        private List<Fruta> frutasTemp;
        private AppDatabase db;

        private LoadFrutasAsync() {
            this.db = AppDatabase.getDatabase(getActivity());
        }

        @Override
        protected Void doInBackground(final Void... params) {
            frutasTemp = db.frutaDao().getAll();
            for (final Fruta v: frutasTemp) {
                List<FrutaMes> frutasMesList = db.frutaMesDao().findMesesPorFruta(v.getId());
                for (FrutaMes vm: frutasMesList) {
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
            if(frutasTemp.size() > 0) {
                refreshAdapter(frutasTemp);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

}