package edu.usach.tbdgrupo5;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component
public class Neo4j
{

    /*
        Correr de la siguiente forma:

		Neo4j neo = new Neo4j();
		neo.connect("bolt://localhost", "neo4j", "root");
		neo.deleteAll();
		neo.crearEjemplo();
		neo.disconnect();
    */

    private Driver driver;
    private Session session;

    public List<Map<String, Object>> playsRel = new ArrayList<>();
    public List<Map<String, Object>> nodesListTest = new ArrayList<>();

    public List<Map<String, Object>> listaNodos = new ArrayList<>();
    public List<Map<String, Object>> listaRelTweet = new ArrayList<>();

    public void connect(String uri, String username, String password)
    {
        /*
            uriConnection = bolt://localhost
            username = neo4j
            password = root -> cambiar contraseña si usaron otra.
        */
        this.driver = GraphDatabase.driver( uri, AuthTokens.basic( username, password ) );
        this.session = driver.session();
    }

    public void disconnect()
    {
        session.close();
        driver.close();
    }

    public void deleteNodeWithoutRel()
    {
        this.session.run("MATCH (n) WHERE size((n)--())=0 DELETE (n)");
    }

    public void deleteAll()
    {
        this.session.run("match (a)-[r]->(b) delete r");
        this.session.run("match (n) delete n");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void crearNodoPerson(String name, String type)
    {
        String query = "CREATE (a:Person" + "{" + "name:" + "'" + name + "'" + "," + "type:" + "'" + type + "'" + "})";
        session.run(query);
    }

    public void crearNodoGame(String name)
    {
        String query = "CREATE (a:Game" + "{" + "name:" + "'" + name + "'" + "})";
        session.run(query);
    }

    public void crearRelPlays(String personName, String gameName, String role)
    {
        String query = "match (a:Person) where a.name='" + personName + "' "
                + "  match (b:Game) where b.name='" + gameName + "' "
                + "  create (a)-[r:Plays {Role:'" + role + "'}]->(b)";
        session.run(query);
    }

    public void getNodes()
    {
        int x = 0;

        //Label person 4
        StatementResult nodes = session.run("MATCH (a:Person) return a.name as name, a.type as type");
        while(nodes.hasNext())
        {
            Record record = nodes.next();
            nodesListTest.add(mapQuadruple("id", x, "userName", record.get("name").asString(), "tweet", record.get("type").asString() ,"weight", x));
            x++;
        }

        //Label game 4
        nodes = session.run("MATCH (a:Game) return a.name as name");
        while(nodes.hasNext())
        {
            Record record = nodes.next();
            nodesListTest.add(mapQuadruple("id", x,"userName", record.get("name").asString(), "tweet", "nothing" ,"weight", x));
            x++;
        }
    }

    public void getPlaysRelSingleList()
    {
        int pIndex = -1;
        int gIndex = -1;
        StatementResult rel = session.run("MATCH (a:Person)-[r:Plays]->(b:Game) RETURN a.name as personName, r, b.name as gameName");
        while(rel.hasNext())
        {
            Record record = rel.next();

            for(int i = 0; i< nodesListTest.size(); i++)
            {
                if(nodesListTest.get(i).get("userName").equals(record.get("personName").asString()))
                {
                    pIndex = i;
                    for(int j = 0; j< nodesListTest.size(); j++)
                    {
                        if(nodesListTest.get(j).get("userName").equals(record.get("gameName").asString()))
                        {
                            gIndex = j;
                            break;
                        }
                    }
                }
            }

            //System.out.println(">>> REL: " + pIndex + " , " + gIndex);

            playsRel.add(mapDouble("source", pIndex, "target", gIndex));
        }

        for(int i = 0; i<playsRel.size(); i++)
        {
            System.out.println("> Plays = source: " + playsRel.get(i).get("source") + ", target: " + playsRel.get(i).get("target"));
        }

    }

    public Map<String, Object> makeGraphFormat()
    {
        return mapDouble("nodes", nodesListTest, "links", playsRel);
    }


    public void crearEjemplo() {
        /*
            Crea nodos con el label Person y los atributos name y type
        */
        this.crearNodoPerson("Chris", "STARS");
        this.crearNodoPerson("Jill", "STARS");
        this.crearNodoPerson("Leon", "Police");
        this.crearNodoPerson("Claire", "Sister");
        this.crearNodoPerson("Wesker", "Traitor");
        this.crearNodoPerson("Sherry", "Girl");
        this.crearNodoPerson("Steve", "Guy");

        /*
            Crea nodos con el label Game y el atributo name
        */
        this.crearNodoGame("RE1");
        this.crearNodoGame("RE2");
        this.crearNodoGame("RE3");
        this.crearNodoGame("RECV");
        this.crearNodoGame("RE4");

        /*
            Establece relaciones entre los nodos.
            Primero los busca y luego define la relación
            Label relación: Plays.
        */
        this.crearRelPlays("Chris", "RE1", "Main male character");
        this.crearRelPlays("Jill", "RE1", "Main female character");
        this.crearRelPlays("Wesker", "RE1", "Villain");
        this.crearRelPlays("Leon", "RE2", "Main male character");
        this.crearRelPlays("Claire", "RE2", "Main female character");
        this.crearRelPlays("Sherry", "RE2", "Girl character");
        this.crearRelPlays("Jill", "RE3", "Main character");
        this.crearRelPlays("Claire", "RECV", "Main character");
        this.crearRelPlays("Steve", "RECV", "Secondary character");
        this.crearRelPlays("Wesker", "RECV", "Villain");
        this.crearRelPlays("Leon", "RE4", "Main character");
        this.crearRelPlays("Wesker", "RE4", "Hidden character");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Map<String, Object> mapQuadruple(String key1, Object value1, String key2, Object value2,
                                             String key3, Object value3, String key4, Object value4)
    {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
        result.put(key4, value4);
        return result;
    }

    private Map<String, Object> mapDouble(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }

    private Map<String, Object> mapSingle(String key1, Object value1) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        return result;
    }

    public void crearNodosArtistas() throws SQLException
    {

        List artistas = new ArrayList();

        Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost:3306/tbd?useSSL=false","root", "root");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery ("SELECT * FROM artista");

        while (rs.next())
        {
            artistas.add(rs.getString("nombre"));
            //System.out.println (rs.getInt("idartista") + " " + rs.getString("nombre") );

        }

        conexion.close();

        for(int i=0; i<artistas.size(); i++)
        {
            //System.out.println("> Nombre: " + artistas.get(i));
            String query = "CREATE (a:Artista" + "{" + "name:" + "'" + artistas.get(i) + "'" + "})";
            session.run(query);
        }

    }

    public void crearNodosUsuarios()
    {
        MongoConnection mc = new MongoConnection("tweets", "tweetsPrueba");
        mc.connect();
        List<String> listaUserNames = mc.getUserNames();

        for(int i=0; i<listaUserNames.size(); i++)
        {
            //System.out.println("> Usuario: " + listaUserNames.get(i));
            String query = "CREATE (a:Usuario" + "{" + "name:" + "'" + listaUserNames.get(i) + "'" + "})";
            session.run(query);
        }
        mc.disconnect();
    }

    public void crearRelacionTweet()
    {
        MongoConnection mc = new MongoConnection("tweets", "tweetsPrueba");
        Lucene lucene = new Lucene(mc);

        //Toma todos los artistas
        StatementResult artistas = session.run("MATCH (a:Artista) return a.name as name");
        while(artistas.hasNext())
        {
            //Para cada artista
            Record recordArtista = artistas.next();
            List<Tweet> lista = lucene.getTweets(recordArtista.get("name").asString());

            for(int i=0;i<lista.size();i++)
            {
                //Filtrar por seguidores
                if(lista.get(i).getFollowers() > 104 && lista.get(i).getFollowers()<10000)
                {
                    //System.out.println("> Tweet #" + j + ": " + lista.get(i).get(j));

                    //Reemplaza caracter de escape ' por "
                    String tweetModified = lista.get(i).getText().replaceAll("'", "\"");

                    String followerRank = Double.toString(lista.get(i).getFollowerRank());

                    //System.out.println("> TweetModificado #" + j + ": " + tweetModified);

                    String query = "match (a:Usuario) where a.name='" + lista.get(i).getUserName() + "' "
                            + "  match (b:Artista) where b.name='" + recordArtista.get("name").asString() + "' "
                            + "  create (a)-[r:Tweet {texto:'" + tweetModified + "'" + ", followerRank:'" + followerRank + "'}]->(b)";
                    session.run(query);

                }

            }
        }
    }

    public void getNodosUsuarioArtista()
    {
        int x = 0;

        List<String> agregados = new ArrayList<String>();

        //Label Usuario 4
        StatementResult nodes = session.run("MATCH (u:Usuario)-[r:Tweet]-(a:Artista) RETURN u.name AS usuario, r.followerRank as followerRank, r.texto AS texto, a.name AS artista ORDER BY r.followerRank DESC LIMIT 80");
        while(nodes.hasNext())
        {
            Record record = nodes.next();

            if(agregados.contains( record.get("usuario").asString() ))
            {

            }
            else
            {
                listaNodos.add(mapQuadruple("id", x, "userName", record.get("usuario").asString(), "tweet", record.get("texto").asString() ,"weight", Double.parseDouble(record.get("followerRank").asString()) ));
                x++;
                agregados.add( record.get("usuario").asString() );
            }
        }

        //Label Artista 4
        nodes = session.run("MATCH (a:Artista) return a.name as artista");
        while(nodes.hasNext())
        {
            Record record = nodes.next();
            listaNodos.add(mapQuadruple("id", x,"userName", record.get("artista").asString(), "tweet", "nothing" ,"weight", 2));
            x++;
        }
    }

    public void getRelTweetNodo()
    {
        int uIndex = -1;
        int aIndex = -1;
        //StatementResult rel = session.run("MATCH (a:Usuario)-[r:Tweet]->(b:Artista) RETURN a.name as usuario, r, b.name as artista");
        StatementResult rel = session.run("MATCH (u:Usuario)-[r:Tweet]-(a:Artista) RETURN u.name AS usuario, r.followerRank as followerRank, r.texto AS texto, a.name AS artista ORDER BY r.followerRank DESC LIMIT 80");
        while(rel.hasNext())
        {
            Record record = rel.next();

            for(int i = 0; i< listaNodos.size(); i++)
            {
                if(listaNodos.get(i).get("userName").equals(record.get("usuario").asString()))
                {
                    uIndex = Integer.parseInt(listaNodos.get(i).get("id").toString());

                    for(int j = 0; j< listaNodos.size(); j++)
                    {
                        //System.out.println("> userName: " + listaNodos.get(j).get("userName") + " artista: " + record.get("artista").asString());

                        if(listaNodos.get(j).get("userName").equals(record.get("artista").asString()))
                        {
                            aIndex = Integer.parseInt(listaNodos.get(j).get("id").toString());
                            break;
                        }
                    }
                }
            }

            listaRelTweet.add(mapDouble("source", uIndex, "target", aIndex));
        }
    }

    public void crearGrafo() throws SQLException {
        this.deleteAll();

        this.crearNodosArtistas();

        this.crearNodosUsuarios();

        this.crearRelacionTweet();

        this.deleteNodeWithoutRel();

        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
        this.deleteNodeWithoutRel();
    }

    public Map<String, Object> mostrarGrafo()
    {
        this.getNodosUsuarioArtista();
        this.getRelTweetNodo();
        return mapDouble("nodes", listaNodos, "links", listaRelTweet);
    }

    public Map<String, Object> tweetMasInfluyente()
    {
        List<Map<String, Object>> lista = new ArrayList<>();

        StatementResult nodes = session.run("MATCH (u:Usuario)-[r:Tweet]-(a:Artista) RETURN u.name AS usuario, r.followerRank as followerRank, r.texto AS texto, a.name AS artista ORDER BY r.followerRank DESC LIMIT 1");
        while(nodes.hasNext())
        {
            Record record = nodes.next();
            lista.add(mapQuadruple("id", 0, "userName", record.get("name").asString(), "tweet", record.get("texto").asString() ,"weight", Double.parseDouble(record.get("followerRank").asString()) ));
        }
        return lista.get(0);
    }



}