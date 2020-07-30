package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

public class user__list {

	void users_tree_table_vview(JFXTreeTableView<User> aa,String userLogn) {
		
	
	JFXTreeTableColumn<User, String> deptName = new JFXTreeTableColumn<>("Username");
    deptName.setPrefWidth(300);
    deptName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
            return param.getValue().getValue().userName;
        }
    });

    
    JFXTreeTableColumn<User, String> pass = new JFXTreeTableColumn<>("Password");
    pass.setPrefWidth(300);
    pass.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
        public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
            return param.getValue().getValue().password;
        }
    });
    
    

    
    
    
 // data
    ObservableList<User> users = FXCollections.observableArrayList();

    get_users_list_to_tree_table(users,userLogn);
    
    final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
    
    
    aa.getColumns().setAll(deptName, pass);
    aa.setRoot(root);
    aa.setShowRoot(false);
    
    aa.setEditable(false);
    aa.getColumns().setAll(deptName, pass);
     
   
     
  
    
	}
	
	
	void User_filter(JFXTextField zz,JFXTreeTableView<User> deltree) {
		
		zz.textProperty().addListener((o,oldVal,newVal)->{
			deltree.setPredicate(user -> user.getValue().userName.get().contains(newVal)
		                    || user.getValue().password.get().contains(newVal)
		                    );
		    });
	}
	 void get_users_list_to_tree_table(ObservableList<User> pr,String userLogn ) {
			
			try {
				
				jdbc.setConnection();
				Statement stm=jdbc.setConnection().createStatement();
				String strcheck="select * from utilisateur where not id in (select id from admin) and not username = "+'"'+userLogn+'"';
								
					
				ResultSet res = stm.executeQuery(strcheck);
				
				
				while (res.next()) {
					pr.add(new User(res.getString("username"),res.getString("password")));
				}
				jdbc.setConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    
}
