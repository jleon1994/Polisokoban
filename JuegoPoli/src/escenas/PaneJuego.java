package escenas;

import componentes.BackgroundEscena;
import componentes.BackgroundSprites;
import componentes.Botones;
import componentes.CajaDialogo;
import componentes.Logos;
import componentes.Tipografia;
import motor.Board;
import motor.Juego;
import motor.Obstaculos;
import motor.PortalEventos;
import motor.TipoEntidad;
import motor.TipoObstaculo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//***************//
//**--CLASE8**--//
//**************//

//**PANEJUEGO ES EL CONTENEDOR QUE CONTENDRA EL JUEGO  Y SUS CONFIGURACIONES (PARTE GRAFICA)**//
//**HERENCIA: SUBCLASE DE BACKGROUNDESCENA PARA HEREDAD SUS PROPIEDADES
public class PaneJuego extends BackgroundEscena implements PortalEventos {

	// *************//
	// **ATRIBUTOS**//
	// *************//

	final Stage primarystage;
	final Juego juego;
	final Background cespedBackground = BackgroundSprites
			.spriteBackground(new Image(getClass().getResourceAsStream("/sprites/FondoCesped.jpg")));
	final Background cajaBackground = BackgroundSprites
			.spriteBackground(new Image(getClass().getResourceAsStream("/sprites/Caja3.png")));
	final Background bloqueBackground = BackgroundSprites
			.spriteBackground(new Image(getClass().getResourceAsStream("/sprites/Bloque3.png")));
	final Background jugadorBackground = BackgroundSprites
			.spriteBackground(new Image(getClass().getResourceAsStream("/sprites/man3.png")));
	final StackPane jugadorSprite = crearJugadorSprite();
	boolean isBoardInitialized = false;

	GridPane boardGrid = new GridPane();
	StackPane canvasStack = new StackPane();
	Tipografia nivelTexto = new Tipografia("Nivel", 40, Color.WHITE);
	Tipografia tiempoTexto = new Tipografia("00:00", 40, Color.WHITE);
	Tipografia previaTiempoTexto = new Tipografia("", 80, Color.GREEN, 800);

