package devandroid.kauamatheus.appgerenciamento.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import devandroid.kauamatheus.appgerenciamento.R;
import devandroid.kauamatheus.appgerenciamento.controller.ControllerTarefa;
import devandroid.kauamatheus.appgerenciamento.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    ControllerTarefa controller;

    Tarefa outraTarefa;

    EditText editTitulo;
    EditText editObservacao;
    EditText editDatavencimento;

    Button btnbuton_excluir;
    Button btnbuton_editar;
    Button btnbuton_Salvar;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        controller = new ControllerTarefa(MainActivity.this);
        controller.toString();

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
                Toast.makeText(MainActivity.this, "Dados salvos" + outraTarefa.toString(), Toast.LENGTH_LONG).show();

                outraTarefa.setTitulo(editTitulo.getText().toString());
                outraTarefa.setObservacao(editObservacao.getText().toString());
                outraTarefa.setDatavencimento(editDatavencimento.getText().toString());
                controller.salvar(outraTarefa);

            }
        });

        btnbuton_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTitulo.setText("");
                editObservacao.setText("");
                editDatavencimento.setText("");

                controller.editar();
            }
        });

        Log.i("ProgramacaoPOO", outraTarefa.toString());

    }
}