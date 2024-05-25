package eazysorder.view;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Menu1 {
    private Scene scene;
    
    public Scene tampilkanMenu1(){
        Button lanjut = new Button("Lanjut ->");
        Button kembali = new Button("<- Kembali");
        
        Pane pane = new Pane();
        pane.getChildren().addAll(lanjut, kembali);
        VBox root = new VBox(pane);

        scene = new Scene(root, 1280, 700);
        return scene;
    
        
    }  
}
