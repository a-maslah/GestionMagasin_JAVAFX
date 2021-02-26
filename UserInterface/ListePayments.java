package UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entites.Client;
import Entites.Payment;
import Entites.Vente;
import ImplementationDAO.ImplClientDao;
import ImplementationDAO.PaymentDaoImpL;
import ImplementationDAO.VenteDaoImpL;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ListePayments {

	
	Stage window  = new Stage();
	   BorderPane root = new BorderPane();
	   Scene sc = new Scene(root);
	   GridPane pane = new GridPane();
	   GridPane pane1 = new GridPane();
        Label title = new Label("Liste Payments des Clients");
	   TableView<Payment> payments = new TableView<>();
	    ObservableList<Payment> observableTablePayment= FXCollections.observableArrayList();
	   Button btnListPay = new Button("Liste Payments");
	    
	    TableView<Client> Clients = new TableView<>();
	    ObservableList<Client> observableTableClient= FXCollections.observableArrayList(new ImplClientDao().getAll());
	   private void initelements(){
			window.setTitle("Liste des Payments D'un Client");
			 window.setWidth(1000);
			 window.setHeight(700);
			 window.setScene(sc);
			  
		}
	   PaymentDaoImpL daop = new PaymentDaoImpL();
	   
	  
	 	   public void addColonneToTable() {
	       TableColumn<Payment, Integer> numPayment=new TableColumn<>("Num Payment");
	       numPayment.setCellValueFactory(new PropertyValueFactory<>("id"));
	       numPayment.setPrefWidth(100);
	       
	       TableColumn<Payment, Double> montantPayment=new TableColumn<>("Montant");
	       montantPayment.setCellValueFactory(new PropertyValueFactory<>("montant"));
	       montantPayment.setPrefWidth(100);
	       
	       TableColumn<Payment, String> typePayment=new TableColumn<>("Type");
	       typePayment.setCellValueFactory(new PropertyValueFactory<>("type"));
	       typePayment.setPrefWidth(100);
	       
	       TableColumn<Payment, Vente> clientvente_Payment=new TableColumn<>("Client");
	       clientvente_Payment.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getVente()));
	       clientvente_Payment.setPrefWidth(100);
	       
	       TableColumn<Payment, LocalDate> datePayment=new TableColumn<>("Date");
	       datePayment.setCellValueFactory(new PropertyValueFactory<>("date"));
	       datePayment.setPrefWidth(100);

	       payments.getColumns().addAll(numPayment,montantPayment,clientvente_Payment,typePayment,datePayment);
	       //observableTablePayment.addAll(ps);
	       payments.setItems(observableTablePayment);
	       //Liste Clients
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
	       ColEmailClient.setPrefWidth(150);
	       TableColumn<Client, String> ColAdresseClient=new TableColumn<>("Adresse");
	       ColAdresseClient.setCellValueFactory(new PropertyValueFactory<>("adresse"));
	       ColAdresseClient.setPrefWidth(100);
	       

	       Clients.getColumns().addAll(ColIdClient,ColNomClient,ColPrenomClient,ColTelClient,ColEmailClient,ColAdresseClient);
	       Clients.setItems(observableTableClient);
	       
	       
	       
	}
	 	  private void addStyleToNodes() {
	 	   	 sc.getStylesheets().add("/css/styles.css");
	 	   	 payments.getStyleClass().add("table-row-cell");
	 	   	 Clients.getStyleClass().add("table-row-cell");
	 	    title.getStyleClass().add("LabelTitle");
	 	    title.setMinWidth(window.getWidth());
	 	    title.setAlignment(Pos.CENTER);
	 	     pane.setHgap(20);
	 	     pane1.setVgap(10);
	 	    }
	   private void addNodToPane() {
		   root.getChildren().add(title);
		pane.add(Clients, 0, 2);
		pane.add(btnListPay, 0, 5);
		pane1.add(title,1 , 2);
		pane1.add(payments, 1, 4);
		addColonneToTable();
	     root.setLeft(pane);
	     root.setCenter(pane1);
	     
	     
	   }
	   public void addEvents() {
		   btnListPay.setOnAction(event->{
			   Client c=Clients.getSelectionModel().getSelectedItem();
			   VenteDaoImpL daov = new VenteDaoImpL();
			   PaymentDaoImpL daop = new PaymentDaoImpL();
	     	   List<Payment>   ps = new ArrayList<>();
	     	   for(Vente v:daov.getAll()) {
	     		   if(c.getNom().equals(v.getClient().getNom())) {
	     		   for(Payment p : daop.getPayment(v)) {
	     			 p.setVente(v);
	     			 ps.add(p);
	     			 this.observableTablePayment.setAll(ps);
	     		    }
	     		   }
	     		   }
	     		   
	     	   
	   
		   });
	   }
	  public ListePayments() {
		  initelements();
		  addNodToPane();
		  addStyleToNodes();
		  addEvents();
		  window.show();
	  }
}
