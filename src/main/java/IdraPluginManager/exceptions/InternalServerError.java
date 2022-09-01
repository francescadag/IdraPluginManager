package IdraPluginManager.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerError extends RuntimeException{

	private static final long serialVersionUID = -3203421433795459808L;

	public InternalServerError() {
		super("EntityRepresentationModel not found!");
	}

	public InternalServerError(String message) {
		super(message, null);
	}

	public InternalServerError(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ErrorResponse toErrorResponse() {
		ErrorResponse r = new ErrorResponse();
		r.setError("Internal Server Error");
		r.setMessage(this.getMessage());
		r.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return r;
	}
	
}
