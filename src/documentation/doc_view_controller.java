package documentation;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseInputListener;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Constants;

import application.Images;
import application.SampleController;
import application.application_documenter;
import application.image_description;
import application.jdbc;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class doc_view_controller extends ListCell<String>  implements Initializable    {
    @FXML
    private VBox main_window_2;
    
	ListCell thisCell = this;
	String ssss="";
	//ListCell thisCell = this;
	//extends ListCell<String>
	ArrayList<String> m =new ArrayList<String>();
	ArrayList<String> name =new ArrayList<String>();
	double rcx,rcy=0;
	double mx,my,mx1,my1=0;
	double x,z;
	boolean r,rec,paint,canimage=false;
	int TextSize=0;
	int initvalue=1;
	double gr=1;
	float oldx,oldy=0;
	int prev_int_value;
	File  FilesList;
	
	ArrayList<Image> listOfImages_from_database= new ArrayList<Image>();
	ArrayList<Image> list_of_local_image= new ArrayList<Image>();
	
	
	static public ArrayList<String> list_of_image_path =new ArrayList<String>();
	static public ArrayList<String> image_modified_path =new ArrayList<String>();
	static public ArrayList<String> deleted_image =new ArrayList<String>();
	ArrayList<String> images_titre= new ArrayList<String>();
	
	
	public Hashtable<String, String> my_dict = new Hashtable<String, String>();
	
	final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
	DirectoryChooser directoryChooser = new DirectoryChooser();
    String mo="";
    int Listviewcounter=0;
    File[] listOfFiles;
    String folder="";
    GlobalMouseListenerExample lklk=new GlobalMouseListenerExample();
    
    /*
	   @FXML
	    private JFXColorPicker sitou;
	   */
    @FXML
    private JFXTextField img_title;
    
    @FXML
    private Spinner<Double> txt_size;

    @FXML
    private ComboBox<String> txt_style;

    @FXML
    private JFXColorPicker txt_color;
    
    @FXML
    private ImageView record_sts;
    
    @FXML
    private TextArea txt_above;

    @FXML
    private TextArea txt_below;
    
	   @FXML
	    private StackPane container_stack1;
	    @FXML
	    private BorderPane view_list_part;
	    
	   @FXML
	    private Text ktiba;
	   /*
	    @FXML
	    private TextField stroke;
	    */
	   /*
	    @FXML
	    private JFXColorPicker col;
	    */
	   /*
	   @FXML
	    private Rectangle car;
	    */
	   
	   /*
	    @FXML
	    private TextField wid;
	   */
	   /*
	   @FXML
	    private TextField hi;
	    */
	   /*
	   @FXML
	    private CheckBox full;
	    */
	   
	   /*
	   @FXML
	    private JFXColorPicker reccolor;
	   */
	   /*
    @FXML
    private Spinner<Integer> spinners;
    */
	   
    @FXML
    private TabPane tabtab;
    /*
    @FXML
    private Spinner<Integer> spinners1;
    */
    

    

	@FXML
    private Canvas can;
    
    @FXML
    private JFXColorPicker colorpick;
    
    @FXML
    private Rectangle recrec;
    /*
    @FXML
    private Spinner<Integer> strokesize;
    */
    ImageList nouh;
	/*
    @FXML
    private ImageView editlogo;
    */
	
    /*
    @FXML
    private Text virtual;
    */
    
   /* 
    @FXML
    private TextField textarea;
    */
	/*
    @FXML
    private Label kt1;
    */
	
    /*
    @FXML
    private StackPane container_stack;
    */
    /*
    @FXML
    private Label kt2;
    */
    /*
    @FXML
    private Canvas canvas;
    */
   
   
    
    @FXML
    private Canvas canca;

    @FXML
    private ScrollPane sss;

    @FXML
    private ImageView tass;

    @FXML
    private JFXSlider sld;
    
    @FXML
    private Button nou;
    
	@FXML
    private JFXListView<String> lista;
	
    @FXML
    private ImageView logo;
    @FXML
    private Text fMessage;

    @FXML
    private Text sMessage;

    @FXML
    private StackPane cont;
    @FXML
    
    
    Color toto=null;
    @FXML
    void delete_an_image(ActionEvent e) {
    	Images delete_image=new Images();

    	File mo=new File(my_dict.get(lista.getSelectionModel().getSelectedItem())+"\\"+lista.getSelectionModel().getSelectedItem());
    	String img_delet=GlobalMouseListenerExample.path_saved+"\\"+lista.getSelectionModel().getSelectedItem();
    	if (mo.exists()) {
    		
    		Alert alert = new Alert (AlertType.CONFIRMATION);
	        
	        alert.setTitle("Confirmation de la suppression");
	        alert.setHeaderText("Supprimer l'image");
	        alert.setContentText("voulez vous supprimer l'image ?");
	        
	        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
	        
	        if (result.get() ==javafx.scene.control.ButtonType.OK ) {
    		System.out.print("IMAGE EXISTE INSIDE FOLDER");
    		mo.delete();
    		
    	//	list_of_image_path.remove(img_delet);
    		System.out.println("RANI SUPPRIMIT + "+mo);
    		
    		
    		list_of_local_image.remove(lista.getSelectionModel().getSelectedIndex());
    	//	list_of_local_image.remove(list_of_local_image.get(lista.getItems().indexOf(lista.getSelectionModel().getSelectedIndex())));
    	//	list_of_local_image.remove(lista.getSelectionModel().getSelectedItem());
    		list_of_image_path.remove(GlobalMouseListenerExample.path_saved+"\\"+lista.getSelectionModel().getSelectedItem());
    		
    		System.out.println("DELETED IMAGE IS = "+img_delet);
    		
    		System.out.println(name);
    		name.remove(lista.getSelectionModel().getSelectedItem());
    		System.out.println(name);
    		
    		System.out.println("==========================================================");
    		System.out.println("==========================================================");
    		System.out.println("HADOU HOUMA LES NOMS NUH = "+name);
    		System.out.println("==========================================================");
    		System.out.println("==========================================================");
    		
    		
    		ObservableList<String> items =FXCollections.observableArrayList (name);
	    	
            lista.setItems(items);
            
    		lista.setCellFactory(param -> new ListCell<String>() {
	              private ImageView imageView = new ImageView();
	              
	              
	              @Override
	              public void updateItem(String name, boolean empty) {
	                  super.updateItem(name, empty);
	                  
	                  if (empty) {
	                      setText(null);
	                      setGraphic(null);
	                  }
	                  	
	                	 else {
							imageView.setFitWidth(150);
	                    	imageView.setFitHeight(150);
	                    	imageView.setImage(list_of_local_image.get(getIndex()));
	                    	
						}
	                  	
	                	  
	                	  setText(name);
	                      setGraphic(imageView);
                 
	              }
	          });
    		
    		Alert alert2 = new Alert (AlertType.INFORMATION);
			alert2.setTitle("Suppréssion avec Succès ");
	        alert2.setContentText("Image Supprimé avec succès ");
	        Optional<javafx.scene.control.ButtonType> result2=alert2.showAndWait();}
		}else {
			Alert alert = new Alert (AlertType.CONFIRMATION);
	        
	        alert.setTitle("Confirmation de la suppression");
	        alert.setHeaderText("Supprimer une image");
	        alert.setContentText("voulez vous supprimer l'image de la base De Donées ?");
	        
	        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
	        
	        if (result.get() ==javafx.scene.control.ButtonType.OK ) {
    		System.out.print("IMAGE EXISTE INSIDE DATABASE");
    		//delete_image.delete_img(mo.getName());
    		delete_image.delete_img(lista.getSelectionModel().getSelectedItem());
    		listOfImages_from_database.remove(lista.getSelectionModel().getSelectedIndex());
    		
    		images_titre.remove(lista.getSelectionModel().getSelectedIndex());
    		
    		ObservableList<String> items =FXCollections.observableArrayList (images_titre);
	    	
            lista.setItems(items);
            
    		lista.setCellFactory(param -> new ListCell<String>() {
	              private ImageView imageView = new ImageView();
	              
	              
	              @Override
	              public void updateItem(String name, boolean empty) {
	                  super.updateItem(name, empty);
	                  
	                  if (empty) {
	                      setText(null);
	                      setGraphic(null);
	                  }
	                  	
	                	 else {
							imageView.setFitWidth(150);
	                    	imageView.setFitHeight(150);
	                    	imageView.setImage(listOfImages_from_database.get(getIndex()));
	                    	
						}
	                  	
	                	  
	                	  setText(name);
	                      setGraphic(imageView);
	                	  
	                    	
	                    
	                          
	                      
	                  
	              }
	          });
	          
		    	
    		Alert alert2 = new Alert (AlertType.INFORMATION);
			alert2.setTitle("Suppréssion avec Succès ");
	        alert2.setContentText("Image Supprimé avec succès ");
	        Optional<javafx.scene.control.ButtonType> result2=alert2.showAndWait();
		}}
    }
    


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		main_window_2.addEventFilter(KeyEvent.KEY_PRESSED,
                event -> 
		keyEvent(event)
				
				);

		addTextLimiter(img_title,30);
		File zz=new File(System.getProperty("user.home")+"\\DocShot-Workstation\\");
		if (zz.exists() != true) {
			zz.mkdirs();
		}
		
		txt_style.getItems().addAll("System", "Aharoni", 	"Algerian","Andalus" ,"Arial Black","Batang" + 
				"Verdana");
		
		txt_style.setValue("System");
		txt_color.setValue(Color.LIGHTBLUE);
		  SpinnerValueFactory<Double> valueFactory1 = //
	                new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 80, 0);
	 
		  txt_size.setValueFactory(valueFactory1);
	        
		upload_image_from_database();
		
	
		
		// TODO Auto-generated method stub
		Stage stage = new Stage();
		
		
		
		zoomProperty.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable arg0) {
            	
        
				
				//	tass.setFitWidth(zoomProperty.get() * tass.getImage().getWidth()/tass.getImage().getHeight());
	           //     tass.setFitHeight(zoomProperty.get() * 1);
					//tass.setFitHeight(zoomProperty.get() * tass.getImage().getHeight()/tass.getImage().getWidth());
            	
            		tass.setFitWidth(zoomProperty.get() * tass.getImage().getWidth());
 	           		tass.setFitHeight(zoomProperty.get() * tass.getImage().getHeight());
	                System.out.println("l3ard hwa =============== "+tass.getFitWidth());
	                System.out.println("tol hwa =============== "+tass.getFitHeight());
	                System.out.println("hada hwa zoome khou =========== "+zoomProperty.get());
	                
	               
				
                
            }    
        });
		
		
		sld.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
            	 if (sld.getValue()==1){
					zoomProperty.set(1);
				}else {
            		zoomProperty.set((sld.getValue()+1)-1);
				}
            	
            	
            	//zoomProperty.set(tass.getImage().getHeight() * sld.getValue());
                
            }
        });
		SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
		/*
		spinners.setValueFactory(valueFactory);*/
		/*
		SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);*/
		/*spinners1.setValueFactory(valueFactory1);*/
		
		
		SpinnerValueFactory<Integer> valueFactory2 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
		/*strokesize.setValueFactory(valueFactory2);*/
		
		
	}
    
	/*
	   @FXML
	    
	    
	    void lolo(ActionEvent event) {
		   tabtab.getSelectionModel().select(1);
		   canvas.setVisible(true);
		   container_stack.getChildren().remove(editlogo);
		   container_stack.getChildren().remove(kt1);
		   container_stack.getChildren().remove(kt2);
		   
		   GraphicsContext gc = canvas.getGraphicsContext2D();
	    	
	    	gc.clearRect(0, 0, 900, 550);
				
				

		   
		    //	System.out.println(tass.getFitWidth()+" + "+tass.getFitHeight());
		    	System.out.println(tass.getFitWidth()/tass.getFitHeight());
			
	    	
	    	
	    	
	    	
	    	//System.out.println(1+(zoomProperty.get()/800));
	
	    	gc.drawImage(tass.getImage(), 0 ,0,900,550 );
	    	
	    	
	    	
	    }*/
	   
	/*
	   @FXML
	    void nchoufou(KeyEvent event) {
	    	virtual.setText(textarea.getText());
	    	virtual.setFill(Color.web(sitou.getValue().toString()));
	    	//System.out.println("raw dja khou");
	    }
	   */
	   /*
	    @FXML
	    void habat(ActionEvent event) {
	    	try {
	    		my+=spinners.getValue();
	        	container_stack.setMargin(virtual,new Insets(my,0,0,mx));
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }*/

	    /*
	    @FXML
	    void left(ActionEvent event) {
	    	try {
	    		mx-=spinners.getValue();
	        	container_stack.setMargin(virtual,new Insets(my,0,0,mx));
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }*/
	    
	    /*
	    @FXML
	    void right(ActionEvent event) {
	    	try {
	    		mx+=spinners.getValue();
	        	container_stack.setMargin(virtual,new Insets(my,0,0,mx));
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }*/
/*
	    @FXML
	    void tala3(ActionEvent event) {
	    	try {
	    		my-=spinners.getValue();
	        	container_stack.setMargin(virtual,new Insets(my,0,0,mx));
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }*/
	    
	    /*
	    @FXML
	    void zid(ActionEvent event) {
	    	x=virtual.getFont().getSize()+10;
	    	virtual.setFont(Font.font ("Verdana", x));
	    }*/
	    
	    /*
	    @FXML
	    void na9(ActionEvent event) {
	    	x=virtual.getFont().getSize()-10;
	    	virtual.setFont(Font.font ("Verdana", x));
	    }*/
	    
	    /*
	    @FXML
	    void khayar(ActionEvent event) {
	    	virtual.setFill(Color.web(sitou.getValue().toString()));
	    }*/
	    
	    /*
	    @FXML
	    void aff(ActionEvent event) {
	    	car.setVisible(true);
	    	
	    }*/
	   /* 
	    @FXML
	    void fully(ActionEvent event) {
	    	System.out.println(full.isSelected());
	    	if (full.isSelected()) {
	    		try {
	    			car.setFill(car.getStroke());
	    		} catch (Exception e) {
	    			// TODO: handle exception
	    		}
			}else {
				try {
	    			car.setFill(Color.TRANSPARENT);
	    		} catch (Exception e) {
	    			// TODO: handle exception
	    		}
			}
	    	
	    }*/
	    
	   /*
	    @FXML
	    void reccolorfunc(ActionEvent event) {
	    	car.setStroke(reccolor.getValue());
	    }
	    */
	   
	    /*
	    @FXML
	    void recright(ActionEvent event) {
	    	try {
	    		rcx+=spinners1.getValue();
	        	container_stack.setMargin(car,new Insets(rcy,0,0,rcx));
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }*/
	    
	    /*
	    @FXML
	    void recup(ActionEvent event) {
	    	try {
	    		rcy-=spinners1.getValue();
	        	container_stack.setMargin(car,new Insets(rcy,0,0,rcx));
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }*/
	    /*
	    @FXML
	    void recleft(ActionEvent event) {
	    	try {
	    		rcx-=spinners1.getValue();
	        	container_stack.setMargin(car,new Insets(rcy,0,0,rcx));
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }*/
	    
	    /*
	    @FXML
	    void recdown(ActionEvent event) {
	    	try {
	    		rcy+=spinners1.getValue();
	        	container_stack.setMargin(car,new Insets(rcy,0,0,rcx));
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }*/
	    
	    /*
	    @FXML
	    void preview(ActionEvent event) {
	    	try {
				car.setWidth(Integer.valueOf(wid.getText()));
				car.setHeight(Integer.valueOf(hi.getText()));
				try {
					car.setStrokeWidth(Integer.valueOf(stroke.getText()));

				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }*/
	    
	   /*
	    private void initDraw(GraphicsContext gc){
	        double canvasWidth = gc.getCanvas().getWidth();
	        double canvasHeight = gc.getCanvas().getHeight();
	         
	        try {
	        gc.strokeRect(
	                0,              //x of the upper left corner
	                0,              //y of the upper left corner
	                canvasWidth,    //width of the rectangle
	                canvasHeight);  //height of the rectangle
	         
	        	gc.setStroke(toto);
	 	        gc.setLineWidth(strokesize.getValue());
			} catch (Exception e) {
				// TODO: handle exception
			}
	        
	         
	    }
	    */
	    /*
	    @FXML
	    void changepaintcolor(ActionEvent event) {
	    	toto=Color.web(col.getValue().toString());
	    }*/

