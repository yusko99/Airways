package toMail;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import bean.*;

public class Mail {

	
	 
	

	//one-way ticket
	public static void sendTicketToMail(String to, Passenger passenger, Flight flight, Route route, int ticketNumber) {
      
		final String USERNAME = "uktc.airlines@gmail.com";
		final String PASSWORD = "Uktc1999";
		
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator(){
			 
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(USERNAME, PASSWORD);};
					});
		
		
		try{
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jusmen.rashidov@gmail.com")); 	// sender's e-mail
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));		// receiver's e-mail
			message.setSubject("UKTC Airlines - Travel ticket");		// subject of the mail
			
			message.setText("UKTC AIRLINES-THE SAFE TRAVEL \n" +
	        		 "Travel ticket \n"+
	        		 "Ticket number: "+ticketNumber+"\n"+
	        		 "Passenger name: "+passenger.getName()+"\n"+
	        		 "Email: "+ passenger.getEmail()+"\n"+
	        		 "Phone number: "+ passenger.getTelNum()+"\n"+
	        		 "Flight: "+ "UA"+ passenger.getFlight_id()+" -"+route.getRouteName()+"\n"+
	        		 "Date: "+ flight.getFlightDate()+"\n"+
	        		 "Departure Time: " + route.getTime()+"\n"+
	        		 "Arriving Time: "+ route.getArriveTime()+"\n"+
	        		 "Seat No.: "+ passenger.getSeatNum()+"\n"+
	        		 "Price: " + passenger.getPrice()+"BGN" + "\n"+
	        		 "Have a nice flight with UKTC Airlines!!!!"
);  // the content of the mail
			
			
				Transport.send(message); // sending the message
			
		}
		catch(MessagingException e){
			e.printStackTrace();
		}
    }
	
	
	//for two-ticket
	public static void sendTicketToMail(String to, Passenger passenger, Flight flight, Route route, Route backRoute, int ticketNumber, String returnDate) {
	      
		final String USERNAME = "uktc.airlines@gmail.com";
		final String PASSWORD = "Uktc1999";
		
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator(){
			 
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(USERNAME, PASSWORD);};
					});
		
		
		try{
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jusmen.rashidov@gmail.com")); 	// sender's e-mail
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));		// receiver's e-mail
			message.setSubject("UKTC Airlines - Travel ticket");		// subject of the mail
			
			message.setText("UKTC AIRLINES-THE SAFE TRAVEL \n" +
	        		 "Travel ticket \n"+
	        		 "Ticket number: "+ticketNumber+"\n"+
	        		 "Passenger name: "+passenger.getName()+"\n"+
	        		 "Email: "+ passenger.getEmail()+"\n"+
	        		 "Phone number: "+ passenger.getTelNum()+"\n"+
	        		 "Flight: "+ "UA"+ passenger.getFlight_id()+" -"+route.getRouteName()+"\n"+
	        		 "Date: "+ flight.getFlightDate()+"\n"+
	        		 "Departure Time: " + route.getTime()+"\n"+
	        		 "Arriving Time: "+ route.getArriveTime()+"\n"+
	        		 "Seat No.: "+ passenger.getSeatNum()+"\n"+"\n"+
	        		 
	        		 "Return flight: "+"UA"+passenger.getBack_flight_id()+"-"+backRoute.getRouteName()+"\n"+
	        		 "Return date: "+returnDate+"\n"+
	        		 "Departure Time: "+ backRoute.getTime()+"\n"+
	        		 "Arrive Time: " + backRoute.getArriveTime()+"\n"+
	        		 "Seat: "+ passenger.getBack_seatNum()+"\n"+
	        		 "Price: " + passenger.getPrice()+"BGN" + "\n"+
	        		 "Have a nice flight with UKTC Airlines!!!!"
);  // the content of the mail
			
			
				Transport.send(message); // sending the message
			
		}
		catch(MessagingException e){
			e.printStackTrace();
		}
    }
	
	
	public static void sendCancelConfirmation(int ticketNum, String to){

		final String USERNAME = "uktc.airlines@gmail.com";
		final String PASSWORD = "Uktc1999";
		
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator(){
			 
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(USERNAME, PASSWORD);};
					});
		
		
		try{
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jusmen.rashidov@gmail.com")); 	// sender's e-mail
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));		// receiver's e-mail
			message.setSubject("UKTC Airlines - Cancel Confirmation");		// subject of the mail
			
			message.setText("UKTC AIRLINES-THE SAFE TRAVEL \n" +
	        		 "Cancel reservation \n"+
	        		 "Your ticket with No."+ticketNum+" has been cancelled successfully." );  // the content of the mail
			
			
				Transport.send(message); // sending the message
			
		}
		catch(MessagingException e){
			e.printStackTrace();
		}
	}
	
}
