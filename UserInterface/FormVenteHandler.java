package UserInterface;

import java.util.List;

import Entites.Client;
import Entites.LigneCommande;
import Entites.Produit;
import Entites.Vente;
import IDAO.ClientDAO;
import ImplementationDAO.ImplClientDao;
import ImplementationDAO.ImplementationProduitDao;
import ImplementationDAO.VenteDaoImpL;

public class FormVenteHandler {
  FormVenteTest formvente=null;
  ClientDAO pdaoclients = new ImplClientDao();


public FormVenteHandler(FormVenteTest formvente) {
	super();
	this.formvente = formvente;
}

public void addLigneCmdToView() {
	ImplementationProduitDao  prod = new ImplementationProduitDao();
	for(Produit pr : prod.getAll()) 
		if(pr.getDesignation().equals(formvente.desTextField.getText())) {
			Produit p = new Produit(pr.getId(),formvente.desTextField.getText(),Double.parseDouble(formvente.prixTextField.getText()));
		   LigneCommande cmd = new LigneCommande(Integer.parseInt(formvente.ProduitQteTextField.getText()),p);
		   formvente.Lignecmds.add(cmd);
		   formvente.observableTableProduit.setAll(formvente.Lignecmds);
		   formvente.total+=Integer.parseInt(formvente.ProduitQteTextField.getText())*cmd.getProduit().getPrix();
		   formvente.totalLabelValue.setText(formvente.total+""+"DH");
		}
}
public void deleteLigneCmdFromView() {
	LigneCommande c = formvente.tableLignecmds.getSelectionModel().getSelectedItem();
	
	   formvente.Lignecmds.remove(c);
    formvente.observableTableProduit.setAll(formvente.Lignecmds);
	   formvente.total-=c.getStotal();
	   formvente.totalLabelValue.setText(formvente.total+""+"DH");
}
public void saveVentewithLigneCmds() {
	Vente v = new Vente();
	  
	   v.setListCommande(formvente.Lignecmds);
	   List<Client> clients = pdaoclients.getAll();
	   for(Client c:clients) {
		   if(c.getNom().equals(formvente.clientnomTextField.getText()))
			 formvente.clt=c;
	   }
	   v.setClient(formvente.clt);
	   v.setDate(formvente.date.getValue());
	   v=new VenteDaoImpL().create(v);
	   System.out.println(v.getId());
}
  
}
