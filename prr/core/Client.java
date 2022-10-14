package prr.core;

import java.io.Serializable;

/**
 * Class representing a client in the app
 */
public class Client implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 2084398098986L;

    /** Client's key */
    private String _key;

    /** Client's name */
    private String _name;

    /** Client's tax number */
    private int _tax;

    /** Client's level */
    private ClientLevel _level;

    /** To check if the client can receive notifications */
    private boolean _recieveNotifications;

    /**
     * Main Construtor
     * @param _key
     * @param _name
     * @param _tax
     * @param _level
     * @param _recieveNotifications
     */
    public Client(String _key, String _name, int _tax, ClientLevel _level, boolean _recieveNotifications) {
        this._key = _key;
        this._name = _name;
        this._tax = _tax;
        this._level = _level;
        this._recieveNotifications = _recieveNotifications;
    }

    /** @return client's key */
    public String get_key() {
        return _key;
    }

    /** @return client's name */
    public String get_name() {
        return _name;
    }

    /** @return client's tax number */
    public int get_tax() {
        return _tax;
    }

    /** @return client's level */
    public ClientLevel get_level() {
        return _level;
    }

    /** @return if the client can receive notifications */
    public boolean is_recieveNotifications() {
        return _recieveNotifications;
    }
}
