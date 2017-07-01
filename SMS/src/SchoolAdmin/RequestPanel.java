package SchoolAdmin;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import OurMessage.Message;
import OurMessage.QTypes;
import chat.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;

public class RequestPanel extends JPanel {
	public JTable table;
	public JLabel lblRequestDone;
	public SchoolHomeAdminGUI tmp;
	
	
	public RequestPanel() {
		setLayout(null);
		table = new JTable(){  
		       public boolean isCellEditable(int row,int column){  
		           Object o = getValueAt(row,column);  
		           if(o!=null) return false;  
		           return true;  
		         }  
		       }; 
		       
		       
		String [] colName={"idRequests", "idStudent", "idCourse", "idClass", "aprove", "type"};
		Object [][] rows={};
		  DefaultTableModel model = new DefaultTableModel(rows,colName);
		  
		  table.setModel(model);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(213, 11, 417, 258);
		add(table);
		
		JButton btnDeny = new JButton("Deny");
		btnDeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(tmp.getflag())
				{
				case 1:
				int index=table.getSelectedRow();
				int id =Integer.parseInt(table.getValueAt(index, 1).toString());
				System.out.println(id);
				String Query="UPDATE request_block SET approve='denied' WHERE id_Student="+id+";";
				Message msg=new Message(Query,QTypes.updateBlockRequest);
				Client.client.handleMessageFromClientUI(msg);
				break; 
				
				case 2:
					int index2=table.getSelectedRow();
					int id2 =Integer.parseInt(table.getValueAt(index2, 1).toString());
					System.out.println(id2);
					String Query2="UPDATE requests SET aprove='denied' WHERE idStudent="+id2+";";
					Message msg2=new Message(Query2,QTypes.updateReq);
					Client.client.handleMessageFromClientUI(msg2);
				break;
				
				case 3:

					int index3=table.getSelectedRow();
					int id3 =Integer.parseInt(table.getValueAt(index3, 1).toString());
					System.out.println(id3);
					String Query3="UPDATE teacher_requests SET aprove='denied' WHERE idTeacher="+id3+";";
					Message msg3=new Message(Query3,QTypes.updateReq);
					Client.client.handleMessageFromClientUI(msg3);
					
					break;
				
				}
			}
		});
		btnDeny.setBackground(Color.RED);
		btnDeny.setBounds(424, 280, 89, 23);
		add(btnDeny);
		
		JButton btnApprove = new JButton("Approve");
		btnApprove.setBackground(Color.GREEN);
		btnApprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(tmp.getflag()){
				
					case 1://blocking request 
						int index=table.getSelectedRow();
						int id =Integer.parseInt(table.getValueAt(index, 0).toString());
						System.out.println(id);
						String Query="UPDATE request_block SET approve='Approved' WHERE id_Student="+id+";";
						Message msg=new Message(Query,QTypes.updateBlockRequest);
						Client.client.handleMessageFromClientUI(msg);
					break;
				
					case 2://registration request
						int index2=table.getSelectedRow();
						int id2 =Integer.parseInt(table.getValueAt(index2, 1).toString());
						System.out.println(id2);
						String Query2="UPDATE requests SET aprove='Approved' WHERE idStudent="+id2+";";
						Message msg2=new Message(Query2,QTypes.updateReq);
						Client.client.handleMessageFromClientUI(msg2);
					break;
						
					
				case 3://for changing teacher
					
					int index3=table.getSelectedRow();
					String courseid =table.getValueAt(index3, 3).toString();
					String classid =table.getValueAt(index3, 0).toString();
					
					
					String Query3="UPDATE teacher_requests SET aprove='Approved' WHERE idClass='"+classid+"' AND idCourse='"+courseid+"';";
					Message msg3=new Message(Query3,QTypes.updateReqTeacher);
					Client.client.handleMessageFromClientUI(msg3);
					
					
					
					
				break;
					
				
				}
			}
		});
		btnApprove.setBounds(308, 280, 89, 23);
		add(btnApprove);
		
		 lblRequestDone = new JLabel("Request Done");
		lblRequestDone.setVisible(false);
		
		lblRequestDone.setBounds(223, 280, 97, 23);
		add(lblRequestDone);

	}
	

//	public void updatelbl(){
//		lblRequestDone=new JLabel("Request Done");
//		
//	}
}
