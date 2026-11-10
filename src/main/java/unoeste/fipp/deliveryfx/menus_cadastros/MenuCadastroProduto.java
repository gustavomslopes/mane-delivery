package unoeste.fipp.deliveryfx.menus_cadastros;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import unoeste.fipp.deliveryfx.db.dals.CategoriaDal;
import unoeste.fipp.deliveryfx.db.entidade.Categoria;
import unoeste.fipp.deliveryfx.db.entidade.Produto;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

public class MenuCadastroProduto {

    @FXML
    private Button btCadastrarProduto;

    @FXML
    private ComboBox<?> cbidcategoria;

    @FXML
    private ComboBox<?> cbidmarca;

    @FXML
    private TextField tfestoqueproduto;

    @FXML
    private TextField tfnomeproduto;

    @FXML
    private TextField tfprecoproduto;

    @FXML
    private TextField tfvolumeproduto;

    //public Produto(String nome, int estoque, double preco, LocalDate data, String unidade, Categoria categoria, double volume, Marca marca)

    @FXML
    void onCadastrarProduto(ActionEvent event) {
        /*Produto produto = new Produto(tfnomeproduto.getText(),Integer.parseInt(tfestoqueproduto.getText()),Double.parseDouble(tfprecoproduto.getText().replace(",",".")));
        ProdutoDal produtoDal = new ProdutoDal();
        if (!produtoDal.gravar(categoria)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao gravar" + SingletonDB.getConexao().getMensagemErro());
            alert.showAndWait();
        }
        else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setContentText("Categoria cadastrada com sucesso!");

            alert.showAndWait();

            tfnomecat.clear();
        }*/
    }

}
