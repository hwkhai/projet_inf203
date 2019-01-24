<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<%
	String cim10libelle_en = (String) request.getAttribute("cim10libelle_en"); 
    String queryback = (String) request.getAttribute("queryback");
    String totalTime = (String) request.getAttribute("totalTime");
    String totalhits = (String) request.getAttribute("totalhits");
    
    int totalNews = Integer.parseInt(totalhits);
    int totalPage = 0;
    if (totalNews % 10 != 0) {
        totalPage = totalNews / 10 + 1;
    } else {
        totalPage = totalNews / 10;
    }

    totalPage = totalPage > 10 ? 10 : totalPage;
    ArrayList<Map<String, Object>> newslist = (ArrayList<Map<String, Object>>) request.getAttribute("newsList");
    
%>
<html>
<head>
    <title>search_result</title>
    <link type="text/css" rel="stylesheet" href="css/result.css">
</head>
<body>
<div class="result_search">
    <div class="logo">
        <h2 style="margin-top:25px;" >
            <a href="/es5newssearch/SearchPatientCIM10?CIM10_langue='<%=queryback%>'">Keywords</a>
        </h2> 
    </div>
    <div class="searchbox">
        <form class="resultform" action="/es5newssearch/Searchnews" method="get">
            <input type="text" name="query"
                   value="<%=queryback %>">
            <input type="submit" value="Search">
        </form>
    </div>
    
   <div class="logo">
        <h2 style="padding:15px; margin-top:10px;">
            <a href="RecherchePatient.jsp">RecherchePatient</a>
        </h2> 
    </div>
    
</div>
<h5 class="result_info">
    le total trouvé<span> <%=totalhits%>  </span>lignes,le temps<span>  <%=totalTime%>  </span>Millisecondes
</h5>
<div class="newslist">

    <%
        Iterator<Map<String, Object>> iter = newslist.iterator();
        while (iter.hasNext()) {
            Map<String, Object> news = iter.next();
    %>

    <div class="news">
        <h4>
            <a href="<%=news.get("url")%>" target="_blank">
                <%=(String) news.get("title")%>
            </a>

        </h4>
        <p>
            <%=news.get("content")%>
        </p>
    </div>
    <%
        }
    %>

</div>
<div id="page">
    <ul>
        <%
            for (int i = 1; i <= totalPage; i++) {
        %>
        <li>
            <a href="/es5newssearch/Searchnews?query=<%=queryback%>&&pagenum=<%=i%>"><%=i%>
            </a>
        </li>
        <%
            }
        %>
        <li><a style="width:200px;" href="#"> <span>totals </span> <%=totalPage%> <span> pages</span></a></li>
    </ul>
</div>

<div class="info">

    <p>
        Search Powerd by <b>Elasticsearch</b>
    </p>
    <p>
        @2019 All rights Reserved
    </p>
</div>

</body>
</html>