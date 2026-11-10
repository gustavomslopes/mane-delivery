package unoeste.fipp.deliveryfx;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PainelController implements Initializable {

    public TilePane painel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarPedidosPedentes();
    }

    private void carregarPedidosPedentes(){
        try{
            for(int i=0;i<10;i++){
                FXMLLoader fxmlLoader = new FXMLLoader(DeliveryFX.class.getResource("bloco-pedido-view.fxml"));

                VBox bloco=(fxmlLoader.load());
                BlocoPedidoController controller=fxmlLoader.getController();
                controller.setIdPedido(""+i);
                painel.getChildren().add(bloco);
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
