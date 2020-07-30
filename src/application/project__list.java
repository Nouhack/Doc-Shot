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
		
		JFXTreeTableColumn<Project, String> Intitulé_projet = new JFXTreeTableColumn<>("Intitulé duprojet");
		Intitulé_projet.setPrefWidth(150);
		
		Intitulé_projet.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Project, String> param) {
            	
                return param.getValue().getValue().Intitulé_projet;
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
        
        
       
        
        JFXTreeTableColumn<Project, String> Date_début = new JFXTreeTableColumn<>("Date de Début");
        Date_début.setPrefWidth(150);
        Date_début.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(Date_début.validateValue(param)) return param.getValue().getValue().Date_début;
		    else return Date_début.getComputedValue(param);
		});
        
        	        
        
        JFXTreeTableColumn<Project, String> période = new JFXTreeTableColumn<>("période de ralisation");
        période.setPrefWidth(150);
        période.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(période.validateValue(param)) return param.getValue().getValue().période;
		    else return période.getComputedValue(param);
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
        
        
    
        
        JFXTreeTableColumn<Project, String> Problématique = new JFXTreeTableColumn<>("Problématique");
        Problématique.setPrefWidth(150);
        Problématique.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(Problématique.validateValue(param)) return param.getValue().getValue().problématique;
		    else return Problématique.getComputedValue(param);
		});
        
       
        
        JFXTreeTableColumn<Project, String> Solution_propo = new JFXTreeTableColumn<>("Solution preposé");
        Solution_propo.setPrefWidth(150);
        Solution_propo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(Solution_propo.validateValue(param)) return param.getValue().getValue().solution_propo;
		    else return Solution_propo.getComputedValue(param);
		});
        
        
        
        
        
        
        
        
        JFXTreeTableColumn<Project, String> étape_projet = new JFXTreeTableColumn<>("étapes du projet");
        étape_projet.setPrefWidth(150);
        étape_projet.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(étape_projet.validateValue(param)) return param.getValue().getValue().étape_projet;
		    else return étape_projet.getComputedValue(param);
		});
        
        
        
      
        
        JFXTreeTableColumn<Project, String> Bénéf_client = new JFXTreeTableColumn<>("Bénéfices client");
        Bénéf_client.setPrefWidth(150);
        Bénéf_client.setCellValueFactory((TreeTableColumn.CellDataFeatures<Project, String> param) ->{
		    if(Bénéf_client.validateValue(param)) return param.getValue().getValue().bénéf_client;
		    else return Bénéf_client.getComputedValue(param);
		});
        
   //------------------------------     
      
        Intitulé_projet.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        Intitulé_projet.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).Intitulé_projet.
        set(t.getNewValue());
//System.out.println(deleteTree.getColumns().get(1).getCellObservableValue(deleteTree.getSelectionModel().getSelectedItem()).getValue());
System.out.println("le champ hwa = Intitulé_projet");
System.out.println("la nouvelle valeur hya "+t.getNewValue());
System.out.println("la valeur key hya "+valKey);

collected_values.add("Intitulé_pr");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
        });
        
        
        Intitulé_projet.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
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
        
        
        Date_début.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        Date_début.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).Date_début.
        set(t.getNewValue());
collected_values.add("Date_début");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
});
        
        Date_début.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
        
        période.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        période.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).période.
        set(t.getNewValue());
collected_values.add("période");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
});
     
        période.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
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
collected_values.add("Téch_mis_oeu");
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
        
        Problématique.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        Problématique.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).problématique.
        set(t.getNewValue());
collected_values.add("Problématique");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
});     
        
        
        Problématique.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
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
        
        
        étape_projet.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        étape_projet.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).étape_projet.
        set(t.getNewValue());
collected_values.add("étape_projet");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
}); 

        
        étape_projet.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
			@Override
			public void handle(CellEditEvent<Project, String> event) {
				// TODO Auto-generated method stub
				valKey=aa.getColumns().get(1).getCellObservableValue(aa.getSelectionModel().getSelectedItem()).getValue().toString();
			}
		});
        
        
        Bénéf_client.setCellFactory((TreeTableColumn<Project, String> param) ->
        new GenericEditableTreeTableCell<Project, String>(new TextFieldEditorBuilder()));
        Bénéf_client.setOnEditCommit((CellEditEvent<Project, String> t)->{
((Project) t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()).bénéf_client.
        set(t.getNewValue());
collected_values.add("Bénéf_client");
collected_values.add(t.getNewValue());
collected_values.add(valKey);
}); 
        
        Bénéf_client.setOnEditStart(new EventHandler<TreeTableColumn.CellEditEvent<Project,String>>() {
			
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
        aa.getColumns().setAll(Intitulé_projet, Nom_projet,cadre_obtention,Date_début,période, technologies_mise,context_projet,Problématique,Solution_propo,étape_projet,Bénéf_client);
        aa.setRoot(root2);
        aa.setShowRoot(false);
        
        aa.setEditable(editable);
        aa.getColumns().setAll(Intitulé_projet, Nom_projet,cadre_obtention,Date_début,période, technologies_mise,context_projet,Problématique,Solution_propo,étape_projet,Bénéf_client);
	}
	
	
	
	void project_filter(JFXTextField zz,JFXTreeTableView<Project> deltree) {
		
		zz.textProperty().addListener((o,oldVal,newVal)->{
			deltree.setPredicate(pro -> pro.getValue().Intitulé_projet.get().contains(newVal)
                        || pro.getValue().Nom_projet.get().contains(newVal)
                        || pro.getValue().cadre_obtention.get().contains(newVal)
        				|| pro.getValue().Date_début.get().contains(newVal)
        				|| pro.getValue().période.get().contains(newVal)
        				|| pro.getValue().technologies_mise.get().contains(newVal)
        				|| pro.getValue().context_projet.get().contains(newVal)
        				|| pro.getValue().problématique.get().contains(newVal)
        				|| pro.getValue().solution_propo.get().contains(newVal)
        				|| pro.getValue().étape_projet.get().contains(newVal)
        				|| pro.getValue().bénéf_client.get().contains(newVal)
        				
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
					pr.add(new Project(res.getString("Intitulé_pr"),res.getString("nom_projet"),res.getString("cadre_obt_pr"),res.getString("Date_début"),res.getString("période"),res.getString("Téch_mis_oeu"),res.getString("Context_projet"),res.getString("Problématique"),res.getString("Solution_propo"),res.getString("étape_projet"),res.getString("Bénéf_client")));
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
