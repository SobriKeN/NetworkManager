package prr.core;

import java.io.Serializable;

/**
 * Class representing a client in the app
 */
public class Client implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 2084398098986L;

    /** Client's key */
    private String key;

    /** Client's name */
    private String name;

    /** Client's tax number */
    private int tax;

    /** Client's level */
    private ClientLevel level;

    /** To check if the client can receive notifications */
    private boolean recieveNotifications;

    /**
     * Main Construtor
     * @param key
     * @param name
     * @param tax
     * @param level
     * @param recieveNotifications
     */
    public Client(String key, String name, int tax, ClientLevel level, boolean recieveNotifications) {
        this.key = key;
        this.name = name;
        this.tax = tax;
        this.level = level;
        this.recieveNotifications = recieveNotifications;
    }

    /** @return client's key */
    public String get_key() {
        return key;
    }

    /** @return client's name */
    public String get_name() {
        return name;
    }

    /** @return client's tax number */
    public int get_tax() {
        return tax;
    }

    /** @return client's level */
    public ClientLevel get_level() {
        return level;
    }

    /** @return if the client can receive notifications */
    public boolean is_recieveNotifications() {
        return recieveNotifications;
    }
}
