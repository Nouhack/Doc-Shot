package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class Images {
java.awt.Image img;
String titre;
int app_id;

public void create_image(ArrayList<String> images_path,int app_id) {
	int index=0;
	int image_count=0;
	for (int i = 0; i < images_path.size(); i++) {
		try {
			
			System.out.println("rani dkhalt laclass ta3 image");
			jdbc.setConnection();
			
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="select count(*) from image";
			ResultSet res = stm.executeQuery(strcheck);
			
			while (res.next()) {
				image_count = res.getInt("count(*)");
				
				
			}
			
			
			PreparedStatement pstmt = jdbc.setConnection().prepareStatement("INSERT INTO image (img,titre,id_app,ordre) VALUES(?, ?,?,?)");

			
			//Inserting Blob type
			System.out.println("THIS IS THE DATABASE IMAGE SAVED PATH =  "+images_path.get(i));
			
			InputStream in = new FileInputStream(images_path.get(i));
			
			pstmt.setBlob(1, in);
			pstmt.setString(2, "newimage"+String.valueOf(image_count));
			pstmt.setInt(3, app_id);
			pstmt.setInt(4, i+1);
			//Executing the statement
		      pstmt.execute();
		      jdbc.setConnection().close();
		//	Statement stm=jdbc.setConnection().createStatement();
			
				//BufferedImage bImage = ImageIO.read(new File(images_path.get(i)));
		   //   ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    //  ImageIO.write(bImage, "jpg", bos );
		    //  byte [] data = bos.toByteArray();
			
	//		File imgfile = new File("C:\\Users\\nouhacker\\Intel\\Desktop\\logo (2).png");
		//    FileInputStream fin = new FileInputStream(imgfile);
		//    System.out.println("hadaaaaaaaaaaaaaaaaaaa ===="+fin );
		    
		    
		    /*
		    PreparedStatement pre = jdbc.setConnection().prepareStatement("insert into image values(?,?,?)");
		    
		    pre.setBinaryStream(1, fin, (int) imgfile.length());
		    pre.setString(2, "5");
		    pre.setInt(3, 21);
		    
		    pre.executeUpdate();
		    pre.close();
		    jdbc.setConnection().close();
			
		    */
			
			
		//	System.out.println("hada hwaaaaaaaaaaaaaaaaaaaaa =========="+bais );
		//	String strcheck="insert into image (img,titre,id_app) values("+'"'+bais+'"'+","+'"'+title+'"'+","+'"'+app_id+'"'+")";
		//	stm.execute(strcheck);
			
			
			//jdbc.setConnection().close();
	}catch (Exception e) {
		// TODO: handle exception
	}
	
 }
	

}

public void modif_image(ArrayList<String> image_modified_list,String titleimg) {
	
	for (int i = 0; i < image_modified_list.size(); i++) {
		try {
		System.out.println("THIS IS THE MODIFIED DATABASE IMAGE SAVED PATH"+image_modified_list.get(i));	
	
		jdbc.setConnection();
		
		File uiui=new File(image_modified_list.get(i));
		
		System.out.println("IMAGE NAME IS = "+uiui.getName().toString());
		PreparedStatement pstmt = jdbc.setConnection().prepareStatement("UPDATE image set img= ? where titre = ?");

		
		//Inserting Blob type
		InputStream in = new FileInputStream(image_modified_list.get(i));
		pstmt.setBlob(1, in);
		pstmt.setString(2, titleimg);
		
		//Executing the statement
	      pstmt.execute();
	      jdbc.setConnection().close();
	      
	    
		
		}catch (Exception e) {
	// TODO: handle exception
}
	}
	
}
	
public void delete_img(String name_img) {
	image_description txt=new image_description();
	try {
		txt.delete_img_description(get_img_id(name_img));
		jdbc.setConnection();
		Statement stm=jdbc.setConnection().createStatement();
		String strcheck="DELETE FROM image where titre= "+'"'+name_img+'"';
							
			
		
		stm.execute(strcheck);
		jdbc.setConnection().close();
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
}
public void update_image_title(String image_name,JFXTextField imgTitre) {
	
		
			try {
			jdbc.setConnection();
			
			
			
			PreparedStatement pstmt = jdbc.setConnection().prepareStatement("UPDATE image set titre= ? where titre = ?");

			
			//Inserting Blob type


			pstmt.setString(1, imgTitre.getText());
			pstmt.setString(2, image_name);
			
			//Executing the statement
		      pstmt.execute();
		      jdbc.setConnection().close();
		      
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
}

public  boolean vérify_title_existance(String imh_title) {
	try {
		jdbc.setConnection();
		Statement stm=jdbc.setConnection().createStatement();
		String strcheck="select titre from image where titre="+'"'+imh_title+'"';
		
		System.out.println("hadi hya STATEMENT KHOU = "+strcheck);
			
		ResultSet res = stm.executeQuery(strcheck);
		
		
		while (res.next()) {
			Alert alert = new Alert (AlertType.ERROR);
			alert.setTitle("Erreur");
	        alert.setHeaderText("Le Titre "+'"'+imh_title+'"'+"existe déja");
	        alert.setContentText("vous devez insérer un nouveau titre pour l'image ");
	        Optional<javafx.scene.control.ButtonType> result=alert.showAndWait();
			return true;
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	return false;
	
}

public int get_img_id(String img_title) {
	try {
		jdbc.setConnection();
		Statement stm=jdbc.setConnection().createStatement();
		String strcheck="select id  from image where titre="+'"'+img_title+'"';
		
			
		ResultSet res = stm.executeQuery(strcheck);
		
		
		while (res.next()) {
			
			return res.getInt("id");
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	return 0;
}

public void update_image_order(JFXListView<String> lista) {
	
	try {
		System.out.println("hadi hya la taill ta3 lista ==== "+lista.getItems().size());
		
		for (int i = 0; i < lista.getItems().size(); i++) {
			
			
			//System.out.println(lista.getItems().get(i));
			//update image set ordre=i+1 where titre=lista.getItems().get(i)
			jdbc.setConnection();
			
			PreparedStatement pstmt = jdbc.setConnection().prepareStatement("update image set ordre=? where titre=?");
			pstmt.setInt(1, (i+1));
			pstmt.setString(2,lista.getItems().get(i) );
			 pstmt.execute();
		      jdbc.setConnection().close();
		//	String strcheck="update image set ordre="+i+1 +"where titre="+lista.getItems().get(i);
		//	System.out.println("had l'image = " +lista.getItems().get(i)+"walat = " +(i+1));
		//	System.out.println("hada hwa size = "+lista.getItems().size());
	//		stm.execute(strcheck);
			
			
			
			
		}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}
}
