package prr.core.exception;

public class ClientKeyAlreadyUsedException extends Exception{

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202110252109L;

    private String _key;

    public ClientKeyAlreadyUsedException(String key) {
        _key = key;
    }

    public String getKey() {
        return _key;
    }
}
