package projek_eazys_order;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Order Application");
        showScenePertama();
    }

    public void showScenePertama() {
        ScenePertama scenePertama = new ScenePertama(this);
        primaryStage.setScene(scenePertama.getScene());
        primaryStage.show();
    }

    public void showSceneKedua() {
        SceneKedua sceneKedua = new SceneKedua(this);
        primaryStage.setScene(sceneKedua.getScene2());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
