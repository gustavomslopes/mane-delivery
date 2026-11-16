package unoeste.fipp.deliveryfx.db.util;

public class Struct implements Comparable<Struct> {
    int quantidade;
    String nome;

    public Struct(int quantidade, String nome) {
        this.quantidade = quantidade;
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int compareTo(Struct o) {
        return 0;
    }
}
