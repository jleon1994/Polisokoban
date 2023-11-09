package componentes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CajaDialogo extends VBox{
	public VBox content = new VBox(10);

	  public CajaDialogo(String title, Color borderColor) {
	    content.setAlignment(Pos.CENTER);
	    content.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
	    content.setPadding(new Insets(30));
	    content.setBorder(
	      new Border(new BorderStroke(borderColor, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderStroke.MEDIUM)));
	    content.getChildren().add(new Tipografia(title, 50, 800));

	    this.getChildren().add(content);
	    this.setAlignment(Pos.CENTER);
	  }
}
