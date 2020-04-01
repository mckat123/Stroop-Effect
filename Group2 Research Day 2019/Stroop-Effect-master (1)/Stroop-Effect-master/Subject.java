import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class Subject
{
    private String lastName;
    private String firstName;
    private Date dob;
    private char sex;
    private String major;
    private char handed;
    private boolean tested;
    private TestResults results;

    // Subject who will be tested

    public Subject()
    {
        this.lastName = "NULL";
        this.firstName = "NULL";
        this.dob = null;
        this.sex = 'M';
        this.major = "Architecture";
        this.handed = 'R';
        this.tested = false;
        this.results = null;
    }

    public Subject(String lastName, String firstName, Date dob, char sex, String major, char handed)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dob = dob;
        this.sex = sex;
        this.major = major;
        this.handed = handed;
        this.tested = false;
        this.results = null;

    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    // DOB is stored at Java Date
    public Date getDOB()
    {
        return dob;
    }

    // 'M' or 'F'
    public char getSex()
    {
        return sex;
    }

    public String getMajor()
    {
        return major;
    }

    // 'L' or 'R'
    public char getHanded()
    {
        return handed;
    }

    public boolean getTested()
    {
        return tested;
    }

    public void setLastName(String s)
    {
        lastName = s;
    }

    public void setFirstName(String s)
    {
        firstName = s;
    }

    // DOB is stored as Java Date
    public void setDOB(Date date)
    {
        Date sdate;
        String pattern = "yyyy-MM-dd";

        dob = date;
    }


    // 'M' or 'F'
    public void setSex(char s)
    {
        sex = s;
    }

    public void setMajor(String m)
    {
        major = m;
    }

    // 'L' or 'R'
    public void setHanded(char h)
    {
        handed = h;
    }

    public void populateFromString(String myStr)
    {
        char ssex, shanded;
        Date sdate;
        String pattern = "yyyy-MM-dd";


        //System.out.println("Populate string-->Subject data is:" + myStr);
        String delims = "[,]";
        String[] token = myStr.split(delims);
        this.lastName = token[0];
        this.firstName = token[1];


        try
        {
            sdate = new SimpleDateFormat(pattern).parse(token[2]);
            dob = sdate;
        }
        catch (ParseException pe)
        {

            System.out.println("Parse exception inside subject populateFromString");
        }

        this.major = token[4];

        // convert sex and handed from strings to char
        if (token[3].equals("M"))
        {
            ssex = 'M';
        }
        else
        {
            ssex = 'F';
        }

        if (token[5].equals("R"))
        {
            shanded = 'R';
        }
        else
        {
            shanded = 'L';
        }

        this.sex = ssex;
        this.handed = shanded;
        // System.out.println("TOKEN 6 is "+token[6]);
        this.tested = !token[6].equals("false");

        if (this.tested)
        {
            TestResults r = new TestResults();
            r.practiceCorrect = Integer.parseInt(token[7]);
            r.practiceTotalTime = Integer.parseInt(token[8]);
            r.stroopTestCorrect = Integer.parseInt(token[9]);
            r.stroopTestTime = Integer.parseInt(token[10]);
            this.results = r;
        }

        // System.out.println("Inside populate string **");
        //  printSubject();
    }

    public String toString()
    {
        String str = "";
        LocalDate localDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();        
        int year = localDate.getYear();
        int day = localDate.getDayOfMonth();
        String dateOfBirth = "";
        dateOfBirth = dateOfBirth + year + "-" + month + "-" + day;
        str = str + lastName + "," + firstName + "," + dateOfBirth + "," + sex + "," + major + "," + handed + "," + tested;
        if (tested)
        {
            str = str + results.toString();
        }

        return str;
    }

    // Whether or not the Subject has been tested
    public boolean isTested()
    {
        return tested;
    }

    public void setTested(boolean tested)
    {
        this.tested = tested;
    }

    public void printSubject()
    {
        System.out.println("");
        System.out.println("Last Name: " + lastName);
        System.out.println("First Name: " + firstName);
        System.out.println("DOB: " + dob);
        System.out.println("Major: " + major);
        System.out.println("Sex: " + sex);
        System.out.println("Handed: " + handed);
        System.out.println("Tested: " + tested);
        if (this.tested)
        {
            this.results.printTestResults();
        }
    }

    public void tablePrintHeader()
    {
        System.out.println("Last Name    First Name  DOB         Major        Sex  Hand    Test    PC  PRT  SC  SRT");
        System.out.println("----------------------------------------------------------------------------------------");
    }

    public void tablePrintSubject()
    {
        String str = "";
        LocalDate localDate = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int day = localDate.getDayOfMonth();
        String dateOfBirth = "";
        dateOfBirth = dateOfBirth + year + "-" + month + "-" + day;


        System.out.printf("%12s %10s %10s %15s %4s %4s %6s", lastName, firstName, dateOfBirth, major, sex, handed, tested);
        if (this.tested)
        {
            System.out.printf(" %4d %4d %4d %4d", this.results.getPracticeCorrect(), this.results.getPracticeTotalTime(), this.results.getStroopTestCorrect(), this.results.getStroopTestTime());
        }
        System.out.println("");

    }


    public TestResults getTestResults()
    {
        return results;
    }

    public void setTestResults(TestResults results)
    {
        this.results = results;
    }
}