<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="bg">

<head>
    <meta name="description" content="Cheap tickets from Bulgaria to all around Europe and more!">
    <meta charset="utf-8">
    <meta http-equiv="refresh" content="60">
    <meta name="keywords" content="Airlines,Tickets,UKTC,Low prices">
    <title>Cancel Reservation</title>
    <link href="css/existingReservation.css" rel="stylesheet"> <link href="https://fonts.googleapis.com/css?family=Monoton" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Playball" rel="stylesheet">
    </head>

<body>
    <h1 align="center">UKTC Airlines</h1>
    <h2 align="center">The safe travel!</h2>

    <h4>To cancel your reservation please enter your ticket's number(you can see it in the mail we have sent you when you reserved the ticket)</h4>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="BuyTickets.jsp">Buy Tickets</a></li>
            <li><a href="ExistingReservation.jsp">Cancel reservation</a></li>
            <li><a href="AboutUs.jsp">About us</a></li>
                 
        </ul>
    </nav>
    <h3>
      <form align="left" name="CancelForm" method="post" action="CancelReserveServlet">    
   Ticket No. :<br><input type="text" name="ticketNumber" ><br>                 
         <br align="center"><input type="submit" value="Submit"> </br>
</form>
      </h3> </body>

</html>