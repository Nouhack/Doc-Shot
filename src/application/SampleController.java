package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import documentation.doc_view_controller;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Cell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import jfxtras.styles.jmetro8.JMetro;
import jfxtras.styles.jmetro8.JMetro.Style;

public class SampleController implements Initializable{
	public static String selected_project_name="";
	public static String selected_app_name="";
	
   static boolean project_modified=false;
    
    @FXML
    private JFXCheckBox intitulé_check;
    
    @FXML
    private JFXCheckBox nom_proj_check;
    
    @FXML
    private JFXCheckBox cadre_obt_proj_check;
    
    @FXML
    private JFXCheckBox date_déb_proj_checkj;
    
    @FXML
    private JFXCheckBox période_proj_check;
    

    @FXML
    private JFXCheckBox tech_mise_oeuv_check;
    

    @FXML
    private JFXCheckBox context_proj_checkj;
    

    @FXML
    private JFXCheckBox problém_proj_check;
    
    @FXML
    private JFXCheckBox solution_prepo_check;
    
    @FXML
    private JFXCheckBox etape_proj_check;
    

    @FXML
    private JFXCheckBox béfin_proj_check;
    

    @FXML
    private JFXCheckBox nom_app_check;
    
    @FXML
    private JFXCheckBox descrip_app_check;
    
    @FXML
    private JFXCheckBox img_title_check;
    
    @FXML
    private JFXTextField app_filter;
    
    @FXML
    private JFXTreeTableView<application_documenter> app_list;
    
    @FXML
    private JFXListView<String> no_collab_users_list;

    @FXML
    private JFXListView<String> newCollab_list;
    
    @FXML
    private JFXTextField modif_pro_filter;
    
    @FXML
    private  JFXTreeTableView<Project> modif_pro_list;
    
    @FXML
    private TextField app_name;

    @FXML
    private TextArea app_descrip;

    @FXML
    private JFXListView<String> select_users;
    
    @FXML
    private Tab add_users;
    @FXML
    private Tab overv;

    @FXML
    private Tab add_pro;
    
    @FXML
    private Tab ajouter_app;
    
    @FXML
    private Tab modif_pro;
    
    @FXML
    private Tab create_doc;
    
    @FXML
    private  Tab export_Doc;
    
    @FXML
    private JFXListView<String> project_list;
    
	 @FXML
	    private JFXTextArea  intitulé_pr;

	        @FXML
	    private JFXTextField nom_pr;

	    @FXML
	    private JFXTextField cadre_obt;


	    @FXML
	    private JFXDatePicker  date_dbt;

	        @FXML
	    private JFXTextField tech_mise;

	        @FXML
	    private JFXTextArea context_prs;

	        @FXML
	    private JFXTextArea problem;

	        @FXML
	    private JFXTextArea solu_pro;

	        @FXML
	    private JFXTextArea etape_pr;

	        @FXML
	    private JFXTextArea benefic_pr;
	        
	        @FXML
	        private JFXTextField doc_name;
	        
    @FXML
    private  JFXListView<String> userList;

    @FXML
    private JFXListView<String> Users_list_2;
    
    @FXML
    private JFXListView<String> princip_collab_list;
    
    @FXML
    private Spinner<Integer> periode;
    
    @FXML
    private static JFXTabPane Admintabpane;
    
    @FXML
    private JFXTextField userTxt;

    @FXML
    private JFXPasswordField passTxt;

    @FXML
    private  JFXPasswordField repassTxt;
    
    @FXML
    private Tab SuppUser;
    
    @FXML
    private Tab deleteusertap;

    @FXML
    private BarChart<?, ?> chart;

    @FXML
    private CategoryAxis Xou;

    @FXML
    private Tab papa;
    
    @FXML
    private JFXTextField filterField;


    @FXML
    private JFXTextField projectFilter;
    
    @FXML
    private JFXTreeTableView<User> treeview;
    
    @FXML
    private JFXTreeTableView<application_documenter> app_list_final_doc;
    
    @FXML
    private JFXTextField app_list_final_filter;
    
