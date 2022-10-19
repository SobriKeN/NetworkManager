package prr.core.exception;

public class ClientKeyAlreadyUsedException extends Exception{
    private String _key;

    public ClientKeyAlreadyUsedException(String key) {
        _key = key;
    }

    public String getKey() {
        return _key;
    }
}
