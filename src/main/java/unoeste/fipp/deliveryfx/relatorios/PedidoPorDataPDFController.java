package unoeste.fipp.deliveryfx.relatorios;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import unoeste.fipp.deliveryfx.util.Relatorios;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PedidoPorDataPDFController {
    public DatePicker dpDataPedidoPdf;

    public void OnGerarDatPedidoPdf(ActionEvent actionEvent) throws IOException {
        if(Relatorios.PedidosData("pedidos_por_data.pdf", dpDataPedidoPdf.getValue()))
            Desktop.getDesktop().open(new File("pedidos_por_data.pdf"));
    }
}
