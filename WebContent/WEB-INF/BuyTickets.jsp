<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="bg">

<head>
    <meta name="description" content="Cheap tickets from Bulgaria to all around Europe and more!">
    <meta charset="utf-8">
    <meta name="keywords" content="Airlines,Tickets,UKTC,Low prices">
    <title>Reserve Ticket</title>
    <link href="css/buyTickets.css" rel="stylesheet"> 
   
    <link href="https://fonts.googleapis.com/css?family=Monoton" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Playball" rel="stylesheet">
 
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    
    <script type="text/javascript" >
    //revealing second datepicker if two-way is checked
    function Reveal (it, box) { 
    	var vis = (box.checked) ? "block" : "none"; 
    	document.getElementById(it).style.display = vis;
    	} 

    
    //hiding datepicker and its label if two-way is not checked
    	function Hide (it, box) { 
    	var vis = (box.checked) ? "none" : "none"; 
    	document.getElementById(it).style.display = vis;
    	} 
    
    	
    	function setToday(){
    		var today = new Date().toISOString().split('T')[0];
    		document.getElementsByName("date")[0].setAttribute('min', today);
    	}
    	
    	function setMinDate(){
    		var day = document.getElementById('dateField').value;
    		document.getElementsByName("returnDate")[0].setAttribute('min', day);
    	}
   
    	function checkReservation() {
    	var empty = false;
        $('input').not(function() {
            return $(this).css('display') == 'none';
        }).each(function () {
            if ($(this).val().length == 0) {
                empty = true;
            }
        });
        if (empty) {
            $('#reserve').attr('disabled', 'disabled');
        } else {
            $('#reserve').removeAttr('disabled');
        }
    }
    	
    //hiding button until the fields are filled
  $(document).ready(function () {
	$('#reserve').attr('disabled', true);
    $('input').keyup(function () {
    	checkReservation();
    }).change(function () {
    	checkReservation();
    });
});
    
  
 </script>
    </head>

<body>
	<h1 align="center">UKTC Airlines</h1>
    <h3>Fill the fields to buy a ticket.</h3>
    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="BuyTickets.jsp">Buy Tickets</a></li>
            <li><a href="ExistingReservation.jsp">Cancel reservation</a></li>
            <li><a href="AboutUs.jsp">About us</a></li>
        </ul>
    </nav>
    <h3>
<form name="reserveForm" method="post" action="TicketReserveServlet" id="reserve_form">
	
   Enter your name: <input type="text" name="name" id="name"   /> <br/>
   Enter your telephone number: <input type="text" name="telNum" id="telNum"  /> <br/>
   Enter your email: <input type ="text" name = "email" id="email"    /> <br/>
   Chose route: 
   <select name="route" id="route" >
        <option value=""></option>
        <option value="8">Sofia-London    14:00h-17:10h   350 BGN</option>
        <option value="9">Sofia-Berlin     10:00h-12:20h  225 BGN</option>
        <option value="10">Sofia-Rome     08:00h-09:40h   190 BGN</option>
        <option value="1">Sofia-Moscow     16:00h-18:00h  220 BGN</option>
        <option value="2">Sofia-Istanbul     10:00h-11:10h  100 BGN</option>
        <option value="5">Sofia-Paris 	09:30h-11:15h	235 BGN</option>
        <option value="4">Sofia-Athens	15:30h-16:10h	90 BGN</option>
        <option value="6">Sofia-Frankfurt     16:30h-18:00h  190BGN</option>
        <option value="7">Sofia-Viena 13:00h-14:30h  170 BGN</option>
        <option value="3">Sofia-Madrid	12:00h-14:20h	280 BGN</option>
       
        <option value="11">Moscow-Sofia	20:00h-22:00h	220 BGN</option>
        <option value="12">Istanbul-Sofia	14:00h-15:10h	100 BGN</option>
        <option value="13">Madrid-Sofia	18:00h-20:20h	280 BGN</option>
        <option value="14">Athens-Sofia	19:00h-19:40h	90 BGN</option>
        <option value="15">Paris-Sofia	15:00h-16:45h	235 BGN</option>
        <option value="16">Frankfurt-Sofia 	21:00h-22:30h	190 BGN</option>
        <option value="17">Viena-Sofia 	20:00h-21:30h	170 BGN</option>
        <option value="18">London-Sofia	20:00h-23:10h	350 BGN</option>
        <option value="19">Berlin-Sofia	14:30h-16:50h	225 BGN</option>
        <option value="20">Rome-Sofia   12:30h-14:10h   190 bBGN</option>
   </select>
   
  
   
   Type:
<input type="radio" name="type"  value="oneWay" onClick="Hide('returnDate', this); Hide('comingDate', this)" />One-Way
<input type="radio" name="type" value="twoWay" onClick="Reveal('returnDate', this); Reveal('comingDate', this) " />Two-Way
   
  Travel Date: <input id="dateField" type ="date" name = "date" onClick="setToday();"/> 
 
 <label for="backDate" id="comingDate" style="display:none">Return date:</label> 
 <input type ="date" name = "returnDate"  id="returnDate" style= "display:none" onClick="setMinDate();" />
    
    
    <input type="submit" value="Make reservation" id ="reserve"  />
    
</form>




</body>
</html>