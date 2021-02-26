package IDAO;

import java.sql.SQLException;
import java.util.List;

import Entites.Client;

public interface ClientDAO {
	    public void create(Client p);
	    public int getIdClient();
	    public void delete(Client p) throws SQLException;
	    public void update(Client p);
	    public List<Client> getAll();
	    public Client find(int id);
}
