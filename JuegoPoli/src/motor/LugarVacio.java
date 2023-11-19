package motor;

//****************//

//**--CLASE21**--//
//**************//

//**HERENCIA: SUBCLASE DE LA CLASE ENTIDAD
public class LugarVacio extends Entidad {

	// **METODO CONSTRUCTOR PARA BRINDAR PROPIEDADES DE LA CLASE ENTIDAD A
	// LUGARVACIO (METODO1)
	LugarVacio() {
		super(TipoEntidad.EMPTY_SPOT, ' ');
	}
}
