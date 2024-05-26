<<<<<<< HEAD
package eazysorder.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Menu1 {
    private Scene scene;
    private static final double BUTTON_HEIGHT = 150; // Button height remains constant
    private static final double V_GAP = 50;
    private static final double PADDING = 80;

    public Scene tampilkanMenu1() {
        Pane mainPane = createMainArea();

        scene = new Scene(mainPane, 1280, 700);
        String css = this.getClass().getResource("/css/Style.css").toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    private Pane createMainArea() {
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

        Pane menuArea = createMenuArea();
        Pane mainPane = new Pane();
        mainPane.setId("mainarea");
        mainPane.getChildren().addAll(menuArea, atas, tombolAir, tombolDonat, tombolSemua);

        Pane order = bagianOrderan();
        mainPane.getChildren().add(order);

        return mainPane;
    }

    private Pane createMenuArea() {
        Pane menuArea = new Pane();
        menuArea.setId("menuarea");
        menuArea.setPrefSize(929, 541); // Set preferred size for demonstration
        menuArea.setLayoutX(0); // Set x position for menuArea
        menuArea.setLayoutY(159); // Set y position for menuArea
        menuArea.setStyle("-fx-border-color: black; -fx-border-width: 2;"); // Optional: visualize the area

        GridPane menuGrid = new GridPane();
        menuGrid.setPadding(new Insets(PADDING));
        menuGrid.setVgap(V_GAP);
        menuGrid.setAlignment(Pos.CENTER);

        // Sample menu items
        String[] items = {"Donut", "Coffee", "Tea", "Sandwich", "Salad", "Juice", "Smoothie", "Cake", "Pie", "Muffin", "babi", "anj"};
        int itemCount = items.length;

        // Calculate number of columns dynamically based on the available width and button size
        double availableWidth = menuArea.getPrefWidth() - 2 * PADDING;
        int columns = (int) Math.floor(availableWidth / BUTTON_HEIGHT);

        // Calculate the horizontal gap to make the left and right margins symmetrical
        double horizontalGap = (availableWidth - columns * BUTTON_HEIGHT) / (columns + 1);

        // Add buttons and labels to the grid
        for (int i = 0; i < items.length; i++) {
            Button menuItemButton = new Button();
            menuItemButton.setPrefSize(BUTTON_HEIGHT, BUTTON_HEIGHT); // Setting size for buttons
            menuItemButton.getStyleClass().add("menu-item-button");
            
            Label itemNameLabel = new Label(items[i]);
            itemNameLabel.setAlignment(Pos.CENTER);
            itemNameLabel.setPrefWidth(BUTTON_HEIGHT);

            VBox menuItemBox = new VBox(menuItemButton, itemNameLabel);
            menuItemBox.setAlignment(Pos.CENTER);

            int col = i % columns;
            int row = i / columns;
            menuGrid.add(menuItemBox, col, row);
        }

        // Set horizontal gap for the grid
        menuGrid.setHgap(horizontalGap);

        // Wrap the GridPane in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(menuGrid);
        scrollPane.setFitToWidth(true); // Ensure the ScrollPane fits the width of its content
        scrollPane.setPrefSize(929, 541); // Set preferred size for the ScrollPane

        menuArea.getChildren().add(scrollPane);
        return menuArea;
    }

    private Pane bagianOrderan() {
        Pane pane1 = new Pane();
        pane1.setId("pane1");
        pane1.setPrefSize(325, 359);
        pane1.setLayoutX(10);
        pane1.setLayoutY(163);

        Button lanjut = new Button("Lanjut ->");
        lanjut.setLayoutX(172);
        lanjut.setLayoutY(609);
        lanjut.setPrefSize(150, 56);

        Button kembali = new Button("<- Kembali");
        kembali.setLayoutX(8);
        kembali.setLayoutY(609);
        kembali.setPrefSize(150, 56);

        Label namaOrder = new Label("Order : ");
        namaOrder.setLayoutX(14);
        namaOrder.setLayoutY(103);
        namaOrder.setPrefSize(72, 29);

        TextField nama = new TextField();
        nama.setLayoutX(113);
        nama.setLayoutY(94);
        nama.setPrefSize(209, 40);

        Label totalHarga = new Label("TOTAL = ");
        totalHarga.setLayoutY(545);
        totalHarga.setAlignment(Pos.CENTER);

        Pane order = new Pane();
        totalHarga.layoutXProperty().bind(order.widthProperty().subtract(totalHarga.widthProperty()).divide(2));
        order.setId("order");
        order.setLayoutX(929);
        order.setLayoutY(0);
        order.setPrefSize(346, 700);
        order.getChildren().addAll(pane1, lanjut, kembali, nama, namaOrder, totalHarga);
        return order;
    }
}
=======
package eazysorder.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Menu1 {
    private Scene scene;

    public Scene tampilkanMenu1() {
        Button lanjut = new Button("Lanjut ->");
        Button kembali = new Button("<- Kembali");

        Pane pane = new Pane();
        pane.getChildren().addAll(lanjut, kembali);
        VBox root = new VBox(pane);

        scene = new Scene(root, 1280, 700);
        return scene;

    }
}
>>>>>>> f48e2078cc0638222f2d24d94dbad635278daf87
