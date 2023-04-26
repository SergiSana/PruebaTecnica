package excepcion;

public class ExcepcionPersonalizada extends Exception{

	private static final long serialVersionUID = 4862157800292464472L;

	public ExcepcionPersonalizada(String message, Throwable cause) {
        super(message, cause);
    }
}
