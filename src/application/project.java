package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;

class Project extends RecursiveTreeObject<Project> {

	StringProperty Intitulé_projet;
    StringProperty Nom_projet;
    StringProperty cadre_obtention;
    StringProperty Date_début;
    StringProperty période;
    StringProperty technologies_mise;
    StringProperty context_projet;
    StringProperty problématique;
    StringProperty solution_propo;
    StringProperty étape_projet;
    StringProperty bénéf_client;
    ArrayList<Integer> collaborateur_principaux = new ArrayList<Integer>();
    IntegerProperty id_admin;
    
    int projet_id;
    
    
    

    public Project(String intitulé_projet, String Nom_projet, String cadre_obtention,
    		String date_début, String période, String technologies_mise,
    		String context_projet, String problématique, String solution_propo,
    		String étape_projet, String bénéf_client) {
    	
		// TODO Auto-generated constructor stub
    	
    	
    	this.Intitulé_projet = new SimpleStringProperty(intitulé_projet) ;
    	this.Nom_projet = new SimpleStringProperty(Nom_projet) ;
    	this.cadre_obtention = new SimpleStringProperty(cadre_obtention) ;
    	this.Date_début = new SimpleStringProperty(date_début) ;
    	this.période = new SimpleStringProperty(période) ;
    	this.technologies_mise = new SimpleStringProperty(technologies_mise) ;
    	this.context_projet = new SimpleStringProperty(context_projet) ;
    	this.problématique = new SimpleStringProperty(problématique) ;
    	this.solution_propo = new SimpleStringProperty(solution_propo) ;
    	this.étape_projet = new SimpleStringProperty(étape_projet) ;
    	this.bénéf_client = new SimpleStringProperty(bénéf_client) ;
    	
    	
    	
    	
	}


