package escenas;

import componentes.BackgroundEscena;
import componentes.BackgroundSprites;
import componentes.Tipografia;
import motor.Juego;
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

//**PANEJUEGO ES EL CONTENEDOR QUE CONTENDRA EL JUEGO  Y SUS CONFIGURACIONES (PARTE GRAFICA)**//
public class PaneJuego extends BackgroundEscena {

	// **CREAMOS UN NUEVO OBJETO VENTANA DE LA CLASE STAGE
	final Stage primarystage;
	// **CREAMOS UN OBJETO CON METODOS Y PROPIEDADES DE LA CLASE JUEGO
	final Juego juego;
	// *BACKGROUNDS DE LOS OBJETOS QUE TENDREMOS EN EL PANE DEL JUEGO
	final Background cespedBackground = BackgroundSprites.spriteBackground(new Image("/sprites/FondoCesped.jpg"));
	final Background cajaBackground = BackgroundSprites.spriteBackground(new Image("Caja.png"));
	final Background bloqueBackground = BackgroundSprites.spriteBackground(new Image("Bloque.png"));
	final Background jugadorBackground = BackgroundSprites.spriteBackground(new Image("man.png"));
	// **CREAMOS UN ATRIBUTO PARA UN CONTENEDOR STACKPANE PARA SUPERPONER NODOS
	final StackPane jugadorSprite = crearJugadorSprite();

	// **CREA CONTENEDOR QUE APILA LOS NODOS BIDIMENSAIONALMENTE EN FILAS Y COLUMNAS
	GridPane boardGrid = new GridPane();
	// **CREA CONTENEDOR PARA SUPERPONER NODOS
	StackPane canvasStack = new StackPane();
	// **CREA TEXTO QUE ESTARA DENTRO DEL CONTENDOR DE LA CLASE TIPOGRAFIA
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

	// **CONSTRUCTOR**//
	PaneJuego(Stage primaryStage) {
		// **INICIALIZAMOS LA VENTANA Y EL PARAMETRO JUEGO
		this.primarystage = primaryStage;
		juego = new Juego();

		// ****//
		nivelTexto.setText("Level " + juego.getCurrentLevelNum());

		// **CREAR UN NUEVO CONTENEDOR PARA ALMACENAR EL NIVEL Y EL TIEMPO
		var ContNivelTemp = new BorderPane();
		// **DEFINIMOS EL BACKGROUND DEL CONTENEDOR
		ContNivelTemp.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, .2), null, null)));
        //**DEFINIR EL ESPACIADO DEL CONTENEDOR EN TODAS LAS DIRECCIONES EN 15PX
		ContNivelTemp.setPadding(new Insets(15));
		//**ALTURA MAXIMA EN 60 PX DEL CONTENEDOR
		ContNivelTemp.setMaxHeight(60);
		//**ESTABLCEMOS EL NODO AL LADO IZQUIERDO (NIVEL)
		ContNivelTemp.setLeft(nivelTexto);
		//**ESTABLCEMOS EL NODO AL LADO IZQUIERDO (TIEMPO)
		ContNivelTemp.setRight(tiempoTexto);

//	        boardGrid.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, .2), null, null)));

		
		//**CONTENEDOR QUE SUPERPONE NODOS ALINEADO AL CENTRO
		canvasStack.setAlignment(Pos.CENTER);
		//**AGREGAMOS NODOS (CONTENEDOR BIDIMENSIONAL Y PREVIATIEMPO)
		canvasStack.getChildren().addAll(boardGrid, previaTiempoTexto);
		
		
		//**POR HERENCIA DE BACKGROUND ESCENA QUE HEREDA BORDERPANE
		//**POCISIONAMOS EL CONTENEDOR PARTE SUPERIOR
		this.setTop(ContNivelTemp);
		//**POCISIONAMOS PARTE CENTRAL
		this.setCenter(canvasStack);
	
//****//
		juego.initGame(1);
	}

	

	
}
