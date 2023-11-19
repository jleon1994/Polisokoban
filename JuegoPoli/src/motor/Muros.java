package motor;

//****************//

//**--CLASE22**--//
//**************//

//**HERENCIA: SUBCLASE DE LA CLASE OBSTACULOS
public class Muros extends Obstaculos {

	// **METODO CONSTRUCTOR PARA QUE MUROS OBTENGA LAS PROPIEDADES DE LA CLASE
	// OBSTACULOS (METODO1)
	Muros() {
		super(TipoObstaculo.WALL, false, '=');
	}
}
