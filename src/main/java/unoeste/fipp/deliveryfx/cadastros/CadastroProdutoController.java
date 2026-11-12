package unoeste.fipp.deliveryfx.cadastros;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import unoeste.fipp.deliveryfx.db.dals.CategoriaDal;
import unoeste.fipp.deliveryfx.db.dals.MarcaDal;
import unoeste.fipp.deliveryfx.db.dals.ProdutoDal;
import unoeste.fipp.deliveryfx.db.entidade.Categoria;
import unoeste.fipp.deliveryfx.db.entidade.Marca;
import unoeste.fipp.deliveryfx.db.entidade.Produto;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroProdutoController implements Initializable {

    @FXML
    private Button btCadastrarProduto;

    @FXML
    private ComboBox<Categoria> cbidcategoria;

    @FXML
    private ComboBox<Marca> cbidmarca;

    @FXML
    private TextField tfestoqueproduto;

    @FXML
    private TextField tfnomeproduto;

    @FXML
    private TextField tfprecoproduto;


    //public Produto(String nome, int estoque, double preco, Categoria categoria, Marca marca)
    @FXML
    void onCadastrarProduto(ActionEvent event) {
        if (tfnomeproduto.getText().isEmpty() || tfestoqueproduto.getText().isEmpty() || tfprecoproduto.getText().isEmpty() || cbidcategoria.getSelectionModel().isEmpty() || cbidmarca.getSelectionModel().isEmpty()) {
            tfnomeproduto.requestFocus();
        } else {
            try {
                Produto produto = new Produto(tfnomeproduto.getText(), Integer.parseInt(tfestoqueproduto.getText()), Double.parseDouble(tfprecoproduto.getText().replace(",", ".")), cbidcategoria.getSelectionModel().getSelectedItem(), cbidmarca.getSelectionModel().getSelectedItem());
                ProdutoDal produtoDal = new ProdutoDal();
                if (!produtoDal.gravar(produto)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Erro ao gravar" + SingletonDB.getConexao().getMensagemErro());
                    alert.showAndWait();
                } else {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setContentText("Categoria cadastrada com sucesso!");

                    alert.showAndWait();

                    tfnomeproduto.clear();
                    tfprecoproduto.clear();
                    tfestoqueproduto.clear();
                    cbidcategoria.getSelectionModel().clearSelection();
                    cbidmarca.getSelectionModel().clearSelection();
                }
            } catch (Exception e){
                tfnomeproduto.requestFocus();
            }
        }


    }

    private void carregarCategorias() {
        CategoriaDal categoriaDAL = new CategoriaDal();
        cbidcategoria.setItems(FXCollections.observableArrayList(categoriaDAL.get("")));
    }

    private void carregarMarca() {
        MarcaDal marcaDAL = new MarcaDal();
        cbidmarca.setItems(FXCollections.observableArrayList(marcaDAL.get("")));
    }

    //cbCategoria.setItems(FXCollections.observableArrayList(new CategoriaDal().get("")));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarCategorias();
        carregarMarca();
    }
}
