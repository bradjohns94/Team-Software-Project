/**EventInterface
 * One of the two GUI classes corresponding to the
 * calendar program. EventInterface is designed to be
 * a simple interface for the user to input basic information
 * when creating a new event to be used by the calendar
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class EventInterface extends JFrame implements ActionListener {

    //Parts of the actual interface
    private Event event;
    private JButton save;
    private JButton close;
    private JTextField title;
    private JTextField date;
    private JTextField start;
    private JTextField end;
    private JTextField location;
    private JTextArea description;

    //For the HAL joke
    private boolean hasClosed; //This is terrible practice
    
    /**EventInterface Constructor 1
     * Creates a new EventInterface assuming it is the user's intention to create
     * a brand new event instead of altering a pre-existing one
     */

    public EventInterface() {
        super("Add Event");
        event = new Event("Event Title", "Event Description", "Event Date",
                          "Event Start Time", "Event End Time", "Event Location");
        init();
    }

    /**EventInterface Constructor 2
     * Creates a new EventInterface assuming it is the user's intention to alter
     * a pre-existing even instead of creating a new one
     * @param e the event that is being altered
     */

    public EventInterface(Event e) {
        super(e.getTitle());
        event = e;
        init();
    }

    /**init
     * Initializes the GUI with text boxes and buttons
     */

    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(600, 600);
        setBackground(Color.WHITE);

        //Set the HAL joke...
        hasClosed = false;

        //Next lets add some buttonds
        save = new JButton("Save Changes"); 
        save.setBounds(410, 490, 180, 55);
        close = new JButton("Close");
        close.setBounds(220, 490, 180, 55);
        add(save);
        add(close);
        save.addActionListener(this);
        close.addActionListener(this);
        paintText();

        setVisible(true);
    }

    /**paint
     * adds to the GUI what init and paintText could not because
     * they required a graphics object
     * @param g the graphics object to paint with
     */

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g; 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON); 
        Color green = new Color(0x004b00);
        g.setColor(green); 
        g.fillRect(0, 0, getWidth(), 100); 

        //Now we draw the title text
        setFont(new Font("Serif", Font.BOLD, 42)); 
        g.setColor(Color.WHITE); 
        g.drawString("Add Event", 10, 90); 
    }

    /**paintText
     * an extension of init that sets the starting parameters
     * for the text boxes
     */

    public void paintText() {
        //Perform Text box operations
        Font textFont = new Font("Serif", Font.PLAIN, 20);  
        title = new JTextField(event.getTitle());  
        title.setBounds(10, 80, 180, 35);  
        title.setFont(textFont);  
        date = new JTextField(event.getDate()); 
        date.setBounds(10, 135, 180, 35);
        date.setFont(textFont);
        start = new JTextField(event.getStart());
        start.setBounds(210, 135, 180, 35);
        start.setFont(textFont);
        end = new JTextField(event.getEnd());
        end.setBounds(400, 135, 180, 35);
        end.setFont(textFont);
        location = new JTextField(event.getLocation()); 
        location.setBounds(10, 190, 180, 35);
        location.setFont(textFont);
        description = new JTextArea(event.getDesc()); 
        description.setBounds(11, 246, 569, 225);
        description.setFont(textFont);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(title);
        add(date);
        add(start);
        add(end);
        add(location);
        add(description);
    }

    /**getEvent
     * returns the event object represented in the interface
     * @return an even object representing the interface
     */

    public Event getEvent() {
        return event;
    }

    /**actionPerformed
     * Tells the program how to react in the case that one of
     * the buttons is clicked
     * @param e the event of one of the buttons being pressed
     */

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            event.changeTitle(title.getText());
            event.changeDate(date.getText());
            event.changeStart(start.getText());
            event.changeEnd(end.getText());
            event.changeLocation(location.getText());
            event.changeDesc(description.getText());
            //TODO connect to data classes and dispose
        } else if (e.getSource() == close) {
            if (hasClosed) dispose();
            else {
                JOptionPane.showMessageDialog(null, "I can't let you do that, Dave");
                hasClosed = true;
            }
        }
    }
}
