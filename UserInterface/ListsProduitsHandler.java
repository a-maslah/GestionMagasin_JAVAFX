package UserInterface;

import java.time.LocalDate;
import java.util.List;

import Entites.Client;
import Entites.Produit;
import IDAO.ProduitDAO;
import ImplementationDAO.ImplementationProduitDao;

public class ListsProduitsHandler {
	 ListeProduits listeproduits = null;

	 ProduitDAO pdaoproduit = new ImplementationProduitDao();
	public ListsProduitsHandler(ListeProduits listeproduits) {
		this.listeproduits = listeproduits;
	}
	  public void updateListeProduitWindow() {
	      List<Produit> produits = pdaoproduit.getAll();
	      listeproduits.observableTable.addAll(produits);
	      CalculTotal() ;
	  }
	  double total=0;
	  private void CalculTotal() {
		  for(Produit p:listeproduits.observableTable) 
			  total+=p.getPrix()*p.getQte();
		  listeproduits.totalLabelValue.setText(total+"");	
	  }
	
	    
	  public void deleteClick() {
	             Produit prodselectionner=  listeproduits.tableProduits.getSelectionModel().getSelectedItem();
	             try {
	            	 pdaoproduit.delete(prodselectionner);
	                 listeproduits.observableTable.remove(prodselectionner);
	                 total-=prodselectionner.CalcTotal();
	       		  listeproduits.totalLabelValue.setText(total+"");	

	             }catch (Exception e) {
	            	  listeproduits.alert.setContentText(e.getMessage());
	            	  listeproduits.alert.setTitle("impossible de supprimer ce produit il faut selectionner!!!");
	            	  listeproduits.alert.show();
	             }
	  }
	  public void modifierClick() {
	 			if (listeproduits.produitDesignationTextField.getText().isEmpty() || listeproduits.produitPrixTextField.getText().isEmpty() ||listeproduits.produitQteTextField.getText().isEmpty() || listeproduits.choiceBoxCategorie.getValue() == null  ||listeproduits.produitDateTextField.getValue()==null){
	 				listeproduits.alert.setTitle("Error Saisi");
	 				listeproduits.alert.setContentText(" SVP tous les champs sont obligatoires !!");
	 				listeproduits.alert.setHeaderText("Ooops !!!!!");
	 				listeproduits.alert.showAndWait();
	                 return;
	             }
	             try {
	 	            Double.parseDouble(listeproduits.produitPrixTextField.getText());
	             }catch (Exception e){
	            	 listeproduits.alert.setContentText("prix doit etre nombre !!");
	            	 listeproduits.alert.setHeaderText("Ooops !!!!!");
	            	 listeproduits.alert.showAndWait();
	             }
	             //update le produit selctionner dans la tableView
	             Produit prod=listeproduits.tableProduits.getSelectionModel().getSelectedItem();
	             prod.setDesignation(listeproduits.produitDesignationTextField.getText());
	             prod.setPrix(Double.parseDouble(listeproduits.produitPrixTextField.getText()));
	             prod.setQte(Integer.parseInt(listeproduits.produitQteTextField.getText()));
	             if (listeproduits.choiceBoxCategorie !=null) 
	            	 prod.setC(listeproduits.choiceBoxCategorie.getValue());
	             LocalDate valueDate = listeproduits.produitDateTextField.getValue();
	             prod.setDate(valueDate);
	//faire appel a la methode update qui fait la modification dans la BD
	              pdaoproduit.update(prod);
	              CalculTotal();
	             listeproduits.observableTable.set(listeproduits.observableTable.indexOf(prod),prod);

	  }
}

