package unoeste.fipp.deliveryfx.db.dals;

import unoeste.fipp.deliveryfx.db.entidade.Produto;
import unoeste.fipp.deliveryfx.db.util.IDAL;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdutoDal implements IDAL<Produto> {
    @Override
    public boolean gravar(Produto entidade) {
        String sql = "INSERT INTO produto (pro_nome, pro_estoque, pro_preco, cat_id, mar_id) VALUES ('$1', $2, $3, $4, $5)";
        sql = sql.replace("$1", entidade.getNome());
        sql = sql.replace("$2", "" + entidade.getEstoque());
        sql = sql.replace("$3", String.format(Locale.US, "%.2f", entidade.getPreco()));
        sql = sql.replace("$4", "" + entidade.getCategoria().getId());
        sql = sql.replace("$5", "" + entidade.getMarca().getId());
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean alterar(Produto entidade) {
        String sql = """
                UPDATE produto
                SET pro_nome='$1', pro_estoque=$2, pro_preco=$3, cat_id=$4, mar_id=$5
                WHERE pro_id=$6""";
        sql = sql.replace("$1", entidade.getNome());
        sql = sql.replace("$2", "" + entidade.getEstoque());
        sql = sql.replace("$3", String.format(Locale.US, "%.2f", entidade.getPreco()));
        sql = sql.replace("$4", "" + entidade.getCategoria().getId());
        sql = sql.replace("$5", "" + entidade.getMarca().getId());
        sql = sql.replace("$6", "" + entidade.getId());
        System.out.println(sql);
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(Produto entidade) {
        return SingletonDB.getConexao().manipular("DELETE FROM produto WHERE pro_id=" + entidade.getId());
    }

    @Override
    public Produto get(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE pro_id=" + id;
        ResultSet rs = SingletonDB.getConexao().consultar(sql);
        try {
            if (rs.next()) {
                produto = new Produto(
                        rs.getString("pro_nome"),
                        rs.getInt("pro_estoque"),
                        rs.getDouble("pro_preco"),
                        new CategoriaDal().get(rs.getInt("cat_id")),
                        new MarcaDal().get(rs.getInt("mar_id"))
                );
                produto.setId(rs.getInt("pro_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produto;
    }

    @Override
    public List<Produto> get(String filtro) {
        List<Produto> produtoList = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        if (filtro != null && !filtro.isEmpty())
            sql += " WHERE " + filtro;
        ResultSet rs = SingletonDB.getConexao().consultar(sql);
        try {
            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getString("pro_nome"),
                        rs.getInt("pro_estoque"),
                        rs.getDouble("pro_preco"),
                        new CategoriaDal().get(rs.getInt("cat_id")),
                        new MarcaDal().get(rs.getInt("mar_id"))
                );
                produto.setId(rs.getInt("pro_id"));
                produtoList.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtoList;
    }
}