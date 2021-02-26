package UserInterface;

import java.time.LocalDate;

import Entites.Categorie;
import Entites.Client;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListeClients {
ListeClientsHandler handler =  new ListeClientsHandler(this);
   BorderPane root  = new BorderPane();
   Stage window = new Stage();
   GridPane paneClient = new GridPane();
   Scene sc = new Scene(root);
   HBox hbox = new HBox();
   VBox vboxclients = new VBox();
//test
   Button test = new Button("Nouveau BL");
   
   //
	TableView<Client> tableClients=new TableView<>();
   ObservableList<Client> observableTableClient= FXCollections.observableArrayList();
   Alert alert = new Alert(Alert.AlertType.ERROR);

   Label titletop=new Label("GESTION DES CLIENTS");
   
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
Button editbtnclient=new Button("Modifier");
Button deletebtnclient=new Button("Supprimer");
Button initclient=new Button("Reinitialiser");
   
private void addColumnToTableView(){
    TableColumn<Client, Integer> ColIdClient=new TableColumn<>("IdClient");
    ColIdClient.setCellValueFactory(new PropertyValueFactory<>("id"));
    ColIdClient.setPrefWidth(100);

    TableColumn<Client, String> ColNomClient=new TableColumn<>("Nom");
    ColNomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
    ColNomClient.setPrefWidth(100);
    

    TableColumn<Client, String> ColPrenomClient=new TableColumn<>("Prenom");
    ColPrenomClient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    ColPrenomClient.setPrefWidth(100);

    TableColumn<Client, String> ColTelClient=new TableColumn<>("Telephone");
    ColTelClient.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    ColTelClient.setPrefWidth(100);

    TableColumn<Client, String> ColEmailClient=new TableColumn<>("Email");
    ColEmailClient.setCellValueFactory(new PropertyValueFactory<>("email"));
    ColEmailClient.setPrefWidth(250);
    TableColumn<Client, String> ColAdresseClient=new TableColumn<>("Adresse");
    ColAdresseClient.setCellValueFactory(new PropertyValueFactory<>("adresse"));
    ColAdresseClient.setPrefWidth(100);
    

    tableClients.getColumns().addAll(ColIdClient,ColNomClient,ColPrenomClient,ColTelClient,ColEmailClient,ColAdresseClient);
    tableClients.setItems(observableTableClient);

}
private void addStyleToNodes() {
	 sc.getStylesheets().add("/css/styles.css");
	 tableClients.getStyleClass().add("table-row-cell");
	 tableClients.setMinHeight(500);
	 tableClients.setMinWidth(600);
	 hbox.setAlignment(Pos.CENTER);
     hbox.setMinWidth(window.getWidth());
     paneClient.setHgap(40);
 	paneClient.setVgap(40);
 	paneClient.setPadding(new Insets(15,15,15,15));
    hbox.getStyleClass().add("titleTopWindow");
	      titletop.setTextFill(Color.BLUE);
	 
}
private void addElemntstoPane() {
	
	paneClient.add(clientnomLabel , 0, 1);
	paneClient.add(clientprenomLabel , 0, 2);
	paneClient.add(clienttelephoneLabel ,0, 3);
	paneClient.add(clientemailLabel , 0, 4);
	paneClient.add(clientadresseLabel , 0, 5);

     paneClient.add(clientnomTextField, 1, 1);
     paneClient.add(clientprenomTextField, 1, 2);
     paneClient.add(clienttelephoneTextField, 1, 3);
     paneClient.add(clientemailTextField,1,4);
     paneClient.add(clientadresseTextField, 1, 5);

     
     HBox hbox1 = new HBox();
     hbox1.getChildren().add(deletebtnclient);
     hbox1.getChildren().add(initclient);
     hbox1.getChildren().add(editbtnclient);
     //test
     hbox1.getChildren().add(test);
     
     hbox1.setSpacing(30);

   
    addColumnToTableView();
    
    hbox.getChildren().add(titletop);
    vboxclients.getChildren().add(tableClients);
    vboxclients.getChildren().add(hbox1);
  
}
private void initelements(){
	 window.setScene(sc);
	   window.setTitle("liste des clients");
	  root.setCenter(vboxclients);
	   root.setTop(hbox);
	  root.setLeft(paneClient);

}
   public void addEvents() {
	   initclient.setOnAction(event->{
		   clientnomTextField.setText("");
		   clientprenomTextField.setText("");
		   clienttelephoneTextField.setText("");
		   clientemailTextField.setText("");
		   clientadresseTextField.setText("");

	   });
	   deletebtnclient.setOnAction(event->{
		   handler.deleteClick();
	   });
	   editbtnclient.setOnAction(event->{
		   
		   handler.editClick();
		  
		   
	   });
	   
	   test.setOnAction(event->{
		   Client c= tableClients.getSelectionModel().getSelectedItem();
		   if(c!=null) {
		   FormVenteTest f = new FormVenteTest();
		   f.clientnomTextField.setText(c.getNom());
		   f.clientprenomTextField.setText(c.getPrenom());
		   f.clientadresseTextField.setText(c.getAdresse());
		   }
          // new FormVente();
	   });
	   //listpay
	   
	   
	   
	   
	   //
	   tableClients.setOnMouseClicked(event -> {

	          Client tabClt=handler.listeclts.tableClients.getSelectionModel().getSelectedItem();
	          handler.listeclts.clientnomTextField.setText(tabClt.getNom());
	          handler.listeclts.clientprenomTextField.setText(tabClt.getPrenom());
	          handler.listeclts.clienttelephoneTextField.setText(tabClt.getTelephone());
	          handler.listeclts.clientemailTextField.setText(tabClt.getEmail());
	          handler.listeclts.clientadresseTextField.setText(tabClt.getAdresse());

	      });
   }
   public  ListeClients() {
	   initelements();
	   addElemntstoPane() ;
	   addStyleToNodes();
	   
	   
	   handler.updateListeClientindow();
	   addEvents();
	  
	   window.show();
	   
   }
   
   
}
