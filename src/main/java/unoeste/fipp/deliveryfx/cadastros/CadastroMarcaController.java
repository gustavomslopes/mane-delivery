package unoeste.fipp.deliveryfx.cadastros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import unoeste.fipp.deliveryfx.db.dals.MarcaDal;
import unoeste.fipp.deliveryfx.db.entidade.Marca;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

public class CadastroMarcaController {

    @FXML
    private Button btCadastrarMarca;

    @FXML
    private TextField tfnomemarca;

    @FXML
    void onCadastrarMarca(ActionEvent event) {
        if(tfnomemarca.getText().isEmpty()){
            tfnomemarca.requestFocus();
        } else {
            Marca marca = new Marca(tfnomemarca.getText());
            MarcaDal marcaDal = new MarcaDal();
            if (!marcaDal.gravar(marca)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erro ao gravar" + SingletonDB.getConexao().getMensagemErro());
                alert.showAndWait();
            }
            else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setContentText("Categoria cadastrada com sucesso!");

                alert.showAndWait();

                tfnomemarca.clear();
            }
        }

    }

}
