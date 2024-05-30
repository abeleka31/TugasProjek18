package eazysorder.view;

import eazysorder.controller.OrderController;
import eazysorder.model.Food;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DetailPesanan {
    private Stage primaryStage;
    private Scene previousScene;
    private String namaPemesan;
    private TableView<Food> tablePesanan;
    private OrderController orderController;

    public DetailPesanan(Stage primaryStage, Scene previousScene, String namaPemesan, TableView<Food> tablePesanan, OrderController orderController) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
        this.namaPemesan = namaPemesan;
        this.tablePesanan = tablePesanan;
        this.orderController = orderController;
    }

    public Scene tampilkanDetailPesanan() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        Label labelNama = new Label("Nama Pemesan: " + namaPemesan);
        labelNama.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        VBox vBoxPesanan = new VBox(10);
        vBoxPesanan.setAlignment(Pos.CENTER_LEFT);
        vBoxPesanan.setPadding(new Insets(20, 0, 0, 0));
        vBoxPesanan.getChildren().add(labelNama);

        List<String> details = new ArrayList<>();
        // Menambahkan detail pesanan
        ObservableList<Food> orderItems = tablePesanan.getItems();
        for (Food food : orderItems) {
            String detail = food.getName() + " : " + food.getQuantity() + "x, Rp. " + food.getPrice() + "\n";
            details.add(detail);
            Label labelDetailPesanan = new Label(detail);
            vBoxPesanan.getChildren().add(labelDetailPesanan);
        }

        // Menambahkan total harga
        double totalHarga = calculateTotalPrice(orderItems);
        Label labelTotal = new Label("TOTAL: Rp. " + totalHarga);
        labelTotal.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        vBoxPesanan.getChildren().add(labelTotal);

        HBox hBoxButtons = new HBox(10);
        hBoxButtons.setAlignment(Pos.CENTER_RIGHT);
        hBoxButtons.setPadding(new Insets(20, 0, 0, 0));

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(event -> {
            insertOrderToDatabase(details, totalHarga);
            primaryStage.setScene(previousScene);
        });
        kembaliButton.setStyle("-fx-font-size: 16px;");

        hBoxButtons.getChildren().addAll(kembaliButton);

        root.setCenter(vBoxPesanan);
        root.setBottom(hBoxButtons);

        Scene scene = new Scene(root, 1280, 700);
        return scene;
    }

    private void insertOrderToDatabase(List<String> details, double totalHarga) {
        orderController.createOrder(namaPemesan, totalHarga, details);
    }

    private double calculateTotalPrice(ObservableList<Food> orderItems) {
        return orderItems.stream().mapToDouble(food -> food.getPrice() * food.getQuantity()).sum();
    }
}
