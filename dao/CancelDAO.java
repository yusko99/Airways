package dao;


import databaseConnection.*;

import java.sql.*;

public class CancelDAO {
	
	public static final Connection con = Connector.getConnection();

	
	
	public static boolean isTicketExists(int ticketID) throws SQLException{
		
		boolean b = false;
		String querry = "SELECT* FROM airways.passenger WHERE id =?";
		
		PreparedStatement ps = con.prepareStatement(querry);
		ps.setInt(1, ticketID);
	
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()==true) b = true;
		
		return b;
		
	}
	
	
	
	
	public static void deleteReservation(int ticketID) throws SQLException{
	
		String selectQuerry = "SELECT * FROM airways.passenger where airways.passenger.id = ?";
		String deleteQuerry= "DELETE FROM airways.passenger WHERE passenger.id=?";
		String updateQuerry = "UPDATE airways.flight SET seats = seats+1 WHERE id=? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int flightID=0;
		
		//creating statement for finding ticket in DB
		ps = con.prepareStatement(selectQuerry);
		ps.setInt(1, ticketID);
		
		//executing query
		rs = ps.executeQuery();
		
		//getting passenger's flight id if it is in the DB
		if(rs.next()){
			flightID = rs.getInt("flight_id");
		}
	
		//creating statement for deleting ticket from DB
		ps= con.prepareStatement(deleteQuerry);
		ps.setInt(1, ticketID);
		
		//deleting ticket from DB
		ps.executeUpdate();
		
		
		//updating seats for the flight from which was the deleted ticket
		
		
		//creating statement
		ps= con.prepareStatement(updateQuerry);
		ps.setInt(1, flightID);
		
		//updating DB
		ps.executeUpdate();
		
		
	}
	
	public static String getPassengerMail(int ticketID){
		
		String mail= null;
		String querry = "SELECT * FROM airways.passenger where airways.passenger.id = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
				try {
					//creating statement for finding ticket in DB
					ps = con.prepareStatement(querry);
					ps.setInt(1, ticketID);
					
					//executing query
					rs = ps.executeQuery();
					
					//getting passenger's flight id if it is in the DB
					if(rs.next()){
						mail = rs.getString("email");
					}
					
				
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
		return mail;
	}
	
	
}