/*
	    @FXML
	    void appsize(ActionEvent event) {
	    	final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
			 
			initDraw(graphicsContext);
			canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
	                new EventHandler<MouseEvent>(){
	 
	            @Override
	            public void handle(MouseEvent event) {
	                graphicsContext.beginPath();
	                graphicsContext.moveTo(event.getX(), event.getY());
	                graphicsContext.stroke();
	            }
	        });
	         
	        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
	                new EventHandler<MouseEvent>(){
	 
	            @Override
	            public void handle(MouseEvent event) {
	                graphicsContext.lineTo(event.getX(), event.getY());
	                graphicsContext.stroke();
	            }
	        });
	 
	        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, 
	                new EventHandler<MouseEvent>(){
	 
	            @Override
	            public void handle(MouseEvent event) {
	 
	            }
	        });
	    }*/
/*	    
	    @FXML
	    void addilou(ActionEvent event) {
	    	
	    	Bounds boundsInScene = virtual.localToScene(virtual.getBoundsInLocal());
	    	GraphicsContext gc = canvas.getGraphicsContext2D();
	    	
	    	gc.setFill(Color.web(sitou.getValue().toString()));
	    	
	    	gc.setFont(new Font("AGA Arabesque", virtual.getFont().getSize()));
	    	gc.fillText(textarea.getText(),mx,virtual.getFont().getSize()+my);
	    	
	    	
	    	
	    	//System.out.println(virtual.getFont().getSize());
	    	System.out.println(virtual.getX()+"and"+virtual.getY());
	    	virtual.setText("");


	    	//System.out.println(sitou.getValue().toString());
	    	
	    	

	    }*/
	    /*
	    @FXML
	    void dirha(ActionEvent event) {
	    	GraphicsContext ff = canvas.getGraphicsContext2D();
	   
	    	if (full.isSelected()) {
	    		ff.setFill(car.getFill());
	    		System.out.println("raw m3amar");
			} else {
				ff.setFill(Color.TRANSPARENT);
				System.out.println("raw faragh");
			} 
	        ff.setStroke(car.getStroke());
	        
	        ff.setLineWidth(car.getStrokeWidth());
	        
	        ff.fillRect(rcx, rcy, car.getWidth(), car.getHeight());
	    	ff.strokeRect(rcx, rcy, car.getWidth(), car.getHeight());
	    	car.setVisible(false);
	    }*/
	    
	    @FXML
	    void save(ActionEvent event) {
	    	

	    	try {
				jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="select img,titre from image where titre="+'"'+lista.getSelectionModel().getSelectedItem()+'"' ;
							
					
							
										
					
					ResultSet res = stm.executeQuery(strcheck);
					
					if (res.next()) {
						saved_image_modified_in_local_from_database();
					}else {
						saved_image_modified_in_local();
					}
					
					Alert alert = new Alert (AlertType.INFORMATION);
					alert.setTitle("Succès ");
			        alert.setContentText("Image sauvegardé avec succès ");
			        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
			        
					jdbc.setConnection().close();
									
									
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	    	
 /*
	    		 try {
	    			// Files.deleteIfExists(Paths.get(folder.substring(6, folder.length() - 1)));
	    			 File newimage=new File(folder.substring(6, folder.length()));
	    			System.out.println("hada folder = "+folder);
	    			System.out.println("hada newimage = "+newimage);
	    			 WritableImage writableImage = new WritableImage((int)tass.getImage().getWidth(),(int) tass.getImage().getHeight());
	    		     can.snapshot(null, writableImage);
	    		        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
	    		        ImageIO.write(renderedImage, "png",newimage);
	    		        
	    		        Image imim=new Image(folder);
	    		    	tass.setImage(imim);
	    		    	
				} catch (Exception e) {
					// TODO: handle exception
				}
System.out.println("hada 1"+folder.substring(6, folder.length()));

System.out.println("hada 2"+GlobalMouseListenerExample.path_saved.replaceAll("^\\d+(\\.\\d+)?", "_"));

lista.getItems().clear();

UploadImageToLitView(GlobalMouseListenerExample.path_saved);

*/

	    } 
	    
	    @FXML
	    void cancel(ActionEvent event) {

	    }
	    
	    
	    public void UploadImageToLitView(String mo2){
	    	
	    	
		//	if(folder != null){
			     //No Directory selected
		    	
		    	for (File file :listOfFiles) {
		    		System.out.println("hadi hya sah khou   =    "+name.size());
		    		
		    		try {
		    			if (file.isFile() && (file.getName().substring(file.getName().lastIndexOf(".")).equals(".png") || 
			    				file.getName().substring(file.getName().lastIndexOf(".")).equals(".jpg"))) {
			    		
		    				list_of_image_path.add(file.getAbsolutePath());
		    				
						int r=file.getName().lastIndexOf(".");
						// to get file extension (System.out.println(file.getName().substring(r));)
						//System.out.println("le nom est"+file.getName().substring(r));
						//m it the array of images path
						m.add(file.getPath());
						//System.out.println("le path est"+file.getPath());
						//name is the image name array
						name.add(file.getName());
						my_dict.put(file.getName(), mo2);
						System.out.println("THIS IS THE DICT + "+my_dict);
						
						list_of_local_image.add(new Image(file.toURI().toString()));
						
						
						
						
			    				}
					} catch (Exception e2) {
						// TODO: handle exception
					}
		    		

		   
		    		}
		    	nouh=new ImageList(m);
		    	ArrayList<Image> listOfImages=nouh.getv();
		    	System.out.println("hadi hya la liste des image khou =========="+listOfImages);
		    	System.out.println("hadi hya la liste les fichier khou =========="+listOfFiles);
		        	//---------------------------
		    	
		    	
		            ObservableList<String> items =FXCollections.observableArrayList (name);
		            lista.setItems(items);

		           
	    
		            lista.setCellFactory(param -> new ListCell<String>() {
		                private ImageView imageView = new ImageView();
		                
		                
		                
		                
		                @Override
		                public void updateItem(String name, boolean empty) {
		                    super.updateItem(name, empty);
		                    
		                    if (empty) {
		                        setText(null);
		                        setGraphic(null);
		                    } else {
		                    	
		                    	imageView.setFitWidth(150);
		                    	imageView.setFitHeight(150);
		                    	imageView.setImage(listOfImages.get(getIndex()));
		                    		
		                 
		                      
		                            
		                        setText(name);
		                        setGraphic(imageView);
		                    }
		                }
		            });
		            
		           
			//}else{
	//		   //  System.out.println(folder.getAbsolutePath());
	//		}
			
	    	
	         
	     
	    }
	    
	    @FXML
	    void record(ActionEvent event) throws Exception {
	    	record_sts.setVisible(true);
	    	String val="Project--"+SampleController.selected_project_name.replaceAll("\\s", "_")+"\\App--"+SampleController.selected_app_name.replaceAll("\\s", "_");
	    	lklk.main( );
	    	System.out.println(System.getProperty("user.home")+"\\DocShot-Workstation\\"+val);
	    
	   
	    }
	    
	    
	    

	    @FXML
	    void stop(ActionEvent event) {
	    	
	    	record_sts.setVisible(false);
	    	
	    	lklk.stopiha();
	    	img_title.setVisible(false);
			
	    	File folder= new File(GlobalMouseListenerExample.path_saved);
	    	listOfFiles=folder.listFiles();
	    	System.out.println("listes des fichier = "+listOfFiles.length);
	    	UploadImageToLitView(GlobalMouseListenerExample.path_saved);
	    	
	    	Alert alert = new Alert (AlertType.INFORMATION);
			alert.setTitle("Terminé ");
	        alert.setContentText("Upload les Image avec succès ");
	        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
	    	
	    }
	    
	    @FXML
	    void getnchoufou(ActionEvent event) {
	    	if (r==true) {
				r=false;
				
			} else {
				final GraphicsContext graphicsContext = can.getGraphicsContext2D();
				r=true;
				rec=false;
				paint=false;
				can.setFocusTraversable(true);
				recrec.setVisible(false);
				sld.setValue(1);
				sld.setDisable(true);
				sld.setDisable(false);
				if (canimage==true) {
					can.setVisible(true);
					graphicsContext.drawImage(tass.getImage(), 0, 0);
					canimage=false;
				}
				
				
			}
	    }  
	    
	    @FXML
	    void rectangle(ActionEvent event) {
	    	if (rec==true) {
	    		rec=false;
	    		
			} else {
				final GraphicsContext graphicsContext = can.getGraphicsContext2D();
				rec=true;
				r=false;
				paint=false;
				can.setFocusTraversable(true);
				ktiba.setVisible(false);
				sld.setValue(1);
				sld.setDisable(true);
				tass.setDisable(true);
				
				sld.setDisable(false);
				
				if (canimage==true) {
					can.setVisible(true);
					graphicsContext.drawImage(tass.getImage(), 0, 0);
					canimage=false;
				}
			}
	    }
	    
	    @FXML
	    void paintstart(ActionEvent event) {

	    	if (paint==true) {
	    		paint=false;
	    		

			} else {
				final GraphicsContext graphicsContext = can.getGraphicsContext2D();
				paint=true;
				r=false;
				rec=false;
				can.setFocusTraversable(true);
				ktiba.setVisible(false);
				recrec.setVisible(false);
				sld.setValue(1);
				sld.setDisable(true);
				sld.setDisable(false);
				if (canimage==true) {
					can.setVisible(true);
					graphicsContext.drawImage(tass.getImage(), 0, 0);
					canimage=false;
				}
				
			}
	    
	    	System.out.println(paint);
	 
			    }
	    
	    
	   
	    
	    @FXML
	    void choosecolor(ActionEvent event) {
	    	if (r==true) {
	    		ktiba.setFill(Color.web(colorpick.getValue().toString()));
			}else if (rec==true) {
				recrec.setStroke(colorpick.getValue());
			}else if (paint==true) {
				final GraphicsContext graphicsContext = can.getGraphicsContext2D();
				toto=Color.web(colorpick.getValue().toString());
				graphicsContext.setStroke(toto);
				
				
				
			}
	    }
	    @FXML
	    void canvas_mouse_click_listner(MouseEvent event) {
	    	if (r==true && event.getButton()==MouseButton.PRIMARY && tass.getImage()!=null) {
	    		System.out.println(event.getX()+" "+event.getY());
	    		try {
	    			ktiba.setVisible(true);
	    			container_stack1.setMargin(ktiba,new Insets(event.getY(),0,0,event.getX()));
	        		mx=(float) event.getX();
	        		my=(float) event.getY();
				} catch (Exception e) {
					// TODO: handle exception
				}
	    		
			}else if (rec==true && event.getButton()==MouseButton.PRIMARY && tass.getImage()!=null) {
				try {
					recrec.setVisible(true);
					container_stack1.setMargin(recrec,new Insets(event.getY(),0,0,event.getX()));
					mx1=(float) event.getX();
	        		my1=(float) event.getY();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else if (r==true || rec==true || paint==true  && event.getButton() == MouseButton.SECONDARY  ) {
			
				r=false;
				rec=false;
				paint=false;
				
				recrec.setVisible(false);
		    	ktiba.setVisible(false);
			} else {
				System.out.println(event.getButton());
			}
	    	
	    }
	    
	    @FXML
	    void draggedmousse(MouseEvent event) {
	    	if (rec==true && event.getButton()==MouseButton.SECONDARY) {
	    		
	    	System.out.println(event.getX()-oldx);
	    	
	    	
	    	recrec.setWidth(Math.abs(event.getX()-oldx));
	    	recrec.setHeight(Math.abs(event.getY()-oldy));
	    	
	    	
	    	}else if (paint==true && event.getButton()==MouseButton.PRIMARY && tass.getImage()!=null ) {
		    	final GraphicsContext graphicsContext = can.getGraphicsContext2D();
		    	 graphicsContext.lineTo(event.getX(), event.getY());
	             graphicsContext.stroke();
	             graphicsContext.setLineWidth(initvalue);
			}

	    }
	    
	    @FXML
	    void hadi(MouseEvent event) {
	    	
	    	if (event.getButton()==MouseButton.PRIMARY && r==true) {
	    		oldx=(float) event.getX();
	    		oldy=(float) event.getY();
			}else if (paint==true && event.getButton()==MouseButton.PRIMARY) {
				final GraphicsContext graphicsContext = can.getGraphicsContext2D();
				graphicsContext.beginPath();
	            graphicsContext.moveTo(event.getX(), event.getY());
	            graphicsContext.stroke();
	            
			}
	    		
	    	
	    	
	    }
	    
	    @FXML
	    void clearcanvas(ActionEvent event) {
	    //	graphicsContext.clearRect(0, 0, can.getWidth(), can.getHeight());
			final GraphicsContext graphicsContext = can.getGraphicsContext2D();

	    	graphicsContext.drawImage(tass.getImage(), 0, 0);
	    }
	   
	    @FXML
	    void selectItem(MouseEvent event) {
	    	//folder=new File(my_dict.get(lista.getSelectionModel().getSelectedItem())+"\\"+lista.getSelectionModel().getSelectedItem()).toURI().toString();
	    
	    //	File mo=new File(my_dict.get(lista.getSelectionModel().getSelectedItem())+"\\"+lista.getSelectionModel().getSelectedItem());
	  
	    	String selected_img=GlobalMouseListenerExample.path_saved+"\\"+lista.getSelectionModel().getSelectedItem();
	    	if (list_of_image_path.contains(selected_img)) {
	    		System.out.println("hna rak fel 11111111111111111111111");
	    		//---------------------------------------------------------
	    		System.out.println("================================================================================================");
	    		System.out.println("================================================================================================");
	    		System.out.println("PATH LIST IS = "+list_of_image_path);
	    		System.out.println("SELECTED IMAGE IS = "+selected_img);
	    		System.out.println("SELECTED IMAGE INDEX = "+list_of_image_path.indexOf(selected_img));
	    		System.out.println("THE INDEX OF IMAGE IN LOCAL IMAGE LIST "+ list_of_local_image.indexOf(list_of_local_image.get(lista.getSelectionModel().getSelectedIndex())));
	    		System.out.println("================================================================================================");
	    		System.out.println("================================================================================================");
	    		setitems_to_canvas_from_local();
	    		lista.setCellFactory(param -> new BirdCell());
	            lista.setPrefWidth(180);

	    		
			}else {
	    		System.out.print("hna rak f 222222222222222222222222");
	    		System.out.println("HADA HWA DIC "+my_dict);
				setitems_to_canvas_from_DB();
				

				img_title.setText(lista.getSelectionModel().getSelectedItem());
				lista.setCellFactory(param -> new BirdCell2());
	            lista.setPrefWidth(180);
			
	            
				try {
					jdbc.setConnection();
					Statement stm=jdbc.setConnection().createStatement();
					String strcheck="select txt_below,txt_above,taille,style,couleur from image_description where id=(select id from image where titre= "+'"'+lista.getSelectionModel().getSelectedItem()+'"'+")";
						
					ResultSet res = stm.executeQuery(strcheck);
					
					
					if (res.next()) {
						
						txt_color.setValue(Color.web(res.getString("couleur")));
						txt_style.setValue(res.getString("style"));
						txt_size.getValueFactory().setValue(res.getDouble("taille"));
						
						
						txt_below.setText(res.getString("txt_below"));
						txt_above.setText(res.getString("txt_above"));
						
						txt_below.setStyle("-fx-text-inner-color: "+res.getString("couleur"));
						txt_above.setStyle("-fx-text-inner-color: "+res.getString("couleur"));

						txt_below.setFont(Font.font(res.getString("style"), res.getDouble("taille")));
						txt_above.setFont(Font.font(res.getString("style"),res.getDouble("taille")));
						
						
						
						System.out.println("RANI HNA F LI 3ANDHOUM");
						
					}else {
						txt_below.setText("");
			    		txt_above.setText("");
			    		System.out.println("RANI HNA F LI MA3ANDHOUMCH");
					}
						
						
						
						
						
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
	    	

	    }
	    
	    
	    @FXML
	    void test(ActionEvent event) throws IOException {
	    	XWPFDocument docu=new XWPFDocument();

	    	InputStream  pic = new FileInputStream("C:\\pic.png");
	    	
	    	XWPFParagraph par = docu.createParagraph();
	    	XWPFRun run = par.createRun();
	    	try {
				run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, "pic.png",300,300);
			} catch (InvalidFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
	    	pic.close();

	    	
	    	try {
	    	FileOutputStream output=new FileOutputStream("C:\\Users\\nouhacker\\Intel\\Desktop\\didine_klash.docx");
	    	docu.write(output);
	    	
	    	output.close();
	    	
	    } catch (Exception e) {
	    	// TODO: handle exception
	    	System.out.println(e.getMessage());
	    }
	    }
	    
	    
	    @FXML
	    void publier_doc(ActionEvent event) throws SQLException, IOException {
	    	
	    	
	    	
	    	
	    	System.out.println(lista.getSelectionModel().getSelectedIndex());
	    	//FileWriter myWriter = new FileWriter("C:\\Users\\nouhacker\\Intel\\Desktop\\didine_klash.txt");
	    	
	    	//
		    //  myWriter.write(txt_belows.getHtmlText());
		    //  myWriter.close();
	    	if (image_modified_path.isEmpty()) {
	    		application_documenter newDoc=new application_documenter();
	    		
	    		System.out.println("RANI HNA FEL 1");
	    		
	    		Images modify_image=new Images();
	    		image_description txt=new image_description();
	    		try {
	    			newDoc.publier_documentation();
				} catch (Exception e) {
					// TODO: handle exception
				}
		    	
		    	
		    	if (lista.getSelectionModel().getSelectedItem().equals(img_title.getText()) || modify_image.vérify_title_existance(img_title.getText())==false) {
		    		String hex3 = "#"+Integer.toHexString(txt_color.getValue().hashCode()).substring(0, 6).toUpperCase();
		    		
		    		
		    		// create or modify texte description  ===================================================================
		    		try {
		    			 if (!txt_above.getText().equals("") && !txt_below.getText().equals("")) {
							
		    				if (txt.verify_existing_text(lista.getSelectionModel().getSelectedItem())) {
				    			
								System.out.println("RAK F LA PARTIE MODIF TEXT");
								try {
									txt.modif_img_description(modify_image.get_img_id(lista.getSelectionModel().getSelectedItem()), txt_below.getText(), txt_above.getText(), txt_size.getValue(), txt_style.getValue(), hex3);
								} catch (Exception e) {
									// TODO: handle exception
									System.out.println("there is a problem in modification an image description");
								}
								
							} else {
								try {
									txt.create_image_description(modify_image.get_img_id(lista.getSelectionModel().getSelectedItem()), txt_below.getText(), txt_above.getText(), txt_size.getValue(), txt_style.getValue(), hex3);
								} catch (Exception e) {
									// TODO: handle exception
									System.out.println("there is a problem in image description");
								}
								
							System.out.println("RANI NAKTAB F HAD L FILE");
							
							
							
							}
		    				
						}
		    			
					} catch (Exception e) {
						// TODO: handle exception
					}
		    		//==========================================================================================================
		    		
		    		// update image title ======================================================================================
		    		try {
		    			modify_image.update_image_title(lista.getSelectionModel().getSelectedItem(), img_title);
						lista.getItems().set(lista.getSelectionModel().getSelectedIndex(), img_title.getText());
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					//==========================================================================================================
					
			    	// reorder images ==========================================================================================
		    		modify_image.update_image_order(lista);
		    		//==========================================================================================================
			    	
				} else {
					System.out.println("THE TITLE EXISTE ALREADY");
				}
		    	
		    	
			}else {
				
				
				System.out.println(image_modified_path);
	    	Images modify_image=new Images();
	    	modify_image.modif_image(image_modified_path,lista.getSelectionModel().getSelectedItem());
	    	
				System.out.println("RANI HNA FEL 2");
				
				image_modified_path.clear();
			}
	    
	    
	    	Alert alert2 = new Alert (AlertType.INFORMATION);
			alert2.setTitle("Succès ");
	        alert2.setContentText("Les changements effectuer avec Succès sur la Base de donées ");
	        Optional<javafx.scene.control.ButtonType> result2=alert2.showAndWait();
	    
	    
	    }
	    
	    
	    
	    public JFXListView<String> get_list_node(){
			return lista;
	    	
	    }
	    
	    
	    
	   public void upload_image_from_database() {
		   img_title.setVisible(true);
	    	try {
				
        		jdbc.setConnection();
				Statement stm=jdbc.setConnection().createStatement();
				String strcheck="select img,titre from image where id_app=(select id from application where nom_app="+'"'+SampleController.selected_app_name+'"'+") order by ordre";
									
					
				
				ResultSet res = stm.executeQuery(strcheck);
				while (res.next()) {
					java.sql.Blob blob = res.getBlob("img");  
					InputStream in = blob.getBinaryStream(); 
					
					images_titre.add(res.getString("titre"));
					Image imge = new Image(in);
					listOfImages_from_database.add(imge);
					
					
	
				}
				
				jdbc.setConnection().close();

			} catch (Exception e) {
				// TODO: handle exception
				System.out.print(e.getMessage());
			}
	    	System.out.println(listOfImages_from_database);
	    	System.out.println(images_titre);
	    	
	    	ObservableList<String> items =FXCollections.observableArrayList (images_titre);
	    	
            lista.setItems(items);

            lista.setCellFactory(param -> new ListCell<String>() {
                private ImageView imageView2 = new ImageView();
                
                
                @Override
                public void updateItem(String name, boolean empty) {
                    super.updateItem(name, empty);
                    
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                    	
                    	imageView2.setFitWidth(150);
                    	imageView2.setFitHeight(150);
                    	imageView2.setImage(listOfImages_from_database.get(getIndex()));
                    	
                    	
                    		
                 
                      
                            
                        setText(name);
                        setGraphic(imageView2);
                    }
                }
            });
            
            
	    }
	   
	   void setitems_to_canvas_from_local(){
		   double p,t;
	    	int kp;
	    	recrec.setVisible(false);
	    	ktiba.setVisible(false);
	    	r=false;
	    	rec=false;
	    	paint=false;
	    	
	    	canimage=true;
	    	tass.setDisable(false);
	    	can.setVisible(false);
	    	try {
	    		cont.getChildren().remove(logo);
		    	cont.getChildren().remove(fMessage);
		    	cont.getChildren().remove(sMessage);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	
	    	
	    	sss.setDisable(false);
	    	//System.out.println(lista.getSelectionModel().getSelectedItem());
	    	
	    		
	    	//	folder=new File(my_dict.get(lista.getSelectionModel().getSelectedItem())+"\\"+lista.getSelectionModel().getSelectedItem()).toURI().toString();
	    		folder=new File(GlobalMouseListenerExample.path_saved+"\\"+lista.getSelectionModel().getSelectedItem()).toURI().toString();
	    	System.out.println("hada hwa folder"+folder);
		    	Image kokoki=new Image(folder);
		    	tass.setImage(kokoki);
		    	
		    	
		  
		    	tass.setFitHeight(0);
		    	tass.setFitWidth(0);
		    	System.out.println(tass.getImage().getWidth()+" + "+tass.getImage().getHeight());
		    	


		    	
		        can.setWidth(tass.getImage().getWidth());
		        can.setHeight(tass.getImage().getHeight());
		    	
			
			
			
	   }
	   
	   void setitems_to_canvas_from_DB() {
		   Image imge = null;
		   
		   
			//	if (homepaths.exists() ) {
					
			    	//File foldera = new File(homepaths+"\\"+lista.getSelectionModel().getSelectedItem()+".png");
			    //	if (foldera.exists()) {
			    		//imge=new Image(foldera.getAbsolutePath().toString());
			    //		System.out.println("tagisti had lpath");
					//}

		//		}else {
					   try {
						   String vall="Project="+SampleController.selected_project_name.replaceAll("\\s", "_")+"App="+SampleController.selected_app_name.replaceAll("\\s", "_");
							  File homepaths=new File(System.getProperty("user.home")+"\\DocShot-Workstation\\"+vall);
							 
							  if (homepaths.exists()!=true ) {
								  jdbc.setConnection();
									Statement stm=jdbc.setConnection().createStatement();
									String strcheck="select img,titre from image where titre="+'"'+lista.getSelectionModel().getSelectedItem()+'"';
														
										
									
									ResultSet res = stm.executeQuery(strcheck);
									while (res.next()) {
										java.sql.Blob blob = res.getBlob("img");  
										InputStream in = blob.getBinaryStream(); 
										
										res.getString("titre");
										imge = new Image(in);
									}
										
									jdbc.setConnection().close();
							  } 
							  
							  else {
								  File foldera = new File(homepaths+"\\"+lista.getSelectionModel().getSelectedItem()+".png");
								  if (foldera.exists()) {
									  System.out.println("hada path ta3 l'image"+foldera);
									  
									  imge = new Image(foldera.toURI().toString());
									  
									  
									  
								  }else {
									  jdbc.setConnection();
										Statement stm=jdbc.setConnection().createStatement();
										String strcheck="select img,titre from image where titre="+'"'+lista.getSelectionModel().getSelectedItem()+'"';
															
											
										
										ResultSet res = stm.executeQuery(strcheck);
										while (res.next()) {
											java.sql.Blob blob = res.getBlob("img");  
											InputStream in = blob.getBinaryStream(); 
											
											res.getString("titre");
											imge = new Image(in);
										}
											
										jdbc.setConnection().close();
								  }
							  }
								  
							  
					   }catch (Exception e) {
						// TODO: handle exception
					}
			//	}
	   
		   double p,t;
	    	int kp;
	    	recrec.setVisible(false);
	    	ktiba.setVisible(false);
	    	r=false;
	    	rec=false;
	    	paint=false;
	    	
	    	canimage=true;
	    	tass.setDisable(false);
	    	can.setVisible(false);
	    	try {
	    		cont.getChildren().remove(logo);
		    	cont.getChildren().remove(fMessage);
		    	cont.getChildren().remove(sMessage);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	
	    	
	    	sss.setDisable(false);
	    	//System.out.println(lista.getSelectionModel().getSelectedItem());
	  
	    	String val="Project="+SampleController.selected_project_name.replaceAll("\\s", "_")+"App="+SampleController.selected_app_name.replaceAll("\\s", "_");
			 File homepath=new File(System.getProperty("user.home")+"\\DocShot-Workstation\\"+val);
				
				
	    	folder=new File(homepath+"\\"+lista.getSelectionModel().getSelectedItem()+".png").toURI().toString();
	    		
		    	tass.setImage(imge);
		    	
		    	
		  
		    	tass.setFitHeight(0);
		    	tass.setFitWidth(0);
		    	System.out.println(tass.getImage().getWidth()+" + "+tass.getImage().getHeight());
		    	


		    	
		        can.setWidth(tass.getImage().getWidth());
		        can.setHeight(tass.getImage().getHeight());
		    	
	    }
	   
	  private void saved_image_modified_in_local() {

		  
  		 try {
  			// Files.deleteIfExists(Paths.get(folder.substring(6, folder.length() - 1)));
  			 //set path for new modified image
  			 File newimage=new File(folder.substring(6, folder.length()));
  			 System.out.println("hadi hya tasswira khou  ==== "+newimage);
  			System.out.println("hada folder = "+folder);
  			
  			
  			
  			System.out.println("hada newimage = "+newimage);
  			 WritableImage writableImage = new WritableImage((int)tass.getImage().getWidth(),(int) tass.getImage().getHeight());
  		     can.snapshot(null, writableImage);
  		        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
  		        ImageIO.write(renderedImage, "png",newimage);
  		        
  		        Image imim=new Image(folder);
  		    	tass.setImage(imim);
  		    	
  		    	lista.setCellFactory(param -> new ListCell<String>() {
	  	              private ImageView imageView = new ImageView();
	  	              
	  	              
	  	              @Override
	  	              public void updateItem(String name, boolean empty) {
	  	                  super.updateItem(name, empty);
	  	                  
	  	                  if (empty) {
	  	                      setText(null);
	  	                      setGraphic(null);
	  	                  } else {
	  	                  	
	  	                	  if (name.equals(lista.getSelectionModel().getSelectedItem())) {
	  	                		  imageView.setFitWidth(150);
	  	                          imageView.setFitHeight(150);
	  	                          imageView.setImage(imim);
	  	                          list_of_local_image.set(lista.getSelectionModel().getSelectedIndex(), imim);
	  						}else {
	  							imageView.setFitWidth(150);
		                    	imageView.setFitHeight(150);
		                    	imageView.setImage(list_of_local_image.get(getIndex()));
	  						}
	  	                  	
	  	                  		
	  	               
	  	                    
	  	                          
	  	                      setText(name);
	  	                      setGraphic(imageView);
	  	                  }
	  	              }
	  	          });
  		    	
			} catch (Exception e) {
				// TODO: handle exception
			}
  		 

  		try {
			  String val="Project="+SampleController.selected_project_name.replaceAll("\\s", "_")+"App="+SampleController.selected_app_name.replaceAll("\\s", "_");
			  File homepath=new File(System.getProperty("user.home")+"\\DocShot-Workstation\\"+val);
				if (homepath.exists() != true) {
					homepath.mkdir();
				}
				/*
				String ssss=homepath.getAbsolutePath();
				System.out.println("hadi hya la valeur ta3 sss =="+ssss);
				name.clear();
				lista.getItems().clear();


				File folder= new File(ssss);
		    	listOfFiles=folder.listFiles();
		    	System.out.println("listes des fichier 2 = "+listOfFiles.length);
				UploadImageToLitView(ssss);
				*/
				
				
				
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("marahouch yadjkhoul");
		}


	   }
	  
	  private void saved_image_modified_in_local_from_database() {
		  
		  
		  try {
			  
	  			// Files.deleteIfExists(Paths.get(folder.substring(6, folder.length() - 1)));
	  			 //set path for new modified image
			  String val="Project="+SampleController.selected_project_name.replaceAll("\\s", "_")+"App="+SampleController.selected_app_name.replaceAll("\\s", "_");
			  File homepath=new File(System.getProperty("user.home")+"\\DocShot-Workstation\\"+val);
			  
			  
			  
				if (homepath.exists() != true) {
					homepath.mkdir();
				}
				
			  File newimage=new File(folder.substring(6, folder.length()));
		  
			  System.out.println("hna limage tkoun lalalalala ="+ newimage);
	  			 
	  			 System.out.println("hadi hya tasswira khou  ==== "+newimage);
	  			System.out.println("hada folder = "+folder);
	  			
	  			
	  			File oioioioioi=new File(folder.substring(6, folder.length()));
	  			
	  			image_modified_path.add(oioioioioi.getPath());
	  			
	  			System.out.println("hada hwa lArrayNouhhhhhhhhhhhhhhhh=====" +image_modified_path);
	  			
	  			System.out.println("hada newimage = "+newimage);
	  			 WritableImage writableImage = new WritableImage((int)tass.getImage().getWidth(),(int) tass.getImage().getHeight());
	  		     can.snapshot(null, writableImage);
	  		        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
	  		        ImageIO.write(renderedImage, "png",newimage);
	  		        
	  		        Image imim=new Image(folder);
	  		    	tass.setImage(imim);
	  		    	
	  		    	System.out.println("hadi hya la listaaaaa yaaa khouuuuuuuu  ====  "+ listOfImages_from_database);
	  		    	
	  		    	lista.setCellFactory(param -> new ListCell<String>() {
	  	              private ImageView imageView = new ImageView();
	  	              
	  	              
	  	              @Override
	  	              public void updateItem(String name, boolean empty) {
	  	                  super.updateItem(name, empty);
	  	                  
	  	                  if (empty) {
	  	                      setText(null);
	  	                      setGraphic(null);
	  	                  } else {
	  	                  	
	  	                	  if (name.equals(lista.getSelectionModel().getSelectedItem())) {
	  	                		  imageView.setFitWidth(150);
	  	                          imageView.setFitHeight(150);
	  	                          imageView.setImage(imim);
	  	                          listOfImages_from_database.set(lista.getSelectionModel().getSelectedIndex(), imim);
	  						}else {
	  							imageView.setFitWidth(150);
		                    	imageView.setFitHeight(150);
		                    	imageView.setImage(listOfImages_from_database.get(getIndex()));
	  						}
	  	                  	
	  	                  		
	  	               
	  	                    
	  	                          
	  	                      setText(name);
	  	                      setGraphic(imageView);
	  	                  }
	  	              }
	  	          });
	  	          
	  		    	
	  		    	
	  		    	
				} catch (Exception e) {
					// TODO: handle exception
				}
		  
/*
	  		try {
				  
					String ssss=homepath.getAbsolutePath();
					System.out.println("hadi hya la valeur ta3 sss =="+ssss);
					name.clear();
					lista.getItems().clear();


					File folder= new File(ssss);
			    	listOfFiles=folder.listFiles();
			    	System.out.println("listes des fichier 2 = "+listOfFiles.length);
					UploadImageToLitView(ssss);
					
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("marahouch yadjkhoul");
			}*/
	  }
	  
	    @FXML
	    void txt_size_listner(MouseEvent event) {
System.out.println(txt_size.getValue());
txt_above.setFont(Font.font(txt_style.getValue(), txt_size.getValue()));
txt_below.setFont(Font.font(txt_style.getValue(), txt_size.getValue()));

	    }
	    @FXML
	    void txt_family_listner(ActionEvent event) {
	    	txt_above.setFont(Font.font(txt_style.getValue(), txt_size.getValue()));
	    	txt_below.setFont(Font.font(txt_style.getValue(), txt_size.getValue()));
	    	
	    }
	    
	    @FXML
	    void txt_color_listner(ActionEvent event) {
	    //	txt_above.setStyle("-fx-text-inner-color: #"+txt_color.getValue().toString());
		//	txt_below.setStyle("-fx-text-inner-color: #"+txt_color.getValue().toString());
			
			String hex3 = "#"+Integer.toHexString(txt_color.getValue().hashCode()).substring(0, 6).toUpperCase();
			System.out.println(hex3);
			txt_above.setStyle("-fx-text-inner-color: "+hex3);
			txt_below.setStyle("-fx-text-inner-color: "+hex3);
	    }
	    
	    
	    
	    
	   
	   
	    private class BirdCell extends ListCell<String> {
	    	final ImageView imageView = new ImageView();
	    	String prev_value,prev_name;
	    	Image prev_img;
	        public BirdCell() {
              
	            setOnDragDetected(event -> {
	                if (getItem() == null) {
	                    return;
	                }

	                ObservableList<String> items = FXCollections.observableArrayList(name);

	                System.out.println(lista.getSelectionModel().getSelectedIndex());
	                
	                prev_value= GlobalMouseListenerExample.path_saved +"\\"+lista.getSelectionModel().getSelectedItem();  

	                prev_int_value=lista.getSelectionModel().getSelectedIndex();
	               
	                prev_name=lista.getSelectionModel().getSelectedItem();
	                
	                

	                
	                
	                
	                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
	                ClipboardContent content = new ClipboardContent();
	                
	                content.putString(getItem());
	                
	                dragboard.setDragView(
	                		list_of_local_image.get(
	                        items.indexOf(
	                            getItem()
	                        )
	                    )
	                );
	                
	                dragboard.setContent(content);

	                event.consume();
	            });

	            setOnDragOver(event -> {
	                if (event.getGestureSource() != thisCell &&
	                       event.getDragboard().hasString()) {
	                    event.acceptTransferModes(TransferMode.MOVE);
	                   
	                }

	                event.consume();
	            });

	            setOnDragEntered(event -> {
	                if (event.getGestureSource() != thisCell &&
	                        event.getDragboard().hasString()) {
	                    setOpacity(0.3);
	                }
	            });

	            setOnDragExited(event -> {
	                if (event.getGestureSource() != thisCell &&
	                        event.getDragboard().hasString()) {
	                    setOpacity(1);
	                    
	                }
	            });

	            setOnDragDone(event -> {
	            	
	            	System.out.println("HAD IMAGE LA9DIMA = "+prev_value+"Twali f had a position = "+ lista.getItems().indexOf(prev_name));
	            	
	            	System.out.println("w had limage jdida = "+GlobalMouseListenerExample.path_saved+"\\"+lista.getItems().get(prev_int_value)   +"  Twali f had a position = "+prev_int_value);
	            	
	            	//----------------------------
	            	
	            	System.out.println("HADA SIZE TA3 LISTE KHOU = "+list_of_image_path.size());
	            	
	            	list_of_image_path.remove(prev_int_value);
	            	
	            	list_of_image_path.add(prev_int_value , GlobalMouseListenerExample.path_saved+"\\"+lista.getItems().get(prev_int_value) );
	            	
	            	list_of_image_path.remove(lista.getItems().indexOf(prev_name));
	            	
	            	list_of_image_path.add(lista.getItems().indexOf(prev_name) , prev_value );
	            	
	            	
	            	name.remove(prev_int_value);
	            	name.add(prev_int_value, lista.getItems().get(prev_int_value));
	            	
	            	name.remove(lista.getItems().indexOf(prev_name));
	            	name.add(lista.getItems().indexOf(prev_name),prev_name);
	            	/*
	            	
	            	list_of_local_image.remove(prev_int_value);
	            	list_of_local_image.add(prev_int_value,list_of_local_image.get(lista.getItems().indexOf(lista.getItems().get(prev_int_value))));
	            	
	            	list_of_local_image.remove(lista.getItems().indexOf(lista.getItems().get(prev_int_value)));
	            	list_of_local_image.add(lista.getItems().indexOf(lista.getItems().get(prev_int_value)),prev_img);
	            	//list_of_local_image.indexOf(list_of_local_image.get(lista.getSelectionModel().getSelectedIndex()))
	            	
	            */
	            	
	            	
	        //    	my_dict.put(prev_name, GlobalMouseListenerExample.path_saved+"\\"+lista.getItems().get(prev_int_value));
	          //  	my_dict.put(lista.getItems().get(prev_int_value) , prev_value);
	            	
	            	
	            	
	            	
	            	
	            	
	            });
	            setOnDragDropped(event -> {
	                if (getItem() == null) {
	                    return;
	                }

	                Dragboard db = event.getDragboard();
	                boolean success = false;

	                if (db.hasString()) {
	                    ObservableList<String> items = getListView().getItems();
	                    int draggedIdx = items.indexOf(db.getString());
	                    int thisIdx = items.indexOf(getItem());

	                    Image temp = list_of_local_image.get(draggedIdx);
	                    
	                    list_of_local_image.set(draggedIdx, list_of_local_image.get(thisIdx));
	                    list_of_local_image.set(thisIdx, temp);

	                    items.set(draggedIdx, getItem());
	                    items.set(thisIdx, db.getString());

	                    List<String> itemscopy = new ArrayList<>(getListView().getItems());
	                    getListView().getItems().setAll(itemscopy);

	                    success = true;
	                }
	                event.setDropCompleted(success);

	                event.consume();
	            });

	            
	        }
	        
	        @Override
	        protected void updateItem(String item, boolean empty) {
	            super.updateItem(item, empty);

	            if (empty || item == null) {
	            	setText(null);
	                setGraphic(null);
	            } else {
	            	imageView.setFitWidth(150);
                	imageView.setFitHeight(150);
	                imageView.setImage(list_of_local_image.get(getIndex()) );
	                setText(item);
	                setGraphic(imageView);
	            }
	        }
	        
	        
	        
	        
	        
	        
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    private class BirdCell2 extends ListCell<String> {
	        private final ImageView imageView = new ImageView();
	        String prev_value,prev_name;
	    	Image prev_img;
	        public BirdCell2() {
	            ListCell thisCell = this;


	            setOnDragDetected(event -> {
	                if (getItem() == null) {
	                    return;
	                }

	                ObservableList<String> items = getListView().getItems();
	           //    ObservableList<String> items = FXCollections.observableArrayList(images_titre);

	                System.out.println(lista.getSelectionModel().getSelectedIndex());
	                
	                prev_value= lista.getSelectionModel().getSelectedItem();  

	                prev_int_value=lista.getSelectionModel().getSelectedIndex();
	               
	                prev_name=lista.getSelectionModel().getSelectedItem();
	                
	                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
	                ClipboardContent content = new ClipboardContent();
	                content.putString(getItem());
	                dragboard.setDragView(
	                		listOfImages_from_database.get(
	                        items.indexOf(
	                            getItem()
	                        )
	                    )
	                );
	                dragboard.setContent(content);

	                event.consume();
	            });

	            setOnDragOver(event -> {
	                if (event.getGestureSource() != thisCell &&
	                       event.getDragboard().hasString()) {
	                    event.acceptTransferModes(TransferMode.MOVE);
	                }

	                event.consume();
	            });

	            setOnDragEntered(event -> {
	                if (event.getGestureSource() != thisCell &&
	                        event.getDragboard().hasString()) {
	                    setOpacity(0.3);
	                }
	            });

	            setOnDragExited(event -> {
	                if (event.getGestureSource() != thisCell &&
	                        event.getDragboard().hasString()) {
	                    setOpacity(1);
	                }
	            });

	            setOnDragDropped(event -> {
	                if (getItem() == null) {
	                    return;
	                }

	                Dragboard db = event.getDragboard();
	                boolean success = false;

	                if (db.hasString()) {
	                    ObservableList<String> items = getListView().getItems();
	                    int draggedIdx = items.indexOf(db.getString());
	                    int thisIdx = items.indexOf(getItem());

	                    Image temp = listOfImages_from_database.get(draggedIdx);
	                    listOfImages_from_database.set(draggedIdx, listOfImages_from_database.get(thisIdx));
	                    listOfImages_from_database.set(thisIdx, temp);

	                    items.set(draggedIdx, getItem());
	                    items.set(thisIdx, db.getString());

	                    List<String> itemscopy = new ArrayList<>(getListView().getItems());
	                    getListView().getItems().setAll(itemscopy);

	                    success = true;
	                }
	                event.setDropCompleted(success);

	                event.consume();
	            });

setOnDragDone(event -> {
	            	
	            	System.out.println("HAD IMAGE LA9DIMA = "+prev_value+"Twali f had a position = "+ lista.getItems().indexOf(prev_name));
	            	
	            	System.out.println("w had limage jdida = "+GlobalMouseListenerExample.path_saved+"\\"+lista.getItems().get(prev_int_value)   +"  Twali f had a position = "+prev_int_value);
	            	
	            	//----------------------------
	            	
	            	System.out.println("HADA SIZE TA3 LISTE KHOU = "+list_of_image_path.size());
	            	
	           
	            	
	            	
	            	images_titre.remove(prev_int_value);
	            	images_titre.add(prev_int_value, lista.getItems().get(prev_int_value));
	            	
	            	images_titre.remove(lista.getItems().indexOf(prev_name));
	            	images_titre.add(lista.getItems().indexOf(prev_name),prev_name);
	            	/*
	            	
	            	list_of_local_image.remove(prev_int_value);
	            	list_of_local_image.add(prev_int_value,list_of_local_image.get(lista.getItems().indexOf(lista.getItems().get(prev_int_value))));
	            	
	            	list_of_local_image.remove(lista.getItems().indexOf(lista.getItems().get(prev_int_value)));
	            	list_of_local_image.add(lista.getItems().indexOf(lista.getItems().get(prev_int_value)),prev_img);
	            	//list_of_local_image.indexOf(list_of_local_image.get(lista.getSelectionModel().getSelectedIndex()))
	            	
	            */
	            	
	            	
	        //    	my_dict.put(prev_name, GlobalMouseListenerExample.path_saved+"\\"+lista.getItems().get(prev_int_value));
	          //  	my_dict.put(lista.getItems().get(prev_int_value) , prev_value);
	            	
	            	
	            	
	            	
	            	
	            	
	            });
	        }

	        @Override
	        protected void updateItem(String item, boolean empty) {
	            super.updateItem(item, empty);

	            if (empty || item == null) {
	                setGraphic(null);
	            } else {
	            	imageView.setFitWidth(150);
                	imageView.setFitHeight(150);
	                imageView.setImage(
	                		listOfImages_from_database.get(
	                        getListView().getItems().indexOf(item)
	                    )
	                );
	                setText(item);
	                setGraphic(imageView);
	            }
	        }
	    }

	    // Iconset Homepage: http://jozef89.deviantart.com/art/Origami-Birds-400642253
	    // License: CC Attribution-Noncommercial-No Derivate 3.0
	    // Commercial usage: Not allowed    

	    

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
	    

	    
	    
	    void keyEvent(KeyEvent event)
	    {
	    	
	    	try {
				if (event.getCode().getName()=="Backspace") {
			
			    	try {
			    		StringBuilder sb = new StringBuilder(ktiba.getText());
			        	sb.deleteCharAt(sb.length()-1);
			        	ktiba.setText(sb.toString());
					} catch (Exception e) {
						// TODO: handle exception
					}
			    	
			
				}else if (event.getCode()==KeyCode.SPACE) {
					ktiba.setText(ktiba.getText()+"");	
				}
				
				else if (event.getCode()==KeyCode.PAGE_UP) {
					System.out.println(r);
					if (r==true) {
						TextSize=(int) (ktiba.getFont().getSize()+5);
						ktiba.setFont(Font.font ("Verdana", TextSize));
					}else if (rec==true) {
						recrec.setStrokeWidth(gr+=0.5);
					}else if (paint==true) {
						initvalue+=1;
					}
					
				}
				
				else if (event.getCode()==KeyCode.PAGE_DOWN) {
					if (r==true) {
						TextSize=(int) (ktiba.getFont().getSize()-5);
						ktiba.setFont(Font.font ("Verdana", TextSize));
					}else if (rec==true) {
						recrec.setStrokeWidth(gr-=0.5);
					}else if (paint==true) {
						initvalue-=1;
					}
				}
				
				else if (event.getCode()==KeyCode.F2) {
					GraphicsContext gc = can.getGraphicsContext2D();
			
					if (r==true) {
						gc.setFill(Color.BLUE);
						gc.setFill(Color.web(colorpick.getValue().toString()));
						gc.setFont(new Font("AGA Arabesque", ktiba.getFont().getSize()));
						gc.fillText(ktiba.getText(),mx,ktiba.getFont().getSize()+my);
						ktiba.setVisible(false);
						System.out.println(mx+"+"+my);
					}else if (rec==true) {
						gc.setFill(Color.TRANSPARENT);
						gc.setStroke(recrec.getStroke());
				        
						gc.setLineWidth(recrec.getStrokeWidth());
				        
						gc.fillRect(mx1, my1, recrec.getWidth(), recrec.getHeight());
						gc.strokeRect(mx1, my1, recrec.getWidth(), recrec.getHeight());
					}
					
					
				
			    	
				}else if (event.getCode()==KeyCode.F4) {
					GraphicsContext gc = can.getGraphicsContext2D();
					gc.clearRect(0, 0, can.getWidth(), can.getHeight());
			
				}
				
			    	ktiba.setText(ktiba.getText()+event.getText());	
			    	System.out.println(event);
			    	
					} catch (Exception e) {
						// TODO: handle exception
					}
	
	    	
	    }	    
	}

	    
	    



