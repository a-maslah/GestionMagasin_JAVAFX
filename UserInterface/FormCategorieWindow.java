package UserInterface;

import java.util.List;

import Entites.Categorie;
import Entites.Produit;
import IDAO.CategorieDAO;
import IDAO.ProduitDAO;
import ImplementationDAO.CategorieDaoImplL;
import ImplementationDAO.ImplementationProduitDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class FormCategorieWindow {

Stage window = new Stage();
	
CategorieDAO pdao=new CategorieDaoImplL();
List<Categorie> categories=pdao.getAll();
	private BorderPane root=new BorderPane();
	Scene sc = new Scene(root);
    TableView<Categorie> tableViewCategories=new TableView<>();
    ObservableList<Categorie> observableTable= FXCollections.observableArrayList();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    
   
    Button editbtnCatg=new Button("Modifier");

    Button deletebtnCatg=new Button("Supprimer");

Button addbtnCatg=new Button("Ajouter");

Button initbtnCatg=new Button("Reinitialiser");

Label LabelCategorie = new Label("Libelle :");
TextField LabelCategorietextField = new TextField();
HBox titletop=new HBox();
VBox view=new VBox();
HBox buttons = new HBox();

Label title=new Label("GESTION DES CATEGORIES");

private void initelements(){
   
    titletop.setAlignment(Pos.CENTER);
    titletop.getChildren().add(title);
    titletop.setPadding(new Insets(10));

    addStyletoNodes();
    addColumnToTableView();
    view.getChildren().addAll(LabelCategorie,LabelCategorietextField);
    view.getChildren().add(tableViewCategories);
    buttons.getChildren().addAll(addbtnCatg,editbtnCatg,deletebtnCatg,initbtnCatg);
    view.getChildren().add(buttons);
    buttons.setSpacing(15);
    root.setCenter(view);
    root.setTop(titletop);
    view.setSpacing(15);
    
}
	public void addEvents() {
		addbtnCatg.setOnAction(event->{
			if(LabelCategorietextField.getText().isEmpty()) {
				alert.setTitle("Error Champ");
	            alert.setContentText("SVP remplir le champ Label avant l'ajout");
	            alert.setHeaderText("Champ Label Obligatoire!!!!!");
	            alert.showAndWait();
	            return;
			}
	            Categorie c=new Categorie(pdao.getId(),LabelCategorietextField.getText());
	            pdao.create(c);
	            this.observableTable.add(c);
	       
		});
		
        editbtnCatg.setOnAction(event -> {
            Categorie catg=tableViewCategories.getSelectionModel().getSelectedItem();
            catg.setLabel(LabelCategorietextField.getText());
            pdao.update(catg);
            //ajouter la modification a la table view 
            this.observableTable.set(this.observableTable.indexOf(catg),catg);
        });
        deletebtnCatg.setOnAction(event -> {
            Categorie catg=tableViewCategories.getSelectionModel().getSelectedItem();
            try {
                pdao.delete(catg);
                this.observableTable.remove(catg);
            }catch (Exception e) {
                alert.setContentText(e.getMessage());
                alert.setTitle("impossible !!!");
                alert.show();
            }

        });
        tableViewCategories.setOnMouseClicked(event -> {
            Categorie catg=tableViewCategories.getSelectionModel().getSelectedItem();
            this.LabelCategorietextField.setText(catg.getLabel());
        });

        

        initbtnCatg.setOnMouseClicked(events->{
            this.LabelCategorietextField.setText("");
        });
        
		}
    private void addColumnToTableView(){
        TableColumn<Categorie, Integer> CategorieIdColon=new TableColumn<>("Id");
        CategorieIdColon.setCellValueFactory(new PropertyValueFactory<>("id"));
        CategorieIdColon.setPrefWidth(300);

        TableColumn<Categorie, String> LabelCategorie=new TableColumn<>("Libelle");
        LabelCategorie.setCellValueFactory(new PropertyValueFactory<>("label"));
        LabelCategorie.setPrefWidth(450);
        tableViewCategories.getColumns().addAll(CategorieIdColon,LabelCategorie);
       observableTable.setAll(categories);
         tableViewCategories.setItems(observableTable);
        
       
    }
    private void addStyletoNodes() {
    	 sc.getStylesheets().add("/css/styles.css");		
    	  title.setTextFill(Color.BLUE);
    	  titletop.getStyleClass().add("titleTopWindow");
    	 view.setMargin(buttons, new Insets(20,0,0,0));
     	tableViewCategories.setStyle("-fx-border-color: #69779b");

    }
	
	public FormCategorieWindow() {
		
		initelements();
		addStyletoNodes();
		addEvents();
   	window.setScene(sc);
   	window.setWidth(700);
   	window.setHeight(700);

   	window.show();
		
	}
	
}
