package escenas;

import componentes.BackgroundEscena;
import componentes.BackgroundSprites;
import componentes.Botones;
import componentes.CajaDialogo;
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

//**PANEJUEGO ES EL CONTENEDOR QUE CONTENDRA EL JUEGO  Y SUS CONFIGURACIONES (PARTE GRAFICA)**//
public class PaneJuego extends BackgroundEscena implements PortalEventos {

	// **CREAMOS UN NUEVO OBJETO VENTANA DE LA CLASE STAGE
	final Stage primarystage;
	// **CREAMOS UN OBJETO CON METODOS Y PROPIEDADES DE LA CLASE JUEGO
	final Juego juego;
	// *BACKGROUNDS DE LOS OBJETOS QUE TENDREMOS EN EL PANE DEL JUEGO
	final Background cespedBackground = BackgroundSprites.spriteBackground(new Image("/sprites/FondoCesped.jpg"));
	final Background cajaBackground = BackgroundSprites.spriteBackground(new Image("/sprites/Caja.png"));
	final Background bloqueBackground = BackgroundSprites.spriteBackground(new Image("/sprites/Bloque.png"));
	final Background jugadorBackground = BackgroundSprites.spriteBackground(new Image("/sprites/man.png"));
	// **CREAMOS UN ATRIBUTO PARA UN CONTENEDOR STACKPANE PARA SUPERPONER NODOS
	final StackPane jugadorSprite = crearJugadorSprite();
	boolean isBoardInitialized = false;

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
		juego = new Juego(this);

		// ****//
		nivelTexto.setText("Level " + juego.getCurrentLevelNum());

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

//	        boardGrid.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, .2), null, null)));

		// **CONTENEDOR QUE SUPERPONE NODOS ALINEADO AL CENTRO
		canvasStack.setAlignment(Pos.CENTER);
		// **AGREGAMOS NODOS (CONTENEDOR BIDIMENSIONAL Y PREVIATIEMPO)
		canvasStack.getChildren().addAll(boardGrid, previaTiempoTexto);

		// **POR HERENCIA DE BACKGROUND ESCENA QUE HEREDA BORDERPANE
		// **POCISIONAMOS EL CONTENEDOR PARTE SUPERIOR
		this.setTop(ContNivelTemp);
		// **POCISIONAMOS PARTE CENTRAL
		this.setCenter(canvasStack);

//****//
		juego.initGame(1);
	}

//****//	
	public void executeUserCommand(char command) {
		juego.executeUserCommand(command);
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
		var dialogBox = new CajaDialogo("TIME IS OUT!", Color.RED);

		var restartBtn = new Botones("Restart");
		restartBtn.setOnAction(e -> {
			juego.initGame(1);
			canvasStack.getChildren().removeAll(dialogBox);
		});

		var menuBtn = new Botones("Main Menu");
		menuBtn.setOnAction(e -> {
			primarystage.setScene(new Scene(new MenuPrincipal(primarystage), 800, 800));
		});

		dialogBox.content.getChildren().addAll(restartBtn, menuBtn);

		canvasStack.getChildren().add(dialogBox);
	}

	@Override
	public void onFinishLevel(int levelNum) {
		isBoardInitialized = false;

		var dialogBox = new CajaDialogo("Well Done!", Color.GREENYELLOW);

		var nextLevelBtn = new Botones("Next Level");
		nextLevelBtn.setOnAction(e -> {
			juego.initGame(levelNum + 1);
			nivelTexto.setText("Level " + juego.getCurrentLevelNum());
			canvasStack.getChildren().removeAll(dialogBox);
		});

		var menuBtn = new Botones("Main Menu");
		menuBtn.setOnAction(e -> {
			primarystage.setScene(new Scene(new MenuPrincipal(primarystage), 800, 800));
		});

		dialogBox.content.getChildren().addAll(nextLevelBtn, menuBtn);
		canvasStack.getChildren().add(dialogBox);
	}

	@Override
	public void onFinishGame() {
		var dialogBox = new CajaDialogo("Congratulations!", Color.LIGHTSKYBLUE);
		var text = new Tipografia("You have completed all the levels!", 20);
		var menuBtn = new Botones("Main Menu");

		menuBtn.setOnAction(e -> {
			primarystage.setScene(new Scene(new MenuPrincipal(primarystage), 800, 800));
		});

		dialogBox.content.getChildren().addAll(text, menuBtn);
		canvasStack.getChildren().add(dialogBox);
	}

}
