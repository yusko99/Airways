<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Incorrect ticket</title>

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

<h4> Can not find a ticket with such number</h4>
<br>

<a href="ExistingReservation.jsp">Back</a>

</body>
</html>