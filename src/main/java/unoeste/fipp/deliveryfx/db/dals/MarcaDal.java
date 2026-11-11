package unoeste.fipp.deliveryfx.db.dals;

import unoeste.fipp.deliveryfx.db.entidade.Marca;
import unoeste.fipp.deliveryfx.db.util.IDAL;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MarcaDal implements IDAL<Marca> {

    @Override
    public boolean gravar(Marca entidade) {
        String sql = "INSERT INTO marca VALUES (default, '$1')";
        sql = sql.replace("$1", entidade.getNome());
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean alterar(Marca entidade) {
        String sql = "UPDATE marca SET cat_nome = '$1' WHERE mar_id = $2";
        sql = sql.replace("$1", entidade.getNome());
        sql = sql.replace("$2", "" + entidade.getId());
        return SingletonDB.getConexao().manipular(sql);
    }

    @Override
    public boolean apagar(Marca entidade) {
        return SingletonDB.getConexao().manipular("DELETE FROM marca WHERE mar_id = " + entidade.getId());
    }

    @Override
    public Marca get(int id) {
        Marca categoria = null;
        String sql = "SELECT * FROM marca WHERE mar_id = " + id;
        ResultSet rs = SingletonDB.getConexao().consultar(sql);
        try {
            if (rs.next()) {
                categoria = new Marca(rs.getInt("mar_id"), rs.getString("mar_nome"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public List<Marca> get(String filtro) {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT * FROM marca";
        if (!filtro.isEmpty())
            sql += " WHERE " + filtro;
        ResultSet rs = SingletonDB.getConexao().consultar(sql);
        try {
            while (rs.next()) {
                Marca marca = new Marca(rs.getInt("mar_id"), rs.getString("mar_nome"));
                lista.add(marca);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}