package unoeste.fipp.deliveryfx.db.util;

import unoeste.fipp.deliveryfx.db.util.Conexao;

public class SingletonDB {
    private static Conexao conexao = null;

    private SingletonDB() {
        // impede a instância da classe
    }

    public static boolean conectar() {
        conexao = new Conexao();
        return conexao.conectar("jdbc:postgresql://localhost/", "deliverydb", "postgres", "postgres123");
    }

    public static Conexao getConexao() {
        return conexao;
    }
}