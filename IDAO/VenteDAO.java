package IDAO;

import java.util.List;

import Entites.Vente;

public interface VenteDAO {
public Vente create(Vente v);

public int getLastId();
public List<Vente> getAll();

public Vente find(int id);
}
