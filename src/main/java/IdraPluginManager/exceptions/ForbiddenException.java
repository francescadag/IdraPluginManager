package IdraPluginManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 7992904489502842099L;

	public ForbiddenException() {
		this("EntityRepresentationModel not found!");
	}

	public ForbiddenException(String message) {
		this(message, null);
	}

	public ForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ErrorResponse toErrorResponse() {
		ErrorResponse r = new ErrorResponse();
		r.setError("Forbidden");
		r.setMessage(this.getMessage());
		r.setStatus(HttpStatus.FORBIDDEN.value());
		return r;
	}
}
