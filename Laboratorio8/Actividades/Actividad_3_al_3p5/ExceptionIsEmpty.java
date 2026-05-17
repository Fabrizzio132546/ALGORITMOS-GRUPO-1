package Exceptions;

public class ExceptionIsEmpty extends Exception{
	// constructor que recibe el mensaje
	public ExceptionIsEmpty(String mensaje) {
		super(mensaje);
	}
	// constructor por defecto
	public ExceptionIsEmpty() {
		super();
	}
}
