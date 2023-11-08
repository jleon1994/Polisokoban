package componentes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Logos {
	
	//**PARAMETRO DE LA CLASE LOGO
	private ImageView logoPoli;
	private ImageView imageMan;
	private ImageView fondoMenuPrincipal;
	
	
	
	//**CONTRUCTOR DE LA CLASE LOGOS
	 public Logos(Image imagen){
		
		logoPoli = new ImageView(imagen);
		logoPoli.setFitWidth(250);
		logoPoli.setFitHeight(75);
		
		imageMan = new ImageView(imagen);
		imageMan.setFitWidth(170);
		imageMan.setFitHeight(200);
		
		fondoMenuPrincipal = new ImageView(imagen);
		fondoMenuPrincipal.setFitWidth(800);
		fondoMenuPrincipal.setFitHeight(800);
		
		

	}
	
	public ImageView getLogoPoli() {
		return logoPoli;
	}
	
	public ImageView getimageMan() {
		return imageMan;
	}
	
	public ImageView getfondoMenuPrincipal() {
		return imageMan;
	}

}
