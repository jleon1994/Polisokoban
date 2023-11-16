package escenas;

import componentes.BackgroundEscena;
import componentes.Botones;
import componentes.Logos;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//***************//
//**--CLASE2**--//
//*************//

//**HERENCIA: SUBCLASE DE BACKGROUNDESCENA (CLASE3)
public class MenuPrincipal extends BackgroundEscena {
	// **ATRIBUTO PRINCIPAL DE LA CLASE
	private VBox contenedor;

	// **CONSTRUCTOR DE LA CLASE(METODO1)
	public MenuPrincipal(Stage primaryStage) {

		// **INICIALIZAMOS EL ATRIBUTO
		// **AL OBJETO CREADO LE DAMOS ESTILO CSS
		contenedor = new VBox(10);
		contenedor.setAlignment(Pos.CENTER);
		contenedor.getStylesheets().add(getClass().getResource("/componentes/componentes.css").toExternalForm());
		contenedor.getStyleClass().add("backgroundescena");

		// **INSTANCIA: OBJETO BOTON (CLASE4)-LO DIRECCIONAMOS CON CLIK A
		// ESCENARIOJUEGO(CLASE5)
		var start = new Botones("START").getBoton();
		start.setOnAction(e -> {
			primaryStage.setScene(EscenarioJuego.getEscena(primaryStage));
		});

		// **INSTANCIA: OBJETO BOTON (CLASE4)-LO DIRECCIONAMOS CON CLIK A
		// REGLAS DE JUEGO (CLASE6)
		var reglasJuego = new Botones("REGLAS DEL JUEGO").getBoton();
		reglasJuego.setOnAction(e -> {
			primaryStage.getScene().setRoot(new ReglasJuego(primaryStage));
		});

		// **INSTANCIA: OBJETO BOTON (CLASE4)-LO DIRECCIONAMOS CON CLIK A
		// NOSOTROS (CLASE7)
		var nosotros = new Botones("NOSOTROS").getBoton();
		nosotros.setOnAction(e -> {
			primaryStage.getScene().setRoot(new Nosotros(primaryStage));
		});
		
		// **INSTANCIA: OBJETO BOTON (CLASE4)-LO DIRECCIONAMOS CON CLIK A
				// NOSOTROS (CLASE7)
				var prueba = new Botones("NOSOTROS").getBoton();
				prueba.setOnAction(e -> {
					primaryStage.getScene().setRoot(new Nosotros(primaryStage));
				});

		// **INSTANCIA: OBJETO TEXTO - ESTILOS CSS
		var textoMain = new Text("POLISOKOBAN");
		textoMain.getStyleClass().add("textomainprincipal");
		
		// **INSTANCIA: OBJETO TEXTO - ESTILOS CSS
		var textosubMain = new Text("RETO LOGICO");
		textosubMain.getStyleClass().add("textosubmainprincipal");
		

		// **ADICIONAMOS AL VBOX LOS OBJETOS(NODOS) QUE SALDRAN VERTICALES
		contenedor.getChildren().addAll(
				//new Logos(new Image(getClass().getResourceAsStream("/sprites/LogoPoli.png"))).getLogoPoli(),
				new Logos(new Image(getClass().getResourceAsStream("/sprites/man2.png"))).getimageMan(), textoMain,textosubMain,
				start, reglasJuego, nosotros, prueba);

	}

	// **METODO GETTER PARA RETORNAR EL ATRIBUTO (METODO2)
	public VBox getContenedor() {
		return contenedor;
	}

}
