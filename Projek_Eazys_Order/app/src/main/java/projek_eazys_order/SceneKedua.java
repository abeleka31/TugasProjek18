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

public class SceneKedua {
    private App app;
    private Scene scene;

    public SceneKedua(App app) {
        this.app = app;
        initScene();
    }

    private void initScene() {
        Pane layout1 = new Pane();

        // Label
        Label instructionLabel = new Label("Pilih ingin makan dimana");
        instructionLabel.setTextAlignment(TextAlignment.CENTER);
        instructionLabel.getStyleClass().add("label");
        instructionLabel.setPrefSize(300, 100); // Mengatur ukuran label
        instructionLabel.setLayoutX(490); // Posisi X
        instructionLabel.setLayoutY(150); // Posisi Y

        // Buttons
        Button takeInButton = new Button("Take-In");
        takeInButton.getStyleClass().add("button");
        takeInButton.setPrefSize(150, 50); // Mengatur ukuran tombol
        takeInButton.setLayoutX(565); // Posisi X
        takeInButton.setLayoutY(300); // Posisi Y

        Button takeAwayButton = new Button("Take-Away");
        takeAwayButton.getStyleClass().add("button");
        takeAwayButton.setPrefSize(150, 50); // Mengatur ukuran tombol
        takeAwayButton.setLayoutX(565); // Posisi X
        takeAwayButton.setLayoutY(370); // Posisi Y

        // Layout
        layout1.getChildren().addAll(instructionLabel, takeInButton, takeAwayButton);

        // Background Image
        Image backgroundImage = new Image(getClass().getResource("").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        layout1.setBackground(new Background(background));

        // Scene
        scene = new Scene(layout1, 1280, 700);

        // Load CSS
        String cssPath = getClass().getResource("/css/style.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
    }

    public Scene getScene2() {
        return scene;
    }
}
