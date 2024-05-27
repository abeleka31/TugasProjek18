package eazysorder.view;

import com.google.common.collect.Table;

import eazysorder.App;
import eazysorder.controller.FoodController;
import eazysorder.controller.OrderController;
import eazysorder.model.Food;
import eazysorder.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AdminScreen {
    private App app;
    private Scene scene;
    private FoodController foodDAO = new FoodController();
    private OrderController orderDAO = new OrderController();
    private ObservableList<Food> foodList = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private TableView<Food> foodTable;
    private TableView<Order> orderTable;

    public AdminScreen(App app) {
        this.app = app;
        createAdminScene();
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
        addButton.setPrefSize(59, 28);
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            foodDAO.addFood(name, price);
            loadFoods();
        });

        Pane paneSamping = new Pane( nameLabel, nameField, priceLabel, priceField, addButton);
        paneSamping.setLayoutX(20);
        paneSamping.setLayoutY(29);
        paneSamping.setPrefSize(275, 582);
        paneSamping.setId("layartambah");
        Button kembali = new Button("<- Kembali");
        kembali.setId("balik");
        kembali.setLayoutX(40);
        kembali.setLayoutY(623);
        kembali.setPrefSize(124, 47);
        kembali.setOnAction(event -> {
            app.tampilkanSceneUtama();
        });
        Button tambah = new Button("Tambah menu");
        tambah.setId("tambah");
        tambah.setLayoutX(171);
        tambah.setLayoutY(623);
        tambah.setPrefSize(124, 47);
        tambah.setOnAction(event -> {
            paneSamping.setVisible(true);
        });
        Pane pane = new Pane(paneSamping, kembali, tambah);
        return pane;



        // foodTable = new TableView<>();
        // TableColumn<Food, Integer> idColumn = new TableColumn<>("ID");
        // idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        // TableColumn<Food, String> nameColumn = new TableColumn<>("Name");
        // nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        // TableColumn<Food, Double> priceColumn = new TableColumn<>("Price");
        // priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        // foodTable.getColumns().addAll(idColumn, nameColumn, priceColumn);
        // foodTable.setItems(foodList);


    }

    

    
    private void loadFoods() {
        foodList.setAll(foodDAO.getAllFood());
    }

    private void loadOrders() {
        orderList.setAll(orderDAO.getAllOrders());
    }


    public Scene getScene() {
        return scene;
    }
}
