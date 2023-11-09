package escenas;

import componentes.BackgroundEscena;
import componentes.Botones;
import componentes.Logos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;



//HERENCIA: SUBCLASE DE BACKGROUNDESCENA PARA HEREDAR LA PROPIEDAD DEL BORDERPANE
public class MenuPrincipal extends BackgroundEscena {

	// **ATRIBUTO DE LA CLASE VBox INSERTA NODOS DE MANERA VERTICAL
	private VBox contenedor;

	// **CONTRUCTOR DE LA CLASE
	public MenuPrincipal(Stage primaryStage) {

		var textoMain = new Text("POLISOKOBAN");
		textoMain.setFill(Color.rgb(28, 52, 91));
		textoMain.getStyleClass().add("textomainprincipal");
		textoMain.setStroke(Color.BLACK);

		contenedor = new VBox(10); // ESPACIADO ENTRE NODOS DE 10PX

		contenedor.getStyleClass().add("backgroundescena");
		contenedor.getStylesheets().add(getClass().getResource("/componentes/componentes.css").toExternalForm());

		contenedor.setAlignment(Pos.CENTER);

		// **CREAMOS UN NUEVO BOTON Y ADICIONAMOS EL METODO GETBOTON PARA SU STILO CSS
		var start = new Botones("START").getBoton();
		start.setOnAction(e -> {
			primaryStage.setScene(EscenarioJuego.getEscena(primaryStage));
		});

		contenedor.getChildren().addAll(
				new Logos(new Image(getClass().getResourceAsStream("/sprites/LogoPoli.png"))).getLogoPoli(),
				new Logos(new Image(getClass().getResourceAsStream("/sprites/man.png"))).getimageMan(), textoMain,
				start, new Botones("REGLAS DEL JUEGO").getBoton(), new Botones("NOSOTROS").getBoton());

	}

	// **METODO GETTER PARA HACER UNA COPIA DEL ATRIBUTO Y PODERLO USAR
	public VBox getContenedor() {
		return contenedor;
	}

}
