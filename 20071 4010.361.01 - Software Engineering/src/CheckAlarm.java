/**
 * Checks whether it's the time to popup alarm window and send an email 
 * 
 * @author vxj4346
 *
 */

import java.util.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class CheckAlarm extends Thread {
    private GUI myGui;
    private int minBefore;
    private String sender;
    private String senderPassword;
    private String SMTPServer;
    private String port;
    private String rcpt;
    
    //"secalendar2@gmail.com", "password2" 
    public CheckAlarm( GUI gui, int minB, String from, String pass, 
            String smtp, String p, String r ) {
        sender = from;
        senderPassword = pass;
        SMTPServer = smtp;
        port = p;
        rcpt = r;
        myGui = gui;
        minBefore = minB;
    }
    
    static class PopupAuthenticator extends Authenticator {
        String from;
        String pass;
        
        public PopupAuthenticator(String from, String pass){
            this.from = from;
            this.pass = pass;
        }
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, pass);
        }
    }
    
    
    // "smtp.gmail.com" "587"
    public void postMail( String recipient, String subject, 
            String message, String from, String smtp, String port) throws MessagingException {
        boolean debug = false;
        
        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp);
        // use authentication
        props.put("mail.smtp.auth", "true");
        // tls
        props.put("mail.smtp.starttls.enable","true");
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        
        props.put("mail.smtp.port", port);
        
        Authenticator auth = new PopupAuthenticator(sender, senderPassword);
        // create some properties and get the default Session
        Session session = Session.getInstance(props, auth);
        session.setDebug(debug);

        // create a message
        Message msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);

        InternetAddress addressTo = new InternetAddress(recipient);
        InternetAddress[] addressToArray = new InternetAddress[1];
        addressToArray[0]=addressTo;
        msg.setRecipients(Message.RecipientType.TO, addressToArray);
       

        // Optional : You can also set your custom headers in the Email if you Want
        msg.addHeader("X-Email Alarm", "email Alarm");

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        
        Transport.send(msg);
    }   
    
    
    @Override
    public void run() {
        ArrayList<Appointment> apps;
        long currentTime;
        Date currentDate; 
        long hours; 
        long minutes;
        long h;
        long m;
        long startTime;
        Appointment a;
        while (true) {
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
            }
            currentTime = System.currentTimeMillis();
            currentDate = new Date(currentTime);
            hours = currentDate.getHours();
            minutes = currentDate.getMinutes();
            apps = myGui.getAlarmAppointments();
            for (int i = 0; i < apps.size(); i++ ) {
                a = apps.get(i);
                startTime = a.getStartTime();
                h = startTime / 100;
                m = startTime % 100;
                m -= minBefore;
                if (!a.getAlarmed() && h == hours && m == minutes) {
                    if (a.getEmailAlarm()) {
                        try {
                            this.postMail( rcpt, ("You have an appointment "+a.getTitle()+" at " + 
                                a.getStartTime()), a.toString(), sender,
                                SMTPServer, port);
                        } catch (MessagingException ex) { }
                        a.setAlarmed(true);
                    }
                    if (a.getVisibleAlarm()) {
                        //default title and icon
                        JOptionPane.showMessageDialog(null,
                            "You have a(n) " + a.getTitle()+ " in " + minBefore + " minutes.", 
                            "ALARM",JOptionPane.INFORMATION_MESSAGE);
                        a.setAlarmed(true);
                    }
                }
            }   
        }
    }

}
