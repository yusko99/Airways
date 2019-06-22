package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dataValidation.*; 
import dao.*;
import bean.*;
import toMail.*;

/**
 * Servlet implementation class TicketReserveServlet
 */
public class TicketReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//properties get from the HTML form
		String name = request.getParameter("name");
		String telNum = request.getParameter("telNum");	
		String email = request.getParameter("email");
	    String route = request.getParameter("route");
	    String date = request.getParameter("date");
	    String type = request.getParameter("type");
	    String returnDate = request.getParameter("returnDate");
	   
	    
	    //converting route string from the HTML form to integer
	    int routeNumber = Integer.parseInt(route);
	    
	    
	    //objects
	    Passenger passenger = new Passenger();
	    Flight flight = new Flight();
	    Route thisRoute = new Route();
	 
	    //properties for go flight
	    int flightId;
	    int passengerSeat;
	    int ticketNumber;
	    
	
	    
	    
	    // info validation
	    if(Validation.isValidEmailAddress(email) && Validation.isValidTelNum(telNum) &&
	    	Validation.validateDates(date, returnDate)	){
	    
	    try{
	    	//checking for the flight in DB
	    if(ReserveDAO.flightExists(routeNumber, date)){ 
	    	//checking for free seats
	    	if(ReserveDAO.hasFreeSeats(routeNumber, date)){
	    		
	    		//getting route info from DB
	    		thisRoute = ReserveDAO.getRoute(routeNumber);
	    		
	    		//getting the number of the chosen flight
	    		flightId = ReserveDAO.getFlightID(routeNumber, date);
	    		
	    		// creating flight object
	    		flight.setFlightDate(date);
	    		flight.setRouteNumber(routeNumber);
	    		flight.setFlightId(flightId);
	    		
	    		//passenger's seat
	    		passengerSeat = ReserveDAO.giveSeatNum(flight);
	    		
	    		
	    		// for the two-way ticket
	
	    		if(type.equals("twoWay")){
	    			//checking if return-flight exists
	    			if(ReserveDAO.flightExists(thisRoute.getBackRoute(), returnDate)){
	    				
	    		//getting return-route from DB
	    		Route backRoute = ReserveDAO.getRoute(thisRoute.getBackRoute());
	    				
				//getting return flight from DB
	    		Flight backFlight = ReserveDAO.getFlightFromDB(thisRoute.getBackRoute(), returnDate);
	    				
	 			//return flight's seat from DB
	    		int backFlightSeat = ReserveDAO.giveSeatNum(backFlight);
	    		
	    		//creating passenger
	    		passenger.setName(name);
	    		passenger.setTelNum(telNum);
	    		passenger.setEmail(email);
	    		passenger.setFlight_id(flight.getFlightId());
	    		passenger.setSeatNum(passengerSeat);
	    		passenger.setBack_flight_id(backFlight.getFlightId());
	    		passenger.setBack_seatNum(backFlightSeat);
	    		//setting ticket's price for two-way
	    	    passenger.setPrice(thisRoute.getPrice()*2 -thisRoute.getPrice()*0.1);
	    				
	    	    //putting passenger into DB
	    	    ReserveDAO.putPassengerInDatabase(passenger, flight, backFlight);
	    	    
	    	    //getting ticket's number
	    		ticketNumber = ReserveDAO.getPassengerID(passenger, flight);
	    		
	    		//sending two-way ticket to mail
	    		Mail.sendTicketToMail(email, passenger, flight, thisRoute, backRoute, ticketNumber, returnDate);
	    		
	    		//setting attributes for the JSP page
	    		request.setAttribute("ticketNumber", ticketNumber);
	    		request.setAttribute("name", name);
	    		request.setAttribute("email", email);
	    		request.setAttribute("telNum", telNum);
	    		request.setAttribute("flightNum",flight.getFlightId());
	    		request.setAttribute("routeName", thisRoute.getRouteName());
	    		request.setAttribute("date", date);
	    		request.setAttribute("time", thisRoute.getTime());
	    		request.setAttribute("arriveTime", thisRoute.getArriveTime());
	    		request.setAttribute("seat", passengerSeat);
	    		
	    		request.setAttribute("backFlightNum", backFlight.getFlightId());
	    		request.setAttribute("backRouteName", backRoute.getRouteName());
	    		request.setAttribute("returnDate", returnDate);
	    		request.setAttribute("returnTime", backRoute.getTime());
	    		request.setAttribute("returnArriveTime", backRoute.getArriveTime());
	    		request.setAttribute("returnSeat", passenger.getBack_seatNum());
	    		
	    		request.setAttribute("price", passenger.getPrice());
	    	    
	    		request.getRequestDispatcher("successfulReserve_two_way.jsp").forward(request, response);
	    		
	    			}
	    			else{
	    				//creating return-flight in DB
	    				Flight backFlight = ReserveDAO.createFlight(thisRoute.getBackRoute(), returnDate);
	    				
	    				//getting return-route from DB
	    				Route backRoute = ReserveDAO.getRoute(thisRoute.getBackRoute());
	    						
	    	 			//return flight's seat from DB
	    	    		int backFlightSeat = ReserveDAO.giveSeatNum(backFlight);
	    	    		
	    	    		//creating passenger
	    	    		passenger.setName(name);
	    	    		passenger.setTelNum(telNum);
	    	    		passenger.setEmail(email);
	    	    		passenger.setFlight_id(flight.getFlightId());
	    	    		passenger.setSeatNum(passengerSeat);
	    	    		passenger.setBack_flight_id(backFlight.getFlightId());
	    	    		passenger.setBack_seatNum(backFlightSeat);
	    	    		//setting ticket's price for two-way
	    	    	    passenger.setPrice(thisRoute.getPrice()*2 -thisRoute.getPrice()*0.1);
	    	    				
	    	    	    //putting passenger into DB
	    	    	    ReserveDAO.putPassengerInDatabase(passenger, flight, backFlight);
	    	    	    
	    	    	  //getting ticket's number
	    	    		ticketNumber = ReserveDAO.getPassengerID(passenger, flight);
	    	    		
	    	    		//sending two-way ticket to mail
	    	    		Mail.sendTicketToMail(email, passenger, flight, thisRoute, backRoute, ticketNumber, returnDate);
	    	    		
	    	    		//setting attributes for the JSP page
	    	    		request.setAttribute("ticketNumber", ticketNumber);
	    	    		request.setAttribute("name", name);
	    	    		request.setAttribute("email", email);
	    	    		request.setAttribute("telNum", telNum);
	    	    		request.setAttribute("flightNum",flight.getFlightId());
	    	    		request.setAttribute("routeName", thisRoute.getRouteName());
	    	    		request.setAttribute("date", date);
	    	    		request.setAttribute("time", thisRoute.getTime());
	    	    		request.setAttribute("arriveTime", thisRoute.getArriveTime());
	    	    		request.setAttribute("seat", passengerSeat);
	    	    		
	    	    		request.setAttribute("backFlightNum", backFlight.getFlightId());
	    	    		request.setAttribute("backRouteName", backRoute.getRouteName());
	    	    		request.setAttribute("returnDate", returnDate);
	    	    		request.setAttribute("returnTime", backRoute.getTime());
	    	    		request.setAttribute("returnArriveTime", backRoute.getArriveTime());
	    	    		request.setAttribute("returnSeat", passenger.getBack_seatNum());
	    	    		
	    	    		request.setAttribute("price", passenger.getPrice());
	    	    	    
	    	    		request.getRequestDispatcher("successfulReserve_two_way.jsp").forward(request, response);
	    				
	    			}
	    			
	    		}
	    		//one-way ticket
	    		else{
	    		
	    		
	    		//creating passenger object
	    		passenger.setName(name);
	    		passenger.setTelNum(telNum);
	    		passenger.setEmail(email);
	    		passenger.setFlight_id(flight.getFlightId());
	    		passenger.setSeatNum(passengerSeat);
	    	    passenger.setPrice(thisRoute.getPrice());
	    		
	    		//writing passenger in DB
	    		ReserveDAO.putPassengerInDatabase(passenger, flight);
	    		
	    		//getting ticket's number
	    		ticketNumber = ReserveDAO.getPassengerID(passenger, flight);
	    	
	    		//setting attribute parameters of the successfull page
	    		
	    		request.setAttribute("ticketNumber", ticketNumber);
	    		request.setAttribute("name", name);
	    		request.setAttribute("email", email);
	    		request.setAttribute("telNum", telNum);
	    		request.setAttribute("flightNum",flight.getFlightId());
	    		request.setAttribute("routeName", thisRoute.getRouteName());
	    		request.setAttribute("date", date);
	    		request.setAttribute("seat", passengerSeat);
	    		request.setAttribute("time", thisRoute.getTime());
	    		request.setAttribute("arriveTime", thisRoute.getArriveTime());
	    		request.setAttribute("price", thisRoute.getPrice());
	    		
	    		//show successful reserve page
	    		request.getRequestDispatcher("successfullReserve.jsp").forward(request, response);
	    		
	    		//sending ticket to passenger's e-mail
	    		Mail.sendTicketToMail(email, passenger, flight, thisRoute, ticketNumber);
	    		}
	    	}
	    			
	    	// shows if there's no free seats for the chosen flight
	    			else request.getRequestDispatcher("noSeats.jsp").forward(request, response);
	    	
	    }
	    else{
	    	//creating a flight in the DB
	    	 flight = ReserveDAO.createFlight(routeNumber, date);
	    	 passengerSeat = ReserveDAO.giveSeatNum(flight);
	    	 
	    		//getting route info from DB
	    		thisRoute = ReserveDAO.getRoute(routeNumber);
	    		
	    		
	    		//for the return-flight
	    		if(type.equals("twoWay")){
	    			//checking if return-flight exists
	    			if(ReserveDAO.flightExists(thisRoute.getBackRoute(), returnDate)){
	    				
	    		//getting return-route from DB
	    		Route backRoute = ReserveDAO.getRoute(thisRoute.getBackRoute());
	    				
				//getting return flight from DB
	    		Flight backFlight = ReserveDAO.getFlightFromDB(thisRoute.getBackRoute(), returnDate);
	    				
	 			//return flight's seat from DB
	    		int backFlightSeat = ReserveDAO.giveSeatNum(backFlight);
	    		
	    		//creating passenger
	    		passenger.setName(name);
	    		passenger.setTelNum(telNum);
	    		passenger.setEmail(email);
	    		passenger.setFlight_id(flight.getFlightId());
	    		passenger.setSeatNum(passengerSeat);
	    		passenger.setBack_flight_id(backFlight.getFlightId());
	    		passenger.setBack_seatNum(backFlightSeat);
	    		//setting ticket's price for two-way
	    	    passenger.setPrice(thisRoute.getPrice()*2 -thisRoute.getPrice()*0.1);
	    				
	    	    //putting passenger into DB
	    	    ReserveDAO.putPassengerInDatabase(passenger, flight, backFlight);
	    	    
	    	  //getting ticket's number
	    		ticketNumber = ReserveDAO.getPassengerID(passenger, flight);
	    		
	    		//sending two-way ticket to mail
	    		Mail.sendTicketToMail(email, passenger, flight, thisRoute, backRoute, ticketNumber, returnDate);
	    		
	    		//setting attributes for the JSP page
	    		request.setAttribute("ticketNumber", ticketNumber);
	    		request.setAttribute("name", name);
	    		request.setAttribute("email", email);
	    		request.setAttribute("telNum", telNum);
	    		request.setAttribute("flightNum",flight.getFlightId());
	    		request.setAttribute("routeName", thisRoute.getRouteName());
	    		request.setAttribute("date", date);
	    		request.setAttribute("time", thisRoute.getTime());
	    		request.setAttribute("arriveTime", thisRoute.getArriveTime());
	    		request.setAttribute("seat", passengerSeat);
	    		
	    		request.setAttribute("backFlightNum", backFlight.getFlightId());
	    		request.setAttribute("backRouteName", backRoute.getRouteName());
	    		request.setAttribute("returnDate", returnDate);
	    		request.setAttribute("returnTime", backRoute.getTime());
	    		request.setAttribute("returnArriveTime", backRoute.getArriveTime());
	    		request.setAttribute("returnSeat", passenger.getBack_seatNum());
	    		
	    		request.setAttribute("price", passenger.getPrice());
	    	    
	    		request.getRequestDispatcher("successfulReserve_two_way.jsp").forward(request, response);
	    		
	    			}
	    			else{
	    				//creating return-flight in DB
	    				Flight backFlight = ReserveDAO.createFlight(thisRoute.getBackRoute(), returnDate);
	    				
	    				//getting return-route from DB
	    				Route backRoute = ReserveDAO.getRoute(thisRoute.getBackRoute());
	    						
	    	 			//return flight's seat from DB
	    	    		int backFlightSeat = ReserveDAO.giveSeatNum(backFlight);
	    	    		
	    	    		//creating passenger
	    	    		passenger.setName(name);
	    	    		passenger.setTelNum(telNum);
	    	    		passenger.setEmail(email);
	    	    		passenger.setFlight_id(flight.getFlightId());
	    	    		passenger.setSeatNum(passengerSeat);
	    	    		passenger.setBack_flight_id(backFlight.getFlightId());
	    	    		passenger.setBack_seatNum(backFlightSeat);
	    	    		//setting ticket's price for two-way
	    	    	    passenger.setPrice(thisRoute.getPrice()*2 -thisRoute.getPrice()*0.1);
	    	    				
	    	    	    //putting passenger into DB
	    	    	    ReserveDAO.putPassengerInDatabase(passenger, flight, backFlight);
	    	    	    
	    	    	  //getting ticket's number
	    	    		ticketNumber = ReserveDAO.getPassengerID(passenger, flight);
	    	    		
	    	    		//sending two-way ticket to mail
	    	    		Mail.sendTicketToMail(email, passenger, flight, thisRoute, backRoute, ticketNumber, returnDate);
	    	    		
	    	    		//setting attributes for the JSP page
	    	    		request.setAttribute("ticketNumber", ticketNumber);
	    	    		request.setAttribute("name", name);
	    	    		request.setAttribute("email", email);
	    	    		request.setAttribute("telNum", telNum);
	    	    		request.setAttribute("flightNum",flight.getFlightId());
	    	    		request.setAttribute("routeName", thisRoute.getRouteName());
	    	    		request.setAttribute("date", date);
	    	    		request.setAttribute("time", thisRoute.getTime());
	    	    		request.setAttribute("arriveTime", thisRoute.getArriveTime());
	    	    		request.setAttribute("seat", passengerSeat);
	    	    		
	    	    		request.setAttribute("backFlightNum", backFlight.getFlightId());
	    	    		request.setAttribute("backRouteName", backRoute.getRouteName());
	    	    		request.setAttribute("returnDate", returnDate);
	    	    		request.setAttribute("returnTime", backRoute.getTime());
	    	    		request.setAttribute("returnArriveTime", backRoute.getArriveTime());
	    	    		request.setAttribute("returnSeat", passenger.getBack_seatNum());
	    	    		
	    	    		request.setAttribute("price", passenger.getPrice());
	    	    	    
	    	    		request.getRequestDispatcher("successfulReserve_two_way.jsp").forward(request, response);
	    				
	    			}
	    			
	    		}
	    		//one-way ticket
	    		else{
	    		
	    		//creating passenger object
	                passenger.setName(name);
		    		passenger.setTelNum(telNum);
		    		passenger.setEmail(email);
		    		passenger.setFlight_id(flight.getFlightId());
		    		passenger.setSeatNum(passengerSeat);
		    		passenger.setPrice(thisRoute.getPrice());
	    		
	    		//put passenger into DB
	    		ReserveDAO.putPassengerInDatabase(passenger, flight);
	    		
	    		//getting ticket's number
	    		ticketNumber = ReserveDAO.getPassengerID(passenger, flight);
	    		
	    		//setting atribute parameters of the successfull page
	    		request.setAttribute("ticketNumber", ticketNumber);
	    		request.setAttribute("name", name);
	    		request.setAttribute("email", email);
	    		request.setAttribute("telNum", telNum);
	    		request.setAttribute("flightNum",flight.getFlightId());
	    		request.setAttribute("route", route);
	    		request.setAttribute("date", date);
	    		request.setAttribute("seat", passengerSeat);
	    		request.setAttribute("time", thisRoute.getTime());
	    		request.setAttribute("price", thisRoute.getPrice());
	    		
	    		
	    		//show successful reserve page
	    		request.getRequestDispatcher("successfullReserve.jsp").forward(request, response);
	    		
	    		//sending ticket to passenger's e-mail
	    		Mail.sendTicketToMail(email, passenger, flight, thisRoute, ticketNumber);
	    		}
	    	}
	    
	  }catch(SQLException e){
		  e.printStackTrace();
	  }
	    //shows if client enters invalid mail or phone number
	    } else request.getRequestDispatcher("invalidData.jsp").forward(request, response);
	  
	   
	  /*
	    RequestDispatcher rd= request.getRequestDispatcher("successfullReserve.jsp");
		request.setAttribute("name1", name1 );
		rd.forward(request, response);
		*/
		
	}

}
