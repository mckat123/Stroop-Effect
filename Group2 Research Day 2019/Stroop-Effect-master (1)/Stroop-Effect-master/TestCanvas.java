import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

public class TestCanvas extends JComponent
{
    private static Font sansSerifFont = new Font("SansSerif", Font.BOLD, 114);

    private static ImageIcon java2sLogo = new ImageIcon("java2s.gif");
    private Color currentActual;
    private Color currentDisplay;

    private HashMap<Color, String> readableNames;
    private JFrame frame;
    private Examiner examiner;
    private Subject subject;

    public static Color RED = new Color(255, 70, 70);
    public static Color ORANGE = new Color(255, 128, 0);
    public static Color YELLOW = new Color(255, 255, 0);
    public static Color GREEN = new Color(0, 250, 0);
    public static Color BLUE = new Color(27, 161, 235);
    public static Color PURPLE = new Color(128, 0, 255);

    private final ActionListener colorButtonActionListener;

    public TestCanvas(JFrame frame, Examiner examiner, Subject subject)
    {
        this.frame = frame;
        this.examiner = examiner;
        this.subject = subject;
        readableNames = new HashMap<>();
        readableNames.put(RED, "Red");
//        readableNames.put(ORANGE, "Orange");
        readableNames.put(YELLOW, "Yellow");
        readableNames.put(GREEN, "Green");
        readableNames.put(BLUE, "Blue");
        readableNames.put(PURPLE, "Purple");


        this.colorButtonActionListener = e -> {

            // Conclude if we're at the end of the test.
            if (examiner.colorClicked(e, getCurrentActual()))
            {
                examiner.endTest(subject);
                return;
            }

            examiner.colorClicked();
            this.remove((Component) e.getSource());
            createButtons();
        };
        createButtons();

    }

    private void createButtons()
    {
        JPanel subPanel = new JPanel();
        for (Map.Entry<Color, String> entry : readableNames.entrySet())
        {
            JButton colorButton = new JButton();
            colorButton.setText(entry.getValue());
            colorButton.setVerticalTextPosition(AbstractButton.BOTTOM);
            colorButton.setHorizontalTextPosition(AbstractButton.CENTER);
            colorButton.setActionCommand(entry.getValue().toLowerCase());
            colorButton.setMnemonic(KeyEvent.VK_M);
            colorButton.setPreferredSize(new Dimension(200, 100));


//        nextButton.setLocation((int) (getPreferredSize().getWidth() / 2) - 100, (int) getPreferredSize().getHeight() - 100);
            colorButton.setLayout(new FlowLayout(FlowLayout.CENTER));
            colorButton.addActionListener(colorButtonActionListener);
            subPanel.add(colorButton);
        }

        frame.getContentPane().add(subPanel, BorderLayout.SOUTH);

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

//        removeAll();

        // paint the icon below red sqaure

        if (currentActual != null && currentDisplay != null)
        {
            paintText(currentActual, currentDisplay, (Graphics2D) g);
        }
    }

    public void paintText(Color actual, Color display, Graphics2D g)
    {

        g = g == null ? (Graphics2D) getGraphics() : g;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setFont(sansSerifFont);
        this.currentDisplay = display;
        this.currentActual = actual;

        String readable = getReadableName(display);

        AffineTransform transform = g.getTransform();
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(readable)) / 2;
        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
        transform.translate(x, y);

        g.transform(transform);
        g.setColor(Color.BLACK);
        FontRenderContext frc = g.getFontRenderContext();
        TextLayout tl = new TextLayout(readable, sansSerifFont, frc);
        Shape shape = tl.getOutline(null);
        g.setStroke(new BasicStroke(5f));
        g.draw(shape);
        g.setColor(actual);
        g.fill(shape);
    }

    public String getReadableName(Color actual)
    {
        for (Map.Entry<Color, String> entry : readableNames.entrySet())
        {
            Color key = entry.getKey();
            if (key.getRed() == actual.getRed() && key.getGreen() == actual.getGreen() && actual.getBlue() == actual.getBlue())
            {
                return entry.getValue();
            }
        }

        throw new IllegalArgumentException("Illegal color specified! Could not find in Readable Names. Specified: " + actual);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(800, 800);
    }

    public Dimension getMinimumSize()
    {
        return getPreferredSize();
    }

    public void setCurrentActual(Color currentActual)
    {
        this.currentActual = currentActual;
    }

    public void setCurrentDisplay(Color currentDisplay)
    {
        this.currentDisplay = currentDisplay;
    }

    public HashMap<Color, String> getReadableNames()
    {
        return readableNames;
    }

    public Color getCurrentDisplay()
    {
        return currentDisplay;
    }

    public Color getCurrentActual()
    {
        return currentActual;
    }
}
         