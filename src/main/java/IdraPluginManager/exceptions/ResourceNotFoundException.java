package IdraPluginManager.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1636679309287814744L;

	public ResourceNotFoundException() {
		this("EntityRepresentationModel not found!");
	}

	public ResourceNotFoundException(String message) {
		this(message, null);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ErrorResponse toErrorResponse() {
		ErrorResponse r = new ErrorResponse();
		r.setError("Not Found");
		r.setMessage(this.getMessage());
		r.setStatus(HttpStatus.NOT_FOUND.value());
		return r;
	}
}
