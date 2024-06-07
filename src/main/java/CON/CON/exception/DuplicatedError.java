package CON.CON.exception;

import org.springframework.dao.DuplicateKeyException;

public class DuplicatedError extends DuplicateKeyException {
    public DuplicatedError(String msg) {
        super(msg);
    }

    public DuplicatedError(String msg, Throwable cause) {
        super(msg, cause);
    }
}
