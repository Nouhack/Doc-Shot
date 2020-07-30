package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;

public class project__list {
	static String name_pro_bval=""; 
	String valKey="";
	static ArrayList<String> collected_values = new ArrayList<String>();
	
	void project_tree_table_vview(JFXTreeTableView<Project> aa,boolean editable,String statem) {
		
		JFXTreeTableColumn<Project, String> Intitul�_projet = new JFXTreeTableColumn<>("Intitul� duprojet");
		Intitul�_projet.setPrefWidth(150);
		
		Intitul�_projet.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Project, String> param) {
            	
                return param.getValue().getValue().Intitul�_projet;
            }
        });
        
		
		
        
        JFXTreeTableColumn<Project, String> Nom_projet = new JFXTreeTableColumn<>("Nom Projet");
        Nom_projet.setPrefWidth(150);
        Nom_projet.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(Nom_projet.validateValue(param)) return param.getValue().getValue().Nom_projet;
		    else return Nom_projet.getComputedValue(param);
		});
        
        
       
        
        JFXTreeTableColumn<Project, String> cadre_obtention = new JFXTreeTableColumn<>("cadre obtention");
        cadre_obtention.setPrefWidth(150);
        cadre_obtention.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(cadre_obtention.validateValue(param)) return param.getValue().getValue().cadre_obtention;
		    else return cadre_obtention.getComputedValue(param);
		});
        
        
       
        
        JFXTreeTableColumn<Project, String> Date_d�but = new JFXTreeTableColumn<>("Date de D�but");
        Date_d�but.setPrefWidth(150);
        Date_d�but.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(Date_d�but.validateValue(param)) return param.getValue().getValue().Date_d�but;
		    else return Date_d�but.getComputedValue(param);
		});
        
        	        
        
        JFXTreeTableColumn<Project, String> p�riode = new JFXTreeTableColumn<>("p�riode de ralisation");
        p�riode.setPrefWidth(150);
        p�riode.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(p�riode.validateValue(param)) return param.getValue().getValue().p�riode;
		    else return p�riode.getComputedValue(param);
		});
        
        
        
 
        
        JFXTreeTableColumn<Project, String> technologies_mise = new JFXTreeTableColumn<>("technologies mise en oeuvre");
        technologies_mise.setPrefWidth(150);
        technologies_mise.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(technologies_mise.validateValue(param)) return param.getValue().getValue().technologies_mise;
		    else return technologies_mise.getComputedValue(param);
		});
        
        
        
        JFXTreeTableColumn<Project, String> context_projet = new JFXTreeTableColumn<>("Context du projet");
        context_projet.setPrefWidth(150);
        context_projet.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(context_projet.validateValue(param)) return param.getValue().getValue().context_projet;
		    else return context_projet.getComputedValue(param);
		});
        
        
    
        
        JFXTreeTableColumn<Project, String> Probl�matique = new JFXTreeTableColumn<>("Probl�matique");
        Probl�matique.setPrefWidth(150);
        Probl�matique.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(Probl�matique.validateValue(param)) return param.getValue().getValue().probl�matique;
		    else return Probl�matique.getComputedValue(param);
		});
        
       
        
        JFXTreeTableColumn<Project, String> Solution_propo = new JFXTreeTableColumn<>("Solution prepos�");
        Solution_propo.setPrefWidth(150);
        Solution_propo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(Solution_propo.validateValue(param)) return param.getValue().getValue().solution_propo;
		    else return Solution_propo.getComputedValue(param);
		});
        
        
        
        
        
        
        
        
        JFXTreeTableColumn<Project, String> �tape_projet = new JFXTreeTableColumn<>("�tapes du projet");
        �tape_projet.setPrefWidth(150);
        �tape_projet.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(�tape_projet.validateValue(param)) return param.getValue().getValue().�tape_projet;
		    else return �tape_projet.getComputedValue(param);
		});
        
        
        
      
        
        JFXTreeTableColumn<Project, String> B�n�f_client = new JFXTreeTableColumn<>("B�n�fices client");
        B�n�f_client.setPrefWidth(150);
        B�n�f_client.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(B�n�f_client.validateValue(param)) return param.getValue().getValue().b�n�f_client;
		    else return B�n�f_client.getComputedValue(param);
		});
        
   //------------------------------     
      
        Intitul�_projet.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        Intitul�_projet.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).Intitul�_projet.
        set(t.getNewValue());
//System.out.println(deleteTree.getColumns().get(1).getCellObservableValue(deleteTree.getSelectionModel().getSelectedItem()).getValue());
System.out.println("le champ hwa = Intitul�_projet");
System.out.println("la nouvelle valeur hya "+t.getNewValue());
System.out.println("la valeur key hya "+valKey);

collected_values.add("Intitul�_pr");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
        });
        
        
        Intitul�_projet.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> arg0) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
				
			}
		});
        
        Nom_projet.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        Nom_projet.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).Nom_projet.
        set(t.getNewValue());
