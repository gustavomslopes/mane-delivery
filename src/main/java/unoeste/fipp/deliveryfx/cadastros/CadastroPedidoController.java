package unoeste.fipp.deliveryfx.cadastros;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONObject;
import unoeste.fipp.deliveryfx.db.dals.PedidoDal;
import unoeste.fipp.deliveryfx.db.dals.ProdutoDal;
import unoeste.fipp.deliveryfx.db.dals.StatusDal;
import unoeste.fipp.deliveryfx.db.dals.TipoPagamentoDal;
import unoeste.fipp.deliveryfx.db.entidade.*;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

public class CadastroPedidoController {
    public TableColumn coProd;
    public TableColumn coQuant;
    public TableColumn coPreco;
    public TableColumn coTot;
    private double valorTotal = 0.0;

    @FXML
    private ComboBox<Produto> cbProduto;

    @FXML
    private ComboBox<TipoPagamento> cbpgt;

    @FXML
    private DatePicker dpData;

    @FXML
    private Label lbid;

    @FXML
    private Label lbvalor;

    @FXML
    private Spinner<Integer> spQuant;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfcep;

    @FXML
    private TextField tflocal;

    @FXML
    private TextField tfnumero;

    @FXML
    private TextField tftel;

    @FXML
    private TableView<Itens> tvTabela;

    @FXML
    void onBuscarCep(KeyEvent event) {
        if (tfcep.getText().length() == 8) {
            String json = buscarCepViaCep(tfcep.getText());
            JSONObject jsonObject = new JSONObject(json);
            tflocal.setText(jsonObject.getString("logradouro") + " " + jsonObject.getString("bairro") + " " + jsonObject.getString("localidade") + "/" + jsonObject.getString("uf"));
            Platform.runLater(() -> tfnumero.requestFocus());
        }
    }

    private String buscarCepViaCep(String cep) {
        StringBuffer dados = new StringBuffer();
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
            URLConnection con = url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setAllowUserInteraction(false);
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = "";
            while (null != (s = br.readLine()))
                dados.append(s);
            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dados.toString();
    }

    @FXML
    void onCadastrarPedido(ActionEvent event) {
        Status status = new StatusDal().get(6);
        Pedido pedido = new Pedido(dpData.getValue(), tfNome.getText(), tftel.getText(), Double.parseDouble(lbvalor.getText().replace(",", ".")), tflocal.getText(), tfnumero.getText(), cbpgt.getSelectionModel().getSelectedItem(), status);

        PedidoDal pedidoDal = new PedidoDal();
        if (!pedidoDal.gravar(pedido)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erro ao gravar: " + SingletonDB.getConexao().getMensagemErro());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Pedido cadastrado com sucesso!");
            alert.showAndWait();

        }

        tfNome.clear();
        tfcep.clear();
        tflocal.clear();
        tfnumero.clear();
        tftel.clear();
        cbpgt.getSelectionModel().clearSelection();
        cbProduto.getSelectionModel().clearSelection();
        dpData.setValue(null);
        spQuant.getValueFactory().setValue(1);
        tvTabela.getItems().clear();
        lbvalor.setText("0,0");
    }

    @FXML
    public void onSomar(ActionEvent actionEvent) {
        Produto produto = cbProduto.getSelectionModel().getSelectedItem();

        if (produto == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Selecione um produto!");
            alert.showAndWait();
            return;
        }

        int quantidade = spQuant.getValue();
        if (quantidade <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("A quantidade deve ser maior que zero!");
            alert.showAndWait();
            return;
        }


        Itens item = new Itens(null, produto, quantidade);


        ObservableList<Itens> itensAtuais = tvTabela.getItems();
        itensAtuais.add(item);
        tvTabela.setItems(itensAtuais);

        double subtotal = produto.getPreco() * quantidade;
        valorTotal += subtotal;
        lbvalor.setText(String.format(Locale.US, "%.2f", valorTotal));

        cbProduto.getSelectionModel().clearSelection();
        spQuant.getValueFactory().setValue(1);
    }

    @FXML
    public void onCancelar(ActionEvent actionEvent) {
        tfNome.clear();
        tfcep.clear();
        tflocal.clear();
        tfnumero.clear();
        tftel.clear();
        dpData.setValue(null);
        cbpgt.getSelectionModel().clearSelection();
        cbProduto.getSelectionModel().clearSelection();
        spQuant.getValueFactory().setValue(1);

        valorTotal = 0.0;
        lbvalor.setText("0.00");

        tvTabela.setItems(FXCollections.observableArrayList());
    }

    @FXML
    public void OnImprimir(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Funcionalidade de impressão.");
        alert.showAndWait();
    }

    @FXML
    public void initialize() {

        ProdutoDal dal = new ProdutoDal();
        cbProduto.setItems(FXCollections.observableArrayList(dal.get("")));
        TipoPagamentoDal tpd = new TipoPagamentoDal();
        cbpgt.setItems(FXCollections.observableArrayList(tpd.get("")));

        tvTabela.setItems(FXCollections.observableArrayList());

        spQuant.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999, 1));

        lbvalor.setText("0.00");
        coProd.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        coQuant.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        coPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        coTot.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

}