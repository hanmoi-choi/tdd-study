package unimelb.daniel.finances.util;

public class Require {

	public static void that(boolean expression, String message) {
        
		if(!expression) throw new RequireException(message);
		
	}

}


class RequireException extends RuntimeException{

	public RequireException(String message) {
        super(message);
	}

	private static final long serialVersionUID = 1L;
	
}
