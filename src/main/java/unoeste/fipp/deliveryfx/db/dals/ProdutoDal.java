package unoeste.fipp.deliveryfx.db.dals;

import unoeste.fipp.deliveryfx.db.entidade.Produto;
import unoeste.fipp.deliveryfx.db.entidade.Categoria;
import unoeste.fipp.deliveryfx.db.entidade.Marca;
import unoeste.fipp.deliveryfx.db.util.IDAL;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdutoDal implements IDAL<Produto> {
    @Override
    public boolean gravar(Produto entidade) {
        String sql = "INSERT INTO produto VALUES (default,'$1',$2,$3,'$4','$5',$6,$7,$8)";
        sql = sql.replace("$1", entidade.getNome());
        sql = sql.replace("$2", "" + entidade.getEstoque());
        sql = sql.replace("$3", String.format(Locale.US, "%.2f", entidade.getPreco()));
        sql = sql.replace("$6", "" + entidade.getCategoria().getId());
        sql = sql.replace("$8", "" + entidade.getMarca().getId()); // Pega o ID da marca corretamente
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean alterar(Produto entidade) {
        String sql = """
                UPDATE produto
                SET prod_nome='$1', prod_estoque=$2, prod_preco=$3, prod_data='$4', prod_unidade='$5', cat_id=$6, prod_volume=$7, marca_id=$8
                WHERE prod_id=$9""";
        sql = sql.replace("$1", entidade.getNome());
        sql = sql.replace("$2", "" + entidade.getEstoque());
        sql = sql.replace("$3", String.format(Locale.US, "%.2f", entidade.getPreco()));
        sql = sql.replace("$6", "" + entidade.getCategoria().getId());
        sql = sql.replace("$8", "" + entidade.getMarca().getId());
        sql = sql.replace("$9", "" + entidade.getId());
        System.out.println(sql);
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(Produto entidade) {
        return SingletonDB.getConexao().manipular("DELETE FROM produto WHERE prod_id=" + entidade.getId());
    }

    @Override
    public Produto get(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE prod_id=" + id;
        ResultSet rs = SingletonDB.getConexao().consultar(sql);
        try {
            if (rs.next()) {
                produto = new Produto(
                        rs.getString("prod_nome"),
                        rs.getInt("prod_estoque"),
                        rs.getDouble("prod_preco"),
                        new CategoriaDal().get(rs.getInt("cat_id")),
                        new MarcaDal().get(rs.getInt("marca_id"))
                );
                produto.setId(rs.getInt("prod_id"));
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
        if (!filtro.isEmpty())
            sql += " WHERE " + filtro;
        ResultSet rs = SingletonDB.getConexao().consultar(sql);
        try {
            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getString("prod_nome"),
                        rs.getInt("prod_estoque"),
                        rs.getDouble("prod_preco"),
                        new CategoriaDal().get(rs.getInt("cat_id")),
                        new MarcaDal().get(rs.getInt("marca_id"))
                );
                produto.setId(rs.getInt("prod_id"));
                produtoList.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtoList;
    }
}