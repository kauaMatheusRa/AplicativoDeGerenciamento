package devandroid.kauamatheus.appgerenciamento.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import devandroid.kauamatheus.appgerenciamento.database.Lista_DB;
import devandroid.kauamatheus.appgerenciamento.model.Tarefa;
import devandroid.kauamatheus.appgerenciamento.view.MainActivity;

public class ControllerTarefa extends Lista_DB {

    SharedPreferences preferences;

    SharedPreferences.Editor listaVip;

    public static final String NOME_PREFERENCES = "pref_listavip";

    public ControllerTarefa(MainActivity mainActivity) {
        super(mainActivity);
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

    public void salvarDb(Tarefa lista){
        Log.d("MVC_Controller", "Salvo");

        ContentValues dados = new ContentValues();
        dados.put("titulo", lista.getTitulo());
        dados.put("conteudo",lista.getObservacao());
        dados.put("data",lista.getDatavencimento());

        salvarDados("Lista",dados);
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

    private List listaTarefasp;

    public List getListaTarefasp() {
        listaTarefasp = new ArrayList<Tarefa>();

        listaTarefasp.add(new Tarefa("Escola"));
        listaTarefasp.add(new Tarefa("Trabalho"));
        listaTarefasp.add(new Tarefa("Curso"));
        listaTarefasp.add(new Tarefa("Confraternização"));

        return listaTarefasp;
    }

    public ArrayList<String> dadosSpinner(){
        ArrayList<String> dados = new ArrayList<>();

        for (int i = 0; i < getListaTarefasp().size();i++){
            Tarefa objeto = (Tarefa) getListaTarefasp().get(i);

            dados.add(objeto.getCategorias());
        }
        return dados;
    }

}
