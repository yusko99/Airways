package bean;

public class Flight {
	
	private int flightId;
	private int routeNumber;
	private String flightDate;
	private int seats;
	
	public int getFlightId(){
		return flightId;
	}
	
	public void setFlightId(int flightId){
		this.flightId= flightId;
	}
	
	
	public int getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(int routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String date) {
		this.flightDate = date;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}


}