    @FXML
    private JFXTreeTableView<Project> deleteTree;
    
    @FXML
    private NumberAxis You;
    
    XYChart.Series set1=new XYChart.Series<>();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		addTextLimiter(cadre_obt,30);
		addTextLimiter(nom_pr,50);		
		addTextLimiter(tech_mise,200);
		User p=new User();
		switch (p.user_role(login_class.UsernameLog)) {
		
		case "Admin":
			
			System.out.print("hada admin khou");
			export_Doc.getTabPane().getTabs().removeAll(ajouter_app,modif_pro,create_doc,export_Doc) ;
			
			
			 
			
			//------------------------------
			user__list usr_lis=new user__list();
			usr_lis.users_tree_table_vview(treeview, login_class.UsernameLog);
			usr_lis.User_filter(filterField, treeview);
		  //-------------------------------------------------------------------      
		        project__list prolist=new project__list();
		        prolist.project_tree_table_vview(deleteTree, false, "");
		        
		        prolist.project_filter(projectFilter, deleteTree);
		         
		        /*
		        Label size2 = new Label();
		        size.textProperty().bind(Bindings.createStringBinding(()->deleteTree.getCurrentItemsCount()+"",
		        		deleteTree.currentItemsCountProperty()));
		        
		        
		        */
		        
			set1.getData().add(new XYChart.Data("projet",countProjects()));
			set1.getData().add(new XYChart.Data("applications",countapplications()));
			set1.getData().add(new XYChart.Data("utilisateurs",countutilisateurs()));
			
			chart.getData().addAll(set1);
			
			
			
			///----------------------------------------------------------------------------------------------
			
			SpinnerValueFactory<Integer> spinnerYearValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100); 
			periode.setValueFactory(spinnerYearValueFactory);
			
			//-----------------  call get_user_list in add project view--------------------------
			User utilisateur=new User();
			utilisateur.get_user_list(userList, login_class.UsernameLog);
			//---------------------------------------------------------------
			break;
		case "Principal_Simple":
			deleteusertap.getTabPane().getTabs().removeAll(overv,add_users,SuppUser,add_pro,deleteusertap) ;
			
			//-----------------  call get_user_list in add project view--------------------------
			User utilisateur2=new User();
			utilisateur2.get_user_list(Users_list_2, login_class.UsernameLog);
			
			Project.get_project_list(project_list, login_class.UsernameLog);
	//---------------------------------------------------------------
			
			project__list prolist2=new project__list();
	        prolist2.project_tree_table_vview(modif_pro_list, true,"where id in(select id_projet from collab_projet where  id_collab_princ=(select id from utilisateur where username="+'"'+login_class.UsernameLog+'"'+"))");
			
	        prolist2.project_filter(modif_pro_filter, modif_pro_list);
			
	        application__list applist=new application__list();
	        applist.application_tree_table_vview(app_list, false, login_class.UsernameLog);
	        applist.application_filter(app_filter, app_list);
	        
	        //---------------------------------------------------------
	       
	        application__list applist2=new application__list();
	        applist2.application_tree_table_vview(app_list_final_doc, false, login_class.UsernameLog);
	        applist2.application_filter(app_list_final_filter, app_list_final_doc);
	        
	        
			
			break;
		case "Collaborateur_principal":
			System.out.print("hada collaborateur khou");
			deleteusertap.getTabPane().getTabs().removeAll(overv,add_users,SuppUser,add_pro,deleteusertap,create_doc,export_Doc) ;	
			
			
			
			project__list prolist3=new project__list();
	        prolist3.project_tree_table_vview(modif_pro_list, true,"where id in(select id_projet from collab_projet where  id_collab_princ=(select id from utilisateur where username="+'"'+login_class.UsernameLog+'"'+"))");
			
	        prolist3.project_filter(modif_pro_filter, modif_pro_list);
			

	        
	        
	        
			//-----------------  call get_user_list in add project view--------------------------
			User utilisateur23=new User();
			utilisateur23.get_user_list(Users_list_2, login_class.UsernameLog);
			
