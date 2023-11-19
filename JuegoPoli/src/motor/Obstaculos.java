package motor;

//****************//
//**--CLASE23**--//
//**************//

//**HERENCIA: SUBCLASE D ELA CLASE ENTIDAD

public class Obstaculos extends Entidad {

	// **ATRIBUTOS**//
	private final TipoObstaculo obstacleType;
	private final boolean isMovable;

	// **METODO CONSTRUCTOR PARA INICIALIZAR LOS ATRIBUTOS (METODO1 )
	Obstaculos(TipoObstaculo obstacleType, boolean isMovable, char displayValue) {
		super(TipoEntidad.OBSTACLE, displayValue);
		this.obstacleType = obstacleType;
		this.isMovable = isMovable;
	}

	// **METODO QUE REPRESENTA EL TIPO ESPECIFICO DEL OBSTACULO (METODO2)
	public TipoObstaculo getObstacleType() {
		return obstacleType;
	}

	// **METODO PARA INDICAR SI EL OBSTACULO PUEDE SER MOVIDO POR EL JUGADOR
	// (METODO3)
	public boolean isMovable() {
		return isMovable;
	}

	// **METODO QUE COLOCA OBSTACULOS DE MANERA ALEATORIA EN EL TABLERO (METODO4)
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
