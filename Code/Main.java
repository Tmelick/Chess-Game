package Code;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
    private boolean isWhite;
    public void start(Stage primaryStage)  {


        BorderPane center = new BorderPane();
        Scene scene = new Scene(center);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess Game");
        scene.getStylesheets().add("Resources/boardStyle.css");

       //System.out.println("Would you like to be White or Black?");

        isWhite = true;
        Board board = new Board(isWhite);
        center.setCenter(board);
        primaryStage.show();


    }
}
