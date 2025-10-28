package testeo;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import testeo.Model_Menu;

public class ItemMenu extends JPanel {
    private final Model_Menu data;
    JLabel lblText;
    JLabel lblicon;
    private boolean selected;
    
	/**
	 * Create the panel.
	 */
    public boolean isSelected() {
    	return selected;
    }
	public ItemMenu(Model_Menu data) {
		this.data=data;
		setOpaque(false);
		
      
		lblText = new JLabel("Item Name");
		lblText.setForeground(Color.WHITE);
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblText.setBounds(75, 8, 72, 14);
		setLayout(null);
		add(lblText);
		
		lblicon = new JLabel();
		lblicon.setHorizontalAlignment(SwingConstants.CENTER);
		lblicon.setIcon(new ImageIcon(ItemMenu.class.getResource("/icon/albums_selected.png")));
		lblicon.setBounds(31, 0, 30, 35);
		lblicon.setIcon(data.toIcon());
		add(lblicon);

	}
	public void setSelected(boolean selected) {
		
		this.selected=selected;
		if(selected) {
			lblText.setFont(new Font("Tahoma", 1, 14));
			lblText.setForeground(Color.WHITE);
			lblicon.setIcon(data.toIconSelected());
		}else {
			lblText.setFont(new Font("Tahoma", 0, 14));
			lblText.setForeground(new Color(204,204,204));
			lblicon.setIcon(data.toIcon());
		}
		
	}
	@Override
	protected void paintComponent(Graphics grphcs) {
	    if(selected ) {
	    	   Graphics2D g2=(Graphics2D) grphcs;
	    	   g2.setColor(Color.white);
	    	   g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	    	   g2.fillRect(0, 0, 2, getHeight());
	    }
		super.paintComponent(grphcs);
	}
	
}
