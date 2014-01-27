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

public class CalendarInterface extends Applet {
    
    private Date selected;
    private Date[] month;
    private Date[] week;
    private int view; //0 for month, 1 for week, 2 for day

    private JButton next;
    private JButton last;
    private JButton addEvent;
    private JButton removeEvent;
    private JButton dayView;
    private JButton weekView;
    private JTextField go;
    
    Image picture;
    
    public void init() {
        selected = new Date();
        month = new Date[31];
        week = new Date[7];
        view = 0;
        

        picture = getImage(getCodeBase(), "HAL.png");

        //Fill the month array
        Date current = new Date(selected.getYear(), selected.getMonth(), 1);
        while (getNext(current).getMonth() == selected.getMonth()) {
            month[current.getDate() - 1] = current;
            current = getNext(current);
        }

        //Fill out the month array
        week[selected.getDay()] = selected;
        current = getLast(selected);
        for (int i = selected.getDay() - 1; i >= 0; i--) {
            week[i] = current;
            current = getLast(current);
        }
        current = getNext(selected);
        for (int i = selected.getDay() + 1; i < 7; i++) {
            week[i] = current;
            current = getNext(current);
        }
        
        next = new JButton("Next"); 
        next.setBounds(1359, 0, 140, 76);  
        last = new JButton("Last");
        last.setBounds(507, 0, 140, 76);
        addEvent = new JButton("Add Event");
        addEvent.setBounds(1217, 0, 140, 76);
        removeEvent = new JButton("Remove Event");
        removeEvent.setBounds(649, 0, 140, 76);
        dayView = new JButton("Day View");
        dayView.setBounds(1075, 0, 140, 76);
        weekView = new JButton("Week View");
        weekView.setBounds(791, 0, 140, 76);
        go = new JTextField("mm/dd/yyyy");
        go.setBounds(933, 46, 140, 30); 
        go.setHorizontalAlignment(JTextField.CENTER);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        setBackground(Color.BLACK);
        g.setColor(Color.CYAN);

        //Start by separating some reigons
        g.drawLine(506, 0, 506, 750);
        g.drawLine(506, 78, 1500, 78);
        g.drawLine(0, 749, 1500, 749);
        g.drawLine(1499, 0, 1499, 750);

        FontMetrics metrics = g.getFontMetrics(); 

        if (view == 0) { //Month view display
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
            int highlightX = 507 + (142 * selected.getDay());
            int highlightY = 175 + (((selected.getDate() + (6 - selected.getDay())) / 7) * 96);
            g.fillRect(highlightX, highlightY, 141, 95);

            //Draw day numbers
            g.setColor(Color.WHITE);
            Date current = month[0];
            while (current.getMonth() == selected.getMonth()) {
                Rectangle2D textBlock = metrics.getStringBounds(Integer.toString(current.getDate() + 1), g);
                int textX = 510 + (142 * current.getDay());
                int textY = 175 + (((current.getDate() + (6 - current.getDay())) / 7) * 96);
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
        } else if (view == 1) { //Week view display
            g.drawLine(1003, 78, 1003, 750);
            g.drawLine(506, 302, 1500, 302);
            g.drawLine(506, 526, 1500, 526);
            g.drawLine(1003, 638, 1500, 638);
        }

        add(last);
        add(next);
        add(weekView);
        add(dayView);
        add(addEvent);
        add(removeEvent);
        add(go);
         
        g.setColor(Color.WHITE); 
        g.setFont(new Font("Serif", Font.BOLD, 20)); 
        metrics = g.getFontMetrics(); 
        Rectangle2D text = metrics.getStringBounds("Goto", g); 
        int textX = 1003 - (int)(text.getWidth() / 2); 
        int textY = 23 + (int)(text.getHeight() / 2); 
        g.drawString("Goto", textX, textY); 

        g.drawImage(picture, 5, 5, 495, 495, this); 
    }

    //public void update(Graphics g) { }

    private static Date getNext(Date day) {
        int dayNum = day.getDate();
        int month = day.getMonth();
        int year = day.getYear();
        int maxDays = 31;
        //Change for months with < 31 days
        if (month == 4 || month == 6 || month == 9 || month == 11) maxDays = 30;
        else if (month == 2) maxDays = 28;

        //Check for month/year overflow
        if (dayNum + 1 > maxDays) {
            dayNum = 1;
            if (month + 1 > 12) {
                month = 1;
                year++;
            } else {
                month++;
            }
        } else {
            dayNum++;
        }
        return new Date(year, month, dayNum);
    }

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
            if (month - 1 < 1) {
                month = 12;
                year--;
            } else {
                month--;
            }
        } else {
            dayNum--;
        }
        return new Date(year, month, dayNum);
    }
}
