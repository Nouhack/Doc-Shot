package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.control.ListView;

public class collaborateur_principal {
	ArrayList<Integer> users_select_list_id = new ArrayList<Integer>();

	// create principal collaborator method -----------------//
	
	void créer_collab_princ(ListView<String> selected_users,int id_pro){
		get_selected_users_id(selected_users);
		for (int i = 0; i < users_select_list_id.size(); i++) {
			
			try {
				jdbc.setConnection();
				Statement stm=jdbc.setConnection().createStatement();
				Statement stm2=jdbc.setConnection().createStatement();
				Statement stm3=jdbc.setConnection().createStatement();
				
				
					
				String val="select id from collaborateur_principal where id="+'"'+users_select_list_id.get(i)+'"';
				String strcheck="insert into collaborateur_principal (id) values ("+'"'+users_select_list_id.get(i)+'"'+")";
				String strcheck2="insert into collab_projet (id_collab_princ,id_projet) values("+'"'+users_select_list_id.get(i)+'"'+","+'"'+id_pro+'"'+")";
				
				
				
				ResultSet res = stm.executeQuery(val);
				
				while (res.next()==false) {
					
					stm2.execute(strcheck);
					break;
				}
				
				
				stm3.execute(strcheck2);
				
				jdbc.setConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		
		}
	//------------ END create principal collaborater method---------------//
	
	//------ get selected users id method -------------------------------------//
	private void get_selected_users_id(ListView<String> selected_users) {
		for (int i = 0; i <selected_users.getItems().size(); i++) {
			
			try {
				jdbc.setConnection();
				Statement stm=jdbc.setConnection().createStatement();
				String strcheck="select id from utilisateur where username = "+'"'+selected_users.getItems().get(i)+'"';
									
					
				ResultSet res = stm.executeQuery(strcheck);
				
				
				while (res.next()) {
					users_select_list_id.add(res.getInt("id"));
				}
				jdbc.setConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//--------END get selected users id method--------------//

	public boolean vérification_collab_princ(String username) {
		try {
			
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="select collaborateur_principal.id from collaborateur_principal,utilisateur where collaborateur_principal.id=utilisateur.id and utilisateur.username="+'"'+username+'"';
			
				
			ResultSet res = stm.executeQuery(strcheck);
			
			
			while (res.next()) {
				return true;
			}
			
			
			
			
			
			jdbc.setConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
//-----------------------------------------------------
	
	//delete_collab_prnc-------------------------------------
	
	void delete_collab_prnc(String username) {
		try {
			
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="delete from collab_projet where id_collab_princ=\r\n" + 
					"(select collaborateur_principal.id from collaborateur_principal,utilisateur \r\n" + 
					"where collaborateur_principal.id=utilisateur.id and utilisateur.username="+'"'+username+'"'+")";
			
			String strcheck2="delete from collaborateur_principal \r\n" + 
					"where id=(select id from utilisateur where username="+'"'+username+'"'+")";
			stm.executeUpdate(strcheck);

			stm.executeUpdate(strcheck2);

			jdbc.setConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	void delete_collab_prnc_pr(String nom_projet) {
		try {
			
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="delete from collab_projet where id_projet=(select id from projet where nom_projet=+"+'"'+nom_projet+'"'+")";				
			stm.executeUpdate(strcheck);

			String strcheck1="delete from collaborateur_principal where id  not in(select id_collab_princ from collab_projet)";				
			stm.executeUpdate(strcheck1);
			

			jdbc.setConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
