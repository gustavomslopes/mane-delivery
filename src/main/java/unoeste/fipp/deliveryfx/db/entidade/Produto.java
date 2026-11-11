package unoeste.fipp.deliveryfx.db.entidade;

import java.time.LocalDate;
public class Produto {
    private int id;
    private String nome;
    private int estoque;
    private double preco;;
    private Categoria categoria;
    private Marca marca;

    public Produto() {
        this.id = 0;
        this.nome = "";
        this.estoque = 0;
        this.preco = 0.0;
        this.categoria = null;
    }

    public Produto(int id, String nome, int estoque, double preco, Categoria categoria, Marca marca) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
        this.categoria = categoria;
        this.marca = marca;
    }

    public Produto(String nome, int estoque, double preco, Categoria categoria, Marca marca) {
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
        this.categoria = categoria;
        this.marca = marca;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }


    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }

    @Override
    public String toString() {
        return nome;
    }
}