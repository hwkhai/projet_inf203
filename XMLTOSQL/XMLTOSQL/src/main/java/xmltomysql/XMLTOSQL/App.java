package xmltomysql.XMLTOSQL;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		ArrayList<Publication> list= new ArrayList<Publication>();
		PublicationDaoImp  document= new PublicationDaoImp();
		list = document.getParserAuthor();
		/*
		for(int i=0;i<list.size();i++)
		{			
			if (list.get(i)!=null)
			
			ConnectSql.writeToMysql(list.get(i));
		}
		*/
	}
}
