package unoeste.fipp.deliveryfx.db.entidade;

// Itens do pedido (id, pedido (fk), produto (fk), quantidade)
public class Itens {
    private int id;
    private Pedido pedido;
    private Produto produto;
    private int quantidade;

    public Itens() {
        this.id = 0;
        this.pedido = null;
        this.produto = null;
        this.quantidade = 0;
    }

    public Itens(int id, Pedido pedido, Produto produto, int quantidade) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Itens(Pedido pedido, Produto produto, int quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getNomeProduto() {
        return produto != null ? produto.getNome() : "";
    }

    public double getPreco() {
        return produto != null ? produto.getPreco() : 0;
    }

    public double getTotal() {
        return getPreco() * quantidade;
    }
}