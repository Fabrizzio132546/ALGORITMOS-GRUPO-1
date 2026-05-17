package Exceptions;

public class ItemNotFound extends Exception{
	// constructor que recibe el mensaje personalizado
	public ItemNotFound(String mensaje) {
		super(mensaje);
	}
	// constructor por defecto
	public ItemNotFound() {
		super();
	}
}
