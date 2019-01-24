package xmltomysql.XMLTOSQL;

import java.util.Date;
//类属性名要和xml文档标签一样，数据库属性名字可以不一样
public class Publication {  
    private int PMID;
    private String ArticleTitle;
    private String KeywordList;
    private String AbstractText;
    private String ArticleIdList;
    private String Journal;
    private String AuthorList;
    private String Year;
	/**
	 * @return the pMID
	 */
	public int getPMID() {
		return PMID;
	}
	/**
	 * @param pMID the pMID to set
	 */
	public void setPMID(int pMID) {
		PMID = pMID;
	}
	/**
	 * @return the articleTitle
	 */
	public String getArticleTitle() {
		return ArticleTitle;
	}
	/**
	 * @param articleTitle the articleTitle to set
	 */
	public void setArticleTitle(String articleTitle) {
		ArticleTitle = articleTitle;
	}
	/**
	 * @return the keywordList
	 */
	public String getKeywordList() {
		return KeywordList;
	}
	/**
	 * @param keywordList the keywordList to set
	 */
	public void setKeywordList(String keywordList) {
		KeywordList = keywordList;
	}
	/**
	 * @return the abstractText
	 */
	public String getAbstractText() {
		return AbstractText;
	}
	/**
	 * @param abstractText the abstractText to set
	 */
	public void setAbstractText(String abstractText) {
		AbstractText = abstractText;
	}
	/**
	 * @return the articleIdList
	 */
	public String getArticleIdList() {
		return ArticleIdList;
	}
	/**
	 * @param articleIdList the articleIdList to set
	 */
	public void setArticleIdList(String articleIdList) {
		ArticleIdList = articleIdList;
	}
	/**
	 * @return the journal
	 */
	public String getJournal() {
		return Journal;
	}
	/**
	 * @param journal the journal to set
	 */
	public void setJournal(String journal) {
		Journal = journal;
	}
	/**
	 * @return the authorList
	 */
	public String getAuthorList() {
		return AuthorList;
	}
	/**
	 * @param authorList the authorList to set
	 */
	public void setAuthorList(String authorList) {
		AuthorList = authorList;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return Year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		Year = year;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Publication [PMID=" + PMID + ", ArticleTitle=" + ArticleTitle + ", KeywordList=" + KeywordList
				+ ", AbstractText=" + AbstractText + ", Country=" + ArticleIdList + ", Journal=" + Journal
				+ ", AuthorList=" + AuthorList + ", Year=" + Year + "]";
	}

}