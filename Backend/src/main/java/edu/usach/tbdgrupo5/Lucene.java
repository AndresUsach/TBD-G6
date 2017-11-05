package edu.usach.tbdgrupo5;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;






public class Lucene {
	private MongoConnection mongoConnection;
	//private List<String> idList = null;
	private List<List<String>> resultList = null;
	private int positiveResult=0;
	private int negativeResult=0;
	private int neutralResult=0;
	private int commentsCountry=0;
	private List<String> countryList = null;
	public Lucene(MongoConnection mongoConnection){
		this.mongoConnection = mongoConnection;
	}
	
	public void indexCreate(){
		try{
			Directory dir = FSDirectory.open(Paths.get("indice/"));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			iwc.setOpenMode(OpenMode.CREATE);
			
			IndexWriter writer = new IndexWriter(dir,iwc);
			DBCursor cursor = this.mongoConnection.getTweets();
			Document doc = null;
			//DBObject cur2 = cursor.next();
			//System.out.println("Index);
			while (cursor.hasNext()) {
			      DBObject cur = cursor.next();
			      doc = new Document();
			      /*System.out.println(cur.get("_id").toString());
			      System.out.println(cur.get("text").toString());
			      System.out.println(cur.get("userName").toString());*/
			      doc.add(new StringField("id",cur.get("_id").toString(),Field.Store.YES));
			      doc.add(new TextField("text", cur.get("text").toString(),Field.Store.YES));
			      doc.add(new StringField("analysis",cur.get("analysis").toString(),Field.Store.YES));
			      doc.add(new StringField("finalCountry",cur.get("finalCountry").toString(),Field.Store.YES));
			      doc.add(new StringField("userName",cur.get("userName").toString(),Field.Store.YES));
			      //System.out.println("pais del comentario indexando :"+ cur.get("finalCountry"));
			      if (writer.getConfig().getOpenMode() == OpenMode.CREATE){
						//System.out.println("Indexando el tweet: "+cur.get("text")+"\n");
						writer.addDocument(doc);
					}
				else{
						//System.out.println("Actualizando el tweet: "+cur.get("text")+"\n");
						writer.updateDocument(new Term("text"+cur.get("text")), doc);
				}
			   }
			cursor.close();
			writer.close();
			//System.out.println(cur2);
		}
		catch(IOException ioe){
				System.out.println(" caught a "+ ioe.getClass() + "\n with message: " + ioe.getMessage());
			
		}
		
	}
	public void indexSearch(String Artista){
		try{
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("indice/")));
			IndexSearcher searcher = new IndexSearcher(reader);
			Analyzer analyzer = new StandardAnalyzer();
			this.positiveResult=0;
			this.negativeResult=0;
			this.neutralResult=0;
			this.resultList = new ArrayList<List<String>>();
			QueryParser parser = new QueryParser("text",analyzer);
			Query query = parser.parse(Artista);
			
			countryList = new ArrayList<String>();
			TopDocs result = searcher.search(query,25000);
			ScoreDoc[] hits =result.scoreDocs;
			//System.out.println("cantidad tweets:"+hits.length);
			for (int i=0; i<hits.length;i++){
				Document doc = searcher.doc(hits[i].doc);
				List<String> item = new ArrayList<String>();
				//System.out.println(doc.get("userName"));
				item.add(doc.get("userName"));
				item.add(doc.get("text"));
				this.resultList.add(item);

				//System.out.println("pais del comentario indexando :"+ doc.get("finalCountry"));
				if((doc.get("analysis")).equals("Positive")){
					this.countryList.add(doc.get("finalCountry"));
					//System.out.println(doc.get("finalCountry")+"\n");
					this.positiveResult++;
				}
				else if((doc.get("analysis")).equals("Negative")){
					this.negativeResult++;
				}
				else if((doc.get("analysis")).equals("Neutral")){
					this.neutralResult++;
				}
				//System.out.println((i+1) + ".- score="+hits[i].score+" doc="+hits[i].doc+" id="+doc.get("id")+ "twee="+doc.get("text"));
			}
			
			reader.close();
			
			
		}
		catch(IOException ex){
			Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE,null,ex);
			
		}
		catch(ParseException ex){
			Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE,null,ex);
		}
		//return 0;
	}
	public void countryCommentsCount(String artista, String country){
		int comments= this.countryList.size();
		this.commentsCountry=0;
		for(int i=0;i<comments;i++){
			if(country.equals(this.countryList.get(i))){
				this.commentsCountry++;
			}
		}
	}
	
	public List<List<String>> getResultList(){
		return this.resultList;
	}
	public int getpositiveResult(){
		return this.positiveResult;
	}
	public int getnegativeResult(){
		return this.negativeResult;
	}
	public int getneutralResult(){
		return this.neutralResult;
	}
	public List<String> getCountryList(){
		return this.countryList;
	}

	public int getCommentsCountry() {
		return commentsCountry;
	}
}
