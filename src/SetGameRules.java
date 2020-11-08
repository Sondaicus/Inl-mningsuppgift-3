import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;
import java.awt.Font;


public class SetGameRules implements ActionListener
{
	protected static String victoryConditions1String = "Vågrät räkning";
	protected static String victoryConditions2String = "Lodrät räkning";
	protected static String victoryConditions3String = "Tom plats kan finnas var som helst på brädet";
	
	private static JCheckBox victoryConditions1Box = new JCheckBox(victoryConditions1String + " (från vänster till höger).", true);
	private static JCheckBox victoryConditions2Box = new JCheckBox(victoryConditions2String + " (uppifrån och ner).", false);
	private static JCheckBox victoryConditions3Box = new JCheckBox(victoryConditions3String + ".", false);
	
	private static JButton play = new JButton("Starta spelet!");
	
	private static JLabel info = new JLabel("Välj vilka regler som skall gälla för att vinna spelet.");
	
	private static JPanel optionsHolder = new JPanel();
	private static JPanel labelHolder = new JPanel();
	private static JPanel buttonHolder = new JPanel();
	
	private static JFrame box = new JFrame();
	
	private static boolean condition1;
	private static boolean condition2;
	private static boolean condition3;
	private static boolean finished = false;
	
	private static ArrayList<Boolean> booleanHolder = new ArrayList<Boolean>(3);
	
	
	
	
	SetGameRules()
	{
		victoryConditions1Box.setEnabled(false);
		victoryConditions2Box.setEnabled(true);
		victoryConditions3Box.setEnabled(true);
		
		
		optionsHolder.add(victoryConditions1Box);
		optionsHolder.add(victoryConditions2Box);
		optionsHolder.add(victoryConditions3Box);
		
		
		buttonHolder.add(play);
		labelHolder.add(info);
		
		
        box.add(labelHolder);
        box.add(optionsHolder);
        box.add(buttonHolder);

	
		box.setLayout(new GridLayout(3, 0));
		box.setSize(452, 381);
		box.setLocationRelativeTo(null);
        box.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		box.setResizable(false);
		box.setVisible(true);
		
		
		labelHolder.setLayout(new GridLayout(1,1));
		
		optionsHolder.setLayout(new GridLayout(3, 0));
		
		buttonHolder.setLayout(new GridLayout(0, 1));
		
		info.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
		
		play.addActionListener(this);
		
	}
	
	
	
	
	public static ArrayList<Boolean> main()
	{
		SetGameRules main1 = new SetGameRules();

		/*I had som perticular problem making this system work, so I made this coeblock which fixed everything.
		Source for this codeblock is: https://stackoverflow.com/questions/17989686/wait-for-button-press-jbutton*/
			while(!finished)
			{
				try
				{
					Thread.sleep(200);
					
				}
				
				catch(InterruptedException e)
				{}
				
			}
		
			
		/*Code borrowed from the net so I can properly close the JFrame instead of just hiding it. Source:
		https://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe*/
			box.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			box.dispatchEvent(new WindowEvent(box, WindowEvent.WINDOW_CLOSING));
		
			
		finished = false;
		
			
		return booleanHolder;
		
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(victoryConditions1Box .isSelected())
		{
			condition1 = true;
			
		}
		
		else
		{
			condition1 = false;
			
		}
		
		
		if(victoryConditions2Box .isSelected())
		{
			condition2 = true;
			
		}
		
		else
		{
			condition2 = false;
			
		}
		
		
		if(victoryConditions3Box.isSelected())
		{
			condition3 = true;
			
		}
		
		else
		{
			condition3 = false;
			
		}
		
		finished = true;
		
		
		booleanHolder.add(0, condition1);
		booleanHolder.add(1, condition2);
		booleanHolder.add(2, condition3);
		
	}
	
}