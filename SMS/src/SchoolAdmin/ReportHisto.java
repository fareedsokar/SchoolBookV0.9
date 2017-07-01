package SchoolAdmin;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import OurMessage.Message;
import OurMessage.QTypes;
import chat.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class ReportHisto extends JPanel {

	private final JButton Different_Classes = new JButton("Different Classes");
	private int [] ArrayCounter = new int [100];
	private HashMap<String , Integer> StudentsGrades = new HashMap<String, Integer>();
	public ReportHisto() {
		setLayout(null);
		Different_Classes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					String Q=new String ("SELECT * FROM studentgrades WHERE sem_id ="+Integer.parseInt(Client.opnedsem+";"));	
					Message msg = new Message(Q,QTypes.getgrades);
					Client.client.handleMessageFromClientUI(msg);
					
				JFreeChart chart = ChartFactory.createLineChart("Classes For Teacher", "Grades", "Numbe of Students", createDataset(),PlotOrientation.VERTICAL,false,true,true);
				BarRenderer renderer = new BarRenderer();
				CategoryPlot plot = null;
				ChartFrame frame = new ChartFrame("Chart ", chart);
				frame.setVisible(true);
					frame.setSize(400,650);
			}
		});
		
		Different_Classes.setBounds(129, 159, 156, 23);
		add(Different_Classes);
	}
	
	@SuppressWarnings("unchecked")
	public void fillArray(HashMap<String, Integer> msg){
		this.StudentsGrades = (HashMap<String, Integer>) msg.clone();
		for(String key : msg.keySet()){
			ArrayCounter[msg.get(key)]++;
		}
	}
	
	
	private DefaultCategoryDataset createDataset( ){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		for(int i=0;i<ArrayCounter.length;i++){
			dataset.addValue(ArrayCounter[i], "Grades", i+"");
		}
		
		return dataset;
	}
}
