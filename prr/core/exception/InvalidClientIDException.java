package prr.core.exception;

import java.io.Serial;

public class InvalidClientIDException extends Exception{

    private static final long serialVersionUID = 7502542724080056944L;
    private String _key;

    public InvalidClientIDException(String key) {
        _key = key;
    }

    public String getID() {
        return _key;
    }
}
