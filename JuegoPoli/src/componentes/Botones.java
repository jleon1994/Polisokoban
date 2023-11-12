package componentes;

import javafx.scene.control.Button;

//***************//
//**--CLASE4**--//
//**************//

//**HERENCIA: SUBCLASE DE LA CLASE BUTTON JAVAFX
public class Botones extends Button {

	// **ATRIBUTO DE LA CLASE BOTONES
	private Button Boton;

	// **CONSTRUCTOR DE LA CLASE BOTONES(METODO1)
	public Botones(String label) {
		super(label);

		// **CREAMOS UN NUEVO OBJETO Y DAMOS ESTILO CON CSS
		Boton = new Button(label);
		Boton.getStylesheets().add(getClass().getResource("/componentes/componentes.css").toExternalForm());
		Boton.getStyleClass().add("boton");

	}

	// **METODO GET PARA RETURN DE LAS PROPIEDADES DEL PARAMETRO(METODO2)
	public Button getBoton() {
		return Boton;
	}

}
