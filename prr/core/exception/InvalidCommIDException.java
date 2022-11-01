package prr.core.exception;

public class InvalidCommIDException extends Exception{
    private int _key;

    public InvalidCommIDException(int key) {
        _key = key;
    }

    public int getID() {
        return _key;
    }
}
