package SchoolAdmin;

import javax.swing.JPanel;

import OurMessage.Message;
import OurMessage.QTypes;
import User.HomeUI;
import chat.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SchoolHomeAdminGUI extends JPanel {
	

	public int flag=0;
	public JPanel menu_panel;
	public JPanel tmp_panel = new JPanel();
	public JButton button;
	//protected RequestPanel tmp_panel;

	public SchoolHomeAdminGUI() {
		
		setLayout(null);
		setBounds(100, 100, 571, 327);
		 menu_panel = new JPanel();
		menu_panel.setBounds(0, 0, 180, 324);
		add(menu_panel);
		menu_panel.setLayout(null);
		
		
		
		
		tmp_panel.setBounds(180, 0, 391, 324);
		add(tmp_panel);
		tmp_panel.setLayout(null);
		
		
		
		
		 button = new JButton("Blocking Requests");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setflag(1);
				Client.client.handleMessageFromClientUI(new Message("SELECT * FROM request_block WHERE approve ='NYD';",QTypes.getblockrequests));
				
			}
		});
		
		
		
		button.setBounds(10, 57, 166, 23);
		menu_panel.add(button);
		
		JButton button_1 = new JButton("Registration Requests");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setflag(2);
				Client.client.handleMessageFromClientUI(new Message("SELECT * FROM requests WHERE aprove ='NYD' AND status=0;",QTypes.getReq));

				
			}
		});
		button_1.setBounds(10, 91, 166, 23);
		menu_panel.add(button_1);
		
		JButton button_2 = new JButton("Changing Teacher Request");
		button_2.setBounds(10, 125, 166, 23);
		menu_panel.add(button_2);
		
	
		
		
	}
	public void ChangeJPanel(JPanel panel){
		((HomeUI)Client.clientGUI).innerpanel.remove(((SchoolHomeAdminGUI)((HomeUI)Client.clientGUI).innerpanel).tmp_panel);
		((SchoolHomeAdminGUI)((HomeUI)Client.clientGUI).innerpanel).tmp_panel=panel;
		((HomeUI)Client.clientGUI).innerpanel.add(((SchoolHomeAdminGUI)((HomeUI)Client.clientGUI).innerpanel).tmp_panel);
		menuResize();
	}
	
	
	public void menuResize(){
		int y_offset=48;
		int x_offset=177;
		int y0=((HomeUI)Client.clientGUI).innerpanel.getHeight();
		int x=((HomeUI)Client.clientGUI).innerpanel.getWidth();
		int y=((HomeUI)Client.clientGUI).innerpanel.getHeight();
		int home_x=Client.clientGUI.getX();
		int home_y=Client.clientGUI.getY();
		int dif_width=(x_offset+x)-(((HomeUI)Client.clientGUI).getbtnX()+30);
		//System.out.println("home_x="+home_x+",home_y="+home_y+",dif_width="+dif_width);
		//System.out.println("y_offset="+y_offset+",x_offset="+x_offset+",y0="+y0+",x="+x+",y="+y);
		((SchoolHomeAdminGUI)((HomeUI)Client.clientGUI).innerpanel).tmp_panel.setBounds(((SchoolHomeAdminGUI)((HomeUI)Client.clientGUI).innerpanel).tmp_panel.getX(),((SchoolHomeAdminGUI)((HomeUI)Client.clientGUI).innerpanel).tmp_panel.getY(), x, y);
		//System.out.println(y>y0?y:y0);
		((HomeUI)Client.clientGUI).innerpanel.setBounds(0,y_offset , x_offset+x, y_offset+(y<y0?y:y0));
		((HomeUI)Client.clientGUI).contentPane.setBounds(0, 0, x_offset+x, y_offset+(y<y0?y:y0));
		((HomeUI)Client.clientGUI).setBounds(home_x, home_y, x_offset+x, y_offset+(y<y0?y:y0));
		//Setting Buttons
		((HomeUI)Client.clientGUI).setNewBounds(dif_width-10);
		((HomeUI)Client.clientGUI).repaint();
	}
	
	
	public void setflag(int f)
	{	
		this.flag=f;
	}
	
	public int getflag()
	{
		return this.flag;
	}
}




