package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class image_description {

	int id;
	String txt_above;
	String txt_below;
	double taille;
	String style;
	String couleur;
	
	public void create_image_description(int id,String txt_below,String txt_above,double taille,String style,String couleur) {
		
		try {
		
			jdbc.setConnection();
			
			
			PreparedStatement pstmt = jdbc.setConnection().prepareStatement("INSERT INTO image_description (id,txt_below,txt_above,taille,style,couleur) values(?,?,?,?,?,?)");

			
			//Inserting Blob type
			pstmt.setInt(1, id);
			pstmt.setString(2,txt_below );
			pstmt.setString(3,txt_above );
			pstmt.setDouble(4,taille );
			pstmt.setString(5,style );
			pstmt.setString(6, couleur);
			
			//Executing the statement
		      pstmt.execute();
		      jdbc.setConnection().close();
		      
		    
			
			}catch (Exception e) {
		// TODO: handle exception
				System.out.println(e.getMessage());
	}
	}
	
	public boolean verify_existing_text(String image_title) {
		try {
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="select * from image_description where id=(select id from image where titre="+'"'+image_title+'"'+")";
				
			ResultSet res = stm.executeQuery(strcheck);
			
			
			while (res.next()) {
				
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public void modif_img_description(int id,String txt_below,String txt_above,double taille,String style,String couleur){
		try {
			
			jdbc.setConnection();
			
			
			PreparedStatement pstmt = jdbc.setConnection().prepareStatement("UPDATE image_description set txt_below= ? , txt_above= ? , taille= ? , style= ? , couleur= ? where id= ? ");

			
			//Inserting Blob type
			
			pstmt.setString(1,txt_below );
			pstmt.setString(2,txt_above );
			pstmt.setDouble(3,taille );
			pstmt.setString(4,style );
			pstmt.setString(5, couleur);
			pstmt.setInt(6, id);
			//Executing the statement
		      pstmt.execute();
		      jdbc.setConnection().close();
		      
		    
			
			}catch (Exception e) {
		// TODO: handle exception
				System.out.println(e.getMessage());
	}
	}
	public void delete_img_description(int id) {
		try {
			jdbc.setConnection();
			Statement stm=jdbc.setConnection().createStatement();
			String strcheck="DELETE FROM image_description where id= "+'"'+id+'"';
								
				
			
			stm.execute(strcheck);
			jdbc.setConnection().close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
