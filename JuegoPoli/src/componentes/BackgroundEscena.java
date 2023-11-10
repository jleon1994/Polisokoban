package componentes;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

//**HERENCIA: NUESTRA CLASE ES UNA SUBCLASE DE BORDER PANE Y HEREDA SUS PROPIEDADES
public class BackgroundEscena extends BorderPane {

	// **PARAMETRO

	// **CONSTRUCTOR
	public BackgroundEscena() {

		super();

		// *CREAMOS UN NUEVO BACKGROUND

		this.setStyle("-fx-font-family: Arial");
		this.setBackground(new Background(new BackgroundImage(
				new Image(getClass().getResourceAsStream("/sprites/FondoMenuPrincipal.jpg")), BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

	}

}
