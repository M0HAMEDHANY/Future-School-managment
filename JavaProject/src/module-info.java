module JavaProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	opens application to javafx.graphics, javafx.fxml,javafx.controls,javafx.base,javafx.media,javafx.swing,javafx.swt,javafx.web;
}
