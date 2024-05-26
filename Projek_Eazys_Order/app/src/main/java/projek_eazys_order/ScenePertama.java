package projek_eazys_order;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScenePertama {
    private App app;
    private Scene scene;

    public ScenePertama(App app) {
        this.app = app;
        initScene();
    }

    private void initScene() {
        Pane layout = new Pane();
        Image newImage1 = new Image(getClass().getResource("/pic/gambarcewek.png").toExternalForm());
        ImageView imageView1 = new ImageView(newImage1);
        imageView1.setFitWidth(658);
        imageView1.setFitHeight(658);
        imageView1.setLayoutX(0);
        imageView1.setLayoutY(41);
        layout.getChildren().add(imageView1);

        // Label
        Label instructionLabel1 = new Label(
                "Tekan tombol memesan\nJika ingin memesan dan\ntekan tombol admin jika ingin\nmelihat pesanan.");
        instructionLabel1.setTextAlignment(TextAlignment.CENTER);
        instructionLabel1.getStyleClass().add("label");
        instructionLabel1.setPrefSize(558, 235); // Mengatur ukuran label
        instructionLabel1.setLayoutX(70); // Posisi X
        instructionLabel1.setLayoutY(236); // Posisi Y
        layout.getChildren().add(instructionLabel1);

        // Buttons
        Button orderButton1 = new Button("Memesan");
        orderButton1.getStyleClass().add("button");
        orderButton1.setPrefSize(570, 210); // Mengatur ukuran tombol
        orderButton1.setLayoutX(640); // Posisi X
        orderButton1.setLayoutY(70); // Posisi Y
        orderButton1.setOnAction(event -> app.showSceneKedua());
        layout.getChildren().add(orderButton1);

        Button adminButton1 = new Button("Admin");
        adminButton1.getStyleClass().add("button");
        adminButton1.setPrefSize(570, 210); // Mengatur ukuran tombol
        adminButton1.setLayoutX(640); // Posisi X
        adminButton1.setLayoutY(420); // Posisi Y
        layout.getChildren().add(adminButton1);

        // Background Image
        Image backgroundImage = new Image(getClass().getResource("").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        layout.setBackground(new Background(background));

        // Scene
        scene = new Scene(layout, 1280, 700);

        // Load CSS
        String cssPath = getClass().getResource("/css/style.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
    }

    public Scene getScene() {
        return scene;
    }
}
