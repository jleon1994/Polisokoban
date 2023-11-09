package motor;

public class Obstaculos extends Entidad {
	private final TipoObstaculo obstacleType;
	private final boolean isMovable; // if player can move it

	Obstaculos(TipoObstaculo obstacleType, boolean isMovable, char displayValue) {
		super(TipoEntidad.OBSTACLE, displayValue);
		this.obstacleType = obstacleType;
		this.isMovable = isMovable;
	}

	public TipoObstaculo getObstacleType() {
		return obstacleType;
	}

	public boolean isMovable() {
		return isMovable;
	}

	public static void randomlyPlaceOnBoard(Board board, int count) {
		for (int i = 0; i < count; i++) {
			int x, y;

			do {
				x = (int) (Math.random() * board.width);
				y = (int) (Math.random() * board.height);
			} while (!board.isPositionEmpty(x, y));

			board.insert(x, y, new Caja());
		}
	}
}
