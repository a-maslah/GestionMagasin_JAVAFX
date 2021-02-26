package UserInterface;

import java.time.LocalDate;
import java.util.List;

import Entites.Client;
import Entites.LigneCommande;
import Entites.Produit;
import Entites.Vente;
import ImplementationDAO.LigneCommandeDaoImpL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListeVente {
VenteDaoImpL ventedao = new VenteDaoImpL();
List<Vente> ventes = ventedao.getAll();
	Stage window = new Stage();
	BorderPane root = new BorderPane();
    Scene sc = new Scene(root);
    GridPane pane= new GridPane();
    GridPane pane1= new GridPane();

    LigneCommandeDaoImpL lcmd = new LigneCommandeDaoImpL();
	TableView<Vente> tableVentes=new TableView<>();
    ObservableList<Vente> observableTableVente= FXCollections.observableArrayList();
    
	TableView<LigneCommande> tableLigne=new TableView<>();
    ObservableList<LigneCommande> observableTableLigne= FXCollections.observableArrayList();
    
    Label title=new Label("Liste Ventes des Clients");

 Button detailVente = new Button("detail de Vente");
    private void addColumnToTableView(){
    	
        TableColumn<Vente, Integer> IdVente=new TableColumn<>("IdVente");
        IdVente.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdVente.setPrefWidth(150);

        TableColumn<Vente, Client> clientCol=new TableColumn<>("NomClient");
        clientCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getClient()));
        clientCol.setPrefWidth(150);


        TableColumn<Vente, LocalDate> date=new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.setPrefWidth(150);
        
        TableColumn<Vente, Double> totalCol=new TableColumn<>("TotalVente");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        totalCol.setPrefWidth(150);
        
        tableVentes.getColumns().addAll(IdVente,clientCol,date,totalCol);
        observableTableVente.setAll(ventes);
        tableVentes.setItems(observableTableVente);
        
        
        //
        
        TableColumn<LigneCommande, Integer> IdLigne=new TableColumn<>("IdLigne");
        IdLigne.setCellValueFactory(new PropertyValueFactory<>("id"));
        IdLigne.setPrefWidth(150);

        TableColumn<LigneCommande,Produit> prod=new TableColumn<>("Designation");
        prod.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit()));
        prod.setPrefWidth(150);

        TableColumn<LigneCommande,Double> prix=new TableColumn<>("Prix");
        prix.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProduit().getPrix()));
        prix.setPrefWidth(150);
        
        TableColumn<LigneCommande, Integer> qteVendu=new TableColumn<>("QteVendu");
        qteVendu.setCellValueFactory(new PropertyValueFactory<>("qteVendu"));
        qteVendu.setPrefWidth(80);

        TableColumn<LigneCommande, Double> st=new TableColumn<>("Stotal");
        st.setCellValueFactory(new PropertyValueFactory<>("stotal"));
        st.setPrefWidth(150);
        tableLigne.getColumns().addAll(IdLigne,prod,qteVendu,prix,st);
        
        tableLigne.setItems(observableTableLigne);

       
}
    private void initelements(){
   
    	window.setTitle("Liste des Ventes");
    	window.setScene(sc);
        
        
    }
    private void addStyleToNodes() {
   	 sc.getStylesheets().add("/css/styles.css");
   	 tableVentes.getStyleClass().add("table-row-cell");
   	tableVentes.setMinHeight(500);
   	tableVentes.setMinWidth(700);
    title.getStyleClass().add("LabelTitle");
    title.setMinWidth(window.getWidth());
    title.setAlignment(Pos.CENTER);
     pane.setHgap(20);
    }
    private void addElementsToVbox() {
    	root.getChildren().add(title);
    	pane.add(title, 0,1);
    	pane.add(tableVentes, 0, 2);
    	pane.add(detailVente, 0, 5);
    	pane1.add(tableLigne, 1, 3);
    	addColumnToTableView();
    	root.setLeft(pane);
    	root.setCenter(pane1);
    	
    	

    
    }
   
    

    public void addEvents() {
    	detailVente.setOnAction(event->{
    		Vente v = tableVentes.getSelectionModel().getSelectedItem();
    		List<LigneCommande> l =lcmd.getLcmd(v.getId());
    		
    		 this.observableTableLigne.setAll(l);
    		
    	});
    }
    public ListeVente() {
    	initelements();
    	addElementsToVbox();
    	addStyleToNodes();
    	addEvents();
    	window.show();
    }
    
    
}
