package eazysorder;

import eazysorder.view.AdminScreen;
import eazysorder.view.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * App
 */
public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage)  {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Eazys Order");
        tampilkanSceneUtama();
    }

    public void tampilkanSceneUtama(){
        MainScene mainScene = new MainScene(this);
        primaryStage.setScene(mainScene.getScene());
        primaryStage.show();
    }

    public void tampilkanAdminScene(){
        AdminScreen adminScreen = new AdminScreen(this);
        primaryStage.setScene(adminScreen.getScene());
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
