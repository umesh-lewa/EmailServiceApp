package com.emailserviceapp.springboot.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

class EmailEngine {
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("application");
	
  static void generateAndSendEmail(String host, String port, String user, String password,
                                          String subject, String mailContents, String to) throws MessagingException {
           
	System.out.println("Inside EmailEngine....!!!");  
    Properties mailServerProperties;
    //Session getMailSession;
    //MimeMessage generateMailMessage;
    
    // Setup Server Properties
    mailServerProperties = System.getProperties();
    mailServerProperties.put("mail.smtp.port", port);
    mailServerProperties.put("mail.smtp.auth", "true");
    mailServerProperties.put("mail.smtp.starttls.enable", "true");
        
    // Setup mail session
    //getMailSession = Session.getDefaultInstance(mailServerProperties, null);
    
    Session getMailSession = Session.getInstance(mailServerProperties,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
    
    MimeMessage generateMailMessage = new MimeMessage(getMailSession);  
    generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    generateMailMessage.setSubject(subject);
    generateMailMessage.setContent(mailContents, "text/html");
    
    // Send Email
    Transport transport = getMailSession.getTransport("smtp");
       
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	System.out.println("Email sent at :"+dateFormat.format(date));
	
    boolean isSuccess=true; 
    try {
    	 // email and password goes here
        transport.connect(host, user, password);
    	transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
    } catch(Exception e) {
    	e.printStackTrace();
    	isSuccess=false;
    } finally {
    	transport.close();
    }
    
    System.out.println("Email sent status :"+isSuccess);
    
    //insert results into DB

    //String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL = bundle.getString("clearDB.URL");
    
    //  Database credentials
    String USER = bundle.getString("clearDB.USER");
    String PASS = bundle.getString("clearDB.PASSWORD");
    
    Connection conn = null;
    Statement stmt = null;
    
    try {

        //Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Connecting to ClearDB database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected to ClearDB successfully...");
        
        System.out.println("Inserting records into the table...");
        stmt = conn.createStatement();
        
        String sql = "INSERT INTO Email_Results" +
                     " VALUES ('"+to+"','"+dateFormat.format(date)+"',"+isSuccess+");";
        //String sql = String.format("INSERT INTO Email_Results VALUES ('",to,"',",dateFormat.format(date),",",isSuccess,")");
        System.out.println(sql);
        stmt.executeUpdate(sql);
        
        System.out.println("Inserted records into the table...");
    	
    } catch(Exception e) {
    	e.printStackTrace();
    } finally {
    	try{
            if(stmt!=null)
               conn.close();
            if(conn!=null)
               conn.close();
         }catch(Exception e){
            e.printStackTrace();
         }
      }      
  }
}