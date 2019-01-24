package xmltomysql.XMLTOSQL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PublicationDaoImp implements PublicationDao {
	public ArrayList<Publication> getParserAuthor() {
		ArrayList<Publication> list= new ArrayList<Publication>();
		//获取DOM解析器
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			   builder = factory.newDocumentBuilder();
			   Document doc;
			   	//这里所有的xml文档结构要一样好，不然就手动逐个文档解析插入数据库
				String path = "/Users/binh/eclipse-workspace/XMLTOSQL/sourceDOCs";		//要遍历的路径
				File file = new File(path);		//获取其file对象
				File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
				for(File f:fs){					//遍历File[]数组
					if(!f.isDirectory())		//若非目录(即文件)，则打印
						System.out.println(f);
						doc = builder.parse(f);
				
			   //doc = builder.parse(new File("AjayGupta.xml"));
		           //得到一个element根元素,获得根节点
			    Element root = doc.getDocumentElement();	    
			    System.out.println("根元素："+root.getNodeName());
			    
			    
Publication publication = new Publication(); //Crée une liste pour y introduire les items			    
//ISOLE CHAQUE ARTICLE
			   
			    NodeList pubmedNodes = root.getElementsByTagName("PubmedArticle");

			    for(int k = 0; k<pubmedNodes.getLength();k++){ //Pour chaque élément
			    	Element pubmedElement = (Element) pubmedNodes.item(k);				    
			    	NodeList childNodes2 = pubmedElement.getChildNodes();
				    for(int l = 0; l<childNodes2.getLength();l++){ //Pour chacun de ces sous-élements
				    	if(childNodes2.item(l).getNodeType()==Node.ELEMENT_NODE) { //Si le sous-élément est bien un noeud


//RECUPERE L'ID 				    		
			    NodeList introNodes = root.getElementsByTagName("MedlineCitation"); // Récupère tous les éléments dans le noeud
			    
			    
			    for(int i = 0; i<introNodes.getLength();i++){ //Pour chacun de ces élements
			    	Element introElement = (Element) introNodes.item(i); 
			    	
			    	NodeList childNodes = introElement.getChildNodes();
			    	//System.out.println("*****"+childNodes.getLength());  //Calcule le nombre de sous éléments
			    	
			    	for (int j = 0; j < childNodes.getLength(); j++) {
			    		
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) { //Vérifie que c'est bien un noeud
						
						if("PMID".equals(childNodes.item(j).getNodeName())){ 
							publication.setPMID(Integer.parseInt(childNodes.item(j).getTextContent()));
						}
					}
				}
			    	//list.add(publication);
			    }

			    
//RECUPERE LE TITRE			    
			    NodeList articleNodes = root.getElementsByTagName("Article"); // Récupère tous les éléments dans PubmedArticle
			    
			    for(int i = 0; i<articleNodes.getLength();i++){ //Pour chacun de ces élements
			    	Element articleElement = (Element) articleNodes.item(i); 
			    	NodeList childNodes = articleElement.getChildNodes();
			    	//System.out.println("*****"+childNodes.getLength());  
			    	
			    	for (int j = 0; j < childNodes.getLength(); j++) {
			    		
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) { //Vérifie que c'est bien un noeud
						
						if("ArticleTitle".equals(childNodes.item(j).getNodeName())){
							publication.setArticleTitle(childNodes.item(j).getFirstChild().getNodeValue());
						}
//						if("AbstractText".equals(childNodes.item(j).getNodeName())){
//							publication.setKeywordList(childNodes.item(j).getFirstChild().getNodeValue());
//						}
					}
				}
			    	
			    }
			    

//RECUPERE L'ABSTRACT			    
			    NodeList abstractNodes = root.getElementsByTagName("Abstract"); // Récupère tous les éléments dans PubmedArticle
			    
			    for(int i = 0; i<abstractNodes.getLength();i++){ //Pour chacun de ces élements
			    	Element abstractElement = (Element) abstractNodes.item(i); 
			    	NodeList childNodes = abstractElement.getChildNodes();
			    	//System.out.println("*****"+childNodes.getLength());  
			    	
			    	for (int j = 0; j < childNodes.getLength(); j++) {
			    		
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) { //Vérifie que c'est bien un noeud
						
						if("AbstractText".equals(childNodes.item(j).getNodeName())){
							publication.setAbstractText(childNodes.item(j).getFirstChild().getNodeValue());
						}

					}
				}
			    	
			    }
			   

//RECUPERE LES MOTS CLES			    
			    NodeList meshNodes = root.getElementsByTagName("MeshHeading"); // Récupère tous les éléments dans PubmedArticle
			    
			    for(int i = 0; i<meshNodes.getLength();i++){ //Pour chacun de ces élements
			    	Element meshElement = (Element) meshNodes.item(i); 
			    	NodeList childNodes = meshElement.getChildNodes();
			    	//System.out.println("*****"+childNodes.getLength());  
			    	
			    	for (int j = 0; j < childNodes.getLength(); j++) {
			    		
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) { //Vérifie que c'est bien un noeud
						
						if("DescriptorName".equals(childNodes.item(j).getNodeName())){
							for (int k1 = 0; k1 < 10; k1++) {
							
							publication.setKeywordList(childNodes.item(j).getFirstChild().getNodeValue()); // Problème ne sort que le dernier keyword
							}
							}

					}
				}
			    	
			    }
			    

//RECUPERE LE 1ER AUTEUR		    
			    NodeList authorNodes = root.getElementsByTagName("Author"); // Récupère tous les éléments dans PubmedArticle
			    
			    for(int i = 0; i<authorNodes.getLength();i++){ //Pour chacun de ces élements
			    	Element authorElement = (Element) authorNodes.item(i); 
			    	NodeList childNodes = authorElement.getChildNodes();
			    	//System.out.println("*****"+childNodes.getLength());  
			    	
			    	for (int j = 0; j < childNodes.getLength(); j++) {
			    		
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) { //Vérifie que c'est bien un noeud
						
						if("Initials".equals(childNodes.item(j).getNodeName())){
							publication.setAuthorList(childNodes.item(j).getFirstChild().getNodeValue()); // Problème ne sort que le dernier auteur
						}

					}
				}
			    	
			    }
			    

//RECUPERE LA DATE DE PUBLICATION			    
			    NodeList yearNodes = root.getElementsByTagName("PubMedPubDate"); // Récupère tous les éléments dans PubmedArticle
			    
			    for(int i = 0; i<yearNodes.getLength();i++){ //Pour chacun de ces élements
			    	Element yearElement = (Element) yearNodes.item(i); 
			    	NodeList childNodes = yearElement.getChildNodes();
			    	//System.out.println("*****"+childNodes.getLength());  
			    	
			    	for (int j = 0; j < childNodes.getLength(); j++) {
			    		
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) { //Vérifie que c'est bien un noeud
						
						if("Year".equals(childNodes.item(j).getNodeName())){
							publication.setYear(childNodes.item(j).getFirstChild().getNodeValue()); // Problème ne sort que le dernier auteur
						}

					}
				}
			    	
			    }
			    
