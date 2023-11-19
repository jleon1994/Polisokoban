package motor;

//****************//
//**--CLASE24**--//
//**************//

//**CON ESTA INTERFAZ DEFINIMOS METODOS QUE ACTUAN COMO EVENTOS EN EL JUEGO
public interface PortalEventos {

	// **METODO QUE SE LLAMA CUANDO HAY UN CAMBIO EN EL TABLERO DE JUEGO (METODO1)
	void onBoardChange(Board updatedBoard);

	// **SE LLAMA PARA NOTIFICAR QUE HA PASADO UN INTERVALO DE TIEMPO
	// DURANTE UNA VISTA PREVIA
	void onPreviewTimeTick(int milliseconds);

	// **SE LLAMA PARA NOTIFICAR QUE HA PASADO UN INTERVALO DE TIEMPO
	// DURANTE UN NIVEL ESPECIFICO
	void onLevelTimeTick(int milliseconds);

	// **METODO QUE SE LLAMA CUANDO UN JUGADOR PIERDE UN NIVEL ESPECIFICO
	void onLoseLevel();

	// **INVOCADO CUANDO EL JUGADOR COMPLETO UN NIVEL
	void onFinishLevel(int levelNum);

	// **SE LLAMA CUANDO EL JUGADOR HA COMPLETADO TODO EL JUEGO
	void onFinishGame();
}
