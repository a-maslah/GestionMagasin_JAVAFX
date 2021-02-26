package IDAO;

import java.util.List;

import Entites.Categorie;
import Entites.Produit;

public interface CategorieDAO {
	public void create(Categorie c);

	public int getId();

	public List<Categorie> getAll();

	public Categorie find(int id);

	public void delete(Categorie c) throws Exception;

	public void update(Categorie p);
	public List<Produit> getProduitOfCategorie(Categorie catg);
}
