package bean;

public class Route {
	
	private  int routeID;
	private String routeName;
	private String time;
	private String arriveTime;
	private int backRoute;
	
	
	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	private double price;

	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getBackRoute() {
		return backRoute;
	}
	public void setBackRoute(int backRoute) {
		this.backRoute = backRoute;
	}

	
}
