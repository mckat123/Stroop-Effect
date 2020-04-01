import javax.swing.*;

public class Main
{

    public static void main(String[] args)
    {
        boolean unlock = false;
        String pin;

        DataManager gui = new DataManager();

        pin = JOptionPane.showInputDialog("Welcome to Stroop. Please enter your PIN:");
        unlock = gui.unlockScreen(pin);
        if (unlock == true)
        {
            gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Incorrect PIN");
        }
    }


}
