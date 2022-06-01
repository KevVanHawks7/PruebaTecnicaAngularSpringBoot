package backend.api.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SrcNotFoundException extends RuntimeException{
    private static final long serialVersionId = 1L;

    public SrcNotFoundException(String mensaje){
        super(mensaje);
    }
}
