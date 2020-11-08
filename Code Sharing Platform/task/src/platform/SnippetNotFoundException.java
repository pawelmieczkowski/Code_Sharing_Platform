package platform;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SnippetNotFoundException extends RuntimeException  {

    public SnippetNotFoundException(String message) {
        super(message);
    }
}
