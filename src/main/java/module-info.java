module unoeste.fipp.deliveryfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.json;

    opens unoeste.fipp.deliveryfx to javafx.fxml;
    exports unoeste.fipp.deliveryfx;
    exports unoeste.fipp.deliveryfx.menus_cadastros;
    opens unoeste.fipp.deliveryfx.menus_cadastros to javafx.fxml;
}