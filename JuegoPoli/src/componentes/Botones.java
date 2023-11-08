package componentes;

import javafx.scene.control.Button;

public class Botones extends Button{
	
	// *PARAMETRO DE LA CLASE MENUBOTON
		private Button Boton;

		// **CONSTRUCTOR
		public Botones (String label) {
			super(label);
			Boton = new Button(label);
			Boton.getStylesheets().add(getClass().getResource("/componentes/componentes.css").toExternalForm());
			Boton.getStyleClass().add("boton");

		}

		// **METODO GET PARA RETURN DE LAS PROPIEDADES DEL PARAMETRO
		public Button getBoton() {
			return Boton;
		}

}
