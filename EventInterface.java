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
     * Opens a create event screen designed for the creation
     * of a new event.
     */

    public EventInterface() {
        super("Add Event");
        event = new Event("Event Title", "Event Description", "Event Date",
                          "Event Start Time", "Event End Time", "Event Location");
        init();
    }

    /**EventInterface Constructor 2
     * Opens a event alteration screen in order to edit a
     * pre-created event
     * @param e the event that has already been established
     */

    public EventInterface(Event e) {
        super(e.getTitle());
        event = e;
        init();
    }

    /**init
     * Initializes the Event Interface with a series of text boxes and
     * buttons in order to provide adequate space for the user to enter
     * event information
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
     * paints all GUI components that require a Graphics object
     * including the title display
     * @param g the Graphics object to be used for painting
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
     * A segmented method to be used by init which simply sets
     * all the parameters for the text objects
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

        //Add the Text boxed
        add(title);
        add(date);
        add(start);
        add(end);
        add(location);
        add(description);
    }

    /**getEvent
     * passes the event information to data
     * @return the event represented in the interface
     */

    public Event getEvent() {
        return event;
    }

    /**actionPerformed
     * Handles the actionlisteners for when one of the interface's
     * buttons has been pressed
     * @param e the actionevent that occurred to call the method
     */

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            event.changeTitle(title.getText());
            event.changeDate(date.getText());
            event.changeStart(start.getText());
            event.changeEnd(end.getText());
            event.changeLocation(location.getText());
            event.changeDesc(description.getText());
            //TODO pass the event to data and close
        } else if (e.getSource() == close) {
            if (hasClosed) dispose();
            else {
                JOptionPane.showMessageDialog(null, "I can't let you do that, Dave");
                hasClosed = true;
            }
        }
    }
}
