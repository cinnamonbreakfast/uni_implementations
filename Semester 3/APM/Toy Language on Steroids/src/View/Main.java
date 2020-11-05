package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.html.ListView;

public class Main extends Application {

    private Scene scene;
    private ListView lv;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //try {
        Parent root = FXMLLoader.load(getClass().getResource("MainWin.fxml"));

        primaryStage.setTitle("Programs");
        scene = new Scene(root, 300, 275, Color.DARKGRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
//        } catch (Exception e)
//        {
//            System.out.println("pula");
//            System.out.println(Arrays.toString(e.getStackTrace()));
//        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
