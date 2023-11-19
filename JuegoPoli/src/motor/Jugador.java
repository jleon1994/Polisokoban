package motor;

//****************//
//**--CLASE11**--//
//**************//

//**ESTA CLASE REPRESENTA AL JUGADOR EN EL JUEGO
//**HERENCIA: SUBCLASE DE ENTIDAD 
public class Jugador extends Entidad {

	// **CLASE ANIDADA POSITION(SE UTILIZA PARA REPRESENTAR LA POSICION DEL JUGADOR
	// EN EL TABLERO)
	private static class Position {
		public int x, y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// **ATRIBUTOS DE LA CLASE JUGADOR**//
	// **CREA UN ATRIBUTO DE LA CLASE POSITION
	private final Position position = new Position(0, 0);
	// **CREA UN ATRIBUTO DE LA CLASE ENTIDAD (CLASE12)
	private final Board board;

	// **METODO CONSTRUCTOR PARA INICIALIZAR LOS ATRIBUTOS SE INSERTA AL JUGADOR EN
	// EL TABLERO EN LA POSICION 0,0 (METODO1)
	Jugador(char displayValue, Board board) {
		super(TipoEntidad.PLAYER, displayValue);
		this.board = board;
		this.board.insert(position.x, position.y, this);
	}

	// **METODO PARA VERIFICAR SI UNA ENTIDAD ES UN OBSTACULO MOVIL (METODO2)
	private boolean isMovableObstacle(Entidad entity) {
		return (entity instanceof Obstaculos) && ((Obstaculos) entity).isMovable();
	}

	// **METODO PARA QUE EL JUGADOR SE PUEDA MOVER A LA DERECHA (METODO3)
	public boolean moveRight() {
		if (board.moveEntityRight(position.x, position.y)) {
			++position.x;
			return true;
		}
		return false;
	}

	// **METODO PARA QUE EL JUGADOR SE PUEDA MOVER A LA IZQUIERDA (METODO4)
	public boolean moveLeft() {
		if (board.moveEntityLeft(position.x, position.y)) {
			--position.x;
			return true;
		}
		return false;
	}

	// **METODO PARA QUE EL JUGADOR SE PUEDA MOVER ARRIBA (METODO5)
	public boolean moveUp() {
		if (board.moveEntityUp(position.x, position.y)) {
			--position.y;
			return true;
		}
		return false;
	}

	// **METODO PARA QUE EL JUGADOR SE PUEDA MOVER ABAJO (METODO6)
	public boolean moveDown() {
		if (board.moveEntityDown(position.x, position.y)) {
			++position.y;
			return true;
		}
		return false;
	}

	
	//**METODO PARA EMPUJAR OBJETOS MOVILES A LA DERECHA (METODO7)
	public boolean pushRight() {
		if (isMovableObstacle(board.select(position.x + 1, position.y))) {
			if (board.moveEntityRight(position.x + 1, position.y)) {
				return moveRight();
			}
		}
		return pullRight();
	}

	//**METODO PARA EMPUJAR OBJETOS MOVILES A LA IZQUIERDA (METODO8)
	public boolean pushLeft() {
		if (isMovableObstacle(board.select(position.x - 1, position.y))) {
			if (board.moveEntityLeft(position.x - 1, position.y)) {
				return moveLeft();
			}
		}
		return pullLeft();
	}

	//**METODO PARA EMPUJAR OBJETOS MOVILES ARRIBA (METODO9)
	public boolean pushUp() {
		if (isMovableObstacle(board.select(position.x, position.y - 1))) {
			if (board.moveEntityUp(position.x, position.y - 1)) {
				return moveUp();
			}
		}
		return pullUp();
	}

	//**METODO PARA EMPUJAR OBJETOS MOVILES ABAJO (METODO10)
	public boolean pushDown() {
		if (isMovableObstacle(board.select(position.x, position.y + 1))) {
			if (board.moveEntityDown(position.x, position.y + 1)) {
				return moveDown();
			}
		}
		return pullDown();
	}

	//**METODO PARA TIRAR OBJETOS MOVILES A LA DERECHA (METODO11)
	public boolean pullRight() {
		if (isMovableObstacle(board.select(position.x - 1, position.y))) {
			if (moveRight()) {
				return board.moveEntityRight(position.x - 2, position.y);
			}
		}
		return false;
	}

	//**METODO PARA TIRAR OBJETOS MOVILES A LA IZQUIERDA (METODO12)
	public boolean pullLeft() {
		if (isMovableObstacle(board.select(position.x + 1, position.y))) {
			if (moveLeft()) {
				return board.moveEntityLeft(position.x + 2, position.y);
			}
		}
		return false;
	}

	//**METODO PARA TIRAR OBJETOS MOVILES ARRIBA (METODO13)
	public boolean pullUp() {
		if (isMovableObstacle(board.select(position.x, position.y + 1))) {
			if (moveUp()) {
				return board.moveEntityUp(position.x, position.y + 2);
			}
		}
		return false;
	}

	//**METODO PARA TIRAR OBJETOS MOVILES ABAJO (METODO14)
	public boolean pullDown() {
		if (isMovableObstacle(board.select(position.x, position.y - 1))) {
			if (moveDown()) {
				return board.moveEntityDown(position.x, position.y - 2);
			}
		}
		return false;
	}
}
