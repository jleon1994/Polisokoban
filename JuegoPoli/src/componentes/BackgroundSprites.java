package componentes;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

//****************//
//**--CLASE17**--//
//**************//

//CLASE QUE CREA Y DEVUELVE UN OBJETO BACKGROUND DE JAVAFX
public class BackgroundSprites {

	// **METODO CONSTRUCTOR QUE FACILITA LA CREACION DE FONDOS UTILIZANDO IMAGENES
	// (METODO1)
	public static Background spriteBackground(Image image) {
		return new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true, true, true, true)));
	}

}
