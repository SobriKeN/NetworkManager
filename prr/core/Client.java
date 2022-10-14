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

    /** The client's Tariff plan */
    private TariffPlanBasic planoTarifario;

    /**
     * Main Construtor
     * @param key
     * @param name
     * @param tax
     * @param recieveNotifications
     * @param planoTarifario
     */
    public Client(String key, String name, int tax, boolean recieveNotifications, TariffPlanBasic planoTarifario) {
        this.key = key;
        this.name = name;
        this.tax = tax;
        this.level = ClientLevel.NORMAL;
        this.recieveNotifications = recieveNotifications;
        this.planoTarifario = planoTarifario;
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
