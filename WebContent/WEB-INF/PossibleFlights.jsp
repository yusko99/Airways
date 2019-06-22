<!DOCTYPE html>
<html lang="bg">

<head>
    <meta name="description" content="Cheap tickets from Bulgaria to all around Europe and more!">
    <meta charset="utf-8">
    <meta http-equiv="refresh" content="60">
    <meta name="keywords" content="Airlines,Tickets,UKTC,Low prices">
    <title>Home</title>
    <link href="css/possibleFlights.css" rel="stylesheet"> </head>

<body>
    <h1 align="center">UKTC Airlines</h1>
    <h2 align="center">The safe travel!</h2>
    <nav>
        <ul>
            <ul>
                <li><a href="index.html">Home</a></li>
                <li><a href="PossibleFlights.html">Possible Flights</a></li>
                <li><a href="BuyTickets.html">Buy Tickets</a></li>
                <li><a href="ExistingReservation.html">Existing reservation</a></li>
                <li><a href="AboutUs.html">About us</a></li>
                <lii><a href="LogIn.html">Log in</a></li>
            </ul>
        </ul>
    </nav>
    <h2>Possible Flights</h2>
    <p>Choose the airport you want to fly from.</p>
    <div class="dropdown">
        <button onclick="myFunction()" class="dropbtn">Airports</button>
        <div id="myDropdown" class="dropdown-content"> <a href="#sofiq">Sofiq</a> <a href="#plovdiv">Plovdiv</a> <a href="#varna">Varna</a> <a href="#burgas">Burgas</a> </div>
    </div>
    <script>
        function myFunction() {
            document.getElementById("myDropdown").classList.toggle("show");
        }
        window.onclick = function (event) {
            if (!event.target.matches('.dropbtn')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
                var i;
                for (i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        }
        
    </script>
</body>

</html>