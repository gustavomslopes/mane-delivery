package unoeste.fipp.deliveryfx.db.entidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private LocalDate data;
    private String clienteNome;
    private String clienteTelefone;
    private double total;
    private String local;
    private String numero;
    private TipoPagamento tipoPagamento;
    private Status status;
    private List<Item> itens=new ArrayList<>();



    //inner record
    public record Item(Produto produto, int quantidade, double valor) {}

    public Pedido(int id, LocalDate data, String clienteNome, String clienteTelefone, double total, String local, String numero, TipoPagamento tipoPagamento, Status status ) {
        this.id = id;
        this.data = data;
        this.clienteNome = clienteNome;
        this.clienteTelefone = clienteTelefone;
        this.total = total;
        this.local = local;
        this.numero = numero;
        this.tipoPagamento = tipoPagamento;
        this.status = status;
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getClienteNome() { return clienteNome; }
    public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }
    public String getClienteTelefone() { return clienteTelefone; }
    public void setClienteTelefone(String clienteTelefone) { this.clienteTelefone = clienteTelefone; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public TipoPagamento getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(TipoPagamento tipoPagamento) { this.tipoPagamento = tipoPagamento; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public List<Item> getItens() { return itens; }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}