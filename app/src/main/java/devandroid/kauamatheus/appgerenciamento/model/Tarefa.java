package devandroid.kauamatheus.appgerenciamento.model;

public class Tarefa {
    private String titulo;
    private String observacao;
    private String datavencimento;
    private String categorias;

    public Tarefa() {

    }

    public Tarefa(String categorias) {
        this.categorias = categorias;
    }

    public Tarefa(String titulo, String observacao, String datavencimento, String categorias) {
        this.titulo = titulo;
        this.observacao = observacao;
        this.datavencimento = datavencimento;
        this.categorias = categorias;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDatavencimento() {
        return datavencimento;
    }

    public void setDatavencimento(String datavencimento) {
        this.datavencimento = datavencimento;
    }

    public String getCategorias() {
        return categorias;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "titulo='" + titulo + '\'' +
                ", observacao='" + observacao + '\'' +
                ", datavencimento='" + datavencimento + '\'' +
                ", categorias='" + categorias + '\'' +
                '}';
    }
}
