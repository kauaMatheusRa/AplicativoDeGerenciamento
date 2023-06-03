package devandroid.kauamatheus.appgerenciamento.controller;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import devandroid.kauamatheus.appgerenciamento.model.Tarefa;
import devandroid.kauamatheus.appgerenciamento.view.MainActivity;

public class ControllerTarefa {

    SharedPreferences preferences;

    SharedPreferences.Editor listaVip;

    public static final String NOME_PREFERENCES = "pref_listavip";

    public ControllerTarefa(MainActivity mainActivity) {
        preferences = mainActivity.getSharedPreferences(NOME_PREFERENCES, 0);
        listaVip = preferences.edit();
    }

    @NonNull
    @Override
    public String toString() {
        Log.d("MVC_Controller", "Controller iniciado...");

        return super.toString();
    }

    public Tarefa salvar(Tarefa outraTarefa) {
        Log.d("MVC_Controller","Salvo"+outraTarefa.toString());

        listaVip.putString("titulo", outraTarefa.getTitulo());
        listaVip.putString("observacao", outraTarefa.getObservacao());
        listaVip.putString("dataVencimento", outraTarefa.getDatavencimento());
        listaVip.apply();
        return outraTarefa;
    }

    public Tarefa buscar(Tarefa outraTarefa){
        outraTarefa.setTitulo(preferences.getString("titulo", "NA"));
        outraTarefa.setObservacao(preferences.getString("observacao", "NA"));
        outraTarefa.setDatavencimento(preferences.getString("dataVencimento", "NA"));
        return outraTarefa;
    }

    public void editar(){

        listaVip.clear();
        listaVip.apply();
    }
}
