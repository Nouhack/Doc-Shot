package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;


	 public  class User extends RecursiveTreeObject<User> {
		 static int AdminId;
		 
	        StringProperty userName;
	        SimpleStringProperty password;

	        
	        public User(String userName,String password) {
	            this.userName = new SimpleStringProperty(userName);
	            this.password = new SimpleStringProperty(password);
	            
	        }
	        
	        public User() {
	            
	        }
	        
	 // ---------- verification method----------------------       
	        private  boolean vérification(String userName)
	        {
	        	try {
					jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="select * from utilisateur where username = "+'"'+ userName+'"';
					stm.executeQuery(strcheck);
					
					while (stm.getResultSet().next()==false) {
						return true;
						
					}
					jdbc.setConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return false;
	        	
	        }
	       // END verification method
	        
	        
	        //------ create users method-----------------------
	        void créer_utilisateur(String username,String password,String repass)
	        {
	        	
	        	if (password.contentEquals(repass) && repass.length()!=0) {
		    		System.out.println("sdfsdf");
		    		
					if (vérification(username)) {
						
						try {
						jdbc.setConnection();
							Statement stm=jdbc.setConnection().createStatement();
							String strcheck="insert into utilisateur (username,password) values ("+'"'+username+'"'+","+'"'+password+'"'+")";
							stm.execute(strcheck);
							
							Alert alert = new Alert (AlertType.INFORMATION);
							alert.setTitle("Succès ");
					        alert.setContentText("Utilisateur ajouté avec succès ");
					        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
					        
							jdbc.setConnection().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
					} else {
						
						Alert alert = new Alert (AlertType.WARNING);
				        
				        alert.setTitle("Erreur");
				        alert.setHeaderText("Le Nom existe");
				        alert.setContentText("le nom d'utilisateur existe !!!");
				        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
						System.out.println("erreur khouya moh hada yagzissti");
						
						
					}
					
				} else {
					Alert alert = new Alert (AlertType.WARNING);
			        
			        alert.setTitle("Erreur");
			        alert.setHeaderText("Erreur de la répetition");
			        alert.setContentText("le mot de passe et sa repetition doit étre sémantique !!!");
			        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
			        System.out.println("password and repassword must be equals");}
	        }
	        
	        // END create users method---------------------------------------//
	        
	        
	      // authentification methode----------------------------//  
	        public boolean authentification(String username,String password) {
	        	try {
					
	        		jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="select * from utilisateur where username= "+'"'+username+'"'+"and password= "+'"'+ password+'"';
										
						
					
					ResultSet res = stm.executeQuery(strcheck);
					
					while (res.next()) {
						 AdminId = res.getInt("id");
						return true;
						
					}
					jdbc.setConnection().close();
					
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
	        	return false;
	        }
	       
	        //------------------END authentification method------------------------//
	        
	        
	        // get users list method ---------------------------------------------//
		    void get_user_list(ListView<String> listUsers,String userLogn) {
		    	try {
					jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="select username from utilisateur where not id in (select id from admin) and not username = "+'"'+userLogn+'"';
										
						
					ResultSet res = stm.executeQuery(strcheck);
					
					
					while (res.next()) {
						listUsers.getItems().add(res.getString("username"));
						
					}
					jdbc.setConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    }
		   
		    //----------------------------- END get users list method ------------//
		    
		    // get user role -----------------------------------------------------//
		    String user_role(String username) {
		    	
		    	try {
					jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="select id from admin where id=(select id from utilisateur where username="+'"'+username+'"'+")";
					String strcheck2="select id from collaborateur_principal where id=(select id from utilisateur where username="+'"'+username+'"'+")";
					String strcheck3="select collaborateur_principal.id, collaborateur_simple.id from collaborateur_principal,collaborateur_simple where "
							+ "			collaborateur_principal.id=(select id from utilisateur where username="+'"'+username+'"'+") "
									+ "and collaborateur_simple.id=(select id from utilisateur where username="+'"'+username+'"'+")";
										
					String strcheck4="select id from collaborateur_simple where id=(select id from utilisateur where username="+'"'+username+'"'+")";
					
					ResultSet res = stm.executeQuery(strcheck);
				
					
					while (res.next()) {
						return "Admin";
					}
					
					ResultSet res3 = stm.executeQuery(strcheck3);
				
					
					while (res3.next()) {
						return "Principal_Simple";
					}
					
					ResultSet res2 = stm.executeQuery(strcheck2);
				while (res2.next()) {
						return "Collaborateur_principal";
					}
				
				
				ResultSet res4=stm.executeQuery(strcheck4);
				while (res4.next()) {
					return "Collaborateur_Simple";
				}
					jdbc.setConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
				return "";
		    	
		    }		    
		    //-------------------------------------------------------------------//
		    
		    //no collaborator user list -----------------------------------------//
		    static void user_list_no_collab(JFXListView<String> newCollab_list,String project_name) {
		    	
		    	try {
					jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="select username from utilisateur where  not id in(select id_collab_princ from collab_projet where  id_projet =(select id from projet where nom_projet="+'"'+project_name+'"'+")) and not id in(select id from admin)";
						
										
						
					ResultSet res = stm.executeQuery(strcheck);
					
					
					while (res.next()) {
						newCollab_list.getItems().add(res.getString("username"));
						
					}
					jdbc.setConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
		    //------------------------------------------------------------------//
		    
		    void supp_utilisateur(String username,JFXTreeTableView<User> treeview) {
		    	
		    	collaborateur_principal collab_pr=new collaborateur_principal();
		    	collaborateur_simple collab_smp=new collaborateur_simple();
		    	
		    	if (collab_pr.vérification_collab_princ(username)) {
		    		
					if (only_or_not_collab_princ(username)) {
						Alert alert = new Alert (AlertType.ERROR);
				        
				        alert.setTitle("Erreur");
				        alert.setHeaderText("l'utilisateur "+'"'+username+'"'+"est le seul collaborateur d'un projet");
				        alert.setContentText("vous pouvez pas supprimer le seul collaborateur principal du projet ");
				        
				        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
				        if (result.get() ==javafx.scene.control.ButtonType.OK ) {      
						} else {}
					} else {
						if (collab_smp.vérification_collab_simp(username)&&only_or_not_collab_simp(username)) {
							
							Alert alert = new Alert (AlertType.ERROR);
							alert.setTitle("Erreur");
					        alert.setHeaderText("l'utilisateur "+'"'+username+'"'+"est le seul collaborateur d'une application");
					        alert.setContentText("vous pouvez pas supprimer le seul collaborateur simple de l'application ");
					        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
					        
						} else {
							collab_pr.delete_collab_prnc(username);
							collab_smp.delete_collab_smp(username);
							delete(username);
							delete_user_from_list(treeview);
							
							Alert alert = new Alert (AlertType.INFORMATION);
							alert.setTitle("Succès ");
					        alert.setContentText("Utilisateur Supprimé avec succès ");
					        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
					        
						}
						
						
					}
				}else {
					if (collab_smp.vérification_collab_simp(username)) {
						if (only_or_not_collab_simp(username)) {
							Alert alert = new Alert (AlertType.ERROR);
							alert.setTitle("Erreur");
					        alert.setHeaderText("l'utilisateur "+'"'+username+'"'+"est le seul collaborateur d'une application");
					        alert.setContentText("vous pouvez pas supprimer le seul collaborateur simple de l'application ");
					        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
						} else {
							collab_smp.delete_collab_smp(username);
							delete(username);
							delete_user_from_list(treeview);
							
							Alert alert = new Alert (AlertType.INFORMATION);
							alert.setTitle("Succès ");
					        alert.setContentText("Utilisateur Supprimé avec succès ");
					        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
					        
						}
						
					}else {
						delete(username);
						delete_user_from_list(treeview);
						
						Alert alert = new Alert (AlertType.INFORMATION);
						alert.setTitle("Succès ");
				        alert.setContentText("Utilisateur Supprimé avec succès ");
				        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
				        
						}
					
				}
		    }
		    
		    boolean only_or_not_collab_princ(String username) {
		    	try {
					
					jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="select count(*) from collab_projet where id_projet=(select id_projet from collab_projet where id_collab_princ=(select id from utilisateur where username="+'"'+username+'"'+") )";				
						
					ResultSet res = stm.executeQuery(strcheck);
					
					
					while (res.next()) {
						if (res.getInt("count(*)")>1) {
							return false;
						}
					}
	
					jdbc.setConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
		    }
	 
		    boolean only_or_not_collab_simp(String username) {
		    	try {
					
					jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="select count(*) from collab_app where id_app=(select id_app from collab_app where id_collab_samp=(select id from utilisateur where username="+'"'+username+'"'+") )";				
						
					ResultSet res = stm.executeQuery(strcheck);
					
					
					while (res.next()) {
						if (res.getInt("count(*)")>1) {
							return false;
						}
					}
	
					jdbc.setConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
		    }
	 
	 
		    void delete(String username) {
		    	try {
					
					jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="delete from utilisateur where username="+'"'+username+'"';
					
				
					stm.executeUpdate(strcheck);
					

					jdbc.setConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	 
	 
	 void delete_user_from_list(JFXTreeTableView<User> treeview) {
		 TreeTableViewSelectionModel<User> sm = treeview.getSelectionModel();
	    	
	    	// Get the selected Entry
	        int rowIndex = sm.getSelectedIndex();
	        TreeItem<User> selectedItem = sm.getModelItem(rowIndex);
	        TreeItem<User> parent = selectedItem.getParent();
	        
	        parent.getChildren().remove(selectedItem);
	 }
	 }

