package unoeste.fipp.deliveryfx;

import javafx.application.Application;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        if(SingletonDB.conectar())
            Application.launch(DeliveryFX.class, args);
        else{
            if(JOptionPane.showConfirmDialog(null,"Erro ao conectar\nDeseja criar o Database?")==JOptionPane.OK_OPTION){
                if(SingletonDB.criarBD()){
                    JOptionPane.showMessageDialog(null,"Database criado!\nExecute o sistema novamente e restaure a base de dados");
                }
            }
            else
                JOptionPane.showMessageDialog(null,"Erro ao criar o Database"+SingletonDB.getConexao().getMensagemErro());
        }
    }
}
