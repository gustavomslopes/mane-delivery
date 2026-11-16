package unoeste.fipp.deliveryfx.db.util;

import java.time.LocalDate;
import java.util.ArrayList;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import unoeste.fipp.deliveryfx.db.dals.PedidoDal;
import unoeste.fipp.deliveryfx.db.dals.ProdutoDal;
import unoeste.fipp.deliveryfx.db.entidade.Pedido;
import unoeste.fipp.deliveryfx.db.entidade.Produto;

public class Relatorios {
    public static boolean Pedidos(String pdfFile, String id) {
        try {
            PdfWriter writer = new PdfWriter(pdfFile);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Add content
            GerarPedidoPDF(document, Integer.parseInt(id));

            //cria o arquivo
            document.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean Precos(String pdfFile) {
        try {
            PdfWriter writer = new PdfWriter(pdfFile);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Add content
            GerarPrecosPDF(document);

            //cria o arquivo
            document.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean PedidosData(String pdfFile, LocalDate data) {
        try {
            PdfWriter writer = new PdfWriter(pdfFile);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Add content
            GerarPedidosData(document, data);

            //cria o arquivo
            document.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean EstoqueBaixo(String pdfFile) {
        try {
            PdfWriter writer = new PdfWriter(pdfFile);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Add content
            GerarEstoqueBaixo(document);

            //cria o arquivo
            document.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static void GerarPedidoPDF(Document document, int id) {
        PedidoDal pedidoDal = new PedidoDal();
        Pedido pedido = pedidoDal.get(id);

        // Add first chapter
        Paragraph chapter1 = new Paragraph("Pedido: "+id);
        document.add(chapter1);

        // Add subcategory 2
        Paragraph subCat2 = new Paragraph("Informações do cliente")
                .setFontSize(16)
                .setBold();
        document.add(subCat2);
        document.add(new Paragraph("Data do pedido: "+pedido.getData()));
        document.add(new Paragraph("Nome do cliente: "+pedido.getClienteNome()));
        document.add(new Paragraph("Tel. do cliente: "+pedido.getClienteTelefone()));

        Paragraph subCat3 = new Paragraph("Informações do pedido")
                .setFontSize(16)
                .setBold();
        document.add(subCat2);
        document.add(new Paragraph("Total: R$"+pedido.getTotal()));
        document.add(new Paragraph("Local: "+pedido.getLocal()));
        document.add(new Paragraph("Numero: "+pedido.getNumero()));
        document.add(new Paragraph("Tipo de pagamento: "+pedido.getTipoPagamento().getNome()));
        document.add(new Paragraph("Status: "+pedido.getStatus().getDescricao()));

        // Add a list
        List list = new List()
                .setListSymbol("\u2022")
                .setFontSize(12);
        for(int i=0; i<pedido.getItens().size(); i++)
        {
            list.add(new ListItem("Item "+(i+1)+": "+pedido.getItens().get(i).getNomeProduto()+" x"+pedido.getItens().get(i).getQuantidade()));
        }

        document.add(list);
    }

    private static void GerarPrecosPDF(Document document) {
        ProdutoDal produtoDal = new ProdutoDal();
        ArrayList<Produto> produtoList = new ArrayList<Produto>(produtoDal.get(""));

        // Add first chapter
        Paragraph chapter1 = new Paragraph("Preços: ");
        document.add(chapter1);

        // Add a list
        List list = new List()
                .setListSymbol("\u2022")
                .setFontSize(12);
        for(int i=0; i<produtoList.size(); i++)
        {
            list.add(new ListItem("Produto: "+produtoList.get(i).getNome()+", preço: "+produtoList.get(i).getPreco()));
        }
        document.add(list);
    }

    private static void GerarPedidosData(Document document, LocalDate data) {
        PedidoDal pedidoDal = new PedidoDal();
        ArrayList<Pedido> pedidoList = new ArrayList<Pedido>(pedidoDal.get("ped_data = '"+data.getYear()+"-"+data.getMonth()+"-"+data.getDayOfMonth()+"'"));

        // Add first chapter
        Paragraph chapter1 = new Paragraph("Pedidos do dia: "+data.getDayOfMonth()+"/"+data.getMonth()+"/"+data.getYear());
        document.add(chapter1);

        // Add a list
        List list = new List()
                .setListSymbol("\u2022")
                .setFontSize(12);
        for(int i=0; i<pedidoList.size(); i++)
        {
            list.add(new ListItem("Pedido: "+pedidoList.get(i).getId()+", Nome do cliente: "+pedidoList.get(i).getClienteNome()));
        }

        document.add(list);
    }

    private static void GerarEstoqueBaixo(Document document) {
        ProdutoDal produtoDal = new ProdutoDal();
        ArrayList<Produto> produtoList = new ArrayList<Produto>(produtoDal.get(""));


        // Add first chapter
        Paragraph chapter1 = new Paragraph("Produtos com estoque baixo: ");
        document.add(chapter1);

        // Add a list
        List list = new List()
                .setListSymbol("\u2022")
                .setFontSize(12);
        for(int i=0; i<produtoList.size(); i++)
        {
            if(produtoList.get(i).getEstoque()<5)
                list.add(new ListItem("Produto: "+produtoList.get(i).getNome()+", estoque: "+produtoList.get(i).getEstoque()));
        }

        document.add(list);
    }
}