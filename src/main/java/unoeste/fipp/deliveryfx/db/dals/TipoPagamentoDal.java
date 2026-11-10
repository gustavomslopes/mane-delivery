package unoeste.fipp.deliveryfx.db.dals;

import unoeste.fipp.deliveryfx.db.entidade.TipoPagamento;
import unoeste.fipp.deliveryfx.db.util.IDAL;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoPagamentoDal implements IDAL<TipoPagamento> {

    @Override
    public boolean gravar(TipoPagamento entidade) {
        String sql = "INSERT INTO categoria VALUES (default, '$1')";
        sql = sql.replace("$1", entidade.getNome());
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean alterar(TipoPagamento entidade) {
        String sql = "UPDATE categoria SET cat_nome = '$1' WHERE cat_id = $2";
        sql = sql.replace("$1", entidade.getNome());
        sql = sql.replace("$2", "" + entidade.getId());
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(TipoPagamento entidade) {
        return SingletonDB.getConexao().manipular("DELETE FROM categoria WHERE cat_id = " + entidade.getId());
    }

    @Override
    public TipoPagamento get(int id) {
        TipoPagamento categoria = null;
        String sql = "SELECT * FROM categoria WHERE cat_id = " + id;
        ResultSet rs = SingletonDB.getConexao().consultar(sql);
        try {
            if (rs.next()) {
                categoria = new TipoPagamento(rs.getInt("cat_id"), rs.getString("cat_nome"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public List<TipoPagamento> get(String filtro) {
        List<TipoPagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        if (!filtro.isEmpty())
            sql += " WHERE " + filtro;
        ResultSet rs = SingletonDB.getConexao().consultar(sql);
        try {
            while (rs.next()) {
                TipoPagamento categoria = new TipoPagamento(rs.getInt("cat_id"), rs.getString("cat_nome"));
                lista.add(categoria);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}