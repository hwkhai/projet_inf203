package xmltomysql.XMLTOSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ConnectSql {
	static String sqlStr = "jdbc:mysql://localhost:8080/publications";
	static String rootName = "root";//数据库名
	static String rootPwd = "";//数据库密码
 
	public static void writeToMysql(Publication publication) {
		System.out.println(publication);
		//1.加载driver驱动
		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！/ \n" + 
					"Classe de pilote introuvable, le chargement du pilote a échoué!");
			e.printStackTrace();
		}
		//2.建立连接
		//Statement st = null;
		 PreparedStatement st = null;
		//调用DriverManager对象的getConnection()方法，获得一个Connection对象
		Connection con  =null;
		try {
			//建立数据库连接
			con = DriverManager.getConnection(sqlStr, rootName,rootPwd);
			//INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
			int id= publication.getPMID();
			String title = publication.getArticleTitle();
			String key_word = publication.getKeywordList();
			String content = publication.getAbstractText();
			String url = publication.getArticleIdList();
			String reply = publication.getJournal();
			String source = publication. getAuthorList();
			String postdate = publication.getYear();
			//插入语句格式
			String sql = "insert into news(id,title,key_word,content,url,reply,source,postdate) values(?,?,?,?,?,?,?,?)";
			System.out.println(sql);
	             st = con.prepareStatement(sql);
		  		 
	             st.setInt(1, publication.getPMID());
	             st.setString(2,publication.getArticleTitle());
	             st.setString(3, publication.getKeywordList());
	             st.setString(4, publication.getAbstractText());
	             st.setString(5,publication.getArticleIdList());
	             st.setString(6, publication.getJournal());
	             st.setString(7, publication. getAuthorList());
	             st.setString(8, publication.getYear());
	             st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
