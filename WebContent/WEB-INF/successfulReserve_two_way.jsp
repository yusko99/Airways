<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <meta name="description" content="Cheap tickets from Bulgaria to all around Europe and more!">
    <meta charset="utf-8">
    <meta name="keywords" content="Airlines,Tickets,UKTC,Low prices">
    <title>Successfull reservation</title>
    <style>
body {
    background-image: url("../pictures/47633825-plane-wallpaper.jpg")
}

html {
    font-family: sans-serif;
}

h1 {
    margin: 60px;
}

h2 {
    margin: 50px;
}
        h3{
            margin-left: 500px;
            margin-top: 100px;
            
        }
ul {
    position: fixed;
    top: 0;
    width: 100%;
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

active {
    background-color: #4CAF50;
}

body {
    margin: 0px;
}

lii{
    float: right;
     display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
       background-color: #00FF00;
}
    </style>
</head>

<body>
 <h1 align="center">UKTC Airlines</h1>
    <h2 align="center">The safe travel!</h2>
            <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="BuyTickets.jsp">Buy Tickets</a></li>
            <li><a href="ExistingReservation.jsp">Cancel reservation</a></li>
            <li><a href="AboutUs.jsp">About us</a></li>
            
        </ul>
    </nav>
<h2>You have succesfully made a reservation!</h2>

<p> 
   Ticket No. : <strong>${ticketNumber}</strong><br>
   Passenger name: <strong>${name}</strong><br>
   Email: <strong>${email}</strong><br>
   Phone number: <strong>${telNum}</strong><br><br>
   
   Flight number: <strong>UA${flightNum}</strong>- <strong>${routeName}</strong><br>
   Date: <strong>${date}</strong><br>
   Departure Time:<strong>${time} h</strong><br>
   Arriving Time: <strong> ${arriveTime} h </strong><br>
   Seat No.: <strong>${seat}</strong><br><br>
   
   Return flight number: <strong>UA ${backFlightNum}</strong>- <strong>${backRouteName}</strong><br>
   Return date: <strong>${returnDate}</strong><br>
   Departure Time: <strong>${returnTime}</strong><br>
   Arriving Time: <strong>${returnArriveTime}</strong><br>
   Seat No.:<strong> ${returnSeat}</strong><br>
   
   Price: <strong>${price} BGN</strong><br>
</p>

<h2>Have a nice flight with UKTC Airlines!!!</h2>
<h5>You can see your ticket on Your e-mail.</h5>





</body>
</html>