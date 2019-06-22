package dao;
import databaseConnection.*;
import java.sql.*;
import bean.*;

public class ReserveDAO {
	
	// connection to the DB
	static final Connection con= Connector.getConnection();
	
	
	public static boolean flightExists(int routeNumber, String date) throws SQLException{
		
		
		String sql = "SELECT* FROM airways.Flight where routeNumber=? and flightDate=?";
		ResultSet rs =null;
		PreparedStatement ps = null;
		
		boolean b= false;
		
		try{
		 ps = con.prepareStatement(sql);
		
		ps.setInt(1, routeNumber);
		ps.setString(2, date);
		
		
		rs = ps.executeQuery();
		
		if(rs.next() == true){
			b= true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(ps!=null){
				ps.close();
			}
			
		}
		
		
		return b;
		
	}

	public static Flight createFlight(int routeNumber, String date) throws SQLException{
		
		Flight flight = new Flight();
		int id = 0;
		
		
		PreparedStatement psInsert = null;
		PreparedStatement psSelect = null;
		ResultSet rs = null;
		
		String insert = "INSERT INTO airways.Flight (routeNumber, flightDate, seats) VALUES (?, ?, 200)";
		String select = "SELECT * FROM airways.flight where routeNumber= ? and flightDate= ?";
		
		try{
		// inserting flight in DB
		psInsert = con.prepareStatement(insert);
		
		psInsert.setInt(1, routeNumber);
		psInsert.setString(2, date);
		
		psInsert.executeUpdate();
		
		
		//getting flight's number by its ID in the DB
		psSelect = con.prepareStatement(select);
		
		psSelect.setInt(1, routeNumber);
		psSelect.setString(2, date);
		
		rs = psSelect.executeQuery();
		
		while(rs.next()){
			id = rs.getInt("id");
		}
		
		
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
		// setting flights' properties
		
		flight.setRouteNumber(routeNumber);
		flight.setFlightDate(date);
		flight.setSeats(200);
		flight.setFlightId(id);
		return flight;
	}
  
	public static boolean hasFreeSeats(int routeNumber, String date) throws SQLException{
		
		int i=0;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM airways.Flight where routeNumber=? and flightDate=?";
		
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, routeNumber);
			ps.setString(2, date);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				i = rs.getInt("seats");
			}
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			if(ps!=null){
				ps.close();
			}
		}
		
		
		if(i!=0) return true;
		else return false;
		
		
	}

	public static int getFlightID(int routeNumber, String date) throws SQLException
	{
		int id=0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM airways.Flight where routeNumber=? and flightDate=?";
		
		try{
			ps = con.prepareStatement(sql);
			ps.setInt(1, routeNumber);
			ps.setString(2, date);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				id = rs.getInt("id");
			}
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			if(ps!=null){
				ps.close();
			}
			
		}
		
		return id;
	}
	
	public static int giveSeatNum(Flight flight) throws SQLException
	{
		int seatNum=0;
	
		PreparedStatement psSelect = null;
		PreparedStatement psUpdate = null;
		ResultSet rs = null;

		
		String select = "SELECT * FROM airways.Flight where routeNumber=? and flightDate=?";
		String update ="update airways.flight set seats = seats-1 WHERE routeNumber = ? and flightDate=?";
		
		try{
			// Getting the number of the seat
			psSelect = con.prepareStatement(select);
			psSelect.setInt(1, flight.getRouteNumber());
			psSelect.setString(2, flight.getFlightDate());
			
			rs = psSelect.executeQuery();
			
			while(rs.next()){
				seatNum = rs.getInt("seats");
			}
			
			//updating number of seats for the flight
			psUpdate = con.prepareStatement(update);
			psUpdate.setInt(1, flight.getRouteNumber());
			psUpdate.setString(2, flight.getFlightDate());
			
			psUpdate.executeUpdate();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally{
			// closing the conection and statements
			if(psSelect!=null){
				psSelect.close();
			}
			if(psUpdate !=null){
				psUpdate.close();
			}
		}
		
		return seatNum;
		
	}

	//one-way passenger
	public static void putPassengerInDatabase(Passenger passenger, Flight flight) throws SQLException
	{
		PreparedStatement ps = null;
		String sql = "INSERT INTO airways.Passenger (name, telNum, email, flight_id, seatNum, price) VALUES (?, ?, ?, ?, ?, ?)";
		
		try{
		 ps = con.prepareStatement(sql);
		
		ps.setString(1, passenger.getName());
		ps.setString(2, passenger.getTelNum());
		ps.setString(3, passenger.getEmail());
		ps.setInt(4, flight.getFlightId());
		ps.setInt(5, passenger.getSeatNum());
		ps.setDouble(6, passenger.getPrice());
		
		ps.executeUpdate();
		
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if(ps!=null)
			{
				ps.close();
			}
		}
		
	}
	
	//two-way passenger
	public static void putPassengerInDatabase(Passenger passenger, Flight flight, Flight backFlight) throws SQLException
	{
		PreparedStatement ps = null;
		String sql = "INSERT INTO airways.Passenger (name, telNum, email, flight_id, seatNum, back_flight_id, back_seatNum, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
		 ps = con.prepareStatement(sql);
		
		ps.setString(1, passenger.getName());
		ps.setString(2, passenger.getTelNum());
		ps.setString(3, passenger.getEmail());
		ps.setInt(4, flight.getFlightId());
		ps.setInt(5, passenger.getSeatNum());
		ps.setInt(6, backFlight.getFlightId());
		ps.setInt(7, passenger.getBack_seatNum());
		ps.setDouble(8, passenger.getPrice());
		ps.executeUpdate();
		
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			if(ps!=null)
			{
				ps.close();
			}
		}
		
	}

	
	
	public static Route getRoute(int routeNumber) throws SQLException{
		
		//declaring route object
		Route route = new Route();
		
		//route's properties to be set
		String time= null;
		String arriveTime = null;
		String routeName= null;
		int backRoute = 0;
		double price =0;
		
		//Connection properties
		String sql = "SELECT* FROM airways.route WHERE id = ?";
		ResultSet rs =null;
		PreparedStatement ps = null;
		
		
		ps = con.prepareStatement(sql);
		ps.setInt(1, routeNumber);
		
		
		rs = ps.executeQuery();
		
		while(rs.next()){
			
			 time = rs.getString("travelTime");
			 price = rs.getDouble("price");
			 arriveTime = rs.getString("arriveTime");
			 routeName = rs.getString("name");
			 backRoute = rs.getInt("backRoute");
		}
		
		//setting object fields
		route.setRouteID(routeNumber);
		route.setRouteName(routeName);
		route.setTime(time);
		route.setArriveTime(arriveTime);
		route.setPrice(price);
		route.setBackRoute(backRoute);
		
		return route;
		
	}
	
	public static int getPassengerID(Passenger passenger, Flight flight) throws SQLException{
		
		int ticketID=0;
		String querry="SELECT * FROM airways.passenger WHERE name = ? AND flight_id =?;";
		ResultSet rs= null;
		PreparedStatement ps=null;
		
		ps=con.prepareStatement(querry);
		ps.setString(1, passenger.getName());
		ps.setInt(2, flight.getFlightId());
		
		rs= ps.executeQuery();
		
		while(rs.next()){
			ticketID = rs.getInt("id");
		}
		
		return ticketID;
	}

	public static Flight getFlightFromDB(int routeNumber, String date) throws SQLException{
		Flight flight = new Flight();
		
		//temps for flight properties
		int id=0;
		int route=0;
		String flightDate = null;
		int seats =0;
		
		
		PreparedStatement ps= null;
		ResultSet rs = null;
		String sql = "SELECT* FROM airways.flight WHERE routeNumber=? AND flightDate=?";
		
		try{
			//creating statement
			ps = con.prepareStatement(sql);
			
			//setting statement properties
			ps.setInt(1, routeNumber);
			ps.setString(2, date);
			
			//executing statement
			rs= ps.executeQuery();
			
			//getting result
			while(rs.next()){
				id = rs.getInt("id");
				route = rs.getInt("routeNumber");
				flightDate = rs.getString("flightDate");
				seats = rs.getInt("seats");
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		//setting object fields
		flight.setFlightId(id);
		flight.setRouteNumber(route);
		flight.setSeats(seats);
		flight.setFlightDate(flightDate);
		
		return flight;
	}
}







