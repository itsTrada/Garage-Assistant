package garage.assistant.alert;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class AlertMaker {

    public static void showSimpleInforAlert(String title, String content) {
        Alert altInf = new Alert(AlertType.INFORMATION);
        altInf.setTitle(title);
        altInf.setHeaderText(null);
        altInf.setContentText(content);

        altInf.showAndWait();
    }

    public static void showErrorMessage(String title, String content) {
        Alert altErr = new Alert(AlertType.ERROR);
        altErr.setTitle("Error");
        altErr.setHeaderText(title);
        altErr.setContentText(content);

        altErr.showAndWait();
    }
    
    public static void showSimpleErrorMessage(String title, String content) {
        Alert altSmpErr = new Alert(AlertType.ERROR);
        altSmpErr.setTitle(title);
        altSmpErr.setHeaderText(null);
        altSmpErr.setContentText(content);

        altSmpErr.showAndWait();
    }

    public static void showErrorMessage(Exception ex) {
        Alert altErr = new Alert(AlertType.ERROR);
        altErr.setTitle("Error occured");
        altErr.setHeaderText("Error Occured");
        altErr.setContentText(ex.getLocalizedMessage());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        altErr.getDialogPane().setExpandableContent(expContent);
        altErr.showAndWait();
    }

    public static void showErrorMessage(Exception ex, String title, String content) {
        Alert altErr = new Alert(AlertType.ERROR);
        altErr.setTitle("Error occured");
        altErr.setHeaderText(title);
        altErr.setContentText(content);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        altErr.getDialogPane().setExpandableContent(expContent);
        altErr.showAndWait();
    }

    public static void showMaterialDialog(StackPane root, Node nodeToBeBlurred, List<JFXButton> controls, String header, String body) {
        BoxBlur blur = new BoxBlur(2, 2, 2); //create effect
        if (controls.isEmpty()) { //default button
            controls.add(new JFXButton("OK"));
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);

        controls.forEach(controlButton -> {
            controlButton.getStyleClass().add("dialog-button");
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close(); //close whenever clicked
            });
        });

        dialogLayout.setHeading(new Label(header));
        dialogLayout.setBody(new Label(body));
        dialogLayout.setActions(controls);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            nodeToBeBlurred.setEffect(null); //RESET effect to node: unblur
        });
        nodeToBeBlurred.setEffect(blur); //set effect
    }

}