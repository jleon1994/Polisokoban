package motor;

//****************//

//**--CLASE20**--//
//**************//

//**HERENCIA: SUBCLASE DE LA CLASE OBSTACULOS
//**CON ESTA CLASE DEFINIMOS PROPIEDADES A OBSTACULOS PARA NUESTRA CLASE CAJA
public class Caja extends Obstaculos {

	// **METODO CONSTRUCTOR DE LA CLASE CAJA LLAMAMOS SUPER PARA QUE TOME LAS
	// PROPIEDADES DE OBSTACULOS
	Caja() {
		super(TipoObstaculo.BOX, true, '#');
	}
}
