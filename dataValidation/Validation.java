package dataValidation;
import java.util.regex.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Validation {

	public static boolean isValidEmailAddress(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
       
        return matcher.matches();
 }
	
	public static boolean isValidTelNum(String tel){
		String regex = "([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tel);
		
		return matcher.matches();
		
	}
	
	public static boolean isValidTicketId(String ticketId){
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(ticketId);
		
		return matcher.matches();
	}
	

	
	public static boolean validateDates(String departureDate, String returnDate){
		boolean b = false;
	
		//current date string
		String current_Date;
		//date objects 
		DateFormat df;
		Date departure_Date = new Date();
		Date return_Date = new Date();
		Date currentDate = new Date();
		
		try{
			//creating date format 
			df = new SimpleDateFormat("yyyy-mm-dd");
			 
			//formating dates from the JSP
			 departure_Date = df.parse(departureDate);
			 return_Date = df.parse(returnDate);
			
			 //getting current date as a string
			 current_Date= df.format(currentDate);
			
			 //formating the current date 
			 currentDate = df.parse(current_Date);
			
		}catch(ParseException e){
			e.printStackTrace();
		}
		
		//checking dates
		if(!returnDate.isEmpty()){
		if(departure_Date.before(return_Date) && departure_Date.after(currentDate)) b = true;
		}
		else{
			if(departure_Date.after(currentDate)) b = true;
		}
		return b;
	}
	
}
