package documentation;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class ImageList {
int i=0;
ArrayList<Image> listOfImages= new ArrayList<Image>();

	public ImageList(ArrayList<String> a) {
		try {
			for (int i = 0; i < a.size(); i++) {
				Image IMAGE_RUBY=new Image(new File(a.get(i)).toURI().toString());
			
				listOfImages.add(IMAGE_RUBY);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public ArrayList<Image> getv() {
		return listOfImages;
	}
}
