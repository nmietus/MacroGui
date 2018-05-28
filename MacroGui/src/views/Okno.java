package views;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Robot;			//klasa Robot
import java.awt.event.*;		//po³o¿enie myszki i inne

import java.awt.EventQueue;
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
	Timer t;
	private JLabel lblWspdne;
	private JLabel lblWsprzdne;

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
		t.start();
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
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Okno.class.getResource("/resources/robot-arm.png")));
		setTitle("Robot");
		
		lblWspdne = new JLabel("Wsp\u00F3\u0142rz\u0119dne");
		lblWspdne.setBounds(341, 237, 71, 14);
		contentPane.add(lblWspdne);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 11, 147, 102);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		
		lblWsprzdne = new JLabel("Wsp\u00F3\u0142rz\u0119dne:");
		lblWsprzdne.setBounds(341, 212, 83, 14);
		contentPane.add(lblWsprzdne);
	}
	
	///////////////////////////////////////////////////////////////////////////
	//DEKLARACJE I INICJALIZACJE ELEMENTÓW
	///////////////////////////////////////////////////////////////////////////
	public void initEvents() 
	{
		t = new Timer(1, new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//setLocation(punkt);
				punkt = MouseInfo.getPointerInfo().getLocation();
				lblWspdne.setText(punkt.getX()+" : "+punkt.getY());
			}
		});
	}
}
