package escenas;

import javafx.scene.Scene;
import javafx.stage.Stage;

//***************//
//**--CLASE5**--//
//**************//

public class EscenarioJuego {

//**METODO GET PARA RETORNO DE LA ACCION DE LA ESCENA (UTILIZAMOS CLASE SCENE JAVAFX) (METODO1)
	public static Scene getEscena(Stage primaryStage) {

		// **INSTANCIA: OBJETO PANEJUEGO (CLASE8)
		var paneJuego = new PaneJuego(primaryStage);
		var escena = new Scene(paneJuego, 800, 860);

		escena.setOnKeyPressed(event -> {
			switch (event.getCode()) {
			case W, UP -> paneJuego.executeUserCommand(event.isShiftDown() ? 'W' : 'w');
			case S, DOWN -> paneJuego.executeUserCommand(event.isShiftDown() ? 'S' : 's');
			case A, LEFT -> paneJuego.executeUserCommand(event.isShiftDown() ? 'A' : 'a');
			case D, RIGHT -> paneJuego.executeUserCommand(event.isShiftDown() ? 'D' : 'd');
			case E -> paneJuego.executeUserCommand('e');
			}
		});

		escena.setOnKeyReleased(event -> {
			switch (event.getCode()) {
			case E -> paneJuego.executeUserCommand('E');
			}
		});

		return escena;

	}
}
