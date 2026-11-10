package unoeste.fipp.deliveryfx.menus_cadastros;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import unoeste.fipp.deliveryfx.db.dals.CategoriaDal;
import unoeste.fipp.deliveryfx.db.entidade.Categoria;
import unoeste.fipp.deliveryfx.db.entidade.Pedido;
import unoeste.fipp.deliveryfx.db.util.SingletonDB;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MenuCadastroPedido {
    @FXML
    private TextField tfcep;

    @FXML
    private TextField tflocal;

    @FXML
    private TextField tfnumero;

    @FXML
    void onBuscarCep(KeyEvent event) {
        if(tfcep.getText().length()==8) {
            String json = buscarCepViaCep(tfcep.getText());
            JSONObject jsonObject = new JSONObject(json);
            tflocal.setText(jsonObject.getString("logradouro")+" "+jsonObject.getString("bairro")+" "+jsonObject.getString("localidade")+"/"+jsonObject.getString("uf"));
            Platform.runLater(()->tfnumero.requestFocus());
        }
    }

    private String buscarCepViaCep(String cep) {
        StringBuffer dados = new StringBuffer();
        try {
            URL url = new URL("https://viacep.com.br/ws/"+ cep + "/json/");
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
}
