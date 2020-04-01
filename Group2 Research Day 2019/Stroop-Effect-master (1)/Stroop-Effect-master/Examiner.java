import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * StroopEffect
 * Created by Robert Varadan on 3/20/19.
 */
public class Examiner
{
    private long testStartTime;
    private long practiceStartTime;
    private TestCanvas testCanvas;
    private JButton nextButton;
    public long currentTestQuestion = 1;
    public int TEST_QUESTIONS = 8;
    private int currentPracticeQuestion = 1;
    private int testNumCorrect = 0;
    private int practiceNumCorrect = 0;
    private JFrame mainFrame;
    private boolean isPracticeTest = true;


    // Implicit constructor
    public Examiner()
    {
        this.testStartTime = -1;
    }

    public void giveTest(Subject subject)
    {
        subject.setTested(true);

        mainFrame = new JFrame(getFrameTitle());
        testCanvas = new TestCanvas(mainFrame, this, subject);
        mainFrame.getContentPane().add(testCanvas);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        colorClicked();
    }

    public boolean colorClicked(ActionEvent event, Color currentActual)
    {
        String clicked = event.getActionCommand();
        String readableActual = testCanvas.getReadableName(currentActual);
        boolean isFinished = false;

        if (isPracticeTest && currentPracticeQuestion != TEST_QUESTIONS)
        {
            testStartTime = System.currentTimeMillis();
            System.out.println("Test start time = " + testStartTime);
        }

        isPracticeTest = currentPracticeQuestion != TEST_QUESTIONS;

        if (clicked.equalsIgnoreCase(readableActual)) // If we clicked right
        {
            if (isPracticeTest)  // If we clicked right and it was a practice test
            {
                isFinished = false;
                practiceNumCorrect++;

                isPracticeTest = true;
            }
            else // If we clicked right and it was a regular test
            {
                testNumCorrect++;
            }
        }

        if (isPracticeTest) // If it's a practice test
        {
            if (currentPracticeQuestion == 1) // If we've just completed one question, start counting practice time
            {
                practiceStartTime = System.currentTimeMillis();
            }

            if (currentPracticeQuestion == TEST_QUESTIONS - 1) // If we have one question left
            {
                JOptionPane.showMessageDialog(null, "Get ready! After the next question, " +
                        "the test will start.");

            }

            currentPracticeQuestion++;
        }
        else // If it's a regular test
        {
            if (currentTestQuestion == TEST_QUESTIONS)
            {
                // We are out of time
                isFinished = true;
            }
            else
            {
                currentTestQuestion++;
            }
        }

        mainFrame.setTitle(getFrameTitle());

        return isFinished;
    }

    public void colorClicked()
    {
        if (testCanvas.getComponents().length > 0)
        {
//            System.out.println("NUM ELEM: " + testCanvas.getComponents().length);

            for (Component component : testCanvas.getComponents())
            {
                if (component.getClass() != JButton.class)
                {
                    testCanvas.remove(component);
                }
            }
        }

        HashMap<Color, String> readableNames = testCanvas.getReadableNames();
        Set<Color> colors = readableNames.keySet();


        Color newActual;
        Color newDisplay;

        // Shuffle colors so we don't get the same two in a row
        do
        {
            newDisplay = (Color) colors.toArray()[(new Random().nextInt(colors.size()))];
            newActual = (Color) colors.toArray()[(new Random().nextInt(colors.size()))];

        }
        while (newDisplay == testCanvas.getCurrentDisplay() || newActual == testCanvas.getCurrentActual());

        // Keep colors the same
        if (isPracticeTest)
        {
            newDisplay = newActual;
        }

        testCanvas.paintText(newActual, newDisplay, null);
    }

    private void recordTestResults(Subject subject)
    {
        TestResults testResults = new TestResults();
        testResults.setStroopTestTime((int) (System.currentTimeMillis() - testStartTime));
        testResults.setStroopTestCorrect(testNumCorrect);
        testResults.setStroopTestTotalQuestions(TEST_QUESTIONS);

        testResults.setPracticeCorrect(practiceNumCorrect);
        testResults.setPracticeTotalTime((int) (testStartTime - practiceStartTime));
        testResults.setPracticeTotalQuestions(TEST_QUESTIONS);
        testResults.setTestDate(new Date(practiceStartTime));

        subject.setTestResults(testResults);
    }

    public void endTest(Subject subject)
    {
        // Close test frame
        mainFrame.setVisible(false);
        recordTestResults(subject);
        System.out.print("The following subject completed the Stroop test:");
        JOptionPane.showMessageDialog(null, "Stroop test completed successfully. " +
                "Here is your data <insert data here>");
        subject.printSubject();
    }

    public boolean isPracticeTest()
    {
        return isPracticeTest;
    }

    public String getFrameTitle()
    {
        return String.format("Stroop Test (%s%d/%d)", (isPracticeTest ? "Practice: " : ""),
                isPracticeTest ? currentPracticeQuestion : currentTestQuestion - 1, TEST_QUESTIONS);
    }
}
