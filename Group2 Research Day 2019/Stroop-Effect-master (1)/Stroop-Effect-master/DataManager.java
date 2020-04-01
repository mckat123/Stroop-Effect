import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class DataManager extends JFrame
        implements ActionListener
{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 250;
    public static final int MAX = 100;
    static GraphicsConfiguration gc;


    private JTextField lastNameTextField, firstNameTextField, ageTextField,
            sexTextField, majorTextField, handedTextField;
    private FileManager fm;
    private GraphManager gm;
    //private Examiner em;

    private int numSubjects = 0;
    private String[] subjectLine;


    private Subject[] subjectData;
    private Subject subject;
    private JLabel subjectDataStringLabel;
    private JLabel subjectTestStringLabel;


    private char shanded;
    private char ssex;
    private Date sdate;

    private String day;
    private String month;
    private String year;
    private String sex;
    private String handed;
    private String major;

    private String infilename;
    private String outfilename;


    {

        subjectData = new Subject[MAX];
        subjectLine = new String[MAX];

        setSize(WIDTH, HEIGHT);
        setTitle("Stroop Experiment Analyzer");
        Container contentPane = getContentPane();
        Color lightBlue = new Color(0, 0, 182, 155);
        contentPane.setBackground(lightBlue);
        contentPane.setLayout(new GridLayout(8, 2));


        JButton retrieveTestCaseFileButton = new JButton("Retrieve Test Case File");
        retrieveTestCaseFileButton.addActionListener(this);
        contentPane.add(retrieveTestCaseFileButton);


        subjectDataStringLabel = new JLabel("");
        JButton btn = new JButton("Enter New Subject Personal Data");
        //*********** The next line allows the SubjectDataForm object to pass back the subject test data to the Data Manager in the SubjectDataStringLabel
        btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                subjectDataStringLabel.setText(new SubjectDataForm().getsubjectDataString());
            }
        });


        contentPane.add(btn);
        JButton saveDataButton = new JButton("Save New Subject Personal Data");
        saveDataButton.addActionListener(this);
        contentPane.add(saveDataButton);


        JButton takeTestButton = new JButton("Take Test");
        takeTestButton.addActionListener(this);
        contentPane.add(takeTestButton);

        JButton reviewTestCaseFileButton = new JButton("Review Test Case File");
        reviewTestCaseFileButton.addActionListener(this);
        contentPane.add(reviewTestCaseFileButton);

        JButton analyzeTestCaseFileButton = new JButton("Analyze Test Case File");
        analyzeTestCaseFileButton.addActionListener(this);
        contentPane.add(analyzeTestCaseFileButton);

        JButton createTestCaseFileButton = new JButton("Save Test Case File");
        createTestCaseFileButton.addActionListener(this);
        contentPane.add(createTestCaseFileButton);


        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        contentPane.add(exitButton);
    }


    public void convertNewSubjectData(String day, String month, String year, String sex, String handed)
    {
        // Convert string data to appropriate data type for date, sex, and handed fields

        sdate = new Date();
        String pattern = "yyyy-MM-dd";
        String d;
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat(pattern);


        if (sex.equals("Male"))
        {
            ssex = 'M';
        }
        else
        {
            ssex = 'F';
        }

        if (handed.equals("Right"))
        {
            shanded = 'R';
        }
        else
        {
            shanded = 'L';
        }

        d = year + "-" + month + "-" + day;


        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdate = sdf.parse(d);
            // System.out.println("sdate = " + sdate);
        }
        catch (ParseException pe)
        {
            System.out.println("Parse exception of dob in DataManager setNewSubjectData");
        }


    }


    public String retrieveFile(String fname)

    {
        String myStr;
        String message;
        int i;
        numSubjects = 0;
        String infilename = fname;
        try
        {
            File file = new File(infilename);
            Scanner inputFile = new Scanner(file);
            message = "Retrieved file " + infilename;
            while (inputFile.hasNext())
            {
                myStr = inputFile.nextLine();
                //System.out.println(myStr);

                //System.out.println("File Retrieve-->Subject data is:" + myStr);
                i = numSubjects;
                subjectData[i] = new Subject();

                subjectData[i].populateFromString(myStr);

                numSubjects++;
            }

            inputFile.close();

        }

        catch (FileNotFoundException e)
        {
            message = "Error. Unable to open file";
            System.exit(0);
        }

        return message;
    }


    public void actionPerformed(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        String display;
        String fname;
        boolean done = false;
        boolean firstCall = true;
        boolean fileCreated = false;
        String subjectStr = "";

        switch (actionCommand)
        {
            case "Save Test Case File":
                display = "";
                outfilename = "stroopOut.txt";
                fname = JOptionPane.showInputDialog("Enter filename: ");
                System.out.println("fname is " + fname);
                fname = fname.trim();
                if (fname.length() > 0)
                {
                    outfilename = fname;
                }


                fm = new FileManager();
                if (fm.validFileName(outfilename))
                {

                    try
                    {
                        PrintWriter outputFile = new PrintWriter(outfilename);
                        for (int j = 0; j < numSubjects; j++)
                        {
                            subjectStr = subjectData[j].toString();
                            //System.out.println("Writing line to outputfile " + outfilename);
                            outputFile.println(subjectStr);
                        }
                        outputFile.close();
                        display = "Saved data to output file " + outfilename;
                    }
                    catch (FileNotFoundException ec)
                    {
                        //System.out.println("Error. Unable to open file");
                        //ec.printStackTrace();
                        display = "Invalid file name or file does not exist";
                        System.exit(0);
                    }
                } // end of if validFileName

                JOptionPane.showMessageDialog(null, display);

                break;


            case "Save New Subject Personal Data":
                //System.out.println("Save-->Subject data is:" + subjectDataStringLabel.getText());
                int i = numSubjects;
                subjectData[i] = new Subject();
                subjectStr = subjectDataStringLabel.getText();
                String delims = "[,]";
                String[] token = subjectStr.split(delims);
                subjectData[i].setLastName(token[0]);
                subjectData[i].setFirstName(token[1]);

                day = token[2];
                month = token[3];
                year = token[4];

                sex = token[5];
                major = token[6];
                handed = token[7];

                convertNewSubjectData(day, month, year, sex, handed);
                subjectData[i].setDOB(sdate);
                subjectData[i].setSex(ssex);
                subjectData[i].setMajor(major);
                subjectData[i].setHanded(shanded);
                numSubjects++;
                break;


            case "Take Test":

                String lname = JOptionPane.showInputDialog("Enter your last name: ");
                String frname = JOptionPane.showInputDialog("Enter your first name: ");


                boolean match = false;
                int num = 0;
                int index = -1;
                while (num < numSubjects && !match)
                {
                    if (subjectData[num].getLastName().equalsIgnoreCase(lname) && subjectData[num].getFirstName().equalsIgnoreCase(frname))
                    {
                        match = true;
                    }
                    index = num;
                    num++;
                }


                if (match)
                {
                    if (!subjectData[index].getTested())
                    {
                        Examiner em = new Examiner();
                        em.giveTest(subjectData[index]);

                        return;
                    }
                    else
                    {
                        display = "You already took test!!";
                    }
                }
                else
                {
                    display = "Subject name not found. Subject data must be entered by experimenter before testing.";
                }

                JOptionPane.showMessageDialog(null, display);

                break;


            case "Review Test Case File":
                display = "Reviewed Test Case File";
                System.out.println("Review-->Subject data is:");
                subjectData[0].tablePrintHeader();
                for (int j = 0; j < numSubjects; j++)
                {
                    subjectData[j].tablePrintSubject();
                }
                break;

            case "Analyze Test Case File":
                display = "Analyzed Test Case File";
                gm = new GraphManager();
                break;

            case "Retrieve Test Case File":
                fname = JOptionPane.showInputDialog("Enter filename: ");
                System.out.println("file name is " + fname);
                fname = fname.trim();
                if (fname.length() > 0)
                {
                    display = retrieveFile(fname);
                }
                else
                {
                    display = "File does not exist";
                }

                JOptionPane.showMessageDialog(null, display);
                break;

            case "Exit":
                done = true;
                System.exit(0);
                break;
            default:
                System.exit(0);
                break;
        }// end of switch

    }

    public boolean unlockScreen(String code)
    // will return true if code is correct, false otherwise
    {
        return code.equals("1234");
    }

    public Subject[] getSubjectData()
    {
        return subjectData;
    }
}// end of DataManager class


