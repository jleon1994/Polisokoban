package escenas;

import componentes.BackgroundSprites;
import componentes.Tipografia;
import engine.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import motor.Juego;

public class PaneJuego {
	// **PARAMETRO DE LA CLASE SCENE (VENTANA)
	final Stage primarystage = new Stage();
	final Juego juego = new Juego();// *METODOS Y PROPIEDADES DE JUEGO
	// *BACKGROUNDS DE LOS OBJETOS QUE TENDREMOS EN EL JUEGO(IMAGENES DEL JUEGO)
	final Background cespedBackground = BackgroundSprites.spriteBackground(new Image("/sprites/FondoCesped.jpg"));
	final Background cajaBackground = BackgroundSprites.spriteBackground(new Image("Caja.png"));
	final Background bloqueBackground = BackgroundSprites.spriteBackground(new Image("Bloque.png"));
	final Background jugadorBackground = BackgroundSprites.spriteBackground(new Image("man.png"));
	// **CREAMOS UN ATRIBUTO PARA UN CONTENEDOR STACKPANE SOLO PARA EL JUGADOR
	final StackPane jugadorSprite = crearJugadorSprite();
	
	
	
	//**NUEVOS OBJETOS
	//**CREA CONTENEDOR QUE APILA LOS NODOS BIDIMENSAIONALMENTE EN FILAS Y COLUMNAS
	GridPane boardGrid = new GridPane();
	StackPane canvasStack = new StackPane();
	//**CREA TEXTO QUE ESTARA DENTRO DEL CONTENDOR DE LA CLASE TIPOGRAFIA
	Tipografia nivelTexto = new Tipografia("Nivel", 50, Color.WHITE);
	Tipografia tiempoTexto = new Tipografia("00:00", 50, Color.WHITE);
	Tipografia previaTiempoTexto = new Tipografia("", 200, Color.rgb(255, 0, 0), 800); 

	// **METODO PARA CREAR CONTENEDORES DE JUGADOR Y BACKGROUND
	private StackPane crearJugadorSprite() {
		// ** STACKPANE - CONTENEDOR - SUPERPONE UN NODOS ENCIMA DE OTRO
		// **SUPERPONER UN CONTENEDOR JUGADOR ENCIMA DEL CONTENEDOR FONDO
		var jugadorSprite = new StackPane();
		var fondo = new Pane();
		var jugador = new Pane();

		fondo.setBackground(cespedBackground);
		jugador.setBackground(jugadorBackground);

		// **ADICIONAMOS LOS NODOS (CONTENEDORES) A UN CONTENDOR DE SUPERPOCISION
		jugadorSprite.getChildren().addAll(fondo, jugador);

		return jugadorSprite;
	}
	
	PaneJuego(Stage primaryStage) {
	    this.primarystage = primaryStage;
	    this.juego = new Juego();


	    nivelTexto.setText("Level " + juego.getCurrentLevelNum());

	    var StatusBar = new BorderPane();
	    StatusBar.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, .2), null, null)));
	    StatusBar.setPadding(new Insets(15));
	    StatusBar.setMaxHeight(60);
	    StatusBar.setLeft(levelText);
	    StatusBar.setRight(timerText);

//	        boardGrid.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, .2), null, null)));

	    canvasStack.setAlignment(Pos.CENTER);
	    canvasStack.getChildren().addAll(boardGrid, previewTimerText);
	    this.setTop(StatusBar);
	    this.setCenter(canvasStack);

	    game.initGame(1);
	  }
}
