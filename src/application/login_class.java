package application;

import java.io.IOException;
import java.util.Optional;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro8.JMetro;
import jfxtras.styles.jmetro8.JMetro.Style;

public class login_class {
	private static final Style STYLE = Style.LIGHT;
	static String UsernameLog="";
    @FXML
    private JFXTextField logUser;

    
    @FXML
    private JFXPasswordField logpass;

    @FXML
    void check(ActionEvent event) {
    	
    	User NewUser=new User();
    	
    	if (NewUser.authentification(logUser.getText(), logpass.getText())) {
    		UsernameLog=logUser.getText();
				
    			
        		try {
        		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Sample.fxml"));
        		    
        		    Parent root1 = (Parent) fxmlLoader.load();
        		    root1.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
        		  //  new JMetro(STYLE).applyTheme(root1);
        		  //    new JMetro(STYLE);
        		    
        		    Stage stage = new Stage();
        		    stage.setTitle("ABC");
        		    stage.setScene(new Scene(root1,900,700));  
        		    stage.show();
        		    
        		}catch (Exception e) {
    				// TODO: handle exception
        			System.out.println("le probléme rahou hna khou");
    			}
        		
			
    		
    	    
		}else {
			System.out.println("nn mot de pass wrong");
			Alert alert = new Alert (AlertType.ERROR);
	        
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Echec d'authentification");
	        alert.setContentText("Le nom d'utilisateur ou le mot de passe est incorrect  ");
	        
	        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
		}
    }
}
