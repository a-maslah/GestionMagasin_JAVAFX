package IDAO;


import java.sql.SQLException;
import java.util.List;

import Entites.Produit;

public interface ProduitDAO {
	
	public int getId();
	public List<Produit> getAll();
	public void create(Produit p);

	public void delete(Produit p) throws SQLException;


	public void update(Produit p);
	Produit find(int id);


	
	
}

