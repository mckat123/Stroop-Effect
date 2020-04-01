import java.text.DateFormat;
import java.util.Date;


public class TestResults
{
    Date testDate;
    int practiceCorrect;
    int practiceTotalTime;
    int stroopTestCorrect;
    int stroopTestTime;
    int stroopTestTotalQuestions;
    private int practiceTotalQuestions;

    public TestResults()
    {
        practiceCorrect = 0;
        practiceTotalTime = 0;
        stroopTestCorrect = 0;
        stroopTestTime = 0;
        testDate = null;
    }

    public TestResults(int pc, int pt, int sc, int st, int tq)
    {  // Note that pc and sc are in the range of 0-40.
        // This constructor is for creating testsets for code testing
        // actual testResults have a valid date associated with them
        practiceCorrect = pc;
        practiceTotalTime = pt;
        stroopTestCorrect = sc;
        stroopTestTime = st;
        stroopTestTotalQuestions = tq;
        testDate = null;
    }

    public String toString()
    {
        String str = "";
        String pc, pt, sc, st, sdate;

        pc = Integer.toString(practiceCorrect);
        pt = Integer.toString(practiceTotalTime);
        sc = Integer.toString(stroopTestCorrect);
        st = Integer.toString(stroopTestTime);
        //sdate = testDate.toString();

        str = str + "," + pc + "," + pt + "," + sc + "," + st;
        return str;
    }

    public void printTestResults()
    {
        if (testDate != null)
        {
            DateFormat formatter = DateFormat.getDateTimeInstance();
            System.out.println("Date of Test: " + formatter.format(testDate));
        }
        System.out.println("Practice Correct: " + practiceCorrect);
        System.out.println("Practice Total Time : " + practiceTotalTime);
        System.out.println("Practice Test Total Questions: " + practiceTotalQuestions);
        System.out.println("Stroop Test Correct: " + stroopTestCorrect);
        System.out.println("Stroop Test Time: " + stroopTestTime);
        System.out.println("Stroop Test Total Questions: " + stroopTestTotalQuestions);
    }

    public int getPracticeCorrect()
    {
        return practiceCorrect;
    }

    public void setPracticeCorrect(int practiceCorrect)
    {
        this.practiceCorrect = practiceCorrect;
    }

    public int getPracticeTotalTime()
    {
        return practiceTotalTime;
    }

    public void setPracticeTotalTime(int practiceTotalTime)
    {
        this.practiceTotalTime = practiceTotalTime;
    }

    public int getStroopTestCorrect()
    {
        return stroopTestCorrect;
    }

    public void setStroopTestCorrect(int stroopTestCorrect)
    {
        this.stroopTestCorrect = stroopTestCorrect;
    }

    public int getStroopTestTime()
    {
        return stroopTestTime;
    }

    public void setStroopTestTime(int stroopTestTime)
    {
        this.stroopTestTime = stroopTestTime;
    }

    public void setStroopTestTotalQuestions(int stroopTestTotalQuestions)
    {
        this.stroopTestTotalQuestions = stroopTestTotalQuestions;
    }

    public void setPracticeTotalQuestions(int practiceTotalQuestions)
    {
        this.practiceTotalQuestions = practiceTotalQuestions;
    }

    public void setTestDate(Date testDate)
    {
        this.testDate = testDate;
    }
}