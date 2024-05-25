package eazysorder;

import eazysorder.view.MainScene;
import eazysorder.view.Menu1;
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
    
    public static void main(String[] args) {
        launch(args);
    }

    public Object getPrimaryStage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrimaryStage'");
    }  
}
