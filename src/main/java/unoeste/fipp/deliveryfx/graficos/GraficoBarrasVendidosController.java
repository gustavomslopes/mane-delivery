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
import unoeste.fipp.deliveryfx.db.util.Struct;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class GraficoBarrasVendidosController implements Initializable {

    public BarChart<String, Number> bcBarrasVendidos;
    public CategoryAxis eixoX;
    public NumberAxis eixoY;

    public LocalDate datafim, datainicio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datafim = MenuGraficoBarrasController.getData().withDayOfMonth(30);
        datainicio = datafim.withDayOfMonth(1);

        GerarGraficoBarrasVendidos();

    }

    private int temProduto(List<Struct> lista, String nome){
        int pos=0;
        while(pos<lista.size() && !(lista.get(pos).getNome().equals(nome)))
            pos++;
        if(pos<lista.size())
            return pos;

        return -1;
    }

    public void GerarGraficoBarrasVendidos()
    {
        ProdutoDal produtoDal = new ProdutoDal();
        PedidoDal pedidoDal = new PedidoDal();

        List<Pedido> pedidoList = pedidoDal.get("ped_data > '"+datainicio.getYear()+"-"+datainicio.getMonth()+"-01' AND ped_data < '"+datafim.toString()+"'");

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("qtd vendas");

        List<Struct> lista = new ArrayList<>();

        for (int i = 0; i < pedidoList.size(); i++) {
            List<Pedido.Item> itensList = pedidoList.get(i).getItens();
            for (int j = 0; j < itensList.size(); j++) {
                Struct atual = new Struct(itensList.get(j).getQuantidade(), itensList.get(j).getNomeProduto());
                if (temProduto(lista, atual.getNome())==-1) {
                    lista.add(atual);
                    lista.sort(Comparator.comparingInt(Struct::getQuantidade).reversed());
                } else {
                    lista.get(temProduto(lista, atual.getNome())).setQuantidade(lista.get(temProduto(lista, atual.getNome())).getQuantidade()+atual.getQuantidade());
                    lista.sort(Comparator.comparingInt(Struct::getQuantidade).reversed());
                }
            }
        }

        int pos = 0;
        while(pos<lista.size() && pos < 20)
        {
            serie.getData().add(new XYChart.Data<>(lista.get(pos).getNome(), lista.get(pos).getQuantidade()));
            pos++;
        }

        bcBarrasVendidos.getData().clear();
        bcBarrasVendidos.getData().add(serie);
        if(!lista.isEmpty())
            eixoY.setUpperBound(lista.getFirst().getQuantidade());
    }


}
