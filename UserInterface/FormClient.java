package UserInterface;

import Entites.Client;
import IDAO.ClientDAO;
import IDAO.ProduitDAO;
import ImplementationDAO.ImplClientDao;
import ImplementationDAO.ImplementationProduitDao;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormClient {
Stage window = new Stage();
VBox root = new VBox();
Scene sc = new Scene(root);
HBox hboxButtons = new HBox();
Alert alerterror  = new Alert(Alert.AlertType.ERROR);
Alert alertinfo  = new Alert(Alert.AlertType.INFORMATION);


ClientDAO clientdao=new ImplClientDao();

Label title  = new Label("Ajouter un Client");

Label clientnomLabel = new Label("Nom:");
TextField clientnomTextField = new TextField();

Label clientprenomLabel = new Label("Prenom");
TextField clientprenomTextField = new TextField();

Label clienttelephoneLabel = new Label("Telephone");
TextField clienttelephoneTextField = new TextField();

Label clientemailLabel = new Label("Email");
TextField clientemailTextField = new TextField();

Label clientadresseLabel = new Label("Adresse");
TextField clientadresseTextField = new TextField();
Button btnaddclient = new Button("Ajouter");
Button btncancelclient = new Button("Annuler");
Button btninitclient = new Button("Reinitialiser");

public void addStylesToNodes() {
    sc.getStylesheets().add("/css/styles.css");
    title.getStyleClass().add("LabelTitle");
    title.setMinWidth(window.getWidth());
    title.setAlignment(Pos.CENTER);
    clientnomLabel.getStyleClass().add("labelForm");
    clientprenomLabel.getStyleClass().add("labelForm");
    clienttelephoneLabel.getStyleClass().add("labelForm");
    clientemailLabel.getStyleClass().add("labelForm");
    clientadresseLabel.getStyleClass().add("labelForm");
    
    root.setSpacing(20);
    hboxButtons.setSpacing(20);

}
public void addEvents() {
	 window.setOnCloseRequest(event->{
		 event.consume();
	 });
	 btncancelclient.setOnAction(event->{
		
	 window.close();
	 } );
	 btnaddclient.setOnAction(event->{
		 if(this.clientnomTextField.getText().isEmpty()|| this.clientprenomTextField.getText().isEmpty()|| this.clienttelephoneTextField.getText().isEmpty() || this.clientemailTextField.getText().isEmpty() || this.clientadresseTextField.getText().isEmpty())
		 {
			 alerterror.setTitle("Erreur Saisi");
			 alerterror.setContentText("SVP tous les champs sont obligatoires!!");
			 alerterror.setHeaderText("Attention !!!!!");
			 alerterror.showAndWait();
	            return;
		 }
		 
		 Client client = new Client(clientdao.getIdClient(),clientnomTextField.getText() , clientprenomTextField.getText(),clienttelephoneTextField.getText() ,clientemailTextField.getText(), clientadresseTextField.getText());
	     clientdao.create(client);
	     alertinfo.setContentText("Client ajoute avec Succes");
		 alertinfo.showAndWait();

	 });
	 btninitclient.setOnAction(event->{
		 clientnomTextField.setText("");
		 clientprenomTextField.setText("");
		 clienttelephoneTextField.setText("");
		 clientemailTextField.setText("");
		 clientadresseTextField.setText("");

	 });
	 
}
private void addNodetoWindow() {
	root.getChildren().add(title);
	root.getChildren().addAll(clientnomLabel,clientnomTextField);
	root.getChildren().addAll(clientprenomLabel ,clientprenomTextField);
	root.getChildren().addAll(clienttelephoneLabel, clienttelephoneTextField);
	root.getChildren().addAll(clientemailLabel,clientemailTextField);
	root.getChildren().addAll(clientadresseLabel,clientadresseTextField);
    hboxButtons.getChildren().addAll(btnaddclient,btncancelclient,btninitclient);
    root.getChildren().add(hboxButtons);
}

private void initElements() {
	window.setScene(sc);
	window.setHeight(600);
	window.setWidth(700);
	window.setTitle("Nouveau Client ");
}


public FormClient () {
	initElements();
	addNodetoWindow();
	
	addStylesToNodes();
	addEvents() ;
	
	window.show();
}






}
