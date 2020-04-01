   import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;


        

 public class GraphManager extends JFrame
      implements ActionListener
  {
  
		
                                              
        public static final int WIDTH = 400;
        public static final int HEIGHT = 400;
        

	public GraphManager ()
   {
     
          String display;
         //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setSize(WIDTH,HEIGHT);
          Container contentPane = getContentPane();
          Color lightBlue= new Color(0,0,182,155);
          contentPane.setBackground(lightBlue);
          contentPane.setLayout(new GridLayout(10,2));
          
                    
          setTitle("Analyze Stroop Test Data");
                  
                  
                                                                                                   
                  JButton doneButton = new JButton("Done");
                  doneButton.addActionListener(this);
                  contentPane.add(doneButton);
                  
      setLocationRelativeTo(null);     
		setVisible(true);
      
	}
   
     
   public void actionPerformed(ActionEvent e)
         {
                  String actionCommand = e.getActionCommand();
                  String paramStr = e.paramString();
                  String display;
                  String inputStr;
                  
                                  
                  //System.out.println("actionCommand is"+actionCommand);
                  //System.out.println("paramString is"+ paramStr);
                  
                  if (actionCommand == "Done")
                  
                        {System.out.println("Finished Test!    ");                                                                                                                                                              
                         setVisible(false);
                        
                         }
              }
              
                    
                  
}
