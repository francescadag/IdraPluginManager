package IdraPluginManager.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 7992904489502842099L;

	public BadRequestException() {
		this("EntityRepresentationModel not found!");
	}

	public BadRequestException(String message) {
		this(message, null);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ErrorResponse toErrorResponse() {
		ErrorResponse r = new ErrorResponse();
		r.setError("Bad Request");
		r.setMessage(this.getMessage());
		r.setStatus(HttpStatus.BAD_REQUEST.value());
		return r;
	}
	
}
