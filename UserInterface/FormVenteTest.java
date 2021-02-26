package UserInterface;

import java.util.ArrayList;
import java.util.List;

import com.sun.prism.Image;

import Entites.Client;
import Entites.LigneCommande;
import Entites.Produit;
import Entites.Vente;
import IDAO.ClientDAO;
import ImplementationDAO.CategorieDaoImplL;
import ImplementationDAO.ImplClientDao;
import ImplementationDAO.ImplementationProduitDao;
import ImplementationDAO.VenteDaoImpL;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Entites.Categorie;


public class FormVenteTest {
	FormVenteHandler handler = new FormVenteHandler(this);
	  BorderPane root  = new BorderPane();
	   Stage window = new Stage();
	   GridPane paneClient = new GridPane();
	   Scene sc = new Scene(root);
	   HBox hbox = new HBox();
	   VBox vboxclients = new VBox();
	    Alert alertinf = new Alert(Alert.AlertType.INFORMATION);
	    Alert alertconf = new Alert(Alert.AlertType.CONFIRMATION);

List<Produit> produitsChoisi = new ImplementationProduitDao().getAll();
Client clt = new Client();
Label titleLigne = new Label("Ligne de commande");
         VBox vboxView = new VBox();
		TableView<LigneCommande> tableLignecmds = new TableView<>();
	   ObservableList<LigneCommande> observableTableProduit= FXCollections.observableArrayList();

	   
	   TableView<Produit> tableProduit = new TableView<Produit>();
	   ObservableList<Produit> observableProduit= FXCollections.observableArrayList();

	   double total=0;
	   List<LigneCommande> Lignecmds = new ArrayList<LigneCommande>();
	   Alert alert = new Alert(Alert.AlertType.ERROR);

	   Label titletop=new Label("GESTION DES VENTES");
	   
	Label clientnomLabel = new Label("Nom:");
	TextField clientnomTextField = new TextField();

	Label clientprenomLabel = new Label("Prenom");
	TextField clientprenomTextField = new TextField();



	

	Label clientadresseLabel = new Label("Adresse");
	TextField clientadresseTextField = new TextField();
	
	
	Label ProduitQteVenduLabel = new Label("QteVendu");
	TextField ProduitQteTextField = new TextField();
    //
	Label desLabel = new Label("Designation");
	   TextField desTextField = new TextField();
	   Label prixLabel = new Label("Prix");
	   TextField prixTextField = new TextField();
	
	//
	Button addbtnLigne=new Button("Ajouter");
	Button deletebtnLigne=new Button("Supprimer");
	Button savebtnvente = new Button("Enregistrer Vente");
	Button reglementbtn = new Button("Reglement");
	
