<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.csdnes.utils.CIM10" %>
<%
	String number_patient = (String) request.getAttribute("number_patient");
	List<CIM10> ListCIM10= (ArrayList<CIM10>)request.getAttribute("ListCIM10");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient_trouvé</title>
<link type="text/css" rel="stylesheet" href="css/result.css">
</head>
<body>

<div class="result_search">
    <div class="logo">
        <h2>
            <a href="RecherchePatient.jsp">NumberPatient</a>
        </h2>
    </div>
    <div style="margin-left:100px;margin-top:15px" class="searchbox">
        <form class="resultform" action="/es5newssearch/SearchPatients" method="get">
            <input type="text" name="number_patient" value="<%=number_patient %>">
            <input type="submit" value="Search">
        </form>
    </div>
</div>

<div class="box">
	<fieldset>
		<legend>Liste de CIM10</legend>
		<table style="text-align:center;">
		<tr>
			<th>CIM10</th>
			<th>Libelle_EN</th>
			<th>Libelle_FR</th>
		</tr>
 <%
		
        Iterator<CIM10> iter = ListCIM10.iterator();
        while (iter.hasNext()) {
        	CIM10 patient_cim10 = iter.next();
        
 %>
 		<tr>
 		<td>
		<%=patient_cim10.getCodeCIM10() %>
		</td>
		<td>
		<div class="news" >
		<h4>
		<a href="/es5newssearch/SearchPatientCIM10?CIM10_langue='<%=patient_cim10.getLibelle_EN()%>'">
		<%=patient_cim10.getLibelle_EN() %>
		</a>
		</h4>
		</div>
		</td>
		<td>
		<div class="news">
		<h4>
		<a href="/es5newssearch/SearchPatientCIM10?CIM10_langue='<%=patient_cim10.getLibelle_FR()%>'">
		<%=patient_cim10.getLibelle_FR() %>
		</a>
		</h4>
		</div>
		</td>
		</tr>
    <%

        }
        
    %>
    	</table>
    	
		
	</fieldset>
</div>

</body>
</html>