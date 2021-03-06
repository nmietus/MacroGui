package views;
/**
 * @author Norbert
 * Program nagrywaj�cy i odtwarzaj�cy dzia�ania u�ytkownika
 * 
 */

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Robot;			//klasa Robot
import java.awt.event.*;		//po�o�enie myszki i inne

import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;

import common.Mysz;

import javax.swing.JTextField;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class Okno extends JFrame
{

	private JPanel contentPane;
	
	private int i;
	private Robot robot;
	private Point punkt;
	private Mysz mysz;
	private Timer t1, t2, t3;
	private ArrayList<Point> lista;
	private ArrayList<Mysz> listaMyszy;
	private JLabel lblWspdne;
	private JLabel lblWsprzdne;
	private JTextArea txtRecord;
	private JButton btnStartR;
	private JButton btnStopR;
	private JButton btnStartM;
	private JButton btnStopM;
	private JLabel lblKlawisz;
	private JLabel lblKlawisz_1;
	private JTextArea txtPlay;

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
		t1.start();		//odpalenie timera kt�ry wy�wietla wsp�rz�dne myszki
		
	}
	///////////////////////////////////////////////////////////////////////////
	//DEKLARACJE I INICJALIZACJE ELEMENT�W
	///////////////////////////////////////////////////////////////////////////
	public void initElements() 
	{
		try 
		{
			robot = new Robot();
		} catch (AWTException e) 
		{
			e.printStackTrace();
		}
		
		lista = new ArrayList<>();
		
		listaMyszy = new ArrayList<>();
		mysz = new Mysz();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setAutoscrolls(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//contentPane.setBackground(new Color(30,190,250,1));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Okno.class.getResource("/resources/robot-arm.png")));
		setTitle("Robot");
		
		lblWspdne = new JLabel("x : y");
		lblWspdne.setBounds(341, 237, 71, 14);
		contentPane.add(lblWspdne);
		
		JScrollPane scrollPaneRecord = new JScrollPane();
		scrollPaneRecord.setBounds(24, 11, 147, 102);
		contentPane.add(scrollPaneRecord);
		
		JScrollPane scrollPanePlay = new JScrollPane();
		scrollPanePlay.setBounds(246, 12, 147, 102);
		contentPane.add(scrollPanePlay);
		
		txtRecord = new JTextArea();
		txtRecord.setEnabled(false);
		scrollPaneRecord.setViewportView(txtRecord);
		txtRecord.setLineWrap(true);
		
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
		
		lblKlawisz = new JLabel("Klawisz:");
		lblKlawisz.setBounds(248, 212, 83, 14);
		contentPane.add(lblKlawisz);
		
		lblKlawisz_1 = new JLabel("klawisz");
		lblKlawisz_1.setBounds(248, 237, 83, 14);
		contentPane.add(lblKlawisz_1);
		
		txtPlay = new JTextArea();
		txtPlay.setLineWrap(true);
		txtPlay.setEnabled(false);
		txtPlay.setBounds(246, 12, 145, 100);
		scrollPanePlay.setViewportView(txtPlay);
		
	}
	
	///////////////////////////////////////////////////////////////////////////
	//DEKLARACJE I INICJALIZACJE ELEMENT�W
	///////////////////////////////////////////////////////////////////////////
	public void initEvents() 
	{
		
		t1 = new Timer(1, new ActionListener() 		//ten timer odpowiada za wy�wietlanie wsp�rz�dnych w prawym dolnym rogu
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				punkt = MouseInfo.getPointerInfo().getLocation();			
				
				lblWspdne.setText(punkt.getX()+" : "+punkt.getY());
				//lblKlawisz_1.setText(MouseEvent.BUTTON1);
				//textArea.setText(textArea.getText()+"\n"+punkt.getX()+" : "+punkt.getY());
			}
		});
		

		
		t2 = new Timer(1, new ActionListener() 		//timer odpowiadaj�cy za nagrywanie ruch�w myszki
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				txtRecord.setText(txtRecord.getText()+"\n"+punkt.getX()+" : "+punkt.getY());
				lista.add(punkt);
				
				txtPlay.setText(txtPlay.getText()+"\n"+mysz.getKlik());
				listaMyszy.add(mysz);
			}
		});
		
		
		t3 = new Timer(1, new ActionListener() 		//timer odpowiadaj�cy za odtwarzanie
		{
			public void actionPerformed(ActionEvent e) 
			{
				//lista.get(i)
				robot.mouseMove((int)lista.get(i).getX(), (int)lista.get(i).getY());
				
				if(i>0 && listaMyszy.get(i).getKlik()==1 && listaMyszy.get(i-1).getKlik()==0) {
					robot.mousePress(MouseEvent.BUTTON1_MASK);
					System.out.println("Mysz wci�ni�ta");
				}
					
				if(i>0 && listaMyszy.get(i).getKlik()==0 && listaMyszy.get(i-1).getKlik()==1) 
					{
						robot.mouseRelease(MouseEvent.BUTTON1_MASK);
						System.out.println("Mysz puszczona");
					}
				
				i++;
				if(i>lista.size()-1)
				{
					i=0;
					t3.stop();
				}
			}
		});
		
		btnStartR.addActionListener(new ActionListener() //button startuj�cy nagrywanie
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Start nagrywania");
				t2.start();
			}
		});
		btnStopR.addActionListener(new ActionListener() //button przerywaj�cy nagrywanie
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("Stop nagrywania");
				t2.stop();
			}
		});
		
		btnStartM.addActionListener(new ActionListener() //button startuj�cy odtwarzanie
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				t3.start();
			}
		});
		btnStopM.addActionListener(new ActionListener() //button przerywaj�cy odtwarzanie
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				t3.stop();
			}
		});
		
		contentPane.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				mysz.changeKlik();
				lblKlawisz_1.setText("Klawisz wci�ni�ty!");
			}
			@Override
			public void mouseReleased(MouseEvent e) 
			{
				mysz.changeKlik();
				lblKlawisz_1.setText("Klawisz puszczony");
			}
		});
		
	}
}
