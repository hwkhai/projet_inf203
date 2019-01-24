<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String cim10libelle_langue = (String) request.getAttribute("cim10libelle_langue");
	if(cim10libelle_langue==null){
		cim10libelle_langue="";
	} 
%>
<html>
<head>
    <title>ElasticsearchSource</title>
    <link type="text/css" rel="stylesheet" href="css/index.css">
</head>
<body>

<div class="box">
	<div class="recherchepatient_titre" >
	<a href="RecherchePatient.jsp">Recherche patient</a>
	</div>
    <h1>ElasticsearchSource</h1>
    <div class="searchbox">
        <form action="/es5newssearch/Searchnews" method="get">
            <input type="text" name="query" value=<%=cim10libelle_langue%>>
            <input type="submit" value="Search">
        </form>
       
    </div>
</div>
</body>
</html>
