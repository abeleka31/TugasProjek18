package eazysorder.view;

import eazysorder.App;
import eazysorder.controller.FoodController;
import eazysorder.controller.OrderController;
import eazysorder.model.Food;
import eazysorder.model.Order;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

public class AdminScreen {
    private App app;
    private Scene scene;
    private FoodController foodDAO = new FoodController();
    private OrderController orderDAO = new OrderController();
    private ObservableList<Food> foodList = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private TableView<Food> foodTable;
    private TableView<Order> orderTable;
    private String imagePath; // To store the path of the selected image

    public AdminScreen(App app) {
        this.app = app;
        createAdminScene();
        loadFoods();
        loadOrders();
    }

    private void createAdminScene() {
        Pane mainPane = createAdminPane();
        scene = new Scene(mainPane, 1280, 700);
        String css = this.getClass().getResource("/css/Style.css").toExternalForm();
        scene.getStylesheets().add(css);
    }

    private Pane createAdminPane() {
        Label nameLabel = new Label("Food Name: ");
        nameLabel.setLayoutX(17);
        nameLabel.setLayoutY(21);
        nameLabel.setPrefSize(88, 15);
        TextField nameField = new TextField();
        nameField.setLayoutX(17);
        nameField.setLayoutY(45);
        nameField.setPrefSize(230, 35);
        Label priceLabel = new Label("Price: ");
        priceLabel.setLayoutX(17);
        priceLabel.setLayoutY(92);
        priceLabel.setPrefSize(41, 15);
        TextField priceField = new TextField();
        priceField.setLayoutX(17);
        priceField.setLayoutY(111);
        priceField.setPrefSize(230, 35);
        Button addButton = new Button("Add Food");
        addButton.setLayoutX(108);
        addButton.setLayoutY(454);
        addButton.setPrefSize(100, 35);

        Button kali = new Button("X");
        kali.setId("kali");
        kali.setLayoutX(240);
        kali.setLayoutY(2);
        kali.setPrefSize(15, 15);
        kali.getStyleClass().add("transparent-button-kali");

        Pane tampilkanGambar = new Pane();
        tampilkanGambar.setId("tampilkangambar");
        tampilkanGambar.setLayoutX(17);
        tampilkanGambar.setLayoutY(165);
        tampilkanGambar.setPrefSize(176, 170);

        Button tambahGambar = new Button("Select Image");
        tambahGambar.setLayoutX(205);
        tambahGambar.setLayoutY(165);
        tambahGambar.setPrefSize(100, 20);
        tambahGambar.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                imagePath = selectedFile.getAbsolutePath();
                Image image = new Image(selectedFile.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(176);
                imageView.setFitHeight(170);
                tampilkanGambar.getChildren().clear();
                tampilkanGambar.getChildren().add(imageView);
            }
        });

        Pane paneSamping = new Pane(nameLabel, nameField, priceLabel, priceField, addButton, kali, tambahGambar, tampilkanGambar);
        paneSamping.setLayoutX(20);
        paneSamping.setLayoutY(29);
        paneSamping.setPrefSize(275, 582);
        paneSamping.setId("layartambah");
        paneSamping.setVisible(false);

        Button kembali = new Button("<- Kembali");
        kembali.setId("balik");
        kembali.setLayoutX(40);
        kembali.setLayoutY(623);
        kembali.setPrefSize(124, 47);
        kembali.setOnAction(event -> app.tampilkanSceneUtama());

        Button tambah = new Button("+");
        tambah.setId("tambah");
        tambah.setLayoutX(171);
        tambah.setLayoutY(623);
        tambah.setPrefSize(124, 47);
        tambah.setVisible(true);
        tambah.setOnAction(event -> {
            paneSamping.setVisible(true);
            tambah.setVisible(false);
        });
        kali.setOnAction(event -> {
            paneSamping.setVisible(false);
            tambah.setVisible(true);
        });

        addButton.setOnAction(e -> {
            String name = nameField.getText();
            try {
                double price = Double.parseDouble(priceField.getText());
                if (imagePath != null && !imagePath.isEmpty()) { // Pastikan imagePath tidak null atau kosong
                    Food newFood = foodDAO.addFood(name, price, imagePath);
                    if (newFood != null) {
                        foodList.add(newFood);
                        nameField.clear();
                        priceField.clear();
                        tampilkanGambar.getChildren().clear();
                        imagePath = null;
                        paneSamping.setVisible(false);
                        tambah.setVisible(true);
                    } else {
                        showAlert("Database Error", "Failed to add food to the database.");
                    }
                } else {
                    showAlert("Input Error", "Please select an image.");
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid input", "Please enter a valid price.");
            }
        });
        

        // TableView for Foods
        foodTable = new TableView<>();
        TableColumn<Food, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<Food, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Food, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        TableColumn<Food, Image> imageColumn = new TableColumn<>("Image");
        imageColumn.setCellValueFactory(cellData -> {
            // Menampilkan gambar menggunakan ImageView
            Image image = new Image("file:" + cellData.getValue().getImagePath());
            return new SimpleObjectProperty<>(image);
        });

        imageColumn.setCellFactory(col -> {
            TableCell<Food, Image> cell = new TableCell<Food, Image>() {
                @Override
                public void updateItem(Image item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        ImageView imageView = new ImageView(item);
                        imageView.setFitWidth(50);
                        imageView.setFitHeight(50);
                        setGraphic(imageView);
                    }
                }
            };
            return cell;
        });

        foodTable.getColumns().addAll(idColumn, nameColumn, priceColumn, imageColumn);
        foodTable.setItems(foodList);


        
        Pane tabelnya = new Pane();
        tabelnya.getChildren().add(foodTable);
        tabelnya.setId("tabelnya");
        tabelnya.setLayoutX(365);
        tabelnya.setLayoutY(0);
        tabelnya.setPrefSize(915, 700);
        
        Pane pane = new Pane(paneSamping, kembali, tambah, tabelnya);
        return pane;
        }
        
        private void loadFoods() {
        foodList.setAll(foodDAO.getAllFood());
        }
        
        private void loadOrders() {
        orderList.setAll(orderDAO.getAllOrders());
        }
        
        private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
        }
        
        public Scene getScene() {
        return scene;
        
    }
}