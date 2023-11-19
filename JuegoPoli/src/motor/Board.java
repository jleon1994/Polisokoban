package motor;

//****************//
//**--CLASE10**--//
//**************//

//**ESTA CLASE REPRESENTA EL TABLERO DEL JUEGO Y PROPORCIONA METODOS PARA INTERACTUAR CON EL
public class Board {
	// **ATRIBUTOS*//
	// PROPIEDADES DEL TABLERO DONDE SE GUARDAN LAS ENTIDADES//
	public int width;
	public int height;
	// NUEVO ATRIBUTO DE LA CLASE ENTIDAD (CLASE12)
	private Entidad[][] board;

	// PROPIEDADES DE LA MATRIZ DE VISUALIZACION ENCARGADA PARA IMPRIMIR
	private int matrixWidth;
	private int matrixHeight;
	private char[][] matrix;
	// **DEFIMOS PARA LOS ARCHIVOS TXT QUE SERA LO QUE QUEREMOS EN EL JUEGO QUE VA A
	// TOMAR DEL ARCHIVO
	private final char BLANK_CELL = ' ';
	private final char HORIZONTAL_BORDER = '─';
	private final char VERTICAL_BORDER = '│';
	private final char TOP_LEFT_BORDER = '┌';
	private final char TOP_RIGHT_BORDER = '┐';
	private final char BOTTOM_LEFT_BORDER = '└';
	private final char BOTTOM_RIGHT_BORDER = '┘';

	// **METODO CONSTRUCTOR: INICIALIZA LAS PROPIEDADES DEL TABLERO Y LLAMA AL
	// METODO INICIALIZEBOARD PARA LLENAR EL TABLERO CON ENTIDADES VACIAS(METODO1)
	Board(int width, int height) {
		this.width = width;
		this.height = height;
		initializeBoard();
		clearMatrix();
	}

