package componentes;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//**SUBCONJUNTO DE LA CLASE TEXT JAVAFX (HERENCIA)
public class Tipografia extends Text {

	// **USAREMOS POLIMORFISMO PARA CREAR METODOS
	// **METODOS PARA CREAR TEXTOS

	// **CREA TEXTO QUE SOLICITA TEXTO Y EL VALOR FUENTE
	public Tipografia(String label, int fontSize) {
		super();
		this.setFont(Font.font("Arial", FontWeight.findByWeight(400), fontSize));

	}

	// **CREA TEXTO QUE SOLICITA COLOR DE LA FUENTE
	public Tipografia(String label, int fontSize, Color color) {
		super(label);
		this.setFont(Font.font("Arial", FontWeight.findByWeight(400), fontSize));
		this.setFill(color);
	}

	// **CREA TEXTO QUE SOLICITA EL PESO DE GROSURA DE LA FUENTE
	public Tipografia(String label, int fontSize, int fontWeight) {
		super(label);
		this.setFont(Font.font("Arial", FontWeight.findByWeight(fontWeight), fontSize));
	}

	// **CREA TEXTO QUE SOLICITA EL PESO DE GROSURA Y COLOR DE LA FUENTE
	public Tipografia(String label, int fontSize, Color color, int fontWeight) {
		super(label);
		this.setFont(Font.font("Arial", FontWeight.findByWeight(fontWeight), fontSize));
		this.setFill(color);
	}

}
