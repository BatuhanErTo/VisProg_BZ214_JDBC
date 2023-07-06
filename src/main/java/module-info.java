module com.spaghetti.visprog_bz214_jdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.spaghetti.visprog_bz214_jdbc to javafx.fxml;
    exports com.spaghetti.visprog_bz214_jdbc;
    exports com.spaghetti.visprog_bz214_jdbc.first_example;
    exports com.spaghetti.visprog_bz214_jdbc.second_example;
    exports com.spaghetti.visprog_bz214_jdbc.third_example;
}