package eazysorder.view;

import eazysorder.controller.FoodController;
import eazysorder.controller.OrderController;
import eazysorder.model.Food;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import eazysorder.App;

import java.util.List;

public class Menu1 {
    private MainScene mainScene;
    private Scene previousScene;
    private FoodController foodController = new FoodController();
    private TableView<Food> tablePesanan;
    private Label totalHarga;

    public Menu1(MainScene mainScene, Scene previousScene) {
        this.mainScene = mainScene;
        this.previousScene = previousScene;
    }

    public Scene tampilkanMenu1() {
        Pane mainPane = createMainArea();

        Scene scene = new Scene(mainPane, 1280, 700);
        String css = this.getClass().getResource("/css/Style.css").toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    private Pane createMainArea() {
        Pane mainPane = new Pane();
        mainPane.setId("mainarea");

        // Top section
        Pane atas = new Pane();
        atas.setId("atas");
        atas.setPrefSize(929, 91);
        atas.setLayoutX(0);
        atas.setLayoutY(0);

        Button tombolSemua = new Button("Semua");
        tombolSemua.setLayoutX(23);
        tombolSemua.setLayoutY(106);
        tombolSemua.setPrefSize(110, 30);
        tombolSemua.getStyleClass().add("transparent-button");

        Button tombolDonat = new Button("Donat");
        tombolDonat.setLayoutX(154);
        tombolDonat.setLayoutY(106);
        tombolDonat.setPrefSize(110, 30);
        tombolDonat.getStyleClass().add("transparent-button");

        Button tombolAir = new Button("Minuman");
        tombolAir.setLayoutX(287);
        tombolAir.setLayoutY(106);
        tombolAir.setPrefSize(123, 30);
        tombolAir.getStyleClass().add("transparent-button");

        mainPane.getChildren().addAll(tombolAir, tombolDonat, tombolSemua);

        // Menu area
        Pane menuArea = new Pane();
        menuArea.setId("menuarea");
        menuArea.setPrefSize(929, 541);
        menuArea.setLayoutX(0);
        menuArea.setLayoutY(159);
        menuArea.setStyle("-fx-border-color: black; -fx-border-width: 2;");

        GridPane menuGrid = new GridPane();
        menuGrid.setPadding(new Insets(80));
        menuGrid.setVgap(100);
        menuGrid.setAlignment(Pos.CENTER);

        List<Food> foodList = foodController.getAllFood();
        int itemCount = foodList.size();

        double availableWidth = menuArea.getPrefWidth() - 2 * 50;
        int columns = (int) Math.floor(availableWidth / 150);
        double horizontalGap = (availableWidth - columns * 150 + 20) / (columns + 1);

        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            Pane menuItemImage = createMenuItemButton(food);

            Button itemnameButtom = new Button(food.getName() + "\nRp. " + food.getPrice());
            itemnameButtom.setAlignment(Pos.CENTER);
            itemnameButtom.setPrefWidth(100);

            VBox menuItemBox = new VBox(menuItemImage, itemnameButtom);
            menuItemBox.setAlignment(Pos.CENTER);

            int col = i % columns;
            int row = i / columns;
            menuGrid.add(menuItemBox, col, row);

            itemnameButtom.setOnAction(event -> {
                Food selectedFood = foodList.get(col + row * columns);
                boolean alreadyInTable = false;
                for (Food item : tablePesanan.getItems()) {
                    if (item.getId() == selectedFood.getId()) {
                        item.setQuantity(item.getQuantity() + 1);
                        alreadyInTable = true;
                        break;
                    }
                }
                if (!alreadyInTable) {
                    selectedFood.setQuantity(selectedFood.getQuantity() + 1);
                    tablePesanan.getItems().add(selectedFood);
                }
                totalHarga.setText("TOTAL = Rp. " + calculateTotalPrice(tablePesanan.getItems()));
            });
        }

        menuGrid.setHgap(horizontalGap);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(menuGrid);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(929, 541);

        menuArea.getChildren().add(scrollPane);
        mainPane.getChildren().add(menuArea);

        // Order area
        Button lanjut = new Button("Lanjut =>");
        lanjut.setId("lanjut");
        lanjut.setLayoutX(1115);
        lanjut.setLayoutY(609);
        lanjut.setPrefSize(150, 56);

        Button kembali = new Button("<- Kembali");
        kembali.setId("lanjut");
        kembali.setLayoutX(950);
        kembali.setLayoutY(609);
        kembali.setPrefSize(150, 56);
        kembali.setOnAction(event -> App.getPrimaryStage().setScene(previousScene));

        Label namaOrder = new Label("Order : ");
        namaOrder.setId("order");
        namaOrder.setLayoutX(950);
        namaOrder.setLayoutY(100);
        namaOrder.setPrefSize(72, 30);

        TextField nama = new TextField();
        nama.setLayoutX(999);
        nama.setLayoutY(95);
        nama.setPrefSize(266, 40);

        totalHarga = new Label("TOTAL = Rp. 0.0");
        totalHarga.setId("totalharga");
        totalHarga.setLayoutX(950);
        totalHarga.setLayoutY(545);
        totalHarga.setPrefSize(315, 30);
        totalHarga.setAlignment(Pos.CENTER);

        tablePesanan = new TableView<>();
        tablePesanan.setMinWidth(315);
        tablePesanan.setMaxWidth(315);
        tablePesanan.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablePesanan.setLayoutX(950);
        tablePesanan.setLayoutY(160);
        tablePesanan.setPrefSize(315, 357);

        TableColumn<Food, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setPrefWidth(130);

        TableColumn<Food, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        quantityColumn.setPrefWidth(70);

        TableColumn<Food, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> {
            Food food = cellData.getValue();
            double totalPrice = food.getPrice() * food.getQuantity();
            return new SimpleDoubleProperty(totalPrice).asObject();
        });
        priceColumn.setPrefWidth(100);

