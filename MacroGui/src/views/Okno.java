package views;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Okno extends JFrame 
{

	private JPanel contentPane;
	private JTextField textField;
	
	int i;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Okno.class.getResource("/resources/robot-arm.png")));
		setTitle("Robot");
		initElements();
		initEvents();
	}
	///////////////////////////////////////////////////////////////////////////
	//DEKLARACJE I INICJALIZACJE ELEMENTÓW
	///////////////////////////////////////////////////////////////////////////
	public void initElements() 
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 22, 135, 54);
		contentPane.add(textField);
		textField.setColumns(10);
	}
	
	///////////////////////////////////////////////////////////////////////////
	//DEKLARACJE I INICJALIZACJE ELEMENTÓW
	///////////////////////////////////////////////////////////////////////////
	public void initEvents() 
	{
		Timer t = new Timer(1, new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
	}
}
