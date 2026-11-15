package unoeste.fipp.deliveryfx.relatorios;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import unoeste.fipp.deliveryfx.db.util.Relatorios;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuPedidoPDFController {
    public TextField tfIdPedidoPdf;

    public void OnGerarPedidoPDF(ActionEvent actionEvent) throws IOException {
        if(Relatorios.Pedidos("pedido.pdf", tfIdPedidoPdf.getText()))
            Desktop.getDesktop().open(new File("pedido.pdf"));
    }
}
