package UserInterface;

import java.time.LocalDate;
import java.util.List;

import Entites.Client;
import IDAO.ClientDAO;
import ImplementationDAO.ImplClientDao;

public class ListeClientsHandler {
  ListeClients listeclts = null;
  public ListeClientsHandler(ListeClients listeclts) {
	this.listeclts = listeclts;
}
  ClientDAO pdoaclient = new ImplClientDao();
  public void updateListeClientindow() {
      List<Client> clients = pdoaclient.getAll();
      listeclts.observableTableClient.addAll(clients);
  }
  public void deleteClick() {
	  Client clt  =listeclts.tableClients.getSelectionModel().getSelectedItem();
		try { 
			
	  pdoaclient.delete(clt);
	  listeclts.observableTableClient.remove(clt);
		
  }catch (Exception e) {
      listeclts.alert.setContentText(e.getMessage());
      listeclts.alert.setTitle("impossible !!!");
      listeclts.alert.show();
  }
	  
}
  public void editClick() {
		if (listeclts.clientnomTextField.getText().isEmpty() || listeclts.clientprenomTextField.getText().isEmpty() ||listeclts.clienttelephoneTextField.getText().isEmpty()
				|| listeclts.clientemailTextField.getText().isEmpty()  ||listeclts.clientadresseTextField.getText().isEmpty()){
            listeclts.alert.setTitle("Error Saisi");
            listeclts.alert.setContentText(" SVP tous les champs sont obligatoires !!");
            listeclts.alert.setHeaderText("Attention!!!!");
            listeclts.alert.showAndWait();
            return;
        }
        Client clt=listeclts.tableClients.getSelectionModel().getSelectedItem();

        clt.setNom(listeclts.clientnomTextField.getText());
        clt.setPrenom(listeclts.clientprenomTextField.getText());
        clt.setTelephone(listeclts.clienttelephoneTextField.getText());
        clt.setEmail(listeclts.clientemailTextField.getText());
        clt.setAdresse(listeclts.clientadresseTextField.getText());
       
      
		
        
        pdoaclient.update(clt);
        
        listeclts.observableTableClient.set(listeclts.observableTableClient.indexOf(clt),clt);

        
  }
 
}
