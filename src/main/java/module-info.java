module unoeste.fipp.deliveryfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.json;
    requires kernel;
    requires layout;
    opens unoeste.fipp.deliveryfx.db.entidade to javafx.base;

    opens unoeste.fipp.deliveryfx to javafx.fxml;
    exports unoeste.fipp.deliveryfx;

    exports unoeste.fipp.deliveryfx.cadastros;
    opens unoeste.fipp.deliveryfx.cadastros to javafx.fxml;

    exports unoeste.fipp.deliveryfx.menus;
    opens unoeste.fipp.deliveryfx.menus to javafx.fxml;

    exports unoeste.fipp.deliveryfx.db.entidade;

    opens unoeste.fipp.deliveryfx.relatorios to javafx.fxml;
    exports unoeste.fipp.deliveryfx.relatorios;

    opens unoeste.fipp.deliveryfx.graficos to javafx.fxml;
    exports unoeste.fipp.deliveryfx.graficos;
}