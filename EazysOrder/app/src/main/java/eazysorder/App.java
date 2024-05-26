package eazysorder;

<<<<<<< HEAD
import eazysorder.view.AdminScreen;
=======
>>>>>>> f48e2078cc0638222f2d24d94dbad635278daf87
import eazysorder.view.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;

<<<<<<< HEAD
/**
 * App
 */
=======
>>>>>>> f48e2078cc0638222f2d24d94dbad635278daf87
public class App extends Application {

    private Stage primaryStage;

    @Override
<<<<<<< HEAD
    public void start(Stage primaryStage)  {
=======
    public void start(Stage primaryStage) {
>>>>>>> f48e2078cc0638222f2d24d94dbad635278daf87
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Eazys Order");
        tampilkanSceneUtama();
    }

<<<<<<< HEAD
    public void tampilkanSceneUtama(){
=======
    public void tampilkanSceneUtama() {
>>>>>>> f48e2078cc0638222f2d24d94dbad635278daf87
        MainScene mainScene = new MainScene(this);
        primaryStage.setScene(mainScene.getScene());
        primaryStage.show();
    }

<<<<<<< HEAD
    public void tampilkanAdminScene(){
        AdminScreen adminScreen = new AdminScreen(this);
        primaryStage.setScene(adminScreen.getScene());
        primaryStage.show();
    }
    
=======
>>>>>>> f48e2078cc0638222f2d24d94dbad635278daf87
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