	Label dateLabel = new Label("Date de Vente");
	DatePicker date = new DatePicker();
    HBox totalhbox = new HBox();
    Label totalLabel = new Label("Total :");
    Label totalLabelValue = new Label("0.0");

	   
	private void addColumnToTableView(){
           
	    TableColumn<LigneCommande, Produit> produitDesignationColon=new TableColumn<>("Designation");
	    produitDesignationColon.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit()));
        produitDesignationColon.setPrefWidth(100);
        
        TableColumn<LigneCommande,Double> produitPrixColon=new TableColumn<>("Prix");
        produitPrixColon.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit().getPrix()));
        produitPrixColon.setPrefWidth(100);
        
        TableColumn<LigneCommande, Integer> produitQteColon=new TableColumn<>("QteVendu");
        produitQteColon.setCellValueFactory(new PropertyValueFactory<>("qteVendu"));
        produitQteColon.setPrefWidth(100);
        
        TableColumn<LigneCommande, Integer> produitStotalColon=new TableColumn<>("Stotal");
        produitStotalColon.setCellValueFactory(new PropertyValueFactory<>("stotal"));
        produitStotalColon.setPrefWidth(100);
        tableLignecmds.getColumns().addAll(produitDesignationColon,produitPrixColon,produitQteColon,produitStotalColon);
        tableLignecmds.setItems(observableTableProduit);
	    this.observableTableProduit.setAll(Lignecmds);
	    //table view Produit
	    
	    TableColumn<Produit,String> des=new TableColumn<>("Designation");
        des.setCellValueFactory(new PropertyValueFactory<>("designation"));

        des.setPrefWidth(100);
        
        TableColumn<Produit,Double> prix=new TableColumn<>("Prix");
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        prix.setPrefWidth(100);
        
        TableColumn<Produit, Integer> qtestock=new TableColumn<>("QteStock");
        qtestock.setCellValueFactory(new PropertyValueFactory<>("qte"));
        qtestock.setPrefWidth(100);
        tableProduit.getColumns().addAll(des,prix,qtestock);
        this.observableProduit.setAll(produitsChoisi);
        tableProduit.setItems(observableProduit);


	}
	private void addStyleToNodes() {
		 sc.getStylesheets().add("/css/styles.css");
		 tableLignecmds.getStyleClass().add("table-row-cell");
		 tableLignecmds.setMinHeight(400);
		 tableLignecmds.setMinWidth(600);
		 hbox.setAlignment(Pos.CENTER);
	     hbox.setMinWidth(window.getWidth());
	     paneClient.setHgap(10);
	 	paneClient.setVgap(10);
	 	paneClient.setPadding(new Insets(20,20,25,25));
	 	vboxclients.setMinWidth(500);
	    hbox.getStyleClass().add("titleTopWindow");
		      titletop.setTextFill(Color.BLUE);
		      
		 titleLigne.getStyleClass().add("LabelTitle");
		 totalLabel.getStyleClass().add("LabelTotal");
    	 totalLabelValue.getStyleClass().add("LabelTotal");
		 totalhbox.getStyleClass().add("boxTotal");
    	 totalhbox.setSpacing(15);
		 
	}
	private void addElemntstoPane() {
		
		paneClient.add(clientnomLabel , 0, 1);
		paneClient.add(clientprenomLabel , 0, 2);
		paneClient.add(clientadresseLabel , 0, 3);
	     paneClient.add(clientnomTextField, 1, 1);
	     paneClient.add(clientprenomTextField, 1, 2);
	     paneClient.add(clientadresseTextField, 1, 3);
	     
	     paneClient.add(titleLigne,0,5);

	     paneClient.add(desLabel,  0, 7);
	     paneClient.add(prixLabel,  0, 8);
	     paneClient.add(desTextField,  1, 7);
		 paneClient.add(prixTextField,  1, 8);
	     paneClient.add(ProduitQteVenduLabel,  0, 9);
	     paneClient.add(ProduitQteTextField,  1, 9);
	    
	     paneClient.add(dateLabel, 0, 10);
	     paneClient.add(date, 1, 10);
	     paneClient.add(tableProduit,0,11);
	     paneClient.add(addbtnLigne,  1, 12);
	     paneClient.add(deletebtnLigne,  1, 13);
	     paneClient.add(savebtnvente, 8 ,12);
	     paneClient.add(reglementbtn, 8 ,13);

	     
	   

	   
	    addColumnToTableView();
	
	    hbox.getChildren().add(titletop);
	    vboxclients.setSpacing(10);
	    vboxclients.getChildren().add(tableLignecmds);
        totalhbox.getChildren().addAll(totalLabel,totalLabelValue);
	    vboxclients.getChildren().add(totalhbox);
	   
	    root.setCenter(vboxclients);
		root.setTop(hbox);
		root.setLeft(paneClient);
	}
	private void initelements(){
		 window.setScene(sc);
		   window.setTitle("liste des clients");
	}
	   public void addEvents() {
	

		   addbtnLigne.setOnAction(event->{
			   
			   handler.addLigneCmdToView();

			   
		   });
		   tableProduit.setOnMouseClicked(event -> {
			   Produit prodsel=tableProduit.getSelectionModel().getSelectedItem();
	             desTextField.setText(prodsel.getDesignation());
	             prixTextField.setText(String.valueOf(prodsel.getPrix()));

	         });
		   deletebtnLigne.setOnAction(event->{
			
			   alertconf.setContentText("Vous etes vraiment supprimee cette Ligne de Commande?");
			   alertconf.show();
			   handler.deleteLigneCmdFromView();
		   });
		   
		   savebtnvente.setOnAction(event->{
			
			   handler.saveVentewithLigneCmds();
			   alertinf.setContentText("La Vente est Bien Enregistre");
		       alertinf.showAndWait();
		
		   });
		   
		   reglementbtn.setOnAction(event->{
			   VenteDaoImpL daovente = new  VenteDaoImpL();
			   
			   FormReglement reg = new FormReglement();
			   reg.valuenomClient.setText(handler.formvente.clientnomTextField.getText()+" "+handler.formvente.clientprenomTextField.getText());
			  reg.valuenumBl.setText(new VenteDaoImpL().getLastId()+"");
			   reg.valueDate.setText(handler.formvente.date.getValue().toString());
			 //  reg.valuetotal.setText(handler.formvente.totalLabelValue.getText());
			   for(Vente v:daovente.getAll())
			   reg.valuetotal.setText(v.getTotal()+"");

			   
		   });
		   
	   }
	 
	   public  FormVenteTest() {
		   initelements();
		   
		   addElemntstoPane() ;
		   addStyleToNodes();
		  
		   addEvents();
		  
		   window.show();
		 
		 
		   window.setScene(sc);
		   window.show();
		   
	   }
}
