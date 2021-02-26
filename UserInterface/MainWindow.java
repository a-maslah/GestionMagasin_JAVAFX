package UserInterface;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainWindow  extends Application{

	private BorderPane root =new BorderPane();
	
    private Scene sc = new Scene(root);
    MenuItem nouveauproduitMenuItem = new MenuItem("nouveauProduit");
    MenuItem ListproduitMenuItem = new MenuItem("List Produits");
    MenuItem nouveauclientMenuItem = new MenuItem("nouveau Client");
    MenuItem ListclientMenuItem = new MenuItem("List Clients");
    
    MenuItem GstcategorieMenuItem = new MenuItem("Gestion Categories");
    
    MenuItem nouveauVenteMenuItem = new MenuItem("Liste des Ventes");
    MenuItem ListPaymentsMenuItem = new MenuItem("Liste des Payments");

    
    private void createMenu() {
    	MenuBar menuBar = new MenuBar();
    	Menu produitsMenu = new Menu("Produits");
    	Menu clientssMenu = new Menu("Clients");
    	Menu categoriesMenu = new Menu("Categories");

    	Menu ventesMenu = new Menu("Ventes");
    	Menu paymentsMenu = new Menu("Payments");
    	menuBar.getMenus().addAll(produitsMenu,clientssMenu,categoriesMenu,ventesMenu, paymentsMenu);
    	root.setTop(menuBar);
        
    produitsMenu.getItems().addAll(nouveauproduitMenuItem, ListproduitMenuItem);
    clientssMenu.getItems().addAll(nouveauclientMenuItem,ListclientMenuItem);
    categoriesMenu.getItems().add(GstcategorieMenuItem);
    ventesMenu.getItems().add(nouveauVenteMenuItem);
    paymentsMenu.getItems().add(ListPaymentsMenuItem);

        addEvents();
    }
 
    public void addEvents() {
    	
    	nouveauproduitMenuItem.setOnAction(event->{
    		System.out.println("Fenetre nouveau Prod");
    		new FormProduit();
    			
    	});
    	ListproduitMenuItem.setOnAction(event->{
    		System.out.println("Fenetre List Produits");
    		new ListeProduits();
    			
    	});
    	GstcategorieMenuItem.setOnAction(event->{
    		new FormCategorieWindow();
    			
    	});
    	nouveauclientMenuItem.setOnAction(event->{
    		new FormClient();
    	});
    	ListclientMenuItem.setOnAction(event->{
    		new ListeClients();
    	});
    	nouveauVenteMenuItem.setOnAction(event->{
    		// new FormVenteTest();
    		new ListeVente();
    	});
    	ListPaymentsMenuItem.setOnAction(event->{
    		new ListePayments();
    	});
    	 
    }
    public void addStylesToNodes() {
    	root.getStyleClass().add("mainWindow");
        sc.getStylesheets().add("/css/styles.css");
    	

    }
	@Override
	public void start(Stage window) throws Exception {
		// TODO Auto-generated method stub
		createMenu();
		addStylesToNodes();
		
		window.setTitle("Gestion du Magasin");
		window.getIcons().add(new Image("file:g.png"));
		window.setWidth(1000);
		window.setHeight(600);
		
	    window.setScene(sc);
	    window.show();
	} 

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
