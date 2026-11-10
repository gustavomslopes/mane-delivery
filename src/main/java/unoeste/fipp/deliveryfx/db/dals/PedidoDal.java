package unoeste.fipp.deliveryfx.db.dals;



import unoeste.fipp.deliveryfx.db.entidade.Pedido;
import unoeste.fipp.deliveryfx.db.entidade.Produto;
import unoeste.fipp.deliveryfx.db.entidade.TipoPagamento;
import unoeste.fipp.deliveryfx.db.entidade.Status;
import unoeste.fipp.deliveryfx.db.util.IDAL;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PedidoDal implements IDAL<Pedido> {

    @Override
    public boolean gravar(Pedido entidade) {
//        INSERT INTO pedido(
//                 ped_data, ped_clinome, ped_clifone, ped_total, tpg_id, ped_local, ped_numero_res, st_id)
//        VALUES (?, ?, ?, ?, ?, ?, ?, ?);

        String sql = "INSERT INTO pedido VALUES (default, '$1', '$2', '$3', $4, $5, '$6', '$7', $8)";
        sql = sql.replace("$1", entidade.getData().toString());
        sql = sql.replace("$2", entidade.getClienteNome());
        sql = sql.replace("$3", entidade.getClienteTelefone());
        sql = sql.replace("$4", String.valueOf(entidade.getTotal()));
        sql = sql.replace("$5", (entidade.getTipoPagamento() != null) ? String.valueOf(entidade.getTipoPagamento().getId()) : "NULL");
        sql = sql.replace("$6", entidade.getLocal());
        sql = sql.replace("$7", entidade.getNumero());
        sql = sql.replace("$8", entidade.getStatus() != null ? String.valueOf(entidade.getStatus().getId()) : "NULL");

        int id_ped= SingletonDB.getConexao().inserir(sql);

        // Insere os itens do pedido
        for (Pedido.Item item : entidade.getItens()) {
            String itemSql = String.format(Locale.US,"INSERT INTO item (pro_id, ped_id, it_quant, it_valor) VALUES (%d, %d, %d, %.2f)",
                    item.produto().getId(), id_ped, item.quantidade(), item.valor());
            SingletonDB.getConexao().manipular(itemSql);
        }

        return (id_ped!=-1)?true:false;
    }

    @Override
    public boolean alterar(Pedido entidade) {
//        String sql = "UPDATE pedido SET ped_data='$1', ped_clinome='$2', ped_clifone='$3', ped_total=$4, tpg_id=$5, st_id=$6, ped_local='' WHERE ped_id=$7";
        String sql = "UPDATE pedido SET ped_data='$1', ped_clinome='$2', ped_clifone='$3', ped_total=$4, tpg_id=$5, ped_local='$6', ped_numero_res='$7', st_id=$8 WHERE ped_id=$9";

        sql = sql.replace("$1", entidade.getData().toString());
        sql = sql.replace("$2", entidade.getClienteNome());
        sql = sql.replace("$3", entidade.getClienteTelefone());
        sql = sql.replace("$4", String.valueOf(entidade.getTotal()));
        sql = sql.replace("$5", (entidade.getTipoPagamento() != null) ? String.valueOf(entidade.getTipoPagamento().getId()) : "NULL");
        sql = sql.replace("$6", entidade.getLocal());
        sql = sql.replace("$7", entidade.getNumero());
        sql = sql.replace("$8", entidade.getStatus() != null ? String.valueOf(entidade.getStatus().getId()) : "NULL");
        sql = sql.replace("$9", String.valueOf(entidade.getId()));

        // Remover os itens antigos e inserir novos
        String deleteSql = "DELETE FROM item WHERE ped_id=" + entidade.getId();
        SingletonDB.getConexao().manipular(deleteSql);

        boolean result = SingletonDB.getConexao().manipular(sql);

        for (Pedido.Item item : entidade.getItens()) {
            String itemSql = String.format("INSERT INTO item (pro_id, ped_id, it_quant, it_valor) VALUES (%d, %d, %d, %.2f)",
                    item.produto().getId(), entidade.getId(), item.quantidade(), item.valor());
            SingletonDB.getConexao().manipular(itemSql);
        }

        return result;
    }

    @Override
    public boolean apagar(Pedido entidade) {
        // antes, remover itens associados a este pedido
        SingletonDB.getConexao().manipular("DELETE FROM item WHERE ped_id=" + entidade.getId());

        boolean result = SingletonDB.getConexao().manipular("DELETE FROM pedido WHERE ped_id=" + entidade.getId());
        return result;
    }

    @Override
    public Pedido get(int id) {
        Pedido pedido = null;
        String sql = "SELECT * FROM pedido WHERE ped_id=" + id;
        ResultSet rs = SingletonDB.getConexao().consultar(sql);
        try {
            //Pedido(int id, LocalDate data, String clienteNome, String clienteTelefone, double total, String local, String numero, TipoPagamento tipoPagamento, Status status )
            if (rs.next()) {
                pedido = new Pedido(
                        rs.getInt("ped_id"),
                        rs.getDate("ped_data").toLocalDate(),
                        rs.getString("ped_clinome"),
                        rs.getString("ped_clifone"),
                        rs.getDouble("ped_total"),
                        rs.getString("ped_local"),
                        rs.getString("ped_numero_res"),
                        new TipoPagamentoDal().get(rs.getInt("tpg_id")),
                        new StatusDal().get(rs.getInt("st_id"))
                );

                // Recuperar os itens associados a este pedido
                String itemSql = "SELECT * FROM item WHERE ped_id=" + id;
                ResultSet rsItens = SingletonDB.getConexao().consultar(itemSql);
                while (rsItens.next()) {
                    Produto produto = new ProdutoDal().get(rsItens.getInt("pro_id"));
                    Pedido.Item novoItem = new Pedido.Item(produto, rsItens.getInt("it_quant"), rsItens.getDouble("it_valor"));
                    pedido.adicionarItem(novoItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Melhorar tratamento
        }
        return pedido;
    }

    @Override
    public List<Pedido> get(String filtro) {
        List<Pedido> pedidoList = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        if (!filtro.isEmpty()) {
            sql += " WHERE " + filtro;
        }
        ResultSet rs = SingletonDB.getConexao().consultar(sql);
        try {
            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("ped_id"),
                        rs.getDate("ped_data").toLocalDate(),
                        rs.getString("ped_clinome"),
                        rs.getString("ped_clifone"),
                        rs.getDouble("ped_total"),
                        rs.getString("ped_local"),
                        rs.getString("ped_numero_res"),
                        new TipoPagamentoDal().get(rs.getInt("tpg_id")),
                        new StatusDal().get(rs.getInt("st_id"))
                );

                // Recuperar os itens associados a este pedido
                List<Pedido.Item> itens = new ArrayList<>();
                String itemSql = "SELECT * FROM item WHERE ped_id=" + pedido.getId();
                ResultSet rsItens = SingletonDB.getConexao().consultar(itemSql);
                while (rsItens.next()) {
                    Produto produto = new ProdutoDal().get(rsItens.getInt("pro_id"));
                    Pedido.Item novoItem = new Pedido.Item(produto, rsItens.getInt("it_quant"), rsItens.getDouble("it_valor"));
                    pedido.adicionarItem(novoItem);
                }

                pedidoList.add(pedido);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Melhorar tratamento
        }
        return pedidoList;
    }
}