package application;

import escenas.MenuPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		primaryStage.setScene(new Scene(new MenuPrincipal(primaryStage).getContenedor(), 800, 800));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
		
	}
}
