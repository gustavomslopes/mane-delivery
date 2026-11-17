package unoeste.fipp.deliveryfx.menus;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

import java.io.File;

public class MenuOpcoesController {
    public void onRestaurarBanco(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("backup","*.backup"));
        fileChooser.setInitialDirectory(new File("bdutil"));
        File arquivoSelecionado = fileChooser.showOpenDialog(null);
        try {
            SingletonDB.restaurar(arquivoSelecionado.getAbsolutePath());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onBackupBanco(ActionEvent actionEvent) {

        FileChooser fileChooser=new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("backup","*.backup"));
        File backupFile = fileChooser.showSaveDialog(null);
        try {
            SingletonDB.backup(backupFile.getAbsolutePath());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
