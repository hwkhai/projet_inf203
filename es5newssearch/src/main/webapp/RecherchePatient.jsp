<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>RecherchePaitent</title>
    <link type="text/css" rel="stylesheet" href="css/index.css">
</head>
<body>
	<div class="titre">
    <h1>Recherche votre patient</h1>
    </div>
    
<div class="box">
    <h2 style="text-align:center;">Entrez numéro de patient :</h2>
    <div class="searchbox">
        <form action="/es5newssearch/SearchPatients" method="get">
           <input type="text" name="number_patient">
            <input type="submit" value="Search">
        </form>
    </div>
</div>

</body>
</html>
