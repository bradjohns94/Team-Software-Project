/**EventInterface
 * One of the two GUI classes corresponding to the
 * calendar program. EventInterface is designed to be
 * a simple interface for the user to input basic information
 * when creating a new event to be used by the calendar
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class EventInterface extends JFrame implements ActionListener {

    //Parts of the actual interface
    private EventData event;
    private JButton save;
    private JButton close;
    private JTextField title;
    private JTextField date;
    private JTextField start;
    private JTextField end;
    private JTextField location;
    private JTextArea description;

    private boolean newEvent;
    private Control ctrl;
    private CalendarInterface cal;

    //For the HAL joke
    private boolean hasClosed; //This is terrible practice

    /**EventInterface Constructor 1
     * Creates a new EventInterface assuming it is the user's intention to create
     * a brand new event instead of altering a pre-existing one
     */

    public EventInterface(Control toSend, CalendarInterface iface) {
        super("Add Event");
        event = new EventData();
        ctrl = toSend;
        newEvent = true;
        cal = iface;
    }

    /**EventInterface Constructor 2
     * Creates a new EventInterface assuming it is the user's intention to alter
     * a pre-existing even instead of creating a new one
     * @param e the event that is being altered
     */

    public EventInterface(EventData e, Control toSend, CalendarInterface iface) {
        super(e.getTitle()); 
        event = e;
        ctrl = toSend; newEvent = false; 
        cal = iface;
    } 
    /**init
     * Initializes the GUI with text boxes and buttons
     */

    public void init() {
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(600, 600);
        setBackground(Color.WHITE);

        //Set the HAL joke...
        hasClosed = false;

        //Next lets add some buttonds
        save = new JButton("Save Changes"); 
        save.setBounds(410, 490, 180, 55);
        close = new JButton("Close"); close.setBounds(220, 490, 180, 55);
        save.addActionListener(this);
        close.addActionListener(this);
        paintText();

        add(save);
        add(close);

        setFont(new Font("Serif", Font.BOLD, 42));

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
        g.setColor(Color.WHITE); 
        if (event.getTitle().equals("")) {
            g.drawString("Add Event", 10, 90); 
        } else {
            g.drawString("Edit Event", 10, 90);
        }
    }

    /**paintText
     * an extension of init that sets the starting parameters
     * for the text boxes
     */
    @SuppressWarnings("deprecation")
    public void paintText() {
        //Perform Text box operations
        //Title box
        String titleText = event.getTitle();
        if (newEvent) {
            titleText = "Event Title";
        }
        Font textFont = new Font("Serif", Font.PLAIN, 20);  
        title = new JTextField(titleText);
        title.setBounds(10, 80, 180, 35);  
        title.setFont(textFont);  

        //Date box
        String eventDate = "";
        if (newEvent) {
            eventDate = "mm/dd/yy";
        } else {
            eventDate += Integer.toString(event.getStart().getMonth() + 1);
            eventDate += "/";
            eventDate += Integer.toString(event.getStart().getDate());
            eventDate += "/";
            eventDate += Integer.toString(event.getStart().getYear() + 1900);
        }
        date = new JTextField(eventDate);
        date.setBounds(10, 135, 180, 35);
        date.setFont(textFont);

        //Start Time box
        String startTime = "";
        if (newEvent) {
            startTime = "Start: hh:mm";
        } else {
            startTime = event.getStart().toString().substring(11,16);
        }
        start = new JTextField(startTime);
        start.setBounds(210, 135, 180, 35);
        start.setFont(textFont);

        //End Time box
        String endTime = "";
        if (newEvent) {
            endTime = "End: hh:mm";
        } else {
            endTime = event.getEnd().toString().substring(11,16);
        }
        end = new JTextField(endTime);
        end.setBounds(400, 135, 180, 35);
        end.setFont(textFont);

        //Location box
        String loc = "";
        if (newEvent) {
            loc = "Location";
        } else {
            loc = event.getLocation();
        }
        location = new JTextField(loc);
        location.setBounds(10, 190, 180, 35);
        location.setFont(textFont);

        //Description box
        String desc = "";
        if (newEvent) {
            desc = "Description";
        } else {
            desc = event.getDesc();
        }
        description = new JTextArea(desc);
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

    public EventData getEvent() {
        return event;
    }

    /**actionPerformed
     * Tells the program how to react in the case that one of
     * the buttons is clicked
     * @param e the event of one of the buttons being pressed
     */
    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            //Convert the date into 3 strings
            String day = date.getText();
            String startTime = start.getText();
            String endTime = end.getText();
            EventData nEvent = event;
            if (startTime.charAt(2) != ':' || endTime.charAt(2) != ':') {
                JOptionPane.showMessageDialog(null, "Invalid input");
            }
            try {
                int month = Integer.parseInt(day.substring(0,2));
                int dayNum = Integer.parseInt(day.substring(3,5));
                int year = Integer.parseInt(day.substring(6,10));
                int startHour = Integer.parseInt(startTime.substring(0,2));
                int startMin = Integer.parseInt(startTime.substring(3,5));
                int endHour = Integer.parseInt(endTime.substring(0,2));
                int endMin = Integer.parseInt(endTime.substring(3,5));
                year -= 1900;
                month -= 1;
                nEvent.changeStart(new Date(year, month, dayNum, startHour, startMin));
                nEvent.changeEnd(new Date(year, month, dayNum, endHour, endMin));
                nEvent.changeTitle(title.getText());
                nEvent.changeDesc(description.getText());
                nEvent.changeLocation(location.getText());

                //Store event and dispose
                if (newEvent) {
                    ctrl.add(nEvent);
                } else {
                    ctrl.editEvent(event, nEvent);
                }
                cal.update();
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input");
            }
            
        } else if (e.getSource() == close) {
            if (hasClosed) dispose();
            else {
                JOptionPane.showMessageDialog(null, "I can't let you do that, Dave");
                hasClosed = true;
            }
        }
    }
}
