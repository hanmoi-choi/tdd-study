package unimelb.daniel.finances.util;

public class UnreacheableCodeException extends RuntimeException{

	private static final long serialVersionUID = 1L;
    
	public UnreacheableCodeException() {
        super("Supposedly Unreacheable Code Excecuted!");
	}

}
