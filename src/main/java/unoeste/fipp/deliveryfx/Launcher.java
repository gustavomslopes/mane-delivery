package unoeste.fipp.deliveryfx;

import javafx.application.Application;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        if(SingletonDB.conectar())
            Application.launch(DeliveryFX.class, args);
        else
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco: " + SingletonDB.getConexao().getMensagemErro());
    }
}
