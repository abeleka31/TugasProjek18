package eazysorder.view;

import eazysorder.controller.OrderController;
import eazysorder.model.Food;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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

        Label labelNama = new Label("Nama Pemesan: " + namaPemesan);
        labelNama.setAlignment(Pos.CENTER);
        labelNama.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        VBox vBoxPesanan = new VBox(10);
        vBoxPesanan.setAlignment(Pos.CENTER);
        vBoxPesanan.getChildren().add(labelNama);

        List<String> details = new ArrayList<>();
        // Menambahkan detail pesanan
        ObservableList<Food> orderItems = tablePesanan.getItems();
        for (Food food : orderItems) {
            String detail = food.getName() + " : " + food.getQuantity() + "x, Rp. " + food.getPrice() + "\n";
            details.add(detail);
            Label labelDetailPesanan = new Label(detail);
            labelDetailPesanan.setAlignment(Pos.CENTER);
            vBoxPesanan.getChildren().add(labelDetailPesanan);
        }

        // Menambahkan total harga
        double totalHarga = calculateTotalPrice(orderItems);
        Label labelTotal = new Label("TOTAL: Rp. " + totalHarga);
        labelTotal.setAlignment(Pos.CENTER);
        labelTotal.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        vBoxPesanan.getChildren().add(labelTotal);

        // Metode Pembayaran
        Label labelMetodePembayaran = new Label("Pilih Metode Pembayaran:");
        ToggleGroup paymentGroup = new ToggleGroup();
        RadioButton rbDebit = new RadioButton("Debit");
        RadioButton rbLangsung = new RadioButton("Langsung");
        rbDebit.setToggleGroup(paymentGroup);
        rbLangsung.setToggleGroup(paymentGroup);

        // Input jumlah uang jika Debit dipilih
        Label labelJumlahUang = new Label("Jumlah Uang:");
        TextField tfJumlahUang = new TextField();
        tfJumlahUang.setDisable(true);

        rbDebit.setOnAction(event -> tfJumlahUang.setDisable(false));
        rbLangsung.setOnAction(event -> tfJumlahUang.setDisable(true));

        VBox vBoxPembayaran = new VBox(10, labelMetodePembayaran, rbDebit, rbLangsung, labelJumlahUang, tfJumlahUang);
        vBoxPembayaran.setLayoutX(25);
        vBoxPembayaran.setLayoutY(25);
        vBoxPembayaran.setAlignment(Pos.CENTER);

        // Opsi Dine-in atau Takeaway
        Label labelOpsiOrder = new Label("Pilih Opsi Order:");
        ToggleGroup orderGroup = new ToggleGroup();
        RadioButton rbDineIn = new RadioButton("Dine-in");
        RadioButton rbTakeaway = new RadioButton("Takeaway");
        rbDineIn.setToggleGroup(orderGroup);
        rbTakeaway.setToggleGroup(orderGroup);

        VBox vBoxOpsiOrder = new VBox(10, labelOpsiOrder, rbDineIn, rbTakeaway);
        vBoxOpsiOrder.setLayoutX(25);
        vBoxOpsiOrder.setLayoutY(25);
        vBoxOpsiOrder.setAlignment(Pos.CENTER_LEFT);

        Pane paneInformasi = new Pane(vBoxPesanan);
        paneInformasi.setId("paneinformasi");
        paneInformasi.setLayoutX(440);
        paneInformasi.setLayoutY(93);
        paneInformasi.setPrefSize(400, 448);

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(event -> {
            primaryStage.setScene(previousScene);
        });
        kembaliButton.setStyle("-fx-font-size: 16px;");
        kembaliButton.setLayoutX(75);
        kembaliButton.setLayoutY(583);
        kembaliButton.setPrefSize(140, 52);

        Button edit = new Button("Edit");
        edit.setLayoutX(751);
        edit.setLayoutY(462);
        edit.setPrefSize(240, 49);
        edit.setOnAction(event -> {
            Menu1 menu1 = new Menu1(new MainScene(null), previousScene);
            Scene menuScene = menu1.tampilkanMenu1(orderItems); // Pass the current order items
            primaryStage.setScene(menuScene); // Set the scene back to the menu
        });

        Button pesan = new Button("PESAN");
        pesan.setLayoutX(1044);
        pesan.setLayoutY(574);
        pesan.setPrefSize(140, 52);
        pesan.setOnAction(event -> {
            String selectedPaymentMethod = ((RadioButton) paymentGroup.getSelectedToggle()).getText();
            String selectedOrderOption = ((RadioButton) orderGroup.getSelectedToggle()).getText();
            double jumlahUang = 0;

            if (rbDebit.isSelected()) {
                try {
                    jumlahUang = Double.parseDouble(tfJumlahUang.getText());
                    if (jumlahUang < totalHarga) {
                        showAlert("Jumlah uang tidak mencukupi!");
                        return;
                    }
                } catch (NumberFormatException e) {
                    showAlert("Jumlah uang harus berupa angka!");
                    return;
                }
            }

            if (selectedPaymentMethod == null || selectedOrderOption == null) {
                showAlert("Harap pilih metode pembayaran dan opsi order!");
                return;
            }

            details.add("Metode Pembayaran: " + selectedPaymentMethod);
            details.add("Opsi Order: " + selectedOrderOption);
            insertOrderToDatabase(details, totalHarga);

            // Transition to the next scene
            Selesai selesaiScene = new Selesai(primaryStage, namaPemesan, totalHarga, details, selectedOrderOption);
            primaryStage.setScene(selesaiScene.tampilkanSelesai());
        });

        Pane root = new Pane();
        root.setId("root");
        root.setLayoutX(271);
        root.setLayoutY(88);
        root.setPrefSize(400, 448);
        root.getChildren().addAll(vBoxPesanan);

        Pane pembayaran = new Pane(vBoxPembayaran);
        pembayaran.setId("pembayaran");
        pembayaran.setLayoutX(707);
        pembayaran.setLayoutY(88);
        pembayaran.setPrefSize(327, 182);

        Pane opsiOrder = new Pane(vBoxOpsiOrder);
        opsiOrder.setId("opsiorder");
        opsiOrder.setLayoutX(707);
        opsiOrder.setLayoutY(307);
        opsiOrder.setPrefSize(327, 118);

        Pane paneutama = new Pane();
        paneutama.setId("panedetail");
        paneutama.getChildren().addAll(root, pesan, kembaliButton, pembayaran, opsiOrder, edit);

        Scene scene = new Scene(paneutama, 1280, 700);
        String scc = this.getClass().getResource("/css/MenuStyle.css").toExternalForm();
        scene.getStylesheets().addAll(scc);
        return scene;
    }

    private void insertOrderToDatabase(List<String> details, double totalHarga) {
        orderController.createOrder(namaPemesan, totalHarga, details);
    }

    private double calculateTotalPrice(ObservableList<Food> orderItems) {
        return orderItems.stream().mapToDouble(food -> food.getPrice() * food.getQuantity()).sum();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
