package niveles;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//***************//
//**--CLAS14**--//
//*************//

//**ESTA CLASE SE ENCARGA DE CARGAR Y GESTIONAR UNA COLECCION DE NIVELES DESDE ARCHIVOS 
//**DE TEXTO PLANO QUE SE ENCUENTREN EN NUESTRAS RUTAS
public class ColeccionNiveles {

	// **ATRIBUTOS**//
	// ATRIBUTO DE LA CLASE NIVEL (CLASE15)
	private final Nivel[] levels;
	private final int length;

	// **METODO CONSTRUCTOR PARA TRAER LOS ARCHIVOS PLANOS DE LA RUTA Y CONTAR TODOS
	// LOS ARCHIVOS .txt (METODO1)
	public ColeccionNiveles() {

		//**BUSCA LA RUTA DE LOS ARCHIVOS TEXT
		String projectFolderPath = ColeccionNiveles.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		File folder = new File(projectFolderPath + "/niveles");
		File[] files = folder.listFiles();

		//**CUENTA ARCHIVOS TXT
		int filesCount = 0;
		for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
			File file = files[i];
			if (file.getName().endsWith(".txt")) {
				filesCount++;
			}
		}

		levels = new Nivel[filesCount];

		for (int i = 0; i < files.length; i++) {
			File file = files[i];

			if (file.getName().endsWith(".txt")) {
				Pattern p = Pattern.compile("\\d+"); 
				Matcher m = p.matcher(file.getName());

				//**GUARDA LOS NIVELES CON SUS RESPECTIVOS NUMEROS DE ID 
				if (m.find()) {
					int j = Integer.parseInt(m.group()) - 1;

					try {
						levels[j] = new Nivel(file.getPath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		length = levels.length;
	}

	//**METODO GET QUE PERMITE OBTENER UN NIVEL ESPECIFICO POR SU NUMERO (METODO2)
	public Nivel getLevel(int n) {
		return levels[n - 1];
	}

	//**METODO GET PARA DEVOLVER LA LONGITUD (CANTIDAD) DE LA COLECCION DE NIVELES (METODO3)
	public int getLength() {
		return length;
	}
}
