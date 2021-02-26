package UserInterface;

import java.time.LocalDate;

import com.sun.xml.internal.ws.message.PayloadElementSniffer;

import Entites.LigneCommande;
import Entites.Payment;
import Entites.PaymentType;
import Entites.Vente;
import ImplementationDAO.PaymentDaoImpL;
import ImplementationDAO.VenteDaoImpL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormReglement {
   Stage window  = new Stage();
   BorderPane root = new BorderPane();
   Scene sc = new Scene(root);
   GridPane pane = new GridPane();
   VBox vbox = new VBox();
   VBox vboxpay = new VBox();
	TableView<Payment> payments = new TableView<>();
    ObservableList<Payment> observableTablePayment= FXCollections.observableArrayList();
    ObservableList<PaymentType> observablePaymentType = FXCollections.observableArrayList(PaymentType.values());

	Label titledetailclt = new Label("Detail Client");
	Label nomClient = new Label("Client :");
	Label valuenomClient = new Label();
	
	Label numBL = new Label("Num BL :");
	Label valuenumBl = new Label();
	
	Label date = new Label("Date Vente:");
	Label valueDate = new Label();
	
	Label total= new Label("Total :");
	Label valuetotal = new Label();
	
	Label totalPaye = new Label("Total Paye");
	Label valuetotalPaye = new Label();

	Label resteLabel = new Label("Reste");
	Label valuereste = new Label();

	//title Payments
	Label titlePay = new Label("Payments");
	//
	//form payment
	
	Label numLabel = new Label("Numero");
	TextField numpayTextField = new TextField();
	
	Label dateLabel = new Label("Date");
	DatePicker datepayTextField = new DatePicker();
	Label montantLabel = new Label("Montant");
	TextField montantpayTextField = new TextField();

	Label typeLabel = new Label("Type");
	ChoiceBox<PaymentType> type = new ChoiceBox<>(observablePaymentType);
	Button savebtn = new Button("Enregistrer");
	
	
	//
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
	       
	       TableColumn<Payment, LocalDate> datePayment=new TableColumn<>("Date");
	       datePayment.setCellValueFactory(new PropertyValueFactory<>("date"));
	       datePayment.setPrefWidth(100);

	       payments.getColumns().addAll(numPayment,montantPayment,typePayment,datePayment);
	       payments.setItems(observableTablePayment);
	}
	private void initelements(){
		window.setTitle("Reglements");
		 window.setScene(sc);
		 window.setWidth(800);
		 window.setHeight(700);
		  
	}
	private void addStyleToNodes() {
		 sc.getStylesheets().add("/css/styles.css");
		 payments.getStyleClass().add("table-row-cell");

		 titledetailclt.getStyleClass().add("LabelTitle");
		 titlePay.getStyleClass().add("LabelTitle");
		 
          vbox.setMinWidth(250);
          //vboxpay.setMinWidth(400);
        //  vboxpay.setAlignment(Pos.CENTER);
          
	}
	private void addNodToPane() {
		pane.add(titledetailclt, 0,1);
		pane.add(nomClient, 0,2);
		pane.add(valuenomClient, 1,2);
		pane.add(numBL, 0,3);
		pane.add(valuenumBl, 1,3);
		pane.add(date, 0,4);
		pane.add(valueDate, 1,4);
		pane.add(total, 0,5);
		pane.add(valuetotal, 1,5);
		pane.add(resteLabel, 0,6);
		pane.add(valuereste, 1,6);
		pane.add(totalPaye, 0,7);
		pane.add(valuetotalPaye, 1,7);
		pane.add(titlePay, 0,8);
		pane.add(payments, 0,9);
		pane.setHgap(15);
		pane.setVgap(15);
		
		
		pane.add(numLabel,2,1);
		pane.add(numpayTextField,2,2);
		pane.add(dateLabel,2,3);
		pane.add(datepayTextField,2,4);
		pane.add(montantLabel,2,5);
		pane.add(montantpayTextField,2,6);
		pane.add(typeLabel,3,5);
		pane.add(type,3,6);
		pane.add(savebtn,2,7);

		
		
		vbox.getChildren().addAll(pane);
		addColonneToTable();
		//vboxpay.getChildren().addAll(numLabel,numpayTextField,dateLabel,datepayTextField);
	//vboxpay.getChildren().addAll(montantLabel,montantpayTextField,type);

		root.setCenter(vbox);
		//root.setRight(vboxpay);
	}
	
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    double reste=0;
  /*  public double calcul() {
		Vente v = new VenteDaoImpL().find(new VenteDaoImpL().getLastId());

    	PaymentDaoImpL daopay = new PaymentDaoImpL();
    	double t=0;
    	 for(Payment p:daopay.getPayment(v)) {
			    t+=p.getMontant();
    	 }
    	 System.out.println(t);
    	 return t;
    }*/
	public void addEvents() {
		savebtn.setOnAction(event->{
	    	PaymentDaoImpL daopay = new PaymentDaoImpL();

				Vente v = new VenteDaoImpL().find(new VenteDaoImpL().getLastId());
				Payment pay =new Payment(Integer.parseInt(this.numpayTextField.getText()),v,Double.parseDouble(this.montantpayTextField.getText()),this.datepayTextField.getValue(),type.getValue());
				this.observableTablePayment.add(pay);
			    new PaymentDaoImpL().createPayment(pay);
				alert.setContentText("cree payement avec le montant"+Double.parseDouble(this.montantpayTextField.getText()));
				alert.show();    
				double t=0;
				
				 for(Payment p:daopay.getPayment(v)) {
					 
					 t+=p.getMontant();
				 }
				 
				 reste=v.getTotal()-t;
				
				 
				  // reste-=Double.parseDouble(this.montantpayTextField.getText());
					this.valuetotalPaye.setText(t+"");
					this.valuereste.setText(reste+"");
					
		});
	}
	public FormReglement() {
		initelements();
		addNodToPane();
		addStyleToNodes();
		addEvents();
		window.show();
	}
}
