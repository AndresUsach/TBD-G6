package edu.usach.tbdgrupo5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.usach.tbdgrupo5.entities.Artista;
import edu.usach.tbdgrupo5.entities.Pais;
import edu.usach.tbdgrupo5.repository.ArtistaRepository;
import edu.usach.tbdgrupo5.repository.PaisRepository;

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
	
	
    @Scheduled(cron="*/10 * * * * *")
    public void indexCreateTask()
	{
    	mc.connect();
    	System.out.println("[Scheduled Task][Start]: Indexing tweets.");
    	lucene.indexCreate();
    	System.out.println("[Scheduled Task] [End] : Indexing tweets.\n");
    }
    @Scheduled(cron="*/10 * * * * *")
    public void updateComments()
	{
    	
    	artistas= artistarepository.findAll();
    	
    	System.out.println("[Scheduled Task][Start]: Update comments.");
    	for (Artista artista:artistas)
    	{
    		countryList= paisrepository.findAll();
    		//System.out.println("entre a artista :D ");
    		lucene.indexSearch(artista.getNombre());
    		/*System.out.println("Tamaño:"+lucene.getResultList().size());
    		for(int i=0;i<lucene.getResultList().size();i++){
    			for (int j=0;j<lucene.getResultList().get(i).size();j++){
    				System.out.println(lucene.getResultList().get(i).get(j));
    			}
    			System.out.println("\n");
    		}*/
    		/*System.out.println("Artista:"+ artista.getNombre());
    		System.out.println("Positivos: "+ lucene.getpositiveResult());
    		System.out.println("Negativos: "+ lucene.getnegativeResult());
    		System.out.println("Neutral: "+ lucene.getneutralResult());*/
    	    artista.setComentariosPositivos(lucene.getpositiveResult());
    	    artista.setComentariosNegativos(lucene.getnegativeResult());
			artista.setComentariosNeutros(lucene.getneutralResult());
			
			for(Pais countryArtista:countryList){
				//System.out.println("pais :"+countryArtista.getNombre());
				lucene.countryCommentsCount(artista.getNombre(), countryArtista.getNombre());
				//System.out.println("comentarios del pais: "+lucene.getCommentsCountry());
				if(countryArtista.getComentariosPositivos() < lucene.getCommentsCountry()){
					countryArtista.setArtista(artista);
					countryArtista.setComentariosPositivos(lucene.getCommentsCountry());
					paisrepository.save(countryArtista);
				}
				//countryArtista.setComentariosPositivos(countryArtista.getComentariosPositivos()+lucene.getCommentsCountry());	
			}
			/*System.out.println("pais :"+"unknown");
			lucene.countryCommentsCount(artista.getNombre(), "unknown");
			System.out.println("comentarios del pais: "+lucene.getCommentsCountry());
    		artistarepository.save(artista);
    		System.out.println("\n\n");*/
    	}
    	System.out.println("[Scheduled Task] [End] : Update comments.\n");
        //System.out.println("hola fuí programado\n");
    }
    @Scheduled(cron="*/10 * * * * *")
    public void mapreduce()
	{
    	//System.out.println("Empezar\n");
		//MongoConnection mc = new MongoConnection("tweets", "tweetsPrueba");
		//mc.connect();
		//ESTA ES LA FUNCION QUE SIRVE PARA OBTENER LOS NOMBRES DE USUARIOS SIN REPETIR
		//mc.getUserNames(); Retorna una lista de string con los nombres de los usuarios sin repetir
		//El otro metodo es lucene.indexSearch(Artista) -> este no retorna nada pero deja en un atributo los resutlados
		// este atributo se llama resultList para acceder a ese atributo esta el metodo getResultList()
		/* el atributo resultList es una lista de lista que contiene strings cada lista dentro de la lista tiene dos string
		 * los cuales corresponden al usuario y al tweet respectivamente es decir que el de la posicion 0 es el usuuario y el de la posicion 1 es el tweet
		EJEMPLO::
		lucene.indexSearch(artista.getNombre());
		for(int i=0;i<lucene.getResultList().size();i++){
			for (int j=0;j<lucene.getResultList().get(i).size();j++){
				System.out.println(lucene.getResultList().get(i).get(j));
			}
			System.out.println("\n");
		}*/
		//System.out.println(mc.getUserNames());



		/*
		artistas= artistarepository.findAll();
		for(Artista artista:artistas)
		{
			System.out.println("> Artista: " + artista.getNombre());

			//lucene.indexSearch(artista.getNombre());

			List<List<String>> lista = lucene.getListaUsuarioTweet(artista.getNombre());

			for(int i=0;i<lista.size();i++)
			{
				for (int j=0;j<lista.get(i).size();j++)
				{
					System.out.println("> Usuarios para artista " + artista.getNombre() + ":");
					System.out.println(lista.get(i).get(j));
				}
				System.out.println("\n");
			}
		}
		System.out.println("> Usuarios: ");
		System.out.println(mc.getUserNames());
		*/
	}
}