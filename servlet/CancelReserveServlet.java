package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import toMail.*;
import dao.CancelDAO;
import dataValidation.*;

/**
 * Servlet implementation class CancelReserveServlet
 */
public class CancelReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String ticketNumber= request.getParameter("ticketNumber");
		
		int ticketID=0;
		
		
		// checking if client entered a correct number
		if(Validation.isValidTicketId(ticketNumber)){
			ticketID = Integer.parseInt(ticketNumber);
		}
		else{
			request.getRequestDispatcher("invalidTicketNumber.jsp").forward(request, response);
		}
		
		//checking if there is a ticket with this number in DB
		
		try{
		if(CancelDAO.isTicketExists(ticketID)){
			
			//getting the email of the passenger
			String mail = CancelDAO.getPassengerMail(ticketID);
			
			//deleting ticket
			CancelDAO.deleteReservation(ticketID);
			
			//sending confirmation to mail
			Mail.sendCancelConfirmation(ticketID, mail);
			
			// jsp that confirms for successffull canceling the reservation
			request.getRequestDispatcher("successfulCancel.jsp").forward(request, response);
		}
		else {
			// jsp showing "can not find ticket with such number"
			request.getRequestDispatcher("unexictingTicket.jsp").forward(request, response);
			
		}
		
		
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
