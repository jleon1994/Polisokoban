package niveles;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//***************//
//**--CLAS15**--//
//*************//

//**ESTA CLASE NOS PERMITE CARGAR INFORMACION DE UN NIVEL DESDE UN ARCHIVO TXT
public class Nivel {

	// **ATRIBUTOS**//
	private final char[][] map;
	private final int timeLimit;

	// **METODO CONSTRUCTOR PARA LEER EL CONTENIDO DEL ARCHIVO
	// **LA PRIMERA PARTE DEL ARCHIVO CONTIENE LAS DIMENSIONES Y EL TIEMPO LIMITE
	// **(METODO1)
	Nivel(String sourceFilePath) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

		String lineString = bufferedReader.readLine();
		String[] mapInfo = lineString.split(" ");

		int mapWidth = Integer.parseInt(mapInfo[0]);
		int mapHeight = Integer.parseInt(mapInfo[1]);
		timeLimit = Integer.parseInt(mapInfo[2]);

		// **INICIALIZAMOS EL ARREGLO BIDIMENSIONNAL CON LAS VARIABLES OBTENIDAS
		map = new char[mapHeight][mapWidth];

		int currentLine = 0;
		while ((lineString = bufferedReader.readLine()) != null) {
			map[currentLine++] = lineString.toCharArray();
		}

		fileInputStream.close();
	}

	// **METODO GET PARA RETORNAR EL ATRIBUTO MAP (METODO2)
	public char[][] getMap() {
		return map;
	}

	// **METODO GET PARA RETORNAR EL ATRIBUTO TIMELIMIT(METODO3)
	public int getTimeLimit() {
		return timeLimit;
	}
}
