package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.util.Callback;

public class application__list {

void application_tree_table_vview(JFXTreeTableView<application_documenter> aa,boolean editable,String statem) {
		
		JFXTreeTableColumn<application_documenter, String> nom_application = new JFXTreeTableColumn<>("Nom de l'application");
		nom_application.setPrefWidth(280);
		
		nom_application.setCellValueFactory((TreeTableColumn.CellDataFeatures<application_documenter, String> param) ->{
		    if(nom_application.validateValue(param)) return param.getValue().getValue().nom_app;
		    else return nom_application.getComputedValue(param);
        });
        
		
		
        
        JFXTreeTableColumn<application_documenter, String> descrip_application = new JFXTreeTableColumn<>("La description de l'application");
        descrip_application.setPrefWidth(280);
        descrip_application.setCellValueFactory((TreeTableColumn.CellDataFeatures<application_documenter, String> param) ->{
		    if(descrip_application.validateValue(param)) return param.getValue().getValue().app_descrip;
		    else return descrip_application.getComputedValue(param);
		});
        
        
       
        
        JFXTreeTableColumn<application_documenter, String> nom_du_projet = new JFXTreeTableColumn<>("Nom du projet");
        nom_du_projet.setPrefWidth(280);
        nom_du_projet.setCellValueFactory((TreeTableColumn.CellDataFeatures<application_documenter, String> param) ->{
		    if(nom_du_projet.validateValue(param)) return param.getValue().getValue().project_name;
		    else return nom_du_projet.getComputedValue(param);
		});
        
        
       
        
        
       //-------------------------------------------------
        
     // data  FXCollections.observableArrayList();
        ObservableList<application_documenter> apps = FXCollections.observableArrayList();
        
        get_application_list_to_tree_table(statem, apps);
        


        
        final TreeItem<application_documenter> root2 = new RecursiveTreeItem<application_documenter>(apps, RecursiveTreeObject::getChildren);
        
        aa.getColumns().setAll(nom_application,descrip_application,nom_du_projet);
        aa.setRoot(root2);
        aa.setShowRoot(false);
        
        aa.setEditable(editable);
        aa.getColumns().setAll(nom_application,descrip_application,nom_du_projet);
	}

private void get_application_list_to_tree_table(String userlog, ObservableList<application_documenter> apps) {
	// TODO Auto-generated method stub
	try {
		
		jdbc.setConnection();
		Statement stm=jdbc.setConnection().createStatement();
		String strcheck="select nom_app,app_descrip,nom_projet from application,projet where (application.id_projet=projet.id) and application.id in (select Id_app from collab_app where  Id_collab_samp=(select id from utilisateur where username="+'"'+userlog+'"'+"))";
						
			
		ResultSet res = stm.executeQuery(strcheck);
		
		
		while (res.next()) {
			apps.add(new application_documenter(res.getString("nom_app"),res.getString("app_descrip"),res.getString("nom_projet")));
		}
		jdbc.setConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


void application_filter(JFXTextField zz,JFXTreeTableView<application_documenter> deltree) {
	
	zz.textProperty().addListener((o,oldVal,newVal)->{
		deltree.setPredicate(pro -> pro.getValue().nom_app.get().contains(newVal)
                    || pro.getValue().app_descrip.get().contains(newVal)
                    || pro.getValue().project_name.get().contains(newVal)
    				);
    	
					
    });
	
}

}
