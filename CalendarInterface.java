/**CalendarInterface
 * @author Bradley Johns
 * @author Nathan Jackels
 * The general interface of the calendar that shows a month,
 * week, or day view. Comes equipped with a control panel
 * that allows access to other options such as adding or
 * removing events, or going to a specified date.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.Font.*;
import javax.swing.*;
import java.applet.Applet;
import java.awt.Font.*;
import java.util.Date;
import java.awt.geom.Rectangle2D;

// <applet width="1500" height="750" code="CalendarInterface.class"> Applet </applet>

public class CalendarInterface extends Applet implements ActionListener, KeyListener, MouseListener {
    
    private Date selected;
    private int view; //0 for month, 1 for week, 2 for day
    private Image picture;

    private JButton next;
    private JButton last;
    private JButton addEvent;
    private JButton removeEvent;
    private JButton dayView;
    private JButton weekView;
    private JButton monthView;
    private JTextField go;
    private int viewCord = -1; //Chooses the location of monthview button
private Control ctrl;

    /**init
     * First method called when opening calendar application
     * it initializes all global variables, sets bounds on button
     * locations, and adds listeners so the buttons respond properly
     * when pressed
     */
    
    public void init() {
        ctrl = new Control(this);

        selected = new Date();
        view = 0;
        
        picture = getImage(getCodeBase(), "HAL.png");

        int startX = getWidth() / 3;
        int width = ((getWidth() - (getWidth() / 3)) / 7) - 2;
        int height = getHeight() / 10;
        next = new JButton("Next"); 
        next.setBounds(getWidth() - width - 1, 0, width, height);  
        last = new JButton("Last");
        last.setBounds(startX + 1, 0, width, height);
        addEvent = new JButton("Add Event");
        addEvent.setBounds(getWidth() - (2 * width) - 3, 0, width, height);
        removeEvent = new JButton("Remove Event");
        removeEvent.setBounds(startX + width + 3, 0, width, height);
        dayView = new JButton("Day View");
        dayView.setBounds(getWidth() - (3 * width) - 5, 0, width, height);
        weekView = new JButton("Week View");
        weekView.setBounds(startX + (2 * width) + 5, 0, width, height);
        monthView = new JButton("Month View");
        go = new JTextField("mm/dd/yyyy");
        go.setBounds(startX + (3 * width) + 7, getHeight() / 20, width + 5, ((getHeight() / 7)) / 3); 
        go.setHorizontalAlignment(JTextField.CENTER);

        next.addActionListener(this);
        last.addActionListener(this);
        addEvent.addActionListener(this);
        removeEvent.addActionListener(this);
        monthView.addActionListener(this);
        weekView.addActionListener(this);
        dayView.addActionListener(this);
        go.addKeyListener(this);
        addMouseListener(this);
    }

    /**paint
     * Draws the interface itself, uses helper methods of
     * drawMonth, drawWeek, and drawYear depending on which
     * view the calendar is set to and paints buttons, lines
     * and image of HAL on the screen that are independant of
     * any given view
     * @param g the graphics variable that is used to draw the applet
     */

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        setBackground(Color.BLACK);
        g.setColor(Color.CYAN);

        int width = (2 * (getWidth() / 3)) / 7;

        if (view == 1) {
            viewCord = (getWidth() / 3) + (2 * width) + 5;
        } else if (view == 2) {
            viewCord = getWidth() - (3 * width) - 5;
        } else {
            viewCord = -1;
        }

        //Start by separating some reigons
        g.drawLine((getWidth() / 3), 0, (getWidth() / 3), getHeight());
        g.drawLine((getWidth() / 3), (getHeight() / 10), getWidth(), (getHeight() / 10));
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());

        FontMetrics metrics = g.getFontMetrics(); 

        if (view == 0) { //Month view display
            drawMonth(g);
        } else if (view == 1) { //Week view display
            drawWeek(g);
        } else {
            drawDay(g);
        }

        if (viewCord != -1) monthView.setBounds(viewCord, 0, width, getHeight() / 10);

        add(last);
        add(next);
        if (view != 0) add(monthView);
        if (view != 1) add(weekView);
        if (view != 2) add(dayView);
        add(addEvent);
        add(removeEvent);
        add(go);
         
        g.setColor(Color.WHITE); 
        g.setFont(new Font("Serif", Font.BOLD, 20)); 
        metrics = g.getFontMetrics(); 
        Rectangle2D text = metrics.getStringBounds("Goto", g); 
        int textX = (getWidth() / 3) + ((getWidth() - (getWidth() / 3)) / 2)
                    - (int)(text.getWidth() / 2); 
        int textY = ((getHeight() / 10) / 4) + (int)(text.getHeight() / 2); 
        g.drawString("Goto", textX, textY); 

        g.drawImage(picture, 5, 5, (getWidth() / 3) - 5, (getWidth() / 3) - 5, this); 
    }

    /**drawDay
     * helper method to paint, uses the same Graphics object used
     * in the paint method to draw the view-specific graphics for
     * when the calendar has been set to day-view
     * @param g the graphics object to draw the view
     */

    private void drawDay(Graphics g) {
        FontMetrics metrics = g.getFontMetrics();
        String date = selected.toString().substring(0, 10);
        date += selected.toString().substring(23, 28);

        //Highlight the day
        g.setColor(new Color(0x660000));
        g.fillRect((getWidth() / 3) + 1, (getHeight() / 10) + 1, 2 * (getWidth() / 3) - 2, ((getHeight() / 10) * 9) - 2);

        //Draw the day-specific text
        g.setColor(Color.WHITE);
        Rectangle2D textBlock = metrics.getStringBounds(date, g);
        g.drawString(date, (getWidth() / 3) + 4,
                    ((getHeight() / 10) + 1) + (int)textBlock.getHeight());
    }

    /**drawWeek
     * helper method to paint, uses the same Graphics object used
     * in the paint method to draw the view-specific graphics for
     * when the calendar has been set to week-view
     * @param g the graphics object to draw the view
     */
    @SuppressWarnings("deprecation")
    private void drawWeek(Graphics g) {
        FontMetrics metrics = g.getFontMetrics();
        int mid = 2 * (getWidth() / 3);
        g.drawLine(mid , getHeight() / 10, mid, getHeight());
        int yCord = (getHeight() / 10) + ((getHeight() - (getHeight() / 10)) / 3);
        g.drawLine(getWidth() / 3, yCord, getWidth(), yCord);
        yCord = (getHeight() / 10) + (2 * (getHeight() - (getHeight() / 10)) / 3);
        g.drawLine(getWidth() / 3, yCord, getWidth(), yCord);
        yCord += (getHeight() - (getHeight() / 10)) / 6;
        g.drawLine(mid, yCord, getWidth(), yCord);

        //Highlight the Selected Day
        g.setColor(new Color(0x660000));
        int width = ((2 * (getWidth() / 3)) / 2) - 3;
        if (selected.getDay() < 5) {
            int highlightX = (getWidth() / 3) + 1 + (497 * (selected.getDay() % 2));
            int highlightY = 79 + (224 * (selected.getDay() / 2));
            g.fillRect(highlightX, highlightY, 495, 222);
        } else {
            int highlightX = 1004;
            int highlightY = 527;
            if (selected.getDay() == 6) highlightY += 112;
            g.fillRect(highlightX, highlightY, 495, 110);
        }

        //Draw day-specific text
        g.setColor(Color.WHITE);
        Date current = selected;
        while (current.getDay() != 0) current = getLast(current);
        for (int i = 0; i < 7; i++) {
            String date = current.toString().substring(0, 10);
            date += current.toString().substring(23, 28);
            Rectangle2D textBlock = metrics.getStringBounds(date, g);
            int textX = 510 + (497 * (i % 2));
            int textY = 79 + (224 * (i / 2));
            if (i == 6) {
                textX += 497;
                textY -= 112;
            }
            textY += textBlock.getHeight();
            g.drawString(date, textX, textY);
            current = getNext(current);
        }
    }

    /**drawMonth
     * helper method to paint, uses the same Graphics object used
     * in the paint method to draw the view-specific graphics for
     * when the calendar has been set to month-view
     * @param g the graphics object to draw the view
     */
    @SuppressWarnings("deprecation")
    private void drawMonth(Graphics g) {
        FontMetrics metrics = g.getFontMetrics();
	    int startX = 506; //Coordinates decided for even 7x7 grid
        int startY =  78;
        for (int i = 0; i < 6; i++) {
            int currentX = startX + (142 * (i + 1));
            int currentY = startY + (96 * (i + 1));
            g.drawLine(startX, currentY, 1500, currentY);
            g.drawLine(currentX, 126, currentX, 750);
        }
        g.drawLine(startX, 126, 1500, 126);

        //Highlight Selected Day
        g.setColor(new Color(0x660000));
        Date first = new Date(selected.getYear(), selected.getMonth(), 1);
        int highlightX = 507 + (142 * selected.getDay());
        int highlightY = 175 + (getRow(selected, first.getDay()) * 96);
        g.fillRect(highlightX, highlightY, 141, 95);

        //Draw day numbers
        g.setColor(Color.WHITE);
        Date current = new Date(selected.getYear(), selected.getMonth(), 1);
        while (current.getMonth() == selected.getMonth()) {
            Rectangle2D textBlock = metrics.getStringBounds(Integer.toString(current.getDate() + 1), g);
            first = new Date(current.getYear(), current.getMonth(), 1);
            int textX = 510 + (142 * current.getDay());
            int textY = 175 + (getRow(current, first.getDay()) * 96);
            textY += textBlock.getHeight();
            g.drawString(Integer.toString(current.getDate()), textX, textY);
            current = getNext(current);
        }

        //Draw DotW text
        g.setFont(new Font("Serif", Font.BOLD, 20));
        String day = "";
        metrics = g.getFontMetrics();
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    day = "Sunday";
                    break;
                case 1:
                    day = "Monday";
                    break;
                case 2:
                    day = "Tuesday";
                    break;
                case 3:
                    day = "Wednesday";
                    break;
                case 4:
                    day = "Thursday";
                    break;
                case 5:
                    day = "Friday";
                    break;
                case 6:
                    day = "Saturday";
                    break;
            }
            Rectangle2D textBlock = metrics.getStringBounds(day, g);
            int textX = 577 + (142 * i) - (int)(textBlock.getWidth() / 2);
            int textY = 150 + (int)(textBlock.getHeight() / 2);
            g.drawString(day, textX, textY);
        }

        //Draw Month/Year text
        String month = "";
        switch (selected.getMonth() + 1) {
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
        }
        month += ", " + Integer.toString(selected.getYear() + 1900);
        Rectangle2D textBlock = metrics.getStringBounds(month, g);
        int textX = 1003 - (int)(textBlock.getWidth() / 2);
        int textY = 102 + (int)(textBlock.getHeight() / 2);
        g.drawString(month, textX, textY);
    }

    /**getRow
     * A simple formula method to find the specific row that a date belongs
     * in for the month view of the calendar
     * @param day the date to find the row of
     * @param firstOfMonth the day of the week value of the first day of the month
     * @return the integer representation (0 indexed) of what row the day goes in
     */
    @SuppressWarnings("deprecation")
    private static int getRow(Date day, int firstOfMonth) {
        return (((firstOfMonth - 1) + day.getDate()) / 7);
    }

    /**getNext
     * Returns the day following the day given
     * @param day the day before what is returned
     * @return the day after the given parameter
     */
    @SuppressWarnings("deprecation")
    private static Date getNext(Date day) {
        int dayNum = day.getDate();
        int month = day.getMonth();
        int year = day.getYear();
        int maxDays = 31;
        //Change for months with < 31 days
        if (month == 3 || month == 5 || month == 8 || month == 10) maxDays = 30;
        else if (month == 1) maxDays = 28;

        //Check for month/year overflow
        if (dayNum + 1 > maxDays) {
            dayNum = 1;
            if (month + 1 > 11) {
                month = 0;
                year++;
            } else {
                month++;
            }
        } else {
            dayNum++;
        }
        return new Date(year, month, dayNum);
    }

    /**getLast
     * Returns the day before the day given
     * @param day the day after what is returned
     * @return the day before the given parameter
     */
    @SuppressWarnings("deprecation")
    private static Date getLast(Date day) {
        int dayNum = day.getDate();
        int month = day.getMonth();
        int year = day.getYear();
        int maxDays = 31; //For the previous month
        //Change for months with < 31 days
        if (month == 4 || month == 6 || month == 9 || month == 11) maxDays = 30;
        else if (month == 2) maxDays = 28;

        //Check for month/year overflow
        if (dayNum - 1 < 1) {
            dayNum = maxDays;
            if (month < 1) {
                month = 11;
                year--;
            } else {
                month--;
            }
        } else {
            dayNum--;
        }
        return new Date(year, month, dayNum);
    }

    public int getViewStyle() {
        return view;
    }

    public void changeViewStyle(int newView) {
        view = newView;
    }

    /**actionPerformed
     * Handles all cases when one of the buttons used to change
     * the interface of the calendar is pressed
     * @param e the event object containing information on what occurred
     */
    @SuppressWarnings("deprecation")
    public void actionPerformed(ActionEvent e) {
        int currentMonth = selected.getMonth();
        if (e.getSource() == next) { //Goto next view
            if (view == 0) {
                while (selected.getMonth() == currentMonth) {
                    selected = getNext(selected);
                }
            } else if (view == 1) {
                selected = getNext(selected);
                while (selected.getDay() != 0) {
                    selected = getNext(selected);
                }
            } else if (view == 2) {
                selected = getNext(selected);
            }
        } else if (e.getSource() == last) { //Goto previous view
            if (view == 0) {
                while (selected.getMonth() == currentMonth) {
                    selected = getLast(selected);
                }
            } else if (view == 1) {
                selected = getLast(selected);
                while (selected.getDay() != 6) {
                    selected = getLast(selected);
                }
            } else if (view == 2) {
                selected = getLast(selected);
            }
        } else if (e.getSource() == weekView) { //Goto week view
            view = 1;
            remove(weekView);
        } else if (e.getSource() == dayView) { //Goto day view
            view = 2;
            remove(dayView);
        } else if (e.getSource() == monthView) { //Goto month view
            view = 0;
            remove(monthView);
        } else if (e.getSource() == addEvent) { //Goto the addEvent page
            EventInterface iface = new EventInterface(ctrl);
            iface.init();
            iface.setVisible(true);
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) { }

    /**keyPressed
     * Handles the interface changed when the correct key is pressed in
     * the goto text box
     * @param e the event object containing information on what occurred
     */
    @SuppressWarnings("deprecation")
    public void keyPressed(KeyEvent e) { 
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String date = go.getText();
            if (date.charAt(2) != '/' || date.charAt(5) != '/') {
                JOptionPane.showMessageDialog(null, "Invalid input");
            } else {
                try {
                    int month = Integer.parseInt(date.substring(0,2));
                    int day = Integer.parseInt(date.substring(3,5));
                    int year = Integer.parseInt(date.substring(6,10));
                    month--;
                    year -= 1900;
                    selected = new Date(year, month, day);
                    repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                }
            }
        }
    }
    public void keyTyped(KeyEvent e) { }

    @SuppressWarnings("deprecation")
    public void mouseClicked(MouseEvent e) {
        int xCord = e.getX();
        int yCord = e.getY();
        if (view == 0) {
            if (xCord > 506 && yCord > 174) {
                int first = (new Date(selected.getYear(), selected.getMonth(), 1)).getDay();
                int row = (yCord - 175) / 96;
                int col = (xCord - 506) / 142;
                int day = 1 + (7 * row);
                day += (col - first);
                Date newSelected = new Date(selected.getYear(), selected.getMonth(), day);
                if (newSelected.getMonth() == selected.getMonth()) {
                    selected = new Date(selected.getYear(), selected.getMonth(), day);
                }
                repaint();
            }
        }
    }

    public void mouseExited(MouseEvent e) { }

    public void mouseEntered(MouseEvent e) { }

    public void mousePressed(MouseEvent e) { }

    public void mouseReleased(MouseEvent e) { }
}
