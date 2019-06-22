package bean;

public class Passenger {
	private int passengerID;
	private String name;
	private String telNum;
	private String email;
	private int flight_id;
	private int seatNum;
	private int back_flight_id;
	private int back_seatNum;
	private double price;
	
	public int getPassengerID() {
		return passengerID;
	}
	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public int getBack_flight_id() {
		return back_flight_id;
	}
	public void setBack_flight_id(int back_flight_id) {
		this.back_flight_id = back_flight_id;
	}
	public int getBack_seatNum() {
		return back_seatNum;
	}
	public void setBack_seatNum(int back_seatNum) {
		this.back_seatNum = back_seatNum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}



}
