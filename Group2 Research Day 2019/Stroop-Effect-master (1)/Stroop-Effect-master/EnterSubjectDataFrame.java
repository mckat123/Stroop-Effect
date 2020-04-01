import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


        

 public class EnterSubjectDataFrame extends JFrame
      implements ActionListener
  {
  
		
            private JTextField lastNameTextField, firstNameTextField, dobTextField,
                                  sexTextField, majorTextField, handedTextField;
                                  
        public static final int WIDTH = 400;
        public static final int HEIGHT = 300;
        
       
   
	public EnterSubjectDataFrame() {
     
       String display;


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                  setSize(WIDTH,HEIGHT);
                  Container contentPane = getContentPane();
                  Color lightBlue= new Color(0,0,182,155);
                  contentPane.setBackground(lightBlue);
                  contentPane.setLayout(new GridLayout(8,2));


      
                  setTitle("Enter Subject Data");
                  
                  
                  JLabel lastNameLabel = new JLabel("Last Name: ");
                  contentPane.add(lastNameLabel);
                  lastNameTextField = new JTextField(25);
                  contentPane.add(lastNameTextField);
                  //lastNameTextField.addActionListener(this);

  
                  JLabel firstNameLabel = new JLabel("First Name: ");
                  contentPane.add(firstNameLabel);
                  firstNameTextField = new JTextField(25);
                  contentPane.add(firstNameTextField);
 
                  JLabel dobLabel = new JLabel("Date of Birth (DD/MM/YY: ");
                  contentPane.add(dobLabel);
                  dobTextField = new JTextField(25);
                  contentPane.add(dobTextField);
 
                  JLabel sexLabel = new JLabel("Sex: ");
                  contentPane.add(sexLabel);
                  sexTextField = new JTextField(25);
                  contentPane.add(sexTextField);
  
                  JLabel majorLabel = new JLabel("Major: ");
                  contentPane.add(majorLabel);
                  majorTextField = new JTextField(25);
                  contentPane.add(majorTextField);
  
                  JLabel handedLabel = new JLabel("Handed: ");
                  contentPane.add(handedLabel);
                  handedTextField = new JTextField(25);
                  contentPane.add(handedTextField);
 
      
      
                          display = lastNameTextField.getText() + "\n";
                          display += firstNameTextField.getText() + "\n";
                          display += dobTextField.getText() + "\n";
                          display += sexTextField.getText() + "\n";
                          display += majorTextField.getText() + "\n";
                          display += handedTextField.getText() + "\n";
                            
                          lastNameTextField.setText("");
                          firstNameTextField.setText("");
                          dobTextField.setText("");
                          sexTextField.setText("");
                          majorTextField.setText("");
                          handedTextField.setText("");

      JButton doneButton = new JButton("Done");
      doneButton.addActionListener(this);
      contentPane.add(doneButton);
      
      setLocationRelativeTo(null);     
		setVisible(true);
	}
   
   public void actionPerformed(ActionEvent e)
         {
                  String actionCommand = e.getActionCommand();
                  String display;
                  
                  String lastName,firstName;
                  boolean done=false;
                  
                  switch(actionCommand)
                  {

                  case "Last Name: ":
                        lastName= lastNameTextField.getText();
                        System.out.println("The last name is "+lastName);
                        break;
                        
                  case "First Name: ":
                        firstName= firstNameTextField.getText();
                        System.out.println("The first name is"+firstName);
                        break;
                                                
                  case "Done":
                        System.out.println("Got Done");
                        done= true;
                        setVisible(false);
                        break;
                
                  default:
                        System.exit(0);
                        break;
                  }
          }

   
   
   
   
   
   
   
   
   
}
