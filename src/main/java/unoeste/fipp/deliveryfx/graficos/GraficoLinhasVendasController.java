package unoeste.fipp.deliveryfx.graficos;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import unoeste.fipp.deliveryfx.db.dals.PedidoDal;
import unoeste.fipp.deliveryfx.db.entidade.Pedido;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GraficoLinhasVendasController implements Initializable {

    public LineChart<String, Number> lcLinhasVendas;
    public CategoryAxis eixoX;
    public NumberAxis eixoY;
    private LocalDate datainicio, datafim;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datafim = LocalDate.now();
        datainicio = datafim.minusDays(7);

        GerarGraficoLinhaVendas();
    }

    public void GerarGraficoLinhaVendas()
    {
        PedidoDal pedidoDal = new PedidoDal();
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("qtd vendas");
        int maior=0;

        LocalDate atual = datainicio;
        while (atual.isBefore(datafim))
        {
            List<Pedido> pedidoList = pedidoDal.get("ped_data = '"+atual.toString()+"'");
            serie.getData().add(new XYChart.Data<>(atual.toString(), pedidoList.size()));
            if(pedidoList.size() > maior)
                maior = pedidoList.size();
            atual = atual.plusDays(1);
        }

        lcLinhasVendas.getData().clear();
        lcLinhasVendas.getData().add(serie);
        eixoY.setUpperBound(maior);
    }
}
