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
     * @param key
     * @param name
     * @param tax
     */
    public Client(String key, String name, int tax) {
        this._key = key;
        this._name = name;
        this._tax = tax;
        this._level = ClientLevel.NORMAL;
        this._recieveNotifications = true;
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
