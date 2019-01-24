package com.csdnes.controller;

import com.csdnes.utils.EsUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;



@SuppressWarnings("serial")
@WebServlet(name = "Searchnews", urlPatterns = {"/Searchnews"})
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String query = req.getParameter("query");
        
        //changer type de codage
        String pageStr = "1";
        int pageNum = 1;
        pageStr = req.getParameter("pagenum");
        System.out.println(pageStr);


        if (pageStr != null && Integer.parseInt(pageStr) > 1) {
            pageNum = Integer.parseInt(pageStr);
        }
        System.out.println("mot-cle:" + query);


        ArrayList<Map<String, Object>> newsList = searchNewsByPage(query, pageNum, req);
        req.setAttribute("queryback", query);
        req.setAttribute("newsList", newsList);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }

    public static ArrayList<Map<String, Object>> searchNewsByPage(String query, int pageNum, HttpServletRequest req) {

        long start=System.currentTimeMillis();
        ArrayList<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();

        TransportClient client = EsUtils.getSingleClient();
        QueryBuilder multiMatchQuery = QueryBuilders
                .multiMatchQuery(query, "title", "content")
                .operator(Operator.OR);

        HighlightBuilder highlightBuilder = new HighlightBuilder()
                .preTags("<span style=\"color:red\">")
                .postTags("</span>")
                .field("title")
                .field("content");
        // 搜索方法
        SearchResponse response = client.prepareSearch("inf203")
                .setTypes("article_source")
                .setQuery(multiMatchQuery)
                .highlighter(highlightBuilder)
                .setFrom((pageNum - 1) * 10)
                .setSize(10)
                .execute()
                .actionGet();
        SearchHits hits = response.getHits();

        if (hits.getTotalHits() > 0) {
            System.out.println("共搜索到" + hits.getTotalHits() + "条结果!");
            for (SearchHit hit : hits) {

                Map<String, Object> map = hit.getSourceAsMap();

                HighlightField hTitle = hit.getHighlightFields().get("title");
                HighlightField hContent = hit.getHighlightFields().get("content");
                if (hTitle != null) {
                    String highlightTitle = "";
                    Text[] fragments = hTitle.fragments();
                    for (Text text : fragments) {
                        highlightTitle += text;
                    }
                    map.put("title", highlightTitle);
                }
                if (hContent != null) {
                    String highlightContent = "";
                    Text[] fragments = hContent.fragments();
                    for (Text text : fragments) {
                        highlightContent += text;
                    }
                    map.put("content", highlightContent);
                }
                newsList.add(map);

            }
        } else {
            System.out.println("搜到0条结果");
        }

        System.out.println("newslist" + newsList);
        System.out.println(newsList.size());
        long end=System.currentTimeMillis();

        req.setAttribute("totalTime",String.valueOf((end-start)));
        req.setAttribute("totalhits", String.valueOf(hits.getTotalHits()));


        return newsList;
    }
}