        TableColumn<Food, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setPrefWidth(100);
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button addButton = new Button("+");
            private final Button subtractButton = new Button("-");

            {
                addButton.setOnAction(event -> {
                    Food food = getTableView().getItems().get(getIndex());
                    food.setQuantity(food.getQuantity() + 1);
                    totalHarga.setText("TOTAL = Rp. " + calculateTotalPrice(tablePesanan.getItems()));
                });

                subtractButton.setOnAction(event -> {
                    Food food = getTableView().getItems().get(getIndex());
                    if (food.getQuantity() > 0) {
                        food.setQuantity(food.getQuantity() - 1);
                        totalHarga.setText("TOTAL = Rp. " + calculateTotalPrice(tablePesanan.getItems()));
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(5);
                    buttons.getChildren().addAll(addButton, subtractButton);
                    setGraphic(buttons);
                }
            }
        });

        tablePesanan.getColumns().addAll(nameColumn, quantityColumn, priceColumn, actionColumn);

        lanjut.setOnAction(event -> {
            String namaPemesan = nama.getText(); // Mengambil nama dari TextField
            DetailPesanan detailPesanan = new DetailPesanan(App.getPrimaryStage(), previousScene, namaPemesan, tablePesanan, new OrderController()); // Mengirimkan nama dan daftar pesanan ke DetailPesanan
            Scene detailPesananScene = detailPesanan.tampilkanDetailPesanan(); // Mendapatkan scene dari DetailPesanan
            App.getPrimaryStage().setScene(detailPesananScene); // Menampilkan scene DetailPesanan
        });

        mainPane.getChildren().addAll(lanjut, kembali, nama, namaOrder, totalHarga, tablePesanan);

        return mainPane;
    }

    private Pane createMenuItemButton(Food food) {
        Pane menuItemButton = new Pane();
        menuItemButton.setPrefSize(150, 150);
        menuItemButton.getStyleClass().add("menu-item-button");

        ImageView imageView = new ImageView(new Image("file:" + food.getImagePath()));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setLayoutX(25);
        imageView.setLayoutY(25);

        menuItemButton.getChildren().add(imageView);

        return menuItemButton;
    }

    private double calculateTotalPrice(ObservableList<Food> orderItems) {
        return orderItems.stream().mapToDouble(food -> food.getPrice() * food.getQuantity()).sum();
    }
}

