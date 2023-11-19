package componentes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//****************//
//**--CLASE16**--//
//**************//

//**HERENCIA: SUBCLASE DE LA CLASE TEXT DE JAVAFX
//**ESTA CLASE NOS PERMITE CREAR OBJETOS DE TEXTO CON DIFERENTES COMBINACIONES DE PROPIEDADES DE FUENTE
public class Tipografia extends Text {

	// **USAREMOS POLIMORFISMO PARA CREAR METODOS
	// **METODOS PARA CREAR TEXTOS

	// **METODO QUE CREA TEXTO QUE SOLICITA TEXTO Y EL VALOR FUENTE (METODO1)
	public Tipografia(String label, int fontSize) {
		super();
		this.setFont(Font.font("Algerian", FontWeight.findByWeight(400), fontSize));

	}

	// **METODO QUE CREA TEXTO QUE SOLICITA COLOR DE LA FUENTE (METODO2)
	public Tipografia(String label, int fontSize, Color color) {
		super(label);
		this.setFont(Font.font("Algerian", FontWeight.findByWeight(400), fontSize));
		this.setFill(color);
	}

	// **METODO QUE CREA TEXTO QUE SOLICITA EL PESO DE GROSURA DE LA FUENTE
	// (METODO3)
	public Tipografia(String label, int fontSize, int fontWeight) {
		super(label);
		this.setFont(Font.font("Algerian", FontWeight.findByWeight(fontWeight), fontSize));
	}

	// **METODO QUE CREA TEXTO QUE SOLICITA EL PESO DE GROSURA Y COLOR DE LA FUENTE
	// (METODO4)
	public Tipografia(String label, int fontSize, Color color, int fontWeight) {
		super(label);
		this.setFont(Font.font("Algerian", FontWeight.findByWeight(fontWeight), fontSize));
		this.setFill(color);
	}

}
