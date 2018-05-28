package views;
/**
 * @author Norbert
 * Program nagrywaj¹cy i odtwarzaj¹cy dzia³ania u¿ytkownika
 * 
 */

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Robot;			//klasa Robot
import java.awt.event.*;		//po³o¿enie myszki i inne

import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class Okno extends JFrame 
{

	private JPanel contentPane;
	
	int i;
	Robot robot;
	Point punkt;
	Timer t1, t2, t3;
	ArrayList<Point> lista;
	private JLabel lblWspdne;
	private JLabel lblWsprzdne;
	private JTextArea textArea;
	private JButton btnStartR;
	private JButton btnStopR;
	private JButton btnStartM;
	private JButton btnStopM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Okno frame = new Okno();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Okno() 
	{
		i=0;
		initElements();
		initEvents();
		t1.start();		//odpalenie timera który wyœwietla wspó³rzêdne myszki
	}
	///////////////////////////////////////////////////////////////////////////
	//DEKLARACJE I INICJALIZACJE ELEMENTÓW
	///////////////////////////////////////////////////////////////////////////
	public void initElements() 
	{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lista = new ArrayList<>();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Okno.class.getResource("/resources/robot-arm.png")));
		setTitle("Robot");
		
		lblWspdne = new JLabel("x : y");
		lblWspdne.setBounds(341, 237, 71, 14);
		contentPane.add(lblWspdne);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 11, 147, 102);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		
		lblWsprzdne = new JLabel("Wsp\u00F3\u0142rz\u0119dne:");
		lblWsprzdne.setBounds(341, 212, 83, 14);
		contentPane.add(lblWsprzdne);
		
		btnStartR = new JButton("Start");
		btnStartR.setMargin(new Insets(0, 0, 0, 0));
		btnStartR.setBounds(24, 124, 57, 23);
		contentPane.add(btnStartR);
		
		btnStopR = new JButton("Stop");
		btnStopR.setMargin(new Insets(0, 0, 0, 0));
		btnStopR.setBounds(114, 124, 57, 23);
		contentPane.add(btnStopR);
		
		btnStartM = new JButton("Start");
		btnStartM.setMargin(new Insets(0, 0, 0, 0));
		btnStartM.setBounds(246, 124, 57, 23);
		contentPane.add(btnStartM);
		
		btnStopM = new JButton("Stop");
		btnStopM.setMargin(new Insets(0, 0, 0, 0));
		btnStopM.setBounds(336, 124, 57, 23);
		contentPane.add(btnStopM);
	}
	
	///////////////////////////////////////////////////////////////////////////
	//DEKLARACJE I INICJALIZACJE ELEMENTÓW
	///////////////////////////////////////////////////////////////////////////
	public void initEvents() 
	{
		t1 = new Timer(1, new ActionListener() 		//ten timer odpowiada za wyœwietlanie wspó³rzêdnych w prawym dolnym rogu
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				punkt = MouseInfo.getPointerInfo().getLocation();
				lblWspdne.setText(punkt.getX()+" : "+punkt.getY());
				
				//textArea.setText(textArea.getText()+"\n"+punkt.getX()+" : "+punkt.getY());
			}
		});
		
		t2 = new Timer(1, new ActionListener() 		//timer odpowiadaj¹cy za nagrywanie ruchów myszki
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				textArea.setText(textArea.getText()+"\n"+punkt.getX()+" : "+punkt.getY());
				lista.add(punkt);
			}
		});
		
		t3 = new Timer(1, new ActionListener() 		//timer odpowiadaj¹cy za odtwarzanie
		{
			public void actionPerformed(ActionEvent e) 
			{
				//lista.get(i)
				robot.mouseMove((int)lista.get(i).getX(), (int)lista.get(i).getY());
				i++;
				if(i>lista.size()-1)
				{
					i=0;
					t3.stop();
				}
			}
		});
		
		btnStartR.addActionListener(new ActionListener() //button startuj¹cy nagrywanie
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				t2.start();
			}
		});
		btnStopR.addActionListener(new ActionListener() //button przerywaj¹cy nagrywanie
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				t2.stop();
			}
		});
		
		btnStartM.addActionListener(new ActionListener() //button startuj¹cy odtwarzanie
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				t3.start();
			}
		});
		btnStopM.addActionListener(new ActionListener() //button przerywaj¹cy odtwarzanie
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				t3.stop();
			}
		});
	}
	
}
