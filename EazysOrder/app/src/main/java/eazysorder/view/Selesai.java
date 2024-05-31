package eazysorder.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Selesai {
    private Stage primaryStage;
    private String namaPemesan;
    private double totalHarga;
    private List<String> orderDetails;
    private String selectedOrderOption;

    public Selesai(Stage primaryStage, String namaPemesan, double totalHarga, List<String> orderDetails, String selectedOrderOption) {
        this.primaryStage = primaryStage;
        this.namaPemesan = namaPemesan;
        this.totalHarga = totalHarga;
        this.orderDetails = orderDetails;
        this.selectedOrderOption = selectedOrderOption;
    }

    public Scene tampilkanSelesai() {
        Label labelNama = new Label("Nama Pemesan: " + namaPemesan);
        labelNama.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label labelTotal = new Label("TOTAL: Rp. " + totalHarga);
        labelTotal.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        VBox vBoxDetails = new VBox(10);
        vBoxDetails.setAlignment(Pos.CENTER_LEFT);
        vBoxDetails.getChildren().add(labelNama);

        for (String detail : orderDetails) {
            Label labelDetail = new Label(detail);
            vBoxDetails.getChildren().add(labelDetail);
        }

        Label labelOrderOption = new Label("Opsi Order: " + selectedOrderOption);
        labelOrderOption.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        vBoxDetails.getChildren().add(labelOrderOption);

        vBoxDetails.getChildren().add(labelTotal);

        Button selesaiButton = new Button("Selesai");
        selesaiButton.setOnAction(event -> {
            // Logic to handle the finish action, such as returning to the main scene
            primaryStage.close(); // Example action, close the stage or go back to the main menu
        });

        VBox vBoxMain = new VBox(20, vBoxDetails, selesaiButton);
        vBoxMain.setAlignment(Pos.CENTER);
        vBoxMain.setPrefSize(400, 600);

        Scene scene = new Scene(vBoxMain);
        return scene;
    }
}
