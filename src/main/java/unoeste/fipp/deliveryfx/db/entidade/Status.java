package unoeste.fipp.deliveryfx.db.entidade;

// Status do pedido (id, descrição, obs e sucesso)
public class Status {
    private int id;
    private String descricao;
    private String observacao;
    private boolean sucesso;

    public Status() {
        this.id = 0;
        this.descricao = "";
        this.observacao = "";
        this.sucesso = false;
    }

    public Status(int id, String descricao, String observacao, boolean sucesso) {
        this.id = id;
        this.descricao = descricao;
        this.observacao = observacao;
        this.sucesso = sucesso;
    }

    public Status(String descricao, String observacao, boolean sucesso) {
        this.descricao = descricao;
        this.observacao = observacao;
        this.sucesso = sucesso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }
}