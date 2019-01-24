package com.csdnes.dao;

import com.csdnes.utils.EsUtils;
import com.mysql.cj.jdbc.PreparedStatement;
import org.elasticsearch.client.transport.TransportClient;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bee on 17/8/4.
 */
public class Dao {
	

    private Connection conn;
    

    /**
	 * @return the conn
	 */
	public Connection getConn() {
		
		return conn;
	}


	/**
	 * @param conn the conn to set
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}


	public void getConnection() {
        String user = "root";
        String passwd = "";
        String url = "jdbc:mysql://localhost:3306/inf203";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void mysqlToEs() {

        String sql = "SELECT * FROM pubmed";

        TransportClient client = EsUtils.getSingleClient();

        try {
            PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);

            ResultSet res = pstm.executeQuery();
            Map<String, Object> newsJson = new HashMap<String, Object>();
            while (res.next()) {
                newsJson.put("id", res.getInt(1));
                newsJson.put("title", res.getString(2));
                newsJson.put("keyword", res.getString(3));
                newsJson.put("content", res.getString(4));
                newsJson.put("url", res.getString(5));
                newsJson.put("reply", res.getInt(6));
                newsJson.put("source", res.getString(7));
                String posttime = res.getTimestamp(8).toString();
                System.out.println(posttime);
                newsJson.put("postdate", posttime.substring(0, posttime.length() - 2));

                System.out.println(newsJson);
                client.prepareIndex("inf203", "article_source",
                        res.getInt(1) + "")
                        .setSource(newsJson)
                        .execute().actionGet();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    




    public static void main(String[] args) {
        Dao dao = new Dao();
        dao.getConnection();
        dao.mysqlToEs();
    }
}
