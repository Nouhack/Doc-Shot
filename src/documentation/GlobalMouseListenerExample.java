package documentation;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MouseInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.RootPaneContainer;
import javax.swing.plaf.RootPaneUI;
import application.SampleController;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.awt.geom.Rectangle2D;

import com.sun.javafx.stage.WindowEventDispatcher;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;


public class GlobalMouseListenerExample implements NativeMouseInputListener {
	static public ArrayList<BufferedImage> ImageListTaken=new ArrayList<>();
	int i=0;
	static public String path_saved="";
	doc_view_controller ui;
	
	static int counti=0;
	
	public void nativeMouseClicked(NativeMouseEvent e) {
		
		
		
		i+=1;
		System.out.println("Mouse Clicked: " + e.getClickCount());
		Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage screenCapture = null;
      //  BufferedImage resized =null;
    
		try {
			
			screenCapture = new Robot().createScreenCapture(screen);
			//resized = resize(screenCapture, 877, 499);
			System.out.println(screenCapture.getHeight());
		} catch (AWTException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;

        Graphics2D graphics2D = screenCapture.createGraphics();
        // set the thickness,color and position$dimension
        graphics2D.setStroke(new BasicStroke(6));
        graphics2D.setColor(Color.BLUE);
        graphics2D.draw(new Rectangle2D.Double(x-40, y-20, 70, 40));
        
        
        ImageListTaken.add(screenCapture);
       
	}


	
	static void CreateImagesFromBufferedList(ArrayList<BufferedImage> Img ) {
	
		 for (int i = 0; i < Img.size(); i++) {
		
			 try {
		        	System.out.println("====================="+new File(path_saved).list().length);
		        	counti=new File(path_saved).list().length;
					ImageIO.write(Img.get(i), "png", new File(path_saved+"\\"+String.valueOf(counti+1)+".png"));
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 
		}
	}
	private BufferedImage resize(BufferedImage screenCapture, int width, int height) {
		// TODO Auto-generated method stub
		 Image tmp = screenCapture.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resized.createGraphics();
	        g2d.drawImage(tmp, 0, 0, null);
	        g2d.dispose();
	        return resized;
	}



	public void nativeMousePressed(NativeMouseEvent e) {
		System.out.println("Mouse Pressed: " + e.getButton());
		
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
		System.out.println("Mouse Released: " + e.getButton());
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
	}
	
	
	public static void main() {
		
		

		
		try {
		
			String val="Project--"+SampleController.selected_project_name.replaceAll("\\s", "_")+"\\App--"+SampleController.selected_app_name.replaceAll("\\s", "_");
			
				File homepath=new File(System.getProperty("user.home")+"\\DocShot-Workstation\\"+val);
				if (homepath.exists() != true) {
					homepath.mkdirs();
				}
				
				path_saved=homepath.getAbsolutePath();
				counti=new File(path_saved).list().length;
			System.out.println("count of files in directory "+counti);
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		
		// Construct the example object.
		GlobalMouseListenerExample example = new GlobalMouseListenerExample();

		// Add the appropriate listeners.
		GlobalScreen.addNativeMouseListener(example);
		GlobalScreen.addNativeMouseMotionListener(example);
		
	}

	
	public void stopiha() {
		try {
			System.out.println(ImageListTaken+"dsfsdfsdfsdfsdfsdfsdfsdfsdfdsfsdfsdf");
			
			CreateImagesFromBufferedList(ImageListTaken);
			GlobalScreen.unregisterNativeHook();
			
			
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
}