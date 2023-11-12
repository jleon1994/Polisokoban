package componentes;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

//***************//
//**--CLASE3**--//
//**************//

//**HERENCIA: NUESTRA CLASE ES UNA SUBCLASE DE BORDERPANE Y HEREDA SUS PROPIEDADES
//**EL OBJETIVO DE ESTA CLASE ES BRINDAR LA PROPIEDADES DEL FONDO DE LOS ESCENARIOS
public class BackgroundEscena extends BorderPane {

	// **CONSTRUCTOR DE LA CLASE BACKGROUNDESCENA(METODO1)
	public BackgroundEscena() {
		super();

		// **CREAMOS UN NUEVO BACKGROUND(FUNCION PRINCIPAL)

		this.setBackground(new Background(new BackgroundImage(
				new Image(getClass().getResourceAsStream("/sprites/FondoMenuPrincipal.jpg")), BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
	}

}
