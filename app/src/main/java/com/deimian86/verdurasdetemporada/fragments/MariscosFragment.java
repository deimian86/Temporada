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
import com.deimian86.verdurasdetemporada.activities.MainActivity;
import com.deimian86.verdurasdetemporada.adapters.MariscoAdapter;
import com.deimian86.verdurasdetemporada.entities.mariscos.Marisco;
import com.deimian86.verdurasdetemporada.entities.mariscos.MariscoMes;
import com.deimian86.verdurasdetemporada.utils.AppDatabase;
import com.deimian86.verdurasdetemporada.utils.RequestCodes;
import com.deimian86.verdurasdetemporada.utils.bus.MessageEventFruta;
import com.deimian86.verdurasdetemporada.utils.bus.MessageEventMarisco;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MariscosFragment extends Fragment {

    private String tag = this.getClass().getName();
    private RecyclerView rv;
    private List<Marisco> verduras = new ArrayList<>();
    private MariscoAdapter adapter;
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
        adapter = new MariscoAdapter(getContext(), verduras);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        loadMariscos();
        return v;
    }

    @Subscribe
    public void getMessage(String message) {
        if(message.equals(RequestCodes.DB_CREATED)) loadMariscos();
    }

    @Subscribe
    public void getMessageObject(MessageEventMarisco marisco) {
        ((MainActivity)getActivity()).showDetailBottomDialog(marisco.getMarisco());
    }

    private void loadMariscos(){
        new LoadVerdurasAsync().execute();
    }

    private void refreshAdapter(List<Marisco> verdurasTemp){
        verduras.clear();
        verduras.addAll(verdurasTemp);
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

    private class LoadVerdurasAsync extends AsyncTask<Void, Void, Void> {

        private List<Marisco> verdurasTemp;
        private AppDatabase db;

        private LoadVerdurasAsync() {
            this.db = AppDatabase.getDatabase(getActivity());
        }

        @Override
        protected Void doInBackground(final Void... params) {
            verdurasTemp = db.mariscoDao().getAll();
            for (final Marisco v: verdurasTemp) {
                List<MariscoMes> verdurasMesList = db.mariscoMesDao().findMesesPorMarisco(v.getId());
                for (MariscoMes vm: verdurasMesList) {
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
            if(verdurasTemp.size() > 0) {
                refreshAdapter(verdurasTemp);
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