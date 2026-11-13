package unoeste.fipp.deliveryfx;

import javafx.scene.control.Label;

import java.time.LocalDate;

public class BlocoPedidoController {
    public Label lbidPedido;
    public Label lbNomeCliPedido;
    public Label lbValorTotalPedido;
    public Label lbDataPedido;
    public Label lbStatusPedido;

    public void setIdPedido(int lidPedido) {
        lbidPedido.setText(lidPedido+"");
    }

    public void setLbNomeCliPedido(String nomeCliPedido) {
        lbNomeCliPedido.setText(nomeCliPedido);
    }

    public void setLbValorTotalPedido(double valorTotalPedido) {
        lbValorTotalPedido.setText(valorTotalPedido+"");
    }

    public void setLbDataPedido(LocalDate dataPedido) {
        lbDataPedido.setText(dataPedido.toString());
    }

    public void setLbStatusPedido(String statusPedido) {
        lbStatusPedido.setText(statusPedido);
    }
}
