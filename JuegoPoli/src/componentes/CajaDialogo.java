package componentes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CajaDialogo extends VBox{
	public VBox contenedor = new VBox(10);

	  public CajaDialogo(String title, Color borderColor) {
	    contenedor.setAlignment(Pos.CENTER);
	    //contenedor.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
	    contenedor.getStylesheets().add(getClass().getResource("/componentes/componentes.css").toExternalForm());
		contenedor.getStyleClass().add("backgroundescena");
		
	    
	    contenedor.setPadding(new Insets(30));
	    contenedor.setBorder(
	      new Border(new BorderStroke(borderColor, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderStroke.MEDIUM)));
	    contenedor.getChildren().add(new Tipografia(title, 50, 800));

	    this.getChildren().add(contenedor);
	    this.setAlignment(Pos.CENTER);
	  }
}
