package componentes;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class BackgroundSprites {
	public static Background spriteBackground(Image image) {
	    return new Background(
	      new BackgroundImage(
	        image,
	        BackgroundRepeat.NO_REPEAT,
	        BackgroundRepeat.NO_REPEAT,
	        BackgroundPosition.DEFAULT,
	        new BackgroundSize(1, 1, true, true, true, true)
	      )
	    );
	  }

}
