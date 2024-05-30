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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Optional;

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
    private Label customerNameLabel;
    private Label totalPriceLabel;
    private TextArea orderDetailsTextArea;
    private Food selectedFood; // To store the selected food item for editing

    public AdminScreen(App app) {
        this.app = app;
        createAdminScene();
        loadFoods();
        loadOrders();
        orderTable = new TableView<>();
    }

    private void createAdminScene() {
        Pane mainPane = createAdminPane();
        scene = new Scene(mainPane, 1280, 700);
        String css = this.getClass().getResource("/css/Style.css").toExternalForm();
        String scc = this.getClass().getResource("/css/AdminStyle.css").toExternalForm();
        scene.getStylesheets().addAll(css, scc);
    }

    @SuppressWarnings("unchecked")
    private Pane createAdminPane() {
        Label nameLabel = new Label("Nama Makanan: ");
        nameLabel.setLayoutX(51);
        nameLabel.setLayoutY(25);
        nameLabel.setPrefSize(88, 15);
        TextField nameField = new TextField();
        nameField.setId("siku");
        nameField.setLayoutX(51);
        nameField.setLayoutY(46);
        nameField.setPrefSize(172, 24);
        Label priceLabel = new Label("Harga: ");
        priceLabel.setLayoutX(51);
        priceLabel.setLayoutY(85);
        priceLabel.setPrefSize(41, 15);
        TextField priceField = new TextField();
        priceField.setId("siku");
        priceField.setLayoutX(51);
        priceField.setLayoutY(106);
        priceField.setPrefSize(172, 24);
        Button addButton = new Button("Add Food");
        addButton.setId("siku");
        addButton.setLayoutX(200);
        addButton.setLayoutY(364);
        addButton.setPrefSize(57, 17);

        Button kali = new Button("X");
        kali.setId("kali");
        kali.setLayoutX(240);
        kali.setLayoutY(2);
        kali.setPrefSize(15, 15);
        kali.getStyleClass().add("transparent-button-kali");

        Pane tampilkanGambar = new Pane();
        tampilkanGambar.setId("tampilkangambar");
        tampilkanGambar.setId("siku");
        tampilkanGambar.setLayoutX(74);
        tampilkanGambar.setLayoutY(146);
        tampilkanGambar.setPrefSize(126, 126);

        Button tambahGambar = new Button("Select Image");
        tambahGambar.setId("siku");
        tambahGambar.setLayoutX(98);
        tambahGambar.setLayoutY(287);
        tambahGambar.setPrefSize(78, 20);
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
        paneSamping.setLayoutX(42);
        paneSamping.setLayoutY(37);
        paneSamping.setPrefSize(274, 398);
        paneSamping.setId("layartambah");
        paneSamping.setVisible(false);

        Button kembali = new Button("<- Kembali");
        kembali.setId("balik");
        kembali.setLayoutX(28);
        kembali.setLayoutY(624);
        kembali.setPrefSize(83, 33);
        kembali.setOnAction(event -> app.tampilkanSceneUtama());

        Button tambah = new Button("Tambah Menu");
        tambah.setId("tambah");
        tambah.setLayoutX(78);
        tambah.setLayoutY(460);
        tambah.setPrefSize(205, 47);
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
                if (imagePath != null && !imagePath.isEmpty()) { // Ensure imagePath is not null or empty
                    if (selectedFood != null) { // If editing
                        selectedFood.setName(name);
                        selectedFood.setPrice(price);
                        selectedFood.setImagePath(imagePath);
                        foodDAO.updateFood(selectedFood);
                        foodTable.refresh();
                        selectedFood = null; // Clear selection
                    } else { // If adding new food
                        Food newFood = foodDAO.addFood(name, price, imagePath);
                        if (newFood != null) {
                            foodList.add(newFood);
                        } else {
                            showAlert("Database Error", "Failed to add food to the database.");
                        }
                    }
                    nameField.clear();
                    priceField.clear();
                    tampilkanGambar.getChildren().clear();
                    imagePath = null;
                    paneSamping.setVisible(false);
                    tambah.setVisible(true);
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
            Image image = new Image("file:" + cellData.getValue().getImagePath());
            return new SimpleObjectProperty<>(image);
        });

        imageColumn.setCellFactory(col -> new TableCell<Food, Image>() {
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
        });

        TableColumn<Food, Void> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(col -> new TableCell<>() {
            final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(event -> {
                    Food food = getTableView().getItems().get(getIndex());
                    showEditFoodPane(food, nameField, priceField, tampilkanGambar);
                    paneSamping.setVisible(true);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        TableColumn<Food, Void> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellFactory(col -> new TableCell<>() {
            final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Food food = getTableView().getItems().get(getIndex());
                    showDeleteConfirmationDialog(food);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        foodTable.getColumns().addAll(idColumn, nameColumn, priceColumn, imageColumn, editColumn, deleteColumn);
        foodTable.setItems(foodList);

        // Set column widths to divide the pane equally
        foodTable.getColumns().forEach(column -> column.prefWidthProperty().bind(foodTable.widthProperty().divide(6)));

        // Use StackPane to ensure automatic resizing
        StackPane tabelFoodsPane = new StackPane(foodTable);
        tabelFoodsPane.setLayoutX(365);
        tabelFoodsPane.setLayoutY(37);
        tabelFoodsPane.setPrefSize(859, 297);

        foodTable.prefWidthProperty().bind(tabelFoodsPane.widthProperty());
        foodTable.prefHeightProperty().bind(tabelFoodsPane.heightProperty());

        // TableView for Orders
        orderTable = new TableView<>();
        TableColumn<Order, Integer> orderIdColumn = new TableColumn<>("Order ID");
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id")); // Use PropertyValueFactory with field name

        TableColumn<Order, String> customerNameColumn = new TableColumn<>("Customer Name");
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName")); // Use PropertyValueFactory with field name

        TableColumn<Order, Double> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice")); // Use PropertyValueFactory with field name

        TableColumn<Order, Void> detailColumn = new TableColumn<>("Detail");
        detailColumn.setCellFactory(col -> new TableCell<>() {
            final Button detailButton = new Button("Detail");

            {
                detailButton.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    showOrderDetail(order);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(detailButton);
                }
            }
        });

        orderTable.getColumns().addAll(orderIdColumn, customerNameColumn, totalPriceColumn, detailColumn);
        orderTable.setItems(orderList);

        // Set column widths to divide the pane equally
        orderTable.getColumns().forEach(column -> column.prefWidthProperty().bind(orderTable.widthProperty().divide(4)));

        // Use StackPane to ensure automatic resizing
        StackPane tabelOrdersPane = new StackPane(orderTable);
        tabelOrdersPane.setLayoutX(369);
        tabelOrdersPane.setLayoutY(370);
        tabelOrdersPane.setPrefSize(447, 271);

        orderTable.prefWidthProperty().bind(tabelOrdersPane.widthProperty());
        orderTable.prefHeightProperty().bind(tabelOrdersPane.heightProperty());

        // Pane for displaying order details
        customerNameLabel = new Label();
        customerNameLabel.setLayoutX(10);
        customerNameLabel.setLayoutY(10);
        totalPriceLabel = new Label();
        totalPriceLabel.setLayoutX(10);
        totalPriceLabel.setLayoutY(40);
        orderDetailsTextArea = new TextArea();
        orderDetailsTextArea.setLayoutX(10);
        orderDetailsTextArea.setLayoutY(70);
        orderDetailsTextArea.setPrefSize(346, 191);
        orderDetailsTextArea.setEditable(false);

        Pane infoPesanan = new Pane();
        infoPesanan.setId("infopesanan");
        infoPesanan.setLayoutX(858);
        infoPesanan.setLayoutY(370);
        infoPesanan.setPrefSize(366, 271);
        infoPesanan.getChildren().addAll(customerNameLabel, totalPriceLabel, orderDetailsTextArea);

        Pane pane = new Pane(paneSamping, kembali, tambah, tabelFoodsPane, tabelOrdersPane, infoPesanan);
        pane.setId("paneadminutama");
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

    private void showOrderDetail(Order order) {
        customerNameLabel.setText("Customer Name: " + order.getCustomerName());
        totalPriceLabel.setText("Total Price: Rp. " + order.getTotalPrice());
        orderDetailsTextArea.setText(order.getOrderDetailsAsString());
    }

    private void showDeleteConfirmationDialog(Food food) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Are you sure you want to delete this item?");
        alert.setContentText("Item: " + food.getName());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (foodDAO.deleteFood(food)) {
                foodList.remove(food);
            } else {
                showAlert("Database Error", "Failed to delete food from the database.");
            }
        }
    }

    private void showEditFoodPane(Food food, TextField nameField, TextField priceField, Pane tampilkanGambar) {
        selectedFood = food;
        nameField.setText(food.getName());
        priceField.setText(String.valueOf(food.getPrice()));

        Image image = new Image("file:" + food.getImagePath());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(176);
        imageView.setFitHeight(170);
        tampilkanGambar.getChildren().clear();
        tampilkanGambar.getChildren().add(imageView);
        imagePath = food.getImagePath();
    }

    public Scene getScene() {
        return scene;
    }
}
