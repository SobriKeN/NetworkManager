package prr.core.exception;

public class AlreadyExistsTerminalException extends Exception {
    private String _terminalKey;

    public AlreadyExistsTerminalException(String key) {
        _terminalKey = key;
    }

    public String getKey() {
        return _terminalKey;
    }
}
