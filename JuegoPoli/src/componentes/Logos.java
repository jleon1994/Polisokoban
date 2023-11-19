package componentes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//****************//
//**--CLASE19**--//
//**************//


//**ESTA CLASE PERMITE ENCAPSULAR Y GESTIONAR VARIAS IMAGENES DE MANERA CENTRALIZADA
public class Logos {
	
	//**ATRIBUTOS**//
	private ImageView logoPoli;
	private ImageView imageMan;
	private ImageView fondoMenuPrincipal;
	private ImageView imageBloque;
	private ImageView imageCaja;
	private ImageView fondoCesped;
	
	
	
	//**METODO CONTRUCTOR DE LA CLASE LOGOS PARA INICIALIZAR CADA IMAGEVIEW (METODO1)
	 public Logos(Image imagen){
		
		logoPoli = new ImageView(imagen);
		logoPoli.setFitWidth(250);
		logoPoli.setFitHeight(75);
		
		imageMan = new ImageView(imagen);
		imageMan.setFitWidth(110);
		imageMan.setFitHeight(250);
		
		fondoMenuPrincipal = new ImageView(imagen);
		fondoMenuPrincipal.setFitWidth(800);
		fondoMenuPrincipal.setFitHeight(800);
		
		imageBloque = new ImageView(imagen);
		
		imageCaja = new ImageView(imagen);
		
		fondoCesped = new ImageView(imagen);
		
		
		

	}
	
	 //**METODO GET PARA OBTENER IMAGEN (METODO2)
	public ImageView getLogoPoli() {
		return logoPoli;
	}
	
	//**METODO GET PARA OBTENER IMAGEN (METODO3)
	public ImageView getimageMan() {
		return imageMan;
	}
	
	//**METODO GET PARA OBTENER IMAGEN (METODO4)
	public ImageView getfondoMenuPrincipal() {
		return imageMan;
	}
	
	//**METODO GET PARA OBTENER IMAGEN (METODO5)
	public ImageView getimageBloque() {
		return imageBloque;
	}

	//**METODO GET PARA OBTENER IMAGEN (METODO6)
	public ImageView getimageCaja() {
		return imageCaja;
	}

	//**METODO GET PARA OBTENER IMAGEN (METODO7)
	public ImageView getfondoCesped() {
		return fondoCesped;
	}

}
