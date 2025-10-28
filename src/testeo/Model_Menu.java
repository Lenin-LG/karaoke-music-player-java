package testeo;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu {

    private	String menuName;
	private String icon;
	public Model_Menu(String menuName,String icon) {
		this.menuName=menuName;
		this.icon=icon;
	}
	public Model_Menu() {
		
	}
	public Icon toIcon() {
	    String rutaRelativa = "/icon/" + icon + ".png";
	    URL url = getClass().getResource(rutaRelativa);
	    
	    if (url != null) {
	        return new ImageIcon(url);
	    } else {
	        return null; 
	    }
	}

	public Icon toIconSelected() {
	    String rutaRelativa = "/icon/" + icon + "_selected.png";
	    URL url = getClass().getResource(rutaRelativa);
	    
	    if (url != null) {
	        return new ImageIcon(url);
	    } else {
	        return null; 
	    }
	}

	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
	
}
