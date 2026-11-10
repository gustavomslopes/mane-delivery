package unoeste.fipp.deliveryfx.menus_cadastros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import unoeste.fipp.deliveryfx.db.dals.CategoriaDal;
import unoeste.fipp.deliveryfx.db.entidade.Categoria;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

public class MenuCategoriaController {

    @FXML
    private Button btCadastrarCat;

    @FXML
    private TextField tfnomecat;

    @FXML
    void onCadastrarCat(ActionEvent event) throws Exception {
        Categoria categoria = new Categoria(tfnomecat.getText());
        CategoriaDal categoriaDal = new CategoriaDal();
        if (!categoriaDal.gravar(categoria)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao gravar" + SingletonDB.getConexao().getMensagemErro());
            alert.showAndWait();
        }
        else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setContentText("Categoria cadastrada com sucesso!");

            alert.showAndWait();

            tfnomecat.clear();
        }
    }

}