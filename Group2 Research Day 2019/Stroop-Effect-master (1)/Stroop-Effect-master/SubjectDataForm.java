import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/*****************************************************************************************/

class SubjectDataForm extends JDialog
{
    JTextField lastNameTextField = new JTextField();
    private JTextField firstNameTextField, dobTextField;

    private JComboBox<String> sexList, majorList, handedList, dayList, monthList, yearList;


    String[] majorTitles = new String[]{"Architecture", "Business", "Education", "Engineering", "Liberal Arts", "Math", "Science", "Nursing"};

    String[] sexTitles = new String[]{"Male", "Female"};
    String[] handedTitles = new String[]{"Right", "Left"};
    String[] monthTitles = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    String[] dayTitles = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] yearTitles = new String[]{"1940","1941","1942","1943","1944","1945","1946","1947","1948","1949","1950","1951","1952","1953","1954","1955","1956","1957","1958","1959","1960","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005"};


    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private String sex = "M";
    private String major = "Architecture";
    private String handed = "Right";
    private char shanded = 'R';
    private char ssex = 'M';

    private String day = "01";
    private String month = "01";
    private String year = "2000";
    private Date sdate;
    private String lastName = "Kay";
    private String firstName = "Mary";
    private boolean done = false;
    private Subject subject;


    public SubjectDataForm()
    {
        setModal(true);
        setLocation(500, 300);
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel p1 = new JPanel(new GridLayout(10, 2));

        p1.add(new JLabel("Last Name:    ", JLabel.LEFT));
        p1.add(lastNameTextField);

        JLabel firstNameLabel = new JLabel("First Name: ");
        p1.add(firstNameLabel);
        firstNameTextField = new JTextField(25);
        p1.add(firstNameTextField);

        dayList = new JComboBox<>(dayTitles);
        JLabel dayLabel = new JLabel("Date of Birth: Day: ");
        p1.add(dayLabel);
        p1.add(dayList);

        monthList = new JComboBox<>(monthTitles);
        JLabel monthLabel = new JLabel("Date of Birth: Month: ");
        p1.add(monthLabel);
        p1.add(monthList);

        yearList = new JComboBox<>(yearTitles);
        JLabel yearLabel = new JLabel("Date of Birth: Year: ");
        p1.add(yearLabel);
        p1.add(yearList);

        sexList = new JComboBox<>(sexTitles);
        JLabel sexLabel = new JLabel("Sex: ");
        p1.add(sexLabel);
        p1.add(sexList);

        majorList = new JComboBox<>(majorTitles);
        JLabel majorLabel = new JLabel("Major: ");
        p1.add(majorLabel);
        p1.add(majorList);

        handedList = new JComboBox<>(handedTitles);
        JLabel handedLabel = new JLabel("Handed: ");
        p1.add(handedLabel);
        p1.add(handedList);


        JPanel p2 = new JPanel();
        JButton doneButton = new JButton("Done");
        p2.add(doneButton);

        doneButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        getContentPane().add(p1, BorderLayout.CENTER);
        getContentPane().add(p2, BorderLayout.SOUTH);
        //pack();
        setVisible(true);
    }

    public String getsubjectDataString()
    {
        String str = "";

        day = (String) dayList.getSelectedItem();
        month = (String) monthList.getSelectedItem();
        year = (String) yearList.getSelectedItem();
        sex = (String) sexList.getSelectedItem();
        major = (String) majorList.getSelectedItem();
        handed = (String) handedList.getSelectedItem();

        str = str + lastNameTextField.getText() + "," + firstNameTextField.getText() + "," + day + "," + month + "," + year + "," + sex + "," + major + "," + handed;
        System.out.println("Inside SubjectDataForm in getsubjectDataString the string is");
        System.out.println(str);
        return str;
    }


}
