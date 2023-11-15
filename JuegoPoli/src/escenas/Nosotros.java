package escenas;

import componentes.BackgroundEscena;
import componentes.Botones;
import componentes.Tipografia;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Nosotros extends BackgroundEscena {
	Nosotros(Stage primaryStage) {
		var container = new VBox(30);
		var backBtn = new Botones("Atras").getBoton();

		backBtn.setTranslateX(300);
		backBtn.setTranslateY(50);
		backBtn.setOnAction(e -> {
			primaryStage.getScene().setRoot(new MenuPrincipal(primaryStage).getContenedor());
		});

		var TaskContainer = new VBox(10);
		TaskContainer.setTranslateX(140);
		TaskContainer.setTranslateY(80);
		var Task = new Tipografia("OBJETIVO", 30, Color.WHITE, 400);
		Task.setTranslateX(200);
		Task.setLayoutY(80);
		var TaskContent = new Tipografia(
				"Tu objetivo en el juego para ganar POLISOKOBAN \n"
						+ "es mover los cuadros en la pantalla y ponerlos \n"
						+ "de la manera en que se muestra antes de iniciar \n"
						+ "cada nivel. Tienes un limite de tiempo para cada \n"
						+ "nivel. Tu logica hara que acabes la tarea antes \n" + "de terminar el tiempo!",
				20, Color.BLACK, 100);
		TaskContainer.getChildren().addAll(Task, TaskContent);

		var ControlsContainer = new VBox(10);
		ControlsContainer.setTranslateX(140);
		ControlsContainer.setTranslateY(85);
		var Controls = new Tipografia("CONTROLES", 30, Color.WHITE, 400);
		Controls.setTranslateX(200);
		Controls.setLayoutY(85);
		var ControlsContent = new Tipografia(
				"Utiliza las teclas de fecla para poder moverte o:\n\n" + "w - moverse hacia arriba\n"
						+ "s - moverse hacia abajo\n" + "a - moverse a la izquierda\n" + "d - moverse a la derecha\n"
						+ "e - ver el tablero objetivo\n\n" + "Para empujar o tirar de un cuadro, \n"
						+ "mantenga presionada la tecla MAYÚS \n" + "y use los controles",
				20, Color.BLACK, 400);

		ControlsContainer.getChildren().addAll(Controls, ControlsContent);

		container.getChildren().addAll(backBtn, TaskContainer, ControlsContainer);

		this.setCenter(container);
	}
}
