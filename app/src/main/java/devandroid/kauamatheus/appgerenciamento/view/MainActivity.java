package devandroid.kauamatheus.appgerenciamento.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import devandroid.kauamatheus.appgerenciamento.R;
import devandroid.kauamatheus.appgerenciamento.controller.CategoriaController;
import devandroid.kauamatheus.appgerenciamento.controller.ControllerTarefa;
import devandroid.kauamatheus.appgerenciamento.database.Lista_DB;
import devandroid.kauamatheus.appgerenciamento.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    ControllerTarefa controller;
    CategoriaController controllerCategoria;

    Tarefa outraTarefa;


    List<String> nomeCategorias;
    EditText editTitulo;
    EditText editObservacao;
    EditText editDatavencimento;

    Button btnbuton_excluir;
    Button btnbuton_editar;
    Button btnbuton_Salvar;
    Spinner spinner;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        controller = new ControllerTarefa(MainActivity.this);
        controller.toString();

        CategoriaController categoriaController = new CategoriaController();

        categoriaController = new CategoriaController();

        nomeCategorias = controller.dadosSpinner();
        controller.getListaTarefasp();

        outraTarefa = new Tarefa();
        controller.buscar(outraTarefa);

        editTitulo = findViewById(R.id.editTextTitulo);
        editObservacao = findViewById(R.id.editTextObservacao);
        editDatavencimento = findViewById(R.id.editTextDate);

        editTitulo.setText(outraTarefa.getTitulo());
        editObservacao.setText(outraTarefa.getObservacao());
        editDatavencimento.setText(outraTarefa.getDatavencimento());

        btnbuton_editar = findViewById(R.id.button_limpar);
        btnbuton_Salvar = findViewById(R.id.button_salvar);
        btnbuton_excluir = findViewById(R.id.button_excluir);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, controller.dadosSpinner());

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        btnbuton_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Finalizado", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnbuton_Salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Dados salvos", Toast.LENGTH_LONG).show();

                outraTarefa.setTitulo(editTitulo.getText().toString());
                outraTarefa.setObservacao(editObservacao.getText().toString());
                outraTarefa.setDatavencimento(editDatavencimento.getText().toString());
                controller.salvar(outraTarefa);

                Tarefa tarefa = new Tarefa();
                tarefa.setTitulo(editTitulo.getText().toString());
                tarefa.setObservacao(editObservacao.getText().toString());
                tarefa.setDatavencimento(editDatavencimento.getText().toString());

                controller.salvarDb(tarefa);

                editTitulo.setText("");
                editObservacao.setText("");
                editDatavencimento.setText("");

            }
        });

        btnbuton_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparBancoDeDados();
                controller.editar();
            }
        });
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, controller.getListaTarefasp()));
        Log.i("ProgramacaoPOO", outraTarefa.toString());

    }
    private void limparBancoDeDados() {
        Lista_DB listaDb = new Lista_DB(MainActivity.this);
        listaDb.limparTabela("Lista"); // Chama o método limparTabela() da classe Lista_DB para apagar todos os registros da tabela "Lista"
        listaDb.close();
    }
}