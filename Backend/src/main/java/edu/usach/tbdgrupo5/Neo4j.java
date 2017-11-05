package edu.usach.tbdgrupo5;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import sun.rmi.transport.ObjectTable;

import java.util.*;

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

    public List<Map<String, Object>> personNodes = new ArrayList<>();
    public List<Map<String, Object>> gameNodes = new ArrayList<>();

    public List<Map<String, Object>> playsRel = new ArrayList<>();
    public List<Map<String, Object>> nodesListTest = new ArrayList<>();

    public List<Map<String, Object>> nodeList = new ArrayList<>();
    public List<Map<String, Object>> tweetRel = new ArrayList<>();

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

    public void deleteAll()
    {
        this.session.run("match (a)-[r]->(b) delete r");
        this.session.run("match (n) delete n");
    }

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


}