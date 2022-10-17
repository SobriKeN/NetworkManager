package prr.core.exception;

import java.io.Serializable;

public class UnknownIdentifierException extends Exception implements Serializable {

    private int id;

    public UnknownIdentifierException(int id) {
        this.id = id;
    }

    public int getIdException(){
        return id;
    }
}
