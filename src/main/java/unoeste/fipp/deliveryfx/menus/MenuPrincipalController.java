package unoeste.fipp.deliveryfx.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import unoeste.fipp.deliveryfx.DeliveryFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {
    public ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        URL urlImagem = getClass().getResource("/unoeste/fipp/deliveryfx/baixados.png");

        if (urlImagem != null) {
            Image imagem = new Image(urlImagem.toExternalForm());
            imageView.setImage(imagem);
        }
    }

    public void onMenuCadastro(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("menu-cadastro-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Cadastros");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void onMenuRelatorio(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("menu-relatorio-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Relatórios");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void onMenuMovimentacao(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("menu-movimentacao-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Movimentações");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