//RECUPERE LE PAYS ET LE JOURNAL DE PUBLICATION	    
			    NodeList countryNodes = root.getElementsByTagName("MedlineJournalInfo"); // Récupère tous les éléments dans PubmedArticle
			    
			    for(int i = 0; i<countryNodes.getLength();i++){ //Pour chacun de ces élements
			    	Element countryElement = (Element) countryNodes.item(i); 
			    	NodeList childNodes = countryElement.getChildNodes();
			    	//System.out.println("*****"+childNodes.getLength());  
			    	
			    	for (int j = 0; j < childNodes.getLength(); j++) {
			    		
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) { //Vérifie que c'est bien un noeud
						
						if("Country".equals(childNodes.item(j).getNodeName())){
							publication.setArticleIdList(childNodes.item(j).getFirstChild().getNodeValue()); // Problème ne sort que le dernier auteur
						}
						if("MedlineTA".equals(childNodes.item(j).getNodeName())){
							publication.setJournal(childNodes.item(j).getFirstChild().getNodeValue()); // Problème ne sort que le dernier auteur
						}

					}
				}
			    	
			    }
			    
			    

			    
			    list.add(publication);
			    System.out.println(list);
			    }
			    }
				    
			    }
			    for(Publication publication2 : list){  //为了查看数据是否正确，进行打印结果
		             System.out.println(publication2.toString());  
		         } 
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return list;
	       
	}
}



