package motor;

import java.util.Timer;
import java.util.TimerTask;
import niveles.ColeccionNiveles;
import niveles.Nivel;
import javafx.application.Platform;

//***************//
//**--CLASE9**--//
//*************//

public class Juego {

	// ******************//
	// **--ATRIBUTOS**--//
	// ****************//

	// **ATRIBUTOS DE LA CLASE BOARD (CLASE10)
	private Board board;
	private Board activeBoard;
	private Board targetBoard;
	// **ATRIBUTO DE LA CLASE JUGADOR (CLASE11)
	private Jugador player;
	private boolean isTargetVisible = false;
	private final char PLAYER_AVATAR = '█';
	// **NUEVO OBJETO DE LA CLASE COLECCION NIVELES (CLASE14)
	private final ColeccionNiveles LEVEL_COLLECTION = new ColeccionNiveles();
	private int numeroNivel = 1;

	private Timer CURRENT_LEVEL_TIMER;
	private final int CURRENT_LEVEL_TIMER_PERIOD = 1000;
	private int currentLevelTimeLimit; // milliseconds

	private Timer MAIN_LOOP_TIMER;
	private final int MAIN_LOOP_TIMER_DELAY = 3000; // milliseconds
	// **ATRIBUTO DE PORTALEVENTOS
	private final PortalEventos Events;

	// **METODO CONSTRUCTOR IMPLEMENTA LA INTERFAZ PORTAL EVENTOS (METODO1)
	// **ENVIAMOS UN EVENTO A TRAVES DE LA INTERFAZ PORTAL EVENTOS
	// **ASIGNAMOS LA INTERFAZ PORTAL EVENTOS A EVENTS
	public Juego(PortalEventos events) {
		Events = events;
	}

	// **METODO PARA INICIALIZAR Y ENVIAR PORMETODO GET EL NUMERO DE NIVEL (METODO2)
	// **SE INICIALIZA EN 1 (PRIMER NIVEL)
	public int getNumeroNivel() {
		return numeroNivel;
	}

	// **METODO PARA INICIALIZAR UN NUEVO NIVEL EN EL JUEGO (METODO3)
	public void initGame(int levelNum) {
		numeroNivel = levelNum;
		// **LEVEL_COLLECTION VIENE DE LA CLASE COLECCIONNIVELES
		// **EN ESTE PROCESO ASIGNAMOS EL NUMERO DEL NIVEL QUE QUEREMOS USAR
		Nivel currentLevel = LEVEL_COLLECTION.getLevel(levelNum);

		targetBoard = Board.createBoard(currentLevel.getMap());
		board = Board.createBoard(currentLevel.getMap());
		activeBoard = board;
		// **CREAMOS UN NUEVO OBJETO JUGADOR
		player = new Jugador(PLAYER_AVATAR, activeBoard);
		Board.randomizeObstacles(activeBoard);
		previewCurrentLevelTarget();
		currentLevelTimeLimit = currentLevel.getTimeLimit() * 1000 + MAIN_LOOP_TIMER_DELAY;
		CURRENT_LEVEL_TIMER = new Timer();
		CURRENT_LEVEL_TIMER.schedule(new CurrentLevelTimerLoop(), 0, CURRENT_LEVEL_TIMER_PERIOD);
		MAIN_LOOP_TIMER = new Timer();
		MAIN_LOOP_TIMER.schedule(new TimerTask() {
			public void run() {
				displayBoard(activeBoard);
			}
		}, MAIN_LOOP_TIMER_DELAY + 10);
	}

	// **METODO PARA MOSTRAR LA VISTA PREVIA DEL OBJETIVO (METODO4)
	// **ANTES FINALIZAR EL CONTADOR PARA INICIAR EL NIVEL
	// **NOS MUESTRA COMO DEBEN QUEDAR LAS CAJAS
	public void previewCurrentLevelTarget() {
		Events.onBoardChange(targetBoard);
	}

	// **METODO ENCARGADO DE FINALIZAR EL NIVEL CUANDO TERMINAN DE ACOMODAR LAS
	// CAJAS(METODO5)
	public void finishCurrentLevel() {
		stopCurrentLevelTimer();
		MAIN_LOOP_TIMER.cancel();
		MAIN_LOOP_TIMER.purge();
		if (numeroNivel + 1 > LEVEL_COLLECTION.getLength()) {
			Events.onFinishGame();
			return;
		}

		Events.onFinishLevel(numeroNivel);
	}

	// **METODO PARA CUANDO EL JUGADOR PIERDE EL NIVEL (METODO6)
	public void loseCurrentLevel() {
		Events.onLoseLevel();
	}

	// **METODO RESPONSABLE DE DETENER EL TEMPORIZADOR CUANDO FINALICE O PIERDA EL
	// NIVEL (METODO7)
	public void stopCurrentLevelTimer() {
		CURRENT_LEVEL_TIMER.cancel();
		CURRENT_LEVEL_TIMER.purge();
	}

	// **METODO ENCARGADO DE MOSTRAR EL TABLERO ACTUAL DEL JUEGO (METODO8)
	public void displayBoard(Board currentBoard) {
		Platform.runLater(() -> Events.onBoardChange(currentBoard));

		if (Board.matches(activeBoard, targetBoard, PLAYER_AVATAR)) {
			finishCurrentLevel();
		}
	}

	// **METODO RESPONSABLE DE EJECUTAR COMANDOS DE USUARIO EN EL JUEGO (METODO9)
	public void ejecutaComandoUsuario(char input) {
		if (currentLevelTimeLimit < 0)
			return;

		switch (input) {
		case 'd':
			player.moveRight();
			break;

		case 'a':
			player.moveLeft();
			break;

		case 'w':
			player.moveUp();
			break;

		case 's':
			player.moveDown();
			break;

		case 'D':
			player.pushRight();
			break;

		case 'A':
			player.pushLeft();
			break;

		case 'W':
			player.pushUp();
			break;

		case 'S':
			player.pushDown();
			break;

		case 'e':
			isTargetVisible = true;
			break;

		case 'E':
			isTargetVisible = false;
			break;

		default:
			break;
		}

		displayBoard(isTargetVisible ? targetBoard : activeBoard);
	}

	// **CLASE INTERNA QUE SE UTILIZA PARA REALIZAR TAREAS EN CADA EJECUCION DEL
	// TEMPORIZADOR
	class CurrentLevelTimerLoop extends TimerTask {
		private int previewThreshold = MAIN_LOOP_TIMER_DELAY;

		public void run() {
			if (previewThreshold >= 0) {
				Events.onPreviewTimeTick(previewThreshold);
				previewThreshold -= CURRENT_LEVEL_TIMER_PERIOD;
			}

			if (previewThreshold < 0) {
				Events.onLevelTimeTick(currentLevelTimeLimit);
			}

			if (currentLevelTimeLimit < 0) {
				stopCurrentLevelTimer();
				Platform.runLater(Juego.this::loseCurrentLevel);

				return;
			}

			currentLevelTimeLimit -= CURRENT_LEVEL_TIMER_PERIOD;
		}
	}
}
