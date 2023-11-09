package escenas;

import componentes.BackgroundEscena;
import componentes.Botones;
import componentes.Tipografia;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Nosotros extends BackgroundEscena {
	Nosotros(Stage primaryStage) {
		var container = new VBox(30);
		var backBtn = new Botones("< Go Back");

		backBtn.setOnAction(e -> {
			primaryStage.getScene().setRoot(new MenuPrincipal(primaryStage));
		});

		var TaskContainer = new VBox(10);
		var Task = new Tipografia("TASK", 30, Color.WHITE, 400);
		var TaskContent = new Tipografia(
				"Your task in the game is to move the boxes on the screen and\n"
						+ "put them in the way that is show before each level.\n"
						+ "There is a time limit for each level.\n" + "You should finish the task before time is over!",
				20, Color.BLACK, 400);
		TaskContent.setTextAlignment(TextAlignment.CENTER);
		TaskContainer.getChildren().addAll(Task, TaskContent);
		TaskContainer.setAlignment(Pos.CENTER);

		var ControlsContainer = new VBox(10);
		var Controls = new Tipografia("CONTROLS", 30, Color.WHITE, 400);
		var ControlsContent = new Tipografia("Use ARROW KEYS to move around or:\n\n" + "w - move up\n"
				+ "s - move left\n" + "a - move down\n" + "d - move right\n" + "e - see target board\n\n"
				+ "To push or pull a box hold SHIFT key and use controls.", 20, Color.BLACK, 400);
		ControlsContainer.getChildren().addAll(Controls, ControlsContent);
		ControlsContainer.setAlignment(Pos.CENTER);

		container.getChildren().addAll(backBtn, TaskContainer, ControlsContainer);
		container.setAlignment(Pos.CENTER);
		this.setCenter(container);
	}
}
