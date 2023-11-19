package application;

import escenas.MenuPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//***************//
//**--CLASE1**--//
//**************//

//**CLASE PRINCIPAL MAIN LA CUAL HEREDA LA CLASE APLICATION DE JAVAFX
//**CLASE APLICACION SE USA PARA INICIAR Y CONTROLAR UNA APLICACION EN JAVAFX
public class Main extends Application {

	// **METODO: CONFIGURACION PARA MOSTRAR LA INTERFAZ GRAFICA (METODO1)
	public void start(Stage primaryStage) {

		// **CONFIGURACION DE LA ESCENA Y ESCENARIO(UTILIZA LA CLASE MENUPRINCIPAL
		// (CLASE2))
		primaryStage.setScene(new Scene(new MenuPrincipal(primaryStage).getContenedor(), 800, 800));
		primaryStage.show();

	}

//**METODO MAIN: ESTE METODO LLAMA AL METODO LAUNCH LA CUAL INICIA LA APLICACION
	public static void main(String[] args) {
		launch(args);

	}
}
