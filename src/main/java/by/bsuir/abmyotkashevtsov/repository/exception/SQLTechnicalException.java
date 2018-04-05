package by.bsuir.abmyotkashevtsov.repository.exception;

import java.sql.SQLException;

public class SQLTechnicalException extends SQLException {

    public SQLTechnicalException() {
    }

    public SQLTechnicalException(String message) {
        super(message);
    }

    public SQLTechnicalException(Throwable exception) {
        super(exception);
    }

    public SQLTechnicalException(String message, Throwable exception) {
        super(message, exception);
    }
}
