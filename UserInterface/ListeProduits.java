package UserInterface;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

import Entites.Categorie;
import Entites.Produit;
import IDAO.ProduitDAO;
import ImplementationDAO.CategorieDaoImplL;
import ImplementationDAO.ImplementationProduitDao;

public class ListeProduits {
	ListsProduitsHandler handler =  new ListsProduitsHandler(this);
	Stage window = new Stage();
    private BorderPane root=new BorderPane();
    Scene sc = new Scene(root);
    GridPane pane = new GridPane();
    VBox vboxproduit=new VBox();

    Alert alert = new Alert(Alert.AlertType.ERROR);
     
	TableView<Produit> tableProduits=new TableView<>();
    ObservableList<Produit> observableTable= FXCollections.observableArrayList();
    ObservableList<Categorie> categories = FXCollections.observableArrayList((new CategorieDaoImplL().getAll()));

    Label title=new Label("GESTION DES PRODUITS");
    ProduitDAO pdao=new ImplementationProduitDao();
    List<Produit> produits=pdao.getAll();
    Label produitDesignationLabel = new Label("Designation :");
	TextField produitDesignationTextField  = new TextField();
	
	Label produitPrixLabel = new Label("Prix :");
	TextField produitPrixTextField  = new TextField();
	
	Label produitQteLabel = new Label("Quantite :");
	TextField produitQteTextField  = new TextField();
	Label produitCategorieLabel = new Label("Categorie :");

    ChoiceBox<Categorie> choiceBoxCategorie = new ChoiceBox<>(categories);

	Label produitDateLabel = new Label("Date :");
	DatePicker produitDateTextField  = new DatePicker();
    Button btnmodifierProduit=new Button("Modifier");
    Button btnsupprimerProduit=new Button("Supprimer");
    Button initform=new Button("Reinitialiser");
    TextField searchbtnProduit= new TextField();

    HBox totalhbox = new HBox();
    Label totalLabel = new Label("Total :");
    Label totalLabelValue = new Label("0.0");

    HBox titletop=new HBox();
    private void addColumnToTableView(){
        TableColumn<Produit, Integer> produitIdColon=new TableColumn<>("Id");
        produitIdColon.setCellValueFactory(new PropertyValueFactory<>("id"));
        produitIdColon.setPrefWidth(100);

        TableColumn<Produit, String> produitDesignationColon=new TableColumn<>("Designation");
        produitDesignationColon.setCellValueFactory(new PropertyValueFactory<>("designation"));
        produitDesignationColon.setPrefWidth(100);
        
        TableColumn<Produit, Double> produitPrixColon=new TableColumn<>("Prix");
        produitPrixColon.setCellValueFactory(new PropertyValueFactory<>("prix"));
        produitPrixColon.setPrefWidth(100);
        
        TableColumn<Produit, Integer> produitQteColon=new TableColumn<>("Qte");
        produitQteColon.setCellValueFactory(new PropertyValueFactory<>("qte"));
        produitQteColon.setPrefWidth(100);



        TableColumn<Produit, Categorie>category=new TableColumn<>("Categorie");
        category.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getC()));
        category.setPrefWidth(100);
        
        TableColumn<Produit, LocalDate> produitDateColon=new TableColumn<>("Date");
        produitDateColon.setCellValueFactory(new PropertyValueFactory<>("date"));
        produitDateColon.setPrefWidth(100);

        tableProduits.getColumns().addAll(produitIdColon,produitDesignationColon,produitQteColon,produitPrixColon,category,produitDateColon);
        //observableTable.setAll(produits);
        tableProduits.setItems(observableTable);
    
}
    
    private void initelements(){
   
    	window.setScene(sc);
    	window.setTitle("Liste des Produits");
        
        
    }
    private void addElementsToPane() {
    	
        pane.add(produitDesignationLabel, 0, 1);
        pane.add(produitPrixLabel, 0, 2);
        pane.add(produitQteLabel, 0, 3);

        pane.add(produitDesignationTextField, 1, 1);
        pane.add(produitPrixTextField, 1, 2);
        pane.add(produitQteTextField, 1, 3);
        pane.add(produitCategorieLabel,0,4);
        pane.add(choiceBoxCategorie,1,4);
        pane.add(produitDateLabel,0,5);
        pane.add(produitDateTextField, 1, 5);

        pane.add(btnmodifierProduit,1,6);
        pane.add(btnsupprimerProduit,0,7);
        pane.add(initform,1,7);

        addColumnToTableView();
        VBox.setMargin(vboxproduit,new Insets(60,0,0,0));
      
        vboxproduit.getChildren().add(tableProduits);
        
        totalhbox.getChildren().addAll(totalLabel,totalLabelValue);
        vboxproduit.getChildren().add(totalhbox);

        root.setTop(titletop);
        root.setCenter(vboxproduit);
        root.setLeft(pane);
    }
    
    private void addStyleToNodes() {
    	 sc.getStylesheets().add("/css/styles.css");
    	 tableProduits.getStyleClass().add("table-row-cell");
    	 tableProduits.setMinHeight(500);
    	 tableProduits.setMinWidth(800);
    	 totalLabel.getStyleClass().add("LabelTotal");
    	 totalLabelValue.getStyleClass().add("LabelTotal");
         tableProduits.setStyle("-fx-border-color: #69779b");
         titletop.getStyleClass().add("titleTopWindow");
   	      title.setTextFill(Color.BLUE);
          titletop.setAlignment(Pos.CENTER);
          titletop.getChildren().add(title);
          titletop.setPadding(new Insets(20));
    	 totalhbox.getStyleClass().add("boxTotal");
    	 totalhbox.setSpacing(15);
    	 pane.setPadding(new Insets(15,15,15,15));
         pane.setHgap(40);
         pane.setVgap(40);
    	 
    }
    public void addEvents() {
    	btnsupprimerProduit.setOnAction(event -> {
    		handler.deleteClick();

         });
    	  initform.setOnMouseClicked(event->{
              this.produitDesignationTextField.setText("");
              this.produitPrixTextField.setText("");
              this.produitQteTextField.setText("");
             
          });
    	btnmodifierProduit.setOnAction(event -> {

    		handler.modifierClick();
    	});
         //selectionner depuis le table view
         tableProduits.setOnMouseClicked(event -> {
             Produit prodsel=tableProduits.getSelectionModel().getSelectedItem();
             produitDesignationTextField.setText(prodsel.getDesignation());
             this.produitPrixTextField.setText(String.valueOf(prodsel.getPrix()));
             this.produitQteTextField.setText(String.valueOf(prodsel.getQte()));
             this.choiceBoxCategorie.setValue(prodsel.getC());
             this.produitDateTextField.setValue(prodsel.getDate());

         });
    
       
    }
       
        public ListeProduits() {
        	initelements();
        	addElementsToPane();
        	addStyleToNodes();
        	handler.updateListeProduitWindow();
             addEvents() ;
        	window.setTitle("Liste des Produits");
        	window.setScene(sc);
        	window.show();
        }
    
}
