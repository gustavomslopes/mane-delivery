package unoeste.fipp.deliveryfx.db.dals;

import unoeste.fipp.deliveryfx.db.entidade.Itens;
import unoeste.fipp.deliveryfx.db.util.IDAL;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItensDal implements IDAL<Itens> {

    @Override
    public boolean gravar(Itens entidade) {
        // Ajuste os nomes das colunas (ex: ped_id, prod_id, item_quantidade) conforme a sua tabela no banco
        String sql = "INSERT INTO itens VALUES (default, $1, $2, $3)";
        sql = sql.replace("$1", entidade.getPedido() != null ? "" + entidade.getPedido().getId() : "0");
        sql = sql.replace("$2", entidade.getProduto() != null ? "" + entidade.getProduto().getId() : "0");
        sql = sql.replace("$3", "" + entidade.getQuantidade());

        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean alterar(Itens entidade) {
        String sql = """
                UPDATE itens
                SET ped_id = $1, prod_id = $2, item_quantidade = $3
                WHERE item_id = $4""";

        sql = sql.replace("$1", entidade.getPedido() != null ? "" + entidade.getPedido().getId() : "0");
        sql = sql.replace("$2", entidade.getProduto() != null ? "" + entidade.getProduto().getId() : "0");
        sql = sql.replace("$3", "" + entidade.getQuantidade());
        sql = sql.replace("$4", "" + entidade.getId());

        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(Itens entidade) {
        return SingletonDB.getConexao().manipular("DELETE FROM itens WHERE item_id = " + entidade.getId());
    }

    @Override
    public Itens get(int id) {
        Itens item = null;
        String sql = "SELECT * FROM itens WHERE item_id = " + id;

        try (ResultSet rs = SingletonDB.getConexao().consultar(sql)) {
            if (rs.next()) {
                item = new Itens(
                        rs.getInt("item_id"),
                        new PedidoDal().get(rs.getInt("ped_id")),    // Busca o Pedido associado
                        new ProdutoDal().get(rs.getInt("prod_id")),  // Busca o Produto associado
                        rs.getInt("item_quantidade")
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar item por ID: " + e.getMessage());
        }
        return item;
    }

    @Override
    public List<Itens> get(String filtro) {
        List<Itens> lista = new ArrayList<>();
        String sql = "SELECT * FROM itens";
        if (filtro != null && !filtro.isEmpty())
            sql += " WHERE " + filtro;

        try (ResultSet rs = SingletonDB.getConexao().consultar(sql)) {
            while (rs.next()) {
                Itens item = new Itens(
                        rs.getInt("item_id"),
                        new PedidoDal().get(rs.getInt("ped_id")),
                        new ProdutoDal().get(rs.getInt("prod_id")),
                        rs.getInt("item_quantidade")
                );
                lista.add(item);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar itens: " + e.getMessage());
        }
        return lista;
    }
}