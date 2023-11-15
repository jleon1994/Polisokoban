package escenas;

import componentes.BackgroundEscena;
import componentes.Botones;
import componentes.Tipografia;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//***************//
//**--CLASE6**--//
//*************//


//**HERENCIA: SUBCLASE DE BACKGROUNDESCENA PARA EL FONDO Y ADICIONAMOS CONTENEDOR VBOX
public class ReglasJuego extends BackgroundEscena {
	
	//**METODO CONSTRUCTOR DE LA CLASE REGLASJUEGO(METODO1)
	ReglasJuego(Stage primaryStage) {
		//**VARIABLES1 DEL METODO1
		//**CREAMOS CONTENEDORES VBOX Y BOTONES
		var contenedor = new VBox(30);
		var boton = new Botones("Atras").getBoton();
       //**POSICION DEL VBOX Y BOTON DE ATRAS
		boton.setTranslateX(300);
		boton.setTranslateY(50);
		//**CLICK AL BOTON RETORNA AL MENUPRINCIPAL
		boton.setOnAction(e -> {
			primaryStage.getScene().setRoot(new MenuPrincipal(primaryStage).getContenedor());
		});

		//**VARIABLES2 DEL METODO1
	   //**CREAMOS CONTENEDORES VBOX Y TEXTO DE LA CLASE TEXTO
		var objetivoContenedor = new VBox(10);
		//**POSICIONAMOS EL CONTENEDOR VBOX
		objetivoContenedor.setTranslateX(140);
		objetivoContenedor.setTranslateY(80);
		//**POCISIONAMOS EL TITULO DEL TEXTO
		var objetivo = new Tipografia("OBJETIVO", 30, Color.WHITE, 400);
		objetivo.setTranslateX(200);
		objetivo.setLayoutY(80);
		var contenidoObjetivo = new Tipografia(
				"Tu objetivo en el juego para ganar POLISOKOBAN \n"
						+ "es mover los cuadros en la pantalla y ponerlos \n"
						+ "de la manera en que se muestra antes de iniciar \n"
						+ "cada nivel. Tienes un limite de tiempo para cada \n"
						+ "nivel. Tu logica hara que acabes la tarea antes \n" + "de terminar el tiempo!",
				20, Color.BLACK, 100);
		objetivoContenedor.getChildren().addAll(objetivo, contenidoObjetivo);
		
		
		//**VARIABLES3 DEL METODO1
		//**CREAMOS CONTENEDORES VBOX Y TEXTO DE LA CLASE TEXTO
		var contenedorControles = new VBox(10);
		//**POSICIONAMOS EL VBOX
		contenedorControles.setTranslateX(140);
		contenedorControles.setTranslateY(85);
		var controles = new Tipografia("CONTROLES", 30, Color.WHITE, 400);
		//**POSICIONAMOS EL TITULO
		controles.setTranslateX(200);
		controles.setLayoutY(85);
		var contenidoControles = new Tipografia(
				"Utiliza las teclas de fecla para poder moverte o:\n\n" + "w - moverse hacia arriba\n"
						+ "s - moverse hacia abajo\n" + "a - moverse a la izquierda\n" + "d - moverse a la derecha\n"
						+ "e - ver el tablero objetivo\n\n" + "Para empujar o tirar de un cuadro, \n"
						+ "mantenga presionada la tecla MAYÚS \n" + "y use los controles",
				20, Color.BLACK, 400);

		contenedorControles.getChildren().addAll(controles, contenidoControles);
		contenedor.getChildren().addAll(boton, objetivoContenedor, contenedorControles);
		//**POR HERENCIA DAMOS COMO CONTENEDOR CONTENEDOR
	}
}
