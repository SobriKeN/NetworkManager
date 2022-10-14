package prr.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import prr.core.exception.UnrecognizedEntryException;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {
  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  private List<Terminal> _terminals;
  private List<Client> _clients;

  public Network(){
    _terminals = new ArrayList<>();
    _clients = new ArrayList<>();
  }

  public void registerClient(String name, int taxNumber, String key){
    TariffPlanBasic basic = new TariffPlanBasic(String name);
    ClientLevel level = ClientLevel.NORMAL;
    Client client = new Client(key,name, taxNumber, level, true, basic);

  }
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }

}

