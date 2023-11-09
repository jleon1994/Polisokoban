package escenas;

import javafx.scene.Scene;
import javafx.stage.Stage;



public class EscenarioJuego {
	
	
//**METODO GET PARA RETORNO DE LA ACCION DE LA ESCENA (UTILIZAMOS CLASE SCENE JAVAFX)
	public static  Scene getEscena(Stage primaryStage) {
		var gamePane = new PaneJuego(primaryStage);
	    var escena = new Scene(gamePane, 800, 860);

	    escena.setOnKeyPressed(event -> {
	      switch (event.getCode()) {
	        case W, UP -> gamePane.executeUserCommand(event.isShiftDown() ? 'W' : 'w');
	        case S, DOWN -> gamePane.executeUserCommand(event.isShiftDown() ? 'S' : 's');
	        case A, LEFT -> gamePane.executeUserCommand(event.isShiftDown() ? 'A' : 'a');
	        case D, RIGHT -> gamePane.executeUserCommand(event.isShiftDown() ? 'D' : 'd');
	        case E -> gamePane.executeUserCommand('e');
	      }
	    });

	    escena.setOnKeyReleased(event -> {
	      switch (event.getCode()) {
	        case E -> gamePane.executeUserCommand('E');
	      }
	    });

	    return escena;
	

	}
}
