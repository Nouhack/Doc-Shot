package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import documentation.doc_view_controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class application_documenter extends RecursiveTreeObject<application_documenter> {
	
	public application_documenter(String nom_app, String app_descrip,String project_name) {
		super();
		this.nom_app = new SimpleStringProperty(nom_app) ;
		this.app_descrip = new SimpleStringProperty(app_descrip) ;
		this.project_name = new SimpleStringProperty(project_name) ;
	}
	public application_documenter() {
		
	}
	
	StringProperty nom_app;
    StringProperty app_descrip;
    int id_project;
	
    int app_id;
    
    StringProperty project_name;
    
    
    void créer_application(
			
			String nom_app,
			String app_descrip,
			ListView<String> selected_simple_users,
			ListView<String> get_selected_id_project) {
    	
if (vérification(nom_app)) {
    		
    		collaborateur_simple newCollabo_simp=new collaborateur_simple();
    		
    		
    		
    		try {
				jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="insert into application (nom_app,app_descrip,id_projet) values ("+'"'+nom_app+'"'+","+'"'+app_descrip+'"'+","+'"'+get_project_id(get_selected_id_project)+'"'+")" ;
							
					String strcheck2="select id from application where nom_app= "+'"'+nom_app+'"';		
					
							
										
					
					stm.execute(strcheck);
					ResultSet res = stm.executeQuery(strcheck2);
					
					while (res.next()) {
						app_id=res.getInt("id");
						break;
					}
					
					jdbc.setConnection().close();
					
					
						// call method to create a collaborator project entity----------------
					newCollabo_simp.créer_collab_simp(selected_simple_users, app_id);
						//---------------------------------------------------------------------
					
					
					Alert alert = new Alert (AlertType.INFORMATION);
					alert.setTitle("Succès ");
			        alert.setContentText("Application Créer avec succès ");
			        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		
		}else {
			Alert alert = new Alert (AlertType.ERROR);
	        
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Le Nom de l'application existe déja");
	        alert.setContentText("Vous devez saisir un nouveau nom pour l'application");
	        
	        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
	        if (result.get() ==javafx.scene.control.ButtonType.OK ) {      
			} else {}
		}
    }
    private boolean vérification(String nom_app) {
    	try {
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="select nom_app from application where nom_app = "+'"'+ nom_app+'"';
			stm.executeQuery(strcheck);
			
			while (stm.getResultSet().next()==true) {
				return false;
				
			}
			jdbc.setConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
    	
    }
    int get_project_id(ListView<String> project_selected) {
    	try {
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="select id from projet where nom_projet="+'"'+project_selected.getSelectionModel().getSelectedItem()+'"';
			ResultSet res = stm.executeQuery(strcheck);
			while (res.next()) {
				return res.getInt("id");
				
			}
			jdbc.setConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
    	
		return (Integer) null ;
    	
    }
    
    
    public  void publier_documentation() {
    	
    	doc_view_controller controller_obj=new doc_view_controller();
    	
    	Images e=new Images();
    	
    	try {
			
    		jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="select id from application where nom_app= "+'"'+SampleController.selected_app_name+'"';
								
				
			
			ResultSet res = stm.executeQuery(strcheck);
			
			while (res.next()) {
				app_id = res.getInt("id");
				
				
			}
			jdbc.setConnection().close();
			
			
			
		} catch (Exception es) {
			// TODO: handle exception
		}
    	
    //	if (controller_obj.get_list_node().getSelectionModel() != null) {
			e.create_image(doc_view_controller.list_of_image_path, app_id);
	//	} 
    	//else {
		//	e.create_image(doc_view_controller.list_of_image_path, "pas de nom");
	//	}
		
    	}
	public void delete_application(String nom_proj) {
		
		collaborateur_simple collab_sm=new collaborateur_simple();
		Images im=new Images();
try {
			
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			
			//delete collab_app---------------------------------------------------------------//
			String strcheck1="select nom_app from application where id_projet=(select id from projet where nom_projet="+'"'+nom_proj+'"'+")";
			ResultSet res = stm.executeQuery(strcheck1);
			
			while (res.next()) {
				collab_sm.delete_collab_smp_app(res.getString("nom_app"));
				
			}
			
			//--------------------------------------------------------------------------------//
			//delete simple collaborator------------------------------------------------------//
			
			String strcheck3="delete from collaborateur_simple where id not in(select Id_collab_samp from collab_app)";				
			stm.executeUpdate(strcheck3);
			
			//-------------------------------------------------------------------------------//
			
			//delete image description and image------------------------------------------------//
			
			String strcheck2="select titre from image where id_app in (select id from application where id_projet in (select id from projet where nom_projet="+'"'+nom_proj+'"'+"))";
			ResultSet res2 = stm.executeQuery(strcheck2);
			
			while (res2.next()) {
				im.delete_img(res2.getString("titre"));				
			}
			//--------------------------------------------------------------------------------//
			 
			//delete application-------------------------------------------------------------//
			String strcheck="delete from application where id_projet =(select id from projet where nom_projet="+'"'+nom_proj+'"'+")";					
			stm.executeUpdate(strcheck);
			//--------------------------------------------------------------------------------//
			jdbc.setConnection().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
}