			Project.get_project_list(project_list, login_class.UsernameLog);
	//---------------------------------------------------------------
			
			break;
		case "Collaborateur_Simple":
			deleteusertap.getTabPane().getTabs().removeAll(overv,add_users,SuppUser,add_pro,deleteusertap,ajouter_app,modif_pro) ;	
			application__list applist1=new application__list();
	        applist1.application_tree_table_vview(app_list, false, login_class.UsernameLog);
	        applist1.application_filter(app_filter, app_list);
	        
	        //---------------------------------------------------------
	       
	        application__list applist3=new application__list();
	        applist3.application_tree_table_vview(app_list_final_doc, false, login_class.UsernameLog);
	        applist3.application_filter(app_list_final_filter, app_list_final_doc);
	        
			break;
		default:
			System.out.println("ni hadi ni hadik");
		}
		
		
		// TODO Auto-generated method stub
		
	
		
		
		 
		
	
		
	}
	

	  
	    @FXML
	    void deleteUser(ActionEvent event) {
	        User newUser=new User();
	        
	        Alert alert = new Alert (AlertType.CONFIRMATION);
	        
	        alert.setTitle("Confirmation de la suppression");
	        alert.setHeaderText("hada hwa nouh");
	        alert.setContentText("voulez vous supprimer l'utilisateur ?");
	        
	        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
	        if (result.get() ==javafx.scene.control.ButtonType.OK ) {
	        	
	        	newUser.supp_utilisateur(treeview.getSelectionModel().getSelectedItem().getValue().userName.getValue(),treeview);
	        	
	        	
		        
		        
			} else {}
	          
	    	          
	                
	        
}
	    



	    @FXML
	    void addUser(ActionEvent event)  {
	    	
	    	User NewUser=new User();
	    	NewUser.créer_utilisateur(userTxt.getText(),passTxt.getText(),repassTxt.getText());
	    	
	    }
	    

	    void right(ListView<String> left,ListView<String> select_users2) {
	    	try {
	    		if (left.getSelectionModel().getSelectedItem()!=null) {
	    			select_users2.getItems().add(left.getSelectionModel().getSelectedItem());
	    			left.getItems().remove(left.getSelectionModel().getSelectedIndex());
				}
	    		
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }

	    void left(ListView<String> left,ListView<String> selected) {
	    	try {
	    		if (selected.getSelectionModel().getSelectedItem()!=null) {
	    			left.getItems().add(selected.getSelectionModel().getSelectedItem());
	    			selected.getItems().remove(selected.getSelectionModel().getSelectedIndex());
				}
	    		
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }
	    
	    
	    
	    @FXML
	    void leftMove(ActionEvent event) {
	    	
	    	right(princip_collab_list, userList);	
	    	
	    }

	    @FXML
	    void rightMove(ActionEvent event) {
	    	right(userList, princip_collab_list);	    	

	    }

	    

	    @FXML
	    void créer_projet(ActionEvent event) {
	    	Project pro=new Project();
	    	if (princip_collab_list.getItems().size()==0) {
	    		Alert alert = new Alert (AlertType.ERROR);
		        
		        alert.setTitle("Erreur");
		        alert.setHeaderText("Le projet ne peut pas étre sauvegardé");
		        alert.setContentText("Au minimum vous devez ajouté un seul collaborateur principal pour le projet");
		        
		        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
		        if (result.get() ==javafx.scene.control.ButtonType.OK ) {      
				} else {}
			} else {
				pro.créer_projet(intitulé_pr.getText(), nom_pr.getText(), cadre_obt.getText(), date_dbt.getValue(), tech_mise.getText(),context_prs.getText(), problem.getText(), solu_pro.getText(), etape_pr.getText(),benefic_pr.getText(),User.AdminId, periode.getValue(),princip_collab_list);
			}
	    	

	    	
	    }
	    
	

@FXML
void ajouter_app(ActionEvent event) {
	application_documenter app=new application_documenter();
	app.créer_application(app_name.getText(), app_descrip.getText(), select_users, project_list);
	
}
	    

@FXML
void to_left(ActionEvent event) {
	left(Users_list_2, select_users);
}

@FXML
void to_right(ActionEvent event) {
right(Users_list_2, select_users);
}


@FXML
void delete_project(ActionEvent event) {
	Project p=new Project();
	p.supp_projet(deleteTree.getSelectionModel().getSelectedItem().getValue().Nom_projet.getValue(),deleteTree);
	
}


@FXML
void edit_project(ActionEvent event) {
	//System.out.println(modif_pro_list.getColumns().get(1).getCellObservableValue(deleteTree.getSelectionModel().getSelectedItem()).getValue());
}


@FXML
void tata(MouseEvent event) {
	try {
		no_collab_users_list.getItems().clear();
		//System.out.println(modif_pro_list.getColumns().get(1).getCellObservableValue(modif_pro_list.getSelectionModel().getSelectedItem()).getValue());
		User.user_list_no_collab(no_collab_users_list, modif_pro_list.getColumns().get(1).getCellObservableValue(modif_pro_list.getSelectionModel().getSelectedItem()).getValue().toString());
	} catch (Exception e) {
		// TODO: handle exception
	}

}


@FXML
void modif_project(ActionEvent event) {
	Project pp=new Project();
	
	System.out.print(pp.collaborateur_principaux);
	
	pp.modifier_projet(project__list.collected_values);
	project__list.collected_values.clear();
	
	if (project_modified==true) {
		collaborateur_principal colVal=new collaborateur_principal();
		colVal.créer_collab_princ(newCollab_list, project__list.get_project_id(modif_pro_list));
		System.out.print(project__list.get_project_id(modif_pro_list));
		System.out.println("HADA HWA L BOOLEAN YA NOUH ===  "+project_modified);
	}
	
}


@FXML
void toRi(ActionEvent event) {
	right(no_collab_users_list, newCollab_list);
}

@FXML
void tolef(ActionEvent event) {
	right(Users_list_2, select_users);
	
	int i=new File("/documentation/Documentation_view.fxml").list().length;

}


@FXML
void create_doc(ActionEvent event)  {
	//System.out.println(app_list.getColumns().get(0).getCellObservableValue(app_list.getSelectionModel().getSelectedItem()).getValue());
	//doc_view_controller io =new doc_view_controller();
	//io.upload_image_from_database();
	
	System.out.println(app_list.getSelectionModel().getSelectedItem().getValue().nom_app.getValue());
	selected_project_name=app_list.getSelectionModel().getSelectedItem().getValue().project_name.getValue();
	selected_app_name=app_list.getSelectionModel().getSelectedItem().getValue().nom_app.getValue();
	
	
	
		
	
	try {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/documentation/Documentation_view.fxml"));
	    Parent root2 = (Parent) fxmlLoader.load();
	    root2.getStylesheets().add(getClass().getResource("/documentation/gui.css").toExternalForm());
	  //  new JMetro(STYLE).applyTheme(root1);
	  //    new JMetro(STYLE);
	    
	    Stage stage = new Stage();
	    stage.setTitle("ABC");
	    stage.setScene(new Scene(root2,900,700));  
	    stage.show();
	    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	 
	        	/*
	              System.out.println("Stage is closing");
	              File index = new File(System.getProperty("user.home")+"\\DocShot-Workstation\\");
	              String[]entries = index.list();
	              for(String s: entries){	 
	                  File currentFile = new File(index.getPath(),s);              
	                  currentFile.delete();
	                  System.out.println("file will be deleted = "+currentFile);
	              }
	              index.delete();
	              
	              */
	        	  File index = new File(System.getProperty("user.home")+"\\DocShot-Workstation\\");
	        	  try {
					Files.walk(index.toPath())
					    .sorted(Comparator.reverseOrder())
					    .map(Path::toFile)
					    .forEach(File::delete);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	              
	          }
	      });
	} catch (Exception e) {
		// TODO: handle exception
	}
	    
	//doc_view_controller ii=new doc_view_controller();
	//ii.upload_image_from_database();

	    
	
}

