package motor;

import java.util.Timer;
import java.util.TimerTask;
import niveles.ColeccionNiveles;
import niveles.Nivel;
import javafx.application.Platform;

public class Juego {
	private Board board;
	private Board activeBoard;
	private Board targetBoard;
	private Jugador player;
	private boolean isTargetVisible = false;
	private final char PLAYER_AVATAR = '█';

	private final ColeccionNiveles LEVEL_COLLECTION = new ColeccionNiveles();
	private int numeroNivel = 1;

	private Timer CURRENT_LEVEL_TIMER;
	private final int CURRENT_LEVEL_TIMER_PERIOD = 1000;
	private int currentLevelTimeLimit; // milliseconds

	private Timer MAIN_LOOP_TIMER;
	private final int MAIN_LOOP_TIMER_DELAY = 3000; // milliseconds

	private final PortalEventos Events;

	public Juego(PortalEventos events) {
		Events = events;
	}

	public int getNumeroNivel() {
		return numeroNivel;
	}

	public void initGame(int levelNum) {
		numeroNivel = levelNum;
		Nivel currentLevel = LEVEL_COLLECTION.getLevel(levelNum);

		targetBoard = Board.createBoard(currentLevel.getMap());
		board = Board.createBoard(currentLevel.getMap());
		activeBoard = board;

		player = new Jugador(PLAYER_AVATAR, activeBoard);

		Board.randomizeObstacles(activeBoard);

		// Displays preview until `MAIN_LOOP_TIMER_DELAY` ends
		previewCurrentLevelTarget();

		// Start level timer earlier
		// with margin of MAIN_LOOP_TIMER_DELAY
		// to initialize timer info
		currentLevelTimeLimit = currentLevel.getTimeLimit() * 1000 + MAIN_LOOP_TIMER_DELAY;
		CURRENT_LEVEL_TIMER = new Timer();
		CURRENT_LEVEL_TIMER.schedule(new CurrentLevelTimerLoop(), 0, CURRENT_LEVEL_TIMER_PERIOD);

		// Start displaying level with randomized board after delay
		// Add 10ms extra margin to catch up with CURRENT_LEVEL_TIMER's latest updates
		MAIN_LOOP_TIMER = new Timer();
		MAIN_LOOP_TIMER.schedule(new TimerTask() {
			public void run() {
				displayBoard(activeBoard);
			}
		}, MAIN_LOOP_TIMER_DELAY + 10);
	}

	public void previewCurrentLevelTarget() {
		Events.onBoardChange(targetBoard);
	}

	public void finishCurrentLevel() {
		stopCurrentLevelTimer();
		MAIN_LOOP_TIMER.cancel();
		MAIN_LOOP_TIMER.purge();

		// Finish game after completing last level
		if (numeroNivel + 1 > LEVEL_COLLECTION.getLength()) {
			Events.onFinishGame();
			return;
		}

		Events.onFinishLevel(numeroNivel);
	}

	public void loseCurrentLevel() {
		Events.onLoseLevel();
	}

	public void stopCurrentLevelTimer() {
		CURRENT_LEVEL_TIMER.cancel();
		CURRENT_LEVEL_TIMER.purge();
	}

	public void displayBoard(Board currentBoard) {
		Platform.runLater(() -> Events.onBoardChange(currentBoard));

		if (Board.matches(activeBoard, targetBoard, PLAYER_AVATAR)) {
			finishCurrentLevel();
		}
	}

	
	//**METODO DE COMANDO DE USUARIO QUE UTILIZARA PARA JUGAR(METODO)
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