collected_values.add("nom_projet");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
});
        
        
        Nom_projet.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
        
        cadre_obtention.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        cadre_obtention.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).cadre_obtention.
        set(t.getNewValue());
collected_values.add("cadre_obt_pr");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
});
        
        
        cadre_obtention.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
        
        Date_d�but.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        Date_d�but.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).Date_d�but.
        set(t.getNewValue());
collected_values.add("Date_d�but");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
});
        
        Date_d�but.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
        
        p�riode.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        p�riode.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).p�riode.
        set(t.getNewValue());
collected_values.add("p�riode");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
});
     
        p�riode.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
   
        
        technologies_mise.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        technologies_mise.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).technologies_mise.
        set(t.getNewValue());
collected_values.add("T�ch_mis_oeu");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
});
      
        
        technologies_mise.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
        
        context_projet.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        context_projet.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).context_projet.
        set(t.getNewValue());
collected_values.add("Context_projet");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
});
        
        context_projet.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
        Probl�matique.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        Probl�matique.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).probl�matique.
        set(t.getNewValue());
collected_values.add("Probl�matique");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
});     
        
        
        Probl�matique.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
        
        Solution_propo.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        Solution_propo.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).solution_propo.
        set(t.getNewValue());
collected_values.add("Solution_propo");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
}); 
     
        Solution_propo.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
        
        �tape_projet.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        �tape_projet.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).�tape_projet.
        set(t.getNewValue());
collected_values.add("�tape_projet");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
}); 

        
        �tape_projet.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
        
        B�n�f_client.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        B�n�f_client.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).b�n�f_client.
        set(t.getNewValue());
collected_values.add("B�n�f_client");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
}); 
        
        B�n�f_client.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
       //-------------------------------------------------
        
     // data
        ObservableList<Project> projects = FXCollections.observableArrayList();
        
        get_project_list_to_tree_table(statem, projects);
        
        
        
        final TreeItem<Project> root2 = new RecursiveTreeItem<Project>(projects, RecursiveTreeObject::getChildren);
        aa.getColumns().setAll(Intitul�_projet, Nom_projet,cadre_obtention,Date_d�but,p�riode, technologies_mise,context_projet,Probl�matique,Solution_propo,�tape_projet,B�n�f_client);
        aa.setRoot(root2);
        aa.setShowRoot(false);
        
        aa.setEditable(editable);
        aa.getColumns().setAll(Intitul�_projet, Nom_projet,cadre_obtention,Date_d�but,p�riode, technologies_mise,context_projet,Probl�matique,Solution_propo,�tape_projet,B�n�f_client);
	}
	
	
	
	void project_filter(JFXTextField zz,JFXTreeTableView<Project> deltree) {
		
		zz.textProperty().addListener((o,oldVal,newVal)->{
			deltree.setPredicate(pro -> pro.getValue().Intitul�_projet.get().contains(newVal)
                        || pro.getValue().Nom_projet.get().contains(newVal)
                        || pro.getValue().cadre_obtention.get().contains(newVal)
        				|| pro.getValue().Date_d�but.get().contains(newVal)
        				|| pro.getValue().p�riode.get().contains(newVal)
        				|| pro.getValue().technologies_mise.get().contains(newVal)
        				|| pro.getValue().context_projet.get().contains(newVal)
        				|| pro.getValue().probl�matique.get().contains(newVal)
        				|| pro.getValue().solution_propo.get().contains(newVal)
        				|| pro.getValue().�tape_projet.get().contains(newVal)
        				|| pro.getValue().b�n�f_client.get().contains(newVal)
        				
        				|| pro.getValue().technologies_mise.get().contains(newVal));
        	
						
        });
		
	}
	
	 void get_project_list_to_tree_table(String statement,ObservableList<Project> pr) {
			
			try {
				
				jdbc.setConnection();
				Statement stm=jdbc.setConnection().createStatement();
				String strcheck="select * from projet "+statement;
								
					
				ResultSet res = stm.executeQuery(strcheck);
				
				
				while (res.next()) {
					pr.add(new Project(res.getString("Intitul�_pr"),res.getString("nom_projet"),res.getString("cadre_obt_pr"),res.getString("Date_d�but"),res.getString("p�riode"),res.getString("T�ch_mis_oeu"),res.getString("Context_projet"),res.getString("Probl�matique"),res.getString("Solution_propo"),res.getString("�tape_projet"),res.getString("B�n�f_client")));
				}
				jdbc.setConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
	 static Integer get_project_id(JFXTreeTableView<Project> aa) {
		 try {
				
				jdbc.setConnection();
				Statement stm=jdbc.setConnection().createStatement();
				String strcheck="select * from projet where nom_projet ="+ '"'+aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString()+'"';
								
					
				ResultSet res = stm.executeQuery(strcheck);
				
				
				while (res.next()) {
					return res.getInt("id");
				}
	 }catch (Exception e) {
		// TODO: handle exception
	}
	 return 0;
}}
