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

    /** The client's Tariff plan */
    private TariffPlanBasic _planoTarifario;

    /**
     * Main Construtor
     * @param key
     * @param name
     * @param tax
     * @param recieveNotifications
     * @param planoTarifario
     */
    public Client(String key, String name, int tax, boolean recieveNotifications, TariffPlanBasic planoTarifario) {
        this._key = key;
        this._name = name;
        this._tax = tax;
        this._level = ClientLevel.NORMAL;
        this._recieveNotifications = recieveNotifications;
        this._planoTarifario = planoTarifario;
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
