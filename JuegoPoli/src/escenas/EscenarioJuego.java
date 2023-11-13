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

		// **UTILIZAMOS UN SWITCH PARA DEFINIR QUE PASA CUANDO OPRIMIMOS LA TECLAS DE
		// CADA CASO
		escena.setOnKeyPressed(event -> {
			switch (event.getCode()) {
			case W, UP -> paneJuego.ejecutaComandoUsuario(event.isShiftDown() ? 'W' : 'w');
			case S, DOWN -> paneJuego.ejecutaComandoUsuario(event.isShiftDown() ? 'S' : 's');
			case A, LEFT -> paneJuego.ejecutaComandoUsuario(event.isShiftDown() ? 'A' : 'a');
			case D, RIGHT -> paneJuego.ejecutaComandoUsuario(event.isShiftDown() ? 'D' : 'd');
			case E -> paneJuego.ejecutaComandoUsuario('e');
			}
		});

		escena.setOnKeyReleased(event -> {
			switch (event.getCode()) {
			case E -> paneJuego.ejecutaComandoUsuario('E');
			}
		});

		return escena;

	}
}
