package exceptions;

public class NombreDouble extends Exception {

	private static final long serialVersionUID = 1L;

	public NombreDouble(int nombreEnDouble) {
		super("Le nombre" + nombreEnDouble + " est ecrit deux fois");
	}
}
