package testeo;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import testeo.Model_Menu;

public class ListMenu<E extends Object> extends JList<E>{
    private final DefaultListModel model;
	public ListMenu() {
		model=new DefaultListModel();
		setModel(model);
		setOpaque(false);
		
	}
	public ListCellRenderer getCellRenderer() {
		
		return new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList<?> jlist,Object o,int index,boolean selected,boolean focus) {
				Model_Menu data;
				if(o instanceof Model_Menu) {
					data=(Model_Menu) o;
				}else {
					data=new Model_Menu("No data","song");
				}
				ItemMenu item=new ItemMenu(data);
				item.setSelected(selected);
				return item;
			}
		};
		
		
	}
	public void addItem(Model_Menu data) {
		model.addElement(data);
	}

	
}