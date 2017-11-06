package edu.usach.tbdgrupo5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.usach.tbdgrupo5.entities.Artista;
import edu.usach.tbdgrupo5.entities.Pais;
import edu.usach.tbdgrupo5.repository.ArtistaRepository;
import edu.usach.tbdgrupo5.repository.PaisRepository;

import java.sql.SQLException;
import java.util.List;

@Component
public class ScheduledTasks
{
	public MongoConnection mc = new MongoConnection("tweets", "tweetsPrueba");
	public Lucene lucene = new Lucene(mc);
	@Autowired
	public ArtistaRepository artistarepository ;
	@Autowired
	public PaisRepository paisrepository ;
	public Iterable<Artista> artistas = null;
	public Iterable<Pais> countryList=null;
	public int[] countryComments;
	
	
    @Scheduled(cron="*/60 * * * * *")
    public void indexCreateTask()
	{
    	mc.connect();
    	System.out.println("[Scheduled Task][Start]: Indexing tweets.");
    	lucene.indexCreate();
    	System.out.println("[Scheduled Task] [End] : Indexing tweets.\n");
    }
    @Scheduled(cron="*/60 * * * * *")
    public void updateComments()
	{
    	
    	System.out.println("[Scheduled Task][Start]: Update comments.");
    	artistas= artistarepository.findAll();
    	for (Artista artista:artistas)
    	{
    		countryList= paisrepository.findAll();
    		lucene.indexSearch(artista.getNombre());
    		
    		/*System.out.println("Artista:"+ artista.getNombre());
    		System.out.println("Positivos: "+ lucene.getpositiveResult());
    		System.out.println("Negativos: "+ lucene.getnegativeResult());
    		System.out.println("Neutral: "+ lucene.getneutralResult());*/
    	    artista.setComentariosPositivos(lucene.getpositiveResult());
    	    artista.setComentariosNegativos(lucene.getnegativeResult());
			artista.setComentariosNeutros(lucene.getneutralResult());
			
			for(Pais countryArtista:countryList){
				lucene.countryCommentsCount(artista.getNombre(), countryArtista.getNombre());
				if(countryArtista.getComentariosPositivos() < lucene.getCommentsCountry()){
					countryArtista.setArtista(artista);
					countryArtista.setComentariosPositivos(lucene.getCommentsCountry());
					paisrepository.save(countryArtista);
				}
			}
			artistarepository.save(artista);
    	}
    	System.out.println("[Scheduled Task] [End] : Update comments.\n");
    }
    @Scheduled(cron="*/60 * * * * *")
    public void mapreduce() throws SQLException
	{
    	System.out.println("[Scheduled Task][Start]: Update graph db.");
    	Neo4j neo = new Neo4j();
        neo.connect("bolt://localhost", "neo4j", "root");
        neo.crearGrafo();
        neo.disconnect();
        System.out.println("[Scheduled Task] [End] : Update graph db.\n");
    	
	}
}