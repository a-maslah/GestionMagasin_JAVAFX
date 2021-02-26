package IDAO;

import java.util.List;

import Entites.LigneCommande;
import Entites.Vente;

public interface LigneCommandeDAO {

	public void createLigne(LigneCommande cmd,Vente v);

	public int getLastId();

	public List<LigneCommande> getLcmd(int id);
}
