package UserInterface;

import java.time.LocalDate;

import Entites.Categorie;
import Entites.Produit;
import IDAO.ProduitDAO;
import ImplementationDAO.CategorieDaoImplL;
import ImplementationDAO.ImplementationProduitDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormProduit {

	Stage window = new Stage();
	VBox root = new VBox();
	Scene sc = new Scene(root);
	HBox hboxButtons = new HBox();
	ObservableList<Categorie> categories = FXCollections.observableArrayList((new CategorieDaoImplL().getAll()));
    Alert alert = new Alert(Alert.AlertType.ERROR);
    Alert alertinf = new Alert(Alert.AlertType.INFORMATION);

    ProduitDAO pdao=new ImplementationProduitDao();

    Label title  = new Label("Ajouter un Produit");
    
	Label produitDesignationLabel = new Label("Designation");
	TextField produitDesignationTextField  = new TextField();
	
	Label produitPrixLabel = new Label("Prix");
	TextField produitPrixTextField  = new TextField();
	
	Label produitQteLabel = new Label("Quantite");
	TextField produitQteTextField  = new TextField();
	Label produitCategorieLabel = new Label("Categorie");

    ChoiceBox<Categorie> choiceBoxCategorie = new ChoiceBox<>(categories);

	Label produitDateLabel = new Label("Date");
	DatePicker produitDateTextField  = new DatePicker();
	
	Button btnaddproduit = new Button("Ajouter");
	Button btnacancelproduit = new Button("Annuler");

	private void initElement() {
		window.setScene(sc);
		window.setHeight(600);
		window.setWidth(700);
		window.setTitle("Nouveau Produit");
	}
	
	private void addNodeToWindow() {
		root.getChildren().add(title);
		root.getChildren().addAll(produitDesignationLabel,produitDesignationTextField);
		root.getChildren().addAll(produitPrixLabel,produitPrixTextField);
		root.getChildren().addAll(produitQteLabel,produitQteTextField);
		root.getChildren().addAll(produitCategorieLabel,choiceBoxCategorie);

		root.getChildren().addAll(produitDateLabel,produitDateTextField);
		hboxButtons.getChildren().addAll(btnaddproduit,btnacancelproduit);
		root.getChildren().add(hboxButtons);


	}
	private void addEvents() {
		window.setOnCloseRequest(event->{
			event.consume();
		});
		btnacancelproduit.setOnAction(event->{
			window.close();
		});
		
		btnaddproduit.setOnAction(event->{
			if (produitDesignationTextField.getText().isEmpty() || produitPrixTextField.getText().isEmpty() ||produitQteTextField.getText().isEmpty() || this.choiceBoxCategorie.getValue() == null  ||produitDateTextField.getValue()==null){
	            alert.setTitle("Error Saisi");
	            alert.setContentText("SVP tous les champs sont obligatoires  !!");
	            alert.setHeaderText("Attention !!!!!");
	            alert.showAndWait();
	            return;
	        }
	        
            LocalDate valueDate = produitDateTextField.getValue();

	    Produit pro=new Produit(pdao.getId(),produitDesignationTextField.getText(),Double.parseDouble(produitPrixTextField.getText()),Integer.parseInt(produitQteTextField.getText()),choiceBoxCategorie.getValue(),valueDate);
	        pdao.create(pro);
	        alertinf.setContentText("Produit bien Ajoute");
	        alertinf.showAndWait();
		});
		
        
	}
	public void addStylesToNodes() {
        sc.getStylesheets().add("/css/styles.css");
        title.getStyleClass().add("LabelTitle");
        title.setMinWidth(window.getWidth());
        title.setAlignment(Pos.CENTER);
        produitDesignationLabel.getStyleClass().add("abelForm");
        produitPrixLabel.getStyleClass().add("labelForm");
        produitQteLabel.getStyleClass().add("labelForm");
        produitCategorieLabel.getStyleClass().add("labelForm");
        produitDateLabel.getStyleClass().add("labelForm");

        root.setSpacing(15);
        
        hboxButtons.setSpacing(20);


    }
	

	
	
	public FormProduit() {
		initElement();
		addStylesToNodes();
		addNodeToWindow();
		addEvents() ;
		window.show();
		
		
	}
}
