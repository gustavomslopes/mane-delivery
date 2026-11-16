package unoeste.fipp.deliveryfx.graficos;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import unoeste.fipp.deliveryfx.db.dals.PedidoDal;
import unoeste.fipp.deliveryfx.db.dals.ProdutoDal;
import unoeste.fipp.deliveryfx.db.entidade.Pedido;
import unoeste.fipp.deliveryfx.db.entidade.Produto;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class GraficoBarrasVendidosController implements Initializable {

    public BarChart<String, Number> bcBarrasVendidos;
    public CategoryAxis eixoX;
    public NumberAxis eixoY;

    public LocalDate datafim, datainicio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datafim = LocalDate.now();
        datainicio = datafim.minusMonths(1);



    }

    public void GerarGraficoBarrasVendidos()
    {
        ProdutoDal produtoDal = new ProdutoDal();

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("silvio");

        LocalDate atual = datainicio;
        while(atual.isBefore(datafim))
        {
            List<Produto> produtoList = produtoDal.get("");


        }
    }


}
