package prr.core.exception;

public class InvalidTerminalIDException extends Exception{
    private String _terminalKey;

    public InvalidTerminalIDException(String key) {
        _terminalKey = key;
    }

    public String getID() {
        return _terminalKey;
    }
}
