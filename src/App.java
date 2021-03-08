

import javafx.application.Application;
/*import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;*/
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        ContenedorPrincipal contenido = new ContenedorPrincipal();
        ScrollPane root = new ScrollPane();
        root.setContent(contenido);
        root.setFitToHeight(true);
        root.setFitToWidth(true);

        Scene escena = new Scene(root, 800, 600);
        primaryStage.setTitle("programita paint :)(:");
        primaryStage.setScene(escena);
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