	// **METODO PARA INICIALIZAR LA MATRIZ CON INSTANCIAS DE LA CLASE
	// LUGARVACIO(CLASE21) PARA CADA CELDA (METODO2)
	private void initializeBoard() {
		board = new Entidad[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[i][j] = new LugarVacio();
			}
		}
	}

	// **METODO PARA VERIFICAR SI UNA POSICION ESPECIFICA ESTA VACIA (METODO3)
	public boolean isPositionEmpty(int x, int y) {
		return isInsideBoard(x, y) && board[y][x].getTipo() == TipoEntidad.EMPTY_SPOT;
	}

	// **METODO PARA VERIFICAR SI UNA POSICION ESTA DENTRO DE LOS LIMITES DEL
	// TABLERO (METODO4)
	public boolean isInsideBoard(int x, int y) {
		return x >= 0 && y >= 0 && x < width && y < height;
	}

	// **METODO PARA DEVOLVER A LA ENTIDAD EN UNA POSICION ESPECIFICA (METODO5)
	public Entidad select(int x, int y) {
		if (isInsideBoard(x, y)) {
			return board[y][x];
		}
		return null;
	}

	// **METODO PARA INSERTAR UNA ENTIDAD EN UNA POSICION SI ESTA ESTA VACIA
	// (METODO6)
	public boolean insert(int x, int y, Entidad entity) {
		if (isPositionEmpty(x, y)) {
			board[y][x] = entity;
			return true;
		}
		System.out.println("Error setting entity.");
		return false;
	}

	// **METODO PARA BORRAR LA ENTIDAD EN UNA POSICION (METODO7)
	public boolean erase(int x, int y) {
		if (isInsideBoard(x, y)) {
			board[y][x] = new LugarVacio();
			return true;
		}
		System.out.println("Error erasing entity.");
		return false;
	}

	// **METODO ENCARGADO PARA VERIFICAR SI UNA ENTIDAD PUEDE MOVERSE (METODO8)
	private boolean canMoveEntity(Entidad entity) {
		return entity != null && !(entity instanceof Obstaculos);
	}

	// **METODO PARA INTERCAMBIAR A LAS ENTIDADES EN DOS POSICIONES (METODO9)
	public boolean moveEntityTo(int originX, int originY, int targetX, int targetY) {
		Entidad origin = select(originX, originY);
		Entidad target = select(targetX, targetY);
		if ((origin != null) && canMoveEntity(target)) {
			board[originY][originX] = target;
			board[targetY][targetX] = origin;
			return true;
		}
		return false;
	}

	// **METODO PARA MOVER A LAS ENTIDADES A LA DERECHA (METODO10)
	public boolean moveEntityRight(int x, int y) {
		return moveEntityTo(x, y, x + 1, y);
	}

	// **METODO PARA MOVER A LAS ENTIDADES A LA IZQUIERDA (METODO11)
	public boolean moveEntityLeft(int x, int y) {
		return moveEntityTo(x, y, x - 1, y);
	}

	// **METODO PARA MOVER A LAS ENTIDADES ARRIBA (METODO12)
	public boolean moveEntityUp(int x, int y) {
		return moveEntityTo(x, y, x, y - 1);
	}

	// **METODO PARA MOVER A LAS ENTIDADES ABAJO (METODO13)
	public boolean moveEntityDown(int x, int y) {
		return moveEntityTo(x, y, x, y + 1);
	}

	// METODO PARA INICIALIZAR Y DUBUJAR LOS BORDES EN LA MATRIZ (METODO14)
	private void clearMatrix() {
		initializeMatrix();
		drawBorders();
	}

	// METODO PARA INICIALIZAR Y DUBUJAR LOS BORDES EN LA MATRIZ (METODO15)
	private void initializeMatrix() {
		matrixWidth = width + 2;
		matrixHeight = height + 2;
		matrix = new char[matrixHeight][matrixWidth];
		for (int i = 0; i < matrixHeight; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				matrix[i][j] = BLANK_CELL;
			}
		}
	}

	// METODO PARA INICIALIZAR Y DUBUJAR LOS BORDES EN LA MATRIZ (METODO16)
	private void drawBorders() {
		matrix[0][0] = TOP_LEFT_BORDER;
		matrix[0][matrixWidth - 1] = TOP_RIGHT_BORDER;
		for (int i = 1; i < matrixWidth - 1; i++) {
			matrix[0][i] = HORIZONTAL_BORDER;
			matrix[matrixHeight - 1][i] = HORIZONTAL_BORDER;
		}
		matrix[matrixHeight - 1][0] = BOTTOM_LEFT_BORDER;
		matrix[matrixHeight - 1][matrixWidth - 1] = BOTTOM_RIGHT_BORDER;
		for (int i = 1; i < matrixHeight - 1; i++) {
			matrix[i][0] = VERTICAL_BORDER;
			matrix[i][matrixWidth - 1] = VERTICAL_BORDER;
		}
	}

	// **METODO PARA TRANSFERIR LOS VALORES DE LAS ENTIDADES EN EL TABLERO A LA
	// MATRIZ UTILIZADA PARA IMPRIMIR (METODO17)
	private void mirrorBoardToMatrix() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				matrix[i + 1][j + 1] = board[i][j].toChar();
			}
		}
	}

	// **METODO GET PARA DEVOLVER LA MATRIZ DE ENTIDADES BOARD (METODO18)
	public Entidad[][] getBoard() {
		return board;
	}

	// **METODO ENCARGADO DE MOSTRAR EL TABLERO EN LA CONSOLA (METODO19)
	public void display() {
		mirrorBoardToMatrix();

		for (int i = 0; i < matrixHeight; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}

	// **METODO PARA CREAR UN NUEVA TABLERO A PARTIR DE UN MAPA PROPORCIONADO
	// (METODO20) UTILIZAMOS LA CLASE CAJA (CLASE20)
	static public Board createBoard(char[][] map) {
		Board board = new Board(map[0].length, map.length);

		for (int y = 0; y < board.height; y++) {
			for (int x = 0; x < board.width; x++) {
				if (map[y][x] == '#') {
					board.insert(x, y, new Caja());
				} else if (map[y][x] == '=') {
					board.insert(x, y, new Muros());
				}
			}
		}
		return board;
	}

	// **METODO QUE ALETORIZA LAS POSICIONES DE LOS OBSTACULOS EN EL TABLERO
	// (METODO21)
	static public void randomizeObstacles(Board board) {
		int boxCount = 0;

		for (int y = 0; y < board.height; y++) {
			for (int x = 0; x < board.width; x++) {
				if (board.select(x, y) instanceof Caja) {
					boxCount++;
					board.erase(x, y);
				}
			}
		}

		Caja.randomlyPlaceOnBoard(board, boxCount);
	}

	//**METODO PARA COMPARAR DOS TABLEROS Y VERIFICAR SI SUS MATRICES SON IGUALES (METODO22)
	static public boolean matches(Board board1, Board board2, char exclude) {
		board1.mirrorBoardToMatrix();
		board2.mirrorBoardToMatrix();

		char[][] boardMap1 = board1.matrix;
		char[][] boardMap2 = board2.matrix;

		for (int i = 1; i <= board1.height; i++) {
			for (int j = 1; j <= board1.width; j++) {
				if (boardMap1[i][j] == exclude || boardMap2[i][j] == exclude) {
					continue;
				}

				if (boardMap1[i][j] != boardMap2[i][j]) {
					return false;
				}
			}
		}

		return true;
	}
}