@FXML
void generate_final_doc(ActionEvent event) throws InvalidFormatException, IOException {
System.out.println(intitulé_check.isSelected());

XWPFDocument docu=new XWPFDocument();
XWPFTable table=docu.createTable();
table.setCellMargins(200, 200, 200, 200);
try {
	jdbc.setConnection();
	Statement stm=jdbc.setConnection().createStatement();
	Statement stm2=jdbc.setConnection().createStatement();
	String strcheck="select Intitulé_pr from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
	ResultSet res = stm.executeQuery(strcheck);
	//-----------------------
	
	//----------------------------------------
	
	if (res.next() && intitulé_check.isSelected()) {
		
		XWPFTableRow row0=table.getRow(0);
		
		XWPFTableCell cell0=row0.getCell(0);
		
		XWPFTableCell cell1=row0.createCell();
		
		cell0.setText("Intitulé du projet");
		
		cell1.setText(res.getString("Intitulé_pr"));
		
	}
	//-------------------------------------
	
	String strcheck2="select nom_projet from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
	ResultSet res2 = stm2.executeQuery(strcheck2);
	
if (res2.next() && nom_proj_check.isSelected()) {
	
	XWPFTableRow row1=table.createRow();
	XWPFTableCell cell0=row1.getCell(0);
	XWPFTableCell cell1=row1.getCell(1);
		
	cell0.setText("Nom du projet");
	cell1.setText(res2.getString("nom_projet"));

	}
	
	//--------------------------------------

String strcheck3="select cadre_obt_pr from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
ResultSet res3 = stm2.executeQuery(strcheck3);

if (res3.next() && cadre_obt_proj_check.isSelected()) {

XWPFTableRow row2=table.createRow();
XWPFTableCell cell0=row2.getCell(0);
XWPFTableCell cell1=row2.getCell(1);
	
cell0.setText("Cadre d’obtention du projet");
cell1.setText(res3.getString("cadre_obt_pr"));

}

//--------------------------------------

String strcheck4="select Date_début from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
ResultSet res4 = stm2.executeQuery(strcheck4);

if (res4.next() && date_déb_proj_checkj.isSelected()) {

XWPFTableRow row3=table.createRow();
XWPFTableCell cell0=row3.getCell(0);
XWPFTableCell cell1=row3.getCell(1);
	
cell0.setText("Date début");
cell1.setText((res4.getDate("Date_début")).toString());

}

//--------------------------------------

String strcheck5="select période from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
ResultSet res5 = stm2.executeQuery(strcheck5);

if (res5.next() && période_proj_check.isSelected()) {

XWPFTableRow row4=table.createRow();
XWPFTableCell cell0=row4.getCell(0);
XWPFTableCell cell1=row4.getCell(1);
	
cell0.setText("Période");
cell1.setText(Integer.toString(res5.getInt("période")));

}

//--------------------------------------

String strcheck6="select Téch_mis_oeu from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
ResultSet res6 = stm2.executeQuery(strcheck6);

if (res6.next() && tech_mise_oeuv_check.isSelected()) {

XWPFTableRow row5=table.createRow();
XWPFTableCell cell0=row5.getCell(0);
XWPFTableCell cell1=row5.getCell(1);
	
cell0.setText("Technologies mises en œuvre");
cell1.setText(res6.getString("Téch_mis_oeu"));

}

//--------------------------------------

String strcheck7="select Context_projet from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
ResultSet res7 = stm2.executeQuery(strcheck7);

if (res7.next() && context_proj_checkj.isSelected()) {

XWPFParagraph Title_para=docu.createParagraph();


XWPFRun run1=Title_para.createRun();

run1.setText("Contexte du projet");
run1.setFontSize(20);
run1.setFontFamily("Arial Black");
run1.setColor("bd4e5e");

run1.addBreak();
XWPFParagraph para=docu.createParagraph();
XWPFRun run2=para.createRun();

run2.setText(res7.getString("Context_projet"));

}

//--------------------------------------

String strcheck8="select Problématique from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
ResultSet res8 = stm2.executeQuery(strcheck8);



if (res8.next() && problém_proj_check.isSelected()) {

XWPFParagraph Title_para=docu.createParagraph();


XWPFRun run1=Title_para.createRun();

run1.setText("Problématique / besoins");
run1.setFontSize(20);
run1.setFontFamily("Arial Black");
run1.setColor("bd4e5e");

run1.addBreak();
XWPFParagraph para=docu.createParagraph();
XWPFRun run2=para.createRun();

run2.setText(res8.getString("Problématique"));


}

//--------------------------------------

String strcheck9="select Solution_propo from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
ResultSet res9 = stm2.executeQuery(strcheck9);

String strcheck10="select nom_app,app_descrip from application where id_projet=(select id from projet where nom_projet="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().project_name.getValue()+'"'+")";
ResultSet res10 = stm.executeQuery(strcheck10);

if (res9.next() && solution_prepo_check.isSelected()) {

XWPFParagraph Title_para=docu.createParagraph();
XWPFRun run1=Title_para.createRun();
run1.setText("Solution proposée");
run1.setFontSize(20);
run1.setFontFamily("Arial Black");
run1.setColor("bd4e5e");

run1.addBreak();

XWPFParagraph para=docu.createParagraph();
XWPFRun run2=para.createRun();
run2.setText(res9.getString("Solution_propo"));


run2.addBreak();

while (res10.next()) {
	
	
	XWPFParagraph para2=docu.createParagraph();
	XWPFRun run3=para2.createRun();
	run3.setText(res10.getString("nom_app")+" : ");
	run3.setFontSize(15);
	run3.setBold(true);
	
	
	XWPFRun run4=para2.createRun();
	run4.setText(res10.getString("app_descrip"));
	

	
}


}



//--------------------------------------

String strcheck11="select étape_projet from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
ResultSet res11 = stm2.executeQuery(strcheck11);



if (res11.next() && etape_proj_check.isSelected()) {

XWPFParagraph Title_para=docu.createParagraph();


XWPFRun run1=Title_para.createRun();

run1.setText("Étapes du projet");
run1.setFontSize(20);
run1.setFontFamily("Arial Black");
run1.setColor("bd4e5e");

run1.addBreak();
XWPFParagraph para=docu.createParagraph();
XWPFRun run2=para.createRun();

run2.setText(res11.getString("étape_projet"));


}

//---------------------------------------------------------------------

String strcheck12="select Bénéf_client from projet where id=(select id_projet from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+" ) ";
ResultSet res12 = stm2.executeQuery(strcheck12);



if (res12.next() && béfin_proj_check.isSelected()) {

XWPFParagraph Title_para=docu.createParagraph();


XWPFRun run1=Title_para.createRun();

run1.setText("Bénéfices client");
run1.setFontSize(20);
run1.setFontFamily("Arial Black");
run1.setColor("bd4e5e");

run1.addBreak();
XWPFParagraph para=docu.createParagraph();
XWPFRun run2=para.createRun();

run2.setText(res12.getString("Bénéf_client"));


}

//-------------------------------------------

if (nom_app_check.isSelected()) {

XWPFParagraph Title_para=docu.createParagraph();


XWPFRun run1=Title_para.createRun();

run1.setText("Le nom d'application");
run1.setFontSize(20);
run1.setFontFamily("Arial Black");
run1.setColor("bd4e5e");

run1.addBreak();
XWPFParagraph para=docu.createParagraph();
XWPFRun run2=para.createRun();

run2.setText(app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue().toString());


}

if (descrip_app_check.isSelected()) {

XWPFParagraph Title_para=docu.createParagraph();


XWPFRun run1=Title_para.createRun();

run1.setText("La Description de l'application");
run1.setFontSize(20);
run1.setFontFamily("Arial Black");
run1.setColor("bd4e5e");

run1.addBreak();
XWPFParagraph para=docu.createParagraph();
XWPFRun run2=para.createRun();

run2.setText(app_list_final_doc.getSelectionModel().getSelectedItem().getValue().app_descrip.getValue().toString());


}

//----------------------------------------------------

String strcheck13="select * from image where id_app=(select id from application where nom_app="+'"'+app_list_final_doc.getSelectionModel().getSelectedItem().getValue().nom_app.getValue()+'"'+")";
ResultSet res13 = stm2.executeQuery(strcheck13);




while (res13.next() && img_title_check.isSelected()) {

XWPFParagraph Title_para=docu.createParagraph();
XWPFRun run1=Title_para.createRun();


XWPFParagraph para=docu.createParagraph();
XWPFRun run2=para.createRun();

XWPFParagraph para2=docu.createParagraph();
XWPFRun run3=para2.createRun();

XWPFParagraph para3=docu.createParagraph();
XWPFRun run4=para3.createRun();

run1.setText(res13.getString("titre"));
run1.setBold(true);
run1.setFontSize(15);

String strcheck14="select * from image_description where id="+res13.getInt("id");
ResultSet res14 = stm.executeQuery(strcheck14);

String txt_belows = null;
String colors=null;
String styles = null;
int sizes=0;;
if (res14.next()) {
	txt_belows=res14.getString("txt_below");
	run2.setText(res14.getString("txt_above"));
	
	sizes=res14.getInt("taille");
	run2.setFontSize(sizes);
	
	styles=res14.getString("style");
	run2.setFontFamily(styles);
	
	colors=res14.getString("couleur").substring(1, res14.getString("couleur").length());
	run2.setColor(colors);
	
	
}


java.sql.Blob blob = res13.getBlob("img");  
InputStream in = blob.getBinaryStream();
para2.setAlignment(ParagraphAlignment.CENTER);

String imgFile = "";

run3.addBreak();
run3.addPicture(in, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(300), Units.toEMU(300)); // 200x200 pixels


in.close();
run4.setText(txt_belows);
run4.setFontSize(sizes);
run4.setFontFamily(styles);
run4.setColor(colors);
}

//-------------------------------------------


	jdbc.setConnection().close();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}





	
	try {
	FileOutputStream output=new FileOutputStream("C:\\Users\\R00t\\Desktop\\"+ doc_name.getText() +".docx");
	docu.write(output);
	
	output.close();
	
} catch (Exception e) {
	// TODO: handle exception
	System.out.println(e.getMessage());
}
}