	// **METODO PARA CREAR CONTENEDORES DE JUGADOR Y BACKGROUND(METODO1)
	private StackPane crearJugadorSprite() {
		// ** STACKPANE - CONTENEDOR - SUPERPONE UN NODOS ENCIMA DE OTRO
		// **SUPERPONER UN CONTENEDOR JUGADOR ENCIMA DEL CONTENEDOR FONDO
		var jugadorSprite = new StackPane();
		var fondo = new Pane();
		var jugador = new Pane();
		// **ADICIONAMOS FONDOS A LOS CONTENEDORES FONDO Y JUGADOR
		fondo.setBackground(cespedBackground);
		jugador.setBackground(jugadorBackground);
		// **ADICIONAMOS LOS NODOS (CONTENEDORES) A UN CONTENDOR DE SUPERPOCISION
		// STACKPANE
		jugadorSprite.getChildren().addAll(fondo, jugador);
		return jugadorSprite;
	}

	
	
	
	// **METODO CONSTRUCTOR DE LA CLASE PANEJUEGO(METODO2)
	PaneJuego(Stage primaryStage) {
		// **INICIALIZAMOS LA VENTANA Y EL ATRIBUTO JUEGO
		this.primarystage = primaryStage;
		juego = new Juego(this);
		// **OBJETO NIVELTEXTO LE PONEMOS EL NOMBRE Y EL NUMERO DE NIVEL
		// **EL NUMERO DE NIVEL ESTA DADO POR UN METODO DE LA CLASE JUEGO
		nivelTexto.setText("Nivel: " + juego.getNumeroNivel());
		// **CREAR UN NUEVO CONTENEDOR PARA ALMACENAR EL NIVEL Y EL TIEMPO
		var ContNivelTemp = new BorderPane();
		// **DEFINIMOS EL BACKGROUND DEL CONTENEDOR
		ContNivelTemp.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, .2), null, null)));
		// **DEFINIR EL ESPACIADO DEL CONTENEDOR EN TODAS LAS DIRECCIONES EN 15PX
		ContNivelTemp.setPadding(new Insets(15));
		// **ALTURA MAXIMA EN 60 PX DEL CONTENEDOR
		ContNivelTemp.setMaxHeight(60);
		// **ESTABLCEMOS EL NODO AL LADO IZQUIERDO (NIVEL)
		ContNivelTemp.setLeft(nivelTexto);
		// **ESTABLCEMOS EL NODO AL LADO IZQUIERDO (TIEMPO)
		ContNivelTemp.setRight(tiempoTexto);
		// **CONTENEDOR QUE SUPERPONE NODOS ALINEADO AL CENTRO
		canvasStack.setAlignment(Pos.CENTER);
		// **AGREGAMOS NODOS (CONTENEDOR BIDIMENSIONAL Y PREVIATIEMPO)
		canvasStack.getChildren().addAll(boardGrid, previaTiempoTexto);
		// **POR HERENCIA DE BACKGROUND ESCENA QUE HEREDA BORDERPANE
		// **POCISIONAMOS EL CONTENEDOR PARTE SUPERIOR
		this.setTop(ContNivelTemp);
		// **POCISIONAMOS PARTE CENTRAL
		this.setCenter(canvasStack);
		// **LLAMAMOS EL METODO INITGAME DE JUEGO PARA INICIAR EL JUEGO
		juego.initGame(1);
	}

	
	
	
    //**METODO PARA CONTROLAR LOS COMANDOS QUE OPRIME EL USUARIO (METODO3)	
	public void ejecutaComandoUsuario(char command) {
		juego.ejecutaComandoUsuario(command);
	}

	
	
	
	private void initializeBoardGrid(int rows, int columns) {
		boardGrid.getChildren().clear();

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				var cell = new Pane();

				if (rows == columns) {
					cell.prefWidthProperty().bind(this.widthProperty().divide(columns));
					cell.prefHeightProperty().bind(this.widthProperty().divide(row));
				} else if (rows < columns) {
					cell.prefWidthProperty().bind(this.widthProperty().divide(columns));
					cell.prefHeightProperty().bind(this.widthProperty().divide(columns));
				} else {
					cell.prefWidthProperty().bind(this.heightProperty().divide(rows));
					cell.prefHeightProperty().bind(this.heightProperty().divide(rows));
				}

				boardGrid.add(cell, column, row);
			}
		}
	}

	
	
	@Override
	public void onBoardChange(Board updatedBoard) {
		var matrix = updatedBoard.getBoard();
		int rows = matrix.length;
		int columns = matrix[0].length;

		int columnMargin = rows - columns > 1 ? (rows - columns) / 2 : 0;
		int rowMargin = columns - rows > 1 ? (columns - rows) / 2 : 0;

		if (!isBoardInitialized) {
			// Create bigger grid to align the board
			initializeBoardGrid(rows + rowMargin, columns + columnMargin);
		}

		var len = boardGrid.getChildren().size();
		for (int i = 0; i < len; i++) {
			var cell = (Pane) (boardGrid.getChildren().get(i));
			int col = GridPane.getColumnIndex(cell) - columnMargin;
			int row = GridPane.getRowIndex(cell) - rowMargin;

			if (col < 0 || row < 0)
				continue;

			TipoEntidad type = matrix[row][col].getTipo();

			switch (type) {
			case PLAYER -> {
				cell.getChildren().clear();
				jugadorSprite.prefWidthProperty().bind(cell.widthProperty());
				jugadorSprite.prefHeightProperty().bind(cell.widthProperty());
				cell.getChildren().add(jugadorSprite);
			}
			case OBSTACLE -> {
				var obstacle = (Obstaculos) (matrix[row][col]);
				var obstacleType = obstacle.getObstacleType();

				if (obstacleType == TipoObstaculo.BOX) {
					cell.setBackground(cajaBackground);
				} else {
					cell.setBackground(bloqueBackground);
				}
			}
			case EMPTY_SPOT -> {
				cell.setBackground(cespedBackground);
			}
			}
		}
	}

	@Override
	public void onPreviewTimeTick(int milliseconds) {
		if (milliseconds > 0) {
			previaTiempoTexto.setVisible(true);
			previaTiempoTexto.setText(String.valueOf(milliseconds / 1000));
		} else {
			previaTiempoTexto.setText("");
			previaTiempoTexto.setVisible(false);
		}
	}

	@Override
	public void onLevelTimeTick(int currentLevelTimeLimit) {
		tiempoTexto.setFill(currentLevelTimeLimit <= 10_000 ? Color.RED : Color.WHITE);

		if (currentLevelTimeLimit < 0)
			return;

		int minutes = currentLevelTimeLimit / 1000 / 60;
		int seconds = (currentLevelTimeLimit / 1000) % 60;
		String formattedMinutes = minutes / 10 > 0 ? String.valueOf(minutes) : "0" + minutes;
		String formattedSeconds = seconds / 10 > 0 ? String.valueOf(seconds) : "0" + seconds;

		tiempoTexto.setText(formattedMinutes + ":" + formattedSeconds);
	}

	@Override
	public void onLoseLevel() {
		var dialogBox = new CajaDialogo("SE AGOTO EL TIEMPO!", Color.RED);

		var restartBtn = new Botones("Restart").getBoton();
		restartBtn.setOnAction(e -> {
			juego.initGame(1);
			canvasStack.getChildren().removeAll(dialogBox);
		});

		var menuBtn = new Botones("MENU PRINCIPAL").getBoton();
		menuBtn.setOnAction(e -> {
			primarystage.setScene(new Scene(new MenuPrincipal(primarystage).getContenedor(), 800, 800));
		});

		dialogBox.contenedor.getChildren().addAll(restartBtn, menuBtn);

		canvasStack.getChildren().add(dialogBox);
	}

	@Override
	public void onFinishLevel(int levelNum) {
		isBoardInitialized = false;

		var dialogBox = new CajaDialogo("MUY BIEN!", Color.BLACK);

		var nextLevelBtn = new Botones("SIGUIENTE NIVEL").getBoton();
		nextLevelBtn.setOnAction(e -> {
			juego.initGame(levelNum + 1);
			nivelTexto.setText("Level " + juego.getNumeroNivel());
			canvasStack.getChildren().removeAll(dialogBox);
		});

		var menuBtn = new Botones("Menu Principal").getBoton();
		menuBtn.setOnAction(e -> {
			primarystage.setScene(new Scene(new MenuPrincipal(primarystage).getContenedor(), 800, 800));
		});

		dialogBox.contenedor.getChildren().addAll(nextLevelBtn, menuBtn);
		canvasStack.getChildren().add(dialogBox);
	}

	@Override
	public void onFinishGame() {
		var dialogBox = new CajaDialogo("FELICITACIONES GANASTE", Color.BLACK);
		var text = new Tipografia("LOGRASTE COMPLETAR TODOS LOS NIVELES!", 20);
		var menuBtn = new Botones("MENU PRINCIPAL").getBoton();

		menuBtn.setOnAction(e -> {
			primarystage.setScene(new Scene(new MenuPrincipal(primarystage).getContenedor(), 800, 800));
		});

		dialogBox.contenedor.getChildren().addAll(text, menuBtn);
		canvasStack.getChildren().add(dialogBox);
	}

}
