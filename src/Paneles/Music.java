package Paneles;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import GetaSet.Model_Music;

public class Music extends JPanel {
     ListMusic list=new ListMusic();
	/**
	 * Create the panel.
	 */
	public Music() {
		setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("La música más popular");
	    lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));

	    GroupLayout groupLayout = new GroupLayout(this);
	    groupLayout.setHorizontalGroup(
	    	groupLayout.createParallelGroup(Alignment.TRAILING)
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
	    				.addComponent(list, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
	    				.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
	    			.addContainerGap())
	    );
	    groupLayout.setVerticalGroup(
	    	groupLayout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(lblNewLabel)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(list, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)
	    			.addContainerGap(94, Short.MAX_VALUE))
	    );
	    setLayout(groupLayout);
	    init();
	}
	private void init() {
		list.addItem(new Model_Music("1", "Peace Of Mind (feat. Vargas & Lagola) Alan Walker", "03:00"));
		list.addItem(new Model_Music("2", "Heaven NCS", "04:37"));
		list.addItem(new Model_Music("3", "SOS (feat. Aloe Blacc) Camilo", "02:37"));
		list.addItem(new Model_Music("4", "Bad Reputation (feat. Joe Janiak) Romeo Santos", "03:25"));
		list.addItem(new Model_Music("5", "Ain't A Thing", "Coyote of Theory 03:03"));
		list.addItem(new Model_Music("6", "Hold The line (feat. A R I Z O N A) Edd Sheeran", "02:51"));
		list.addItem(new Model_Music("7", "Freak (feat. Bonn)", "Alan Walker 02:59"));
		list.addItem(new Model_Music("8", "Excuse me Mr Sir (feat. Vargas & Lagola) NCS", "03:07"));
		list.addItem(new Model_Music("9", "Heart Upon My Sleeve (feat. Imagine Dragons) Camilo", "04:14"));
		list.addItem(new Model_Music("10", "Never Leave Me (feat. Joe Janiak) Romeo Santos", "02:51"));
		list.addItem(new Model_Music("11", "Fades Away (feat. Noonie Bao) Coyote of Theory", "02:58"));
		list.addItem(new Model_Music("12", "Wake Me Up Edd Sheeran", "04:07"));
		list.addItem(new Model_Music("13", "You Make Me Alan Walker", "03:53"));
		list.addItem(new Model_Music("14", "Hey Brother NCS", "04:15"));
		list.addItem(new Model_Music("15", "Addicted To You Camilo", "02:28"));
        list.addItem(new Model_Music("16", "Bad Reputation (feat. Joe Janiak)", "03:25"));
        list.addItem(new Model_Music("17", "Ain't A Thing", "03:03"));
        list.addItem(new Model_Music("18", "Hold The line (feat. A R I Z O N A)", "02:51"));
        list.addItem(new Model_Music("19", "Freak (feat. Bonn)", "02:59"));
        list.addItem(new Model_Music("20", "Excuse me Mr Sir (feat. Vargas & Lagola)", "03:07"));
        list.addItem(new Model_Music("21", "Heart Upon My Sleeve (feat. Imagine Dragons)", "04:14"));
        list.addItem(new Model_Music("22", "Never Leave Me (feat. Joe Janiak)", "02:51"));
        list.addItem(new Model_Music("23", "Fades Away (feat. Noonie Bao)", "02:58"));
        list.addItem(new Model_Music("24", "Wake Me Up", "04:07"));
        list.addItem(new Model_Music("25", "You Make Me", "03:53"));
        list.addItem(new Model_Music("26", "Hey Brother", "04:15"));
        list.addItem(new Model_Music("27", "Addicted To You", "02:28"));

	}
	
	
}
