module com.mri.examenjava2425 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.mri.examenjava2425 to javafx.fxml;
    exports com.mri.examenjava2425;
}