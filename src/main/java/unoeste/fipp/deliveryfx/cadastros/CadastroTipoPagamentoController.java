package unoeste.fipp.deliveryfx.cadastros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import unoeste.fipp.deliveryfx.db.dals.TipoPagamentoDal;
import unoeste.fipp.deliveryfx.db.entidade.TipoPagamento;

public class CadastroTipoPagamentoController {

    @FXML
    private TextField tfnometipopag;

    public void onCadastrarTipoPag(ActionEvent actionEvent) {
        if(tfnometipopag.getText().isEmpty()){
            tfnometipopag.requestFocus();
        } else{
            TipoPagamento tipoPagamento = new TipoPagamento(tfnometipopag.getText());
            TipoPagamentoDal tipoPagamentodal = new TipoPagamentoDal();
            if(!tipoPagamentodal.gravar(tipoPagamento)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erro!");
                alert.setHeaderText("Falha ao cadastrar tipo de pagamento!");
                alert.setContentText("");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso!");
                alert.setHeaderText("Tipo de Pagamento cadastrado com sucesso!");
                alert.setContentText("");
                alert.showAndWait();
                tfnometipopag.clear();
                tfnometipopag.requestFocus();
            }
        }


    }

}
