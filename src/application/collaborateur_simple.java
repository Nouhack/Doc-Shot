package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.control.ListView;

public class collaborateur_simple {

	ArrayList<Integer> simple_users_select_list_id = new ArrayList<Integer>();

	// create principal collaborator method -----------------//
	
	void créer_collab_simp(ListView<String> selected_users,int id_app){
		get_selected_simple_users_id(selected_users);
		for (int i = 0; i < simple_users_select_list_id.size(); i++) {
			
			try {
				jdbc.setConnection();
				Statement stm=jdbc.setConnection().createStatement();
				Statement stm2=jdbc.setConnection().createStatement();
				Statement stm3=jdbc.setConnection().createStatement();
				
				
					
				String val="select id from collaborateur_simple where id="+'"'+simple_users_select_list_id.get(i)+'"';
				String strcheck="insert into collaborateur_simple (id) values ("+'"'+simple_users_select_list_id.get(i)+'"'+")";
				String strcheck2="insert into collab_app (Id_collab_samp,Id_app) values("+'"'+simple_users_select_list_id.get(i)+'"'+","+'"'+id_app+'"'+")";
				
				
				
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
	private void get_selected_simple_users_id(ListView<String> selected_users) {
		for (int i = 0; i <selected_users.getItems().size(); i++) {
			
			try {
				jdbc.setConnection();
				Statement stm=jdbc.setConnection().createStatement();
				String strcheck="select id from utilisateur where username = "+'"'+selected_users.getItems().get(i)+'"';
									
					
				ResultSet res = stm.executeQuery(strcheck);
				
				
				while (res.next()) {
					simple_users_select_list_id.add(res.getInt("id"));
				}
				jdbc.setConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		add_collab_prnc();
	}
	//--------END get selected users id method--------------//
	void add_collab_prnc() {
		try {
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="select id from utilisateur where username="+'"'+login_class.UsernameLog+'"';
								
				
			ResultSet res = stm.executeQuery(strcheck);
			
			
			while (res.next()) {
				simple_users_select_list_id.add(res.getInt("id"));
			}
			jdbc.setConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete_collab_smp(String username) {

try {
			
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="delete from collab_app where Id_collab_samp=\r\n" + 
					"(select collaborateur_simple.id from collaborateur_simple,utilisateur \r\n" + 
					"where collaborateur_simple.id=utilisateur.id and utilisateur.username="+'"'+username+'"'+")";
			
			String strcheck2="delete from collaborateur_simple \r\n" + 
					"where id=(select id from utilisateur where username="+'"'+username+'"'+")";
			stm.executeUpdate(strcheck);
			stm.executeUpdate(strcheck2);

			jdbc.setConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public boolean vérification_collab_simp(String username) {
		// TODO Auto-generated method stub
try {
			
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="select collaborateur_simple.id from collaborateur_simple,utilisateur where collaborateur_simple.id=utilisateur.id and utilisateur.username="+'"'+username+'"';
			
				
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

	
	
	public void delete_collab_smp_app(String app_name) {
try {
			
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="delete from collab_app where id_app=(select id from application where nom_app=+"+'"'+app_name+'"'+")";
			
			
			stm.executeUpdate(strcheck);


			jdbc.setConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
