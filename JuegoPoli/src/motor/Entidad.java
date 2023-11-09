package motor;

public abstract class Entidad {
	private final TipoEntidad tipo;
	  private final char valorVisualizacion;

	  Entidad(TipoEntidad tipo, char valorVisualizacion) {
	    this.tipo = tipo;
	    this.valorVisualizacion = valorVisualizacion;
	  }

	  public TipoEntidad getTipo() {
	    return tipo;
	  }

	  public char toChar() {
	    return valorVisualizacion;
	  }

}