static int countProjects() {
	try {
		
		jdbc.setConnection();
		Statement stm=jdbc.setConnection().createStatement();
		String strcheck="select count(*) from projet";
							
			
		
		ResultSet res = stm.executeQuery(strcheck);
		
		while (res.next()) {
			return res.getInt("count(*)");
			
			
		}
		jdbc.setConnection().close();
		
		
	} catch (Exception es) {
		// TODO: handle exception
	}
	return 0;
}

static int countapplications() {
	try {
		
		jdbc.setConnection();
		Statement stm=jdbc.setConnection().createStatement();
		String strcheck="select count(*) from application";
							
			
		
		ResultSet res = stm.executeQuery(strcheck);
		
		while (res.next()) {
			return res.getInt("count(*)");
			
			
		}
		jdbc.setConnection().close();
		
		
	} catch (Exception es) {
		// TODO: handle exception
	}
	return 0;
}

static int countutilisateurs() {
	try {
		
		jdbc.setConnection();
		Statement stm=jdbc.setConnection().createStatement();
		String strcheck="select count(*) from utilisateur";
							
			
		
		ResultSet res = stm.executeQuery(strcheck);
		
		while (res.next()) {
			return res.getInt("count(*)");
			
			
		}
		jdbc.setConnection().close();
		
		
	} catch (Exception es) {
		// TODO: handle exception
	}
	return 0;
}


public static void addTextLimiter(final JFXTextField tf, final int maxLength) {
    tf.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
            if (tf.getText().length() > maxLength) {
                String s = tf.getText().substring(0, maxLength);
                tf.setText(s);
            }
        }
    });
}
}