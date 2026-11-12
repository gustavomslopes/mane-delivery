module unoeste.fipp.deliveryfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.json;
    opens unoeste.fipp.deliveryfx.db.entidade to javafx.base;

    opens unoeste.fipp.deliveryfx to javafx.fxml;
    exports unoeste.fipp.deliveryfx;
    exports unoeste.fipp.deliveryfx.cadastros;
    opens unoeste.fipp.deliveryfx.cadastros to javafx.fxml;
    exports unoeste.fipp.deliveryfx.menus;
    opens unoeste.fipp.deliveryfx.menus to javafx.fxml;
}