	public Project() {
		// TODO Auto-generated constructor stub
	}


	
	//--------------  create project method ------------------------------//
	void créer_projet(
			String intitulé_projet, 
			String Nom_projet, 
			String cadre_obtention,
			LocalDate date_début, 
			String technologies_mise,
			String context_projet, 
			String problématique, 
			String solution_propo,
			String étape_projet,
			String bénéfic_pr,
			int adminId,
			Integer période,
			ListView<String> selected_users) {
    	
    	if (vérification(Nom_projet)) {
    		
    		collaborateur_principal newCollabo=new collaborateur_principal();
    		
    		
    		
    		try {
				jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="insert into projet (Intitulé_pr,nom_projet,cadre_obt_pr,Date_début,Téch_mis_oeu,Context_projet,Problématique,Solution_propo,étape_projet,Bénéf_client,id_admin,période) values ("+'"'+intitulé_projet +'"'+","+'"'+Nom_projet +'"'+","+'"'+cadre_obtention +'"'+","+'"'+date_début +'"'+","+'"'+technologies_mise +'"'+","+'"'+context_projet +'"'+","+'"'+problématique +'"'+","+'"'+solution_propo +'"'+","+'"'+étape_projet +'"'+","+'"'+bénéfic_pr +'"'+","+'"'+adminId +'"'+","+'"'+période +'"'+")";	
							
					String strcheck2="select id from projet where nom_projet= "+'"'+Nom_projet+'"';		
					
							
										
					
					stm.execute(strcheck);
					ResultSet res = stm.executeQuery(strcheck2);
					
					while (res.next()) {
						projet_id=res.getInt("id");
					}
					
					jdbc.setConnection().close();
					
					
						// call method to create a collaborator project entity----------------
						newCollabo.créer_collab_princ(selected_users, projet_id);
						//---------------------------------------------------------------------
					
						Alert alert = new Alert (AlertType.INFORMATION);
						alert.setTitle("Succès ");
				        alert.setContentText("Projet Créer avec succès ");
				        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		
		}else {
			Alert alert = new Alert (AlertType.WARNING);
	        
	        alert.setTitle("Alert");
	        alert.setHeaderText("Le nom existe déja");
	        alert.setContentText("le nouveau nom du projet doit étre unique");
	        
	        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
		}
    	
    	
    	
    	
    }
    
    //------------------------- END create project method----------------------//
	
	//---------------------- existing project verification method ----------------//
    private boolean vérification(String nom_projet) {
    	try {
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="select nom_projet from projet where nom_projet = "+'"'+ nom_projet+'"';
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
    
    //------------------- END verification project method--------------------------//

static void get_project_list(ListView<String> project_selected,String collab_prnc){
	try {
		jdbc.setConnection();
		Statement stm=jdbc.setConnection().createStatement();
		String strcheck="select nom_projet from projet where id in (select id_projet from collab_projet where id_collab_princ=(select id from utilisateur where username ="+'"'+collab_prnc+'"'+"))";
						
			
		ResultSet res = stm.executeQuery(strcheck);
		
		
		while (res.next()) {
			project_selected.getItems().add(res.getString("nom_projet"));
		}
		jdbc.setConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}


void modifier_projet(ArrayList<String> val) {
	
	for (int i = 0; i < (val.size()); i+=3) {
		try {
			jdbc.setConnection();
				Statement stm=jdbc.setConnection().createStatement();
				
				if (vérification(val.get(i+1))==false && val.get(i)=="nom_projet") {
					Alert alert = new Alert (AlertType.ERROR);
					alert.setTitle("Erreur");
			        alert.setHeaderText("Le nom du projet existe déja");
			        alert.setContentText("vous devez insérer un nouveau nom du projet");
			        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
			        SampleController.project_modified=false;
			        break;

				}else {
					if (!isValidDate(val.get(i+1)) && val.get(i)=="Date_début") {
						Alert alert = new Alert (AlertType.ERROR);
						alert.setTitle("Erreur");
				        alert.setHeaderText("Invalide date");
				        alert.setContentText("vous devez insérer une nouvel Date");
				        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
				        SampleController.project_modified=false;
				        break;
					}
					
					if (!isValidPeriode(val.get(i+1)) && val.get(i)=="période") {
						
						Alert alert = new Alert (AlertType.ERROR);
						alert.setTitle("Erreur");
				        alert.setHeaderText("Période invalide");
				        alert.setContentText("vous devez insérer une nouvel Période");
				        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
				        SampleController.project_modified=false;
				        break;
					}
					
					
					System.out.println("hada hwa l assam khou = "+val.get(i));
					System.out.println("HADI HYA LA DATE KHOU = "+val.get(i+1));
				String strcheck="UPDATE projet set "+val.get(i)+" = "+'"'+val.get(i+1)+'"'+" where nom_projet = "+'"'+val.get(i+2)+'"';

				stm.execute(strcheck);
				jdbc.setConnection().close();
				SampleController.project_modified=true;
				
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setTitle("Succès ");
		        alert.setContentText("Projet modifié avec succès ");
		        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	System.out.println(val);
}


void supp_projet(String nom_proj,JFXTreeTableView<Project> treeview) {
	
	collaborateur_principal collab_pr=new collaborateur_principal();
	collaborateur_simple collab_smp=new collaborateur_simple();
	
	Alert alert = new Alert (AlertType.CONFIRMATION);
    
    alert.setTitle("Erreur");
    alert.setHeaderText("le Projet "+'"'+nom_proj+'"'+"sera supprimer");
    alert.setContentText("avez vous d'acord ?? ");
    
    Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
    if (result.get() ==javafx.scene.control.ButtonType.OK ) {  
    	
    	collab_pr.delete_collab_prnc_pr(nom_proj);
    	
    	application_documenter app=new application_documenter();
    	app.delete_application(nom_proj);
    	try {
    		
    		jdbc.setConnection();
    		Statement stm=jdbc.setConnection().createStatement();
    		String strcheck="delete from projet where nom_projet="+'"'+nom_proj+'"';
    		
    		
    		stm.executeUpdate(strcheck);

    		Alert alert2 = new Alert (AlertType.INFORMATION);
			alert2.setTitle("Succès ");
	        alert2.setContentText("Projet supprimé avec succès ");
	        Optional<javafx.scene.control.ButtonType> result2=alert2.showAndWait();

    		jdbc.setConnection().close();
    		
    		delete_project_from_list(treeview);
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
	} else {}
    
	
}

void delete_project_from_list(JFXTreeTableView<Project> treeview) {
	 TreeTableViewSelectionModel<Project> sm = treeview.getSelectionModel();
   	
   	// Get the selected Entry
       int rowIndex = sm.getSelectedIndex();
       TreeItem<Project> selectedItem = sm.getModelItem(rowIndex);
       TreeItem<Project> parent = selectedItem.getParent();
       
       parent.getChildren().remove(selectedItem);
}

private boolean isValidDate(String inDate) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    try {
        dateFormat.parse(inDate.trim());
    } catch (ParseException pe) {
        return false;
    }
    return true;
}


private boolean isValidPeriode(String per) {
	try{
		Integer.parseInt(per);
		
	}catch (NumberFormatException ex) {
		
	   return false;
	}
	return true;
	
}
}