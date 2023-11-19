package motor;

//****************//
//**--CLASE12**--//
//**************//

//**CLASE ABSTRACTA QUE SIRVE COMO BASE PARA REPRESENTAR ENTIDADES EN EL JUEGO
public abstract class Entidad {

	// **ATRIBUTOS DEL JUEGO**//
	// **ATRIBUTO DE TIPOENTIDAD (CLASE13)
	private final TipoEntidad tipo;
	private final char valorVisualizacion;

	// **METODO CONSTRUCTOR PARA INICIALIZAR LOS ATRIBUTOS (METODO1)
	Entidad(TipoEntidad tipo, char valorVisualizacion) {
		this.tipo = tipo;
		this.valorVisualizacion = valorVisualizacion;
	}

	// **METODO GET PARA DEVOLVER EL ATRIBUTO DE TIPOENTIDAD (METODO2)
	public TipoEntidad getTipo() {
		return tipo;
	}

	// **METODO QUE DEVUELVE EL CARACTER QUE SE UTILIZARA PARA VISUALIZAR LA ENTIDAD
	// EN EL TABLERO (METODO3)
	public char toChar() {
		return valorVisualizacion;
	}

}
