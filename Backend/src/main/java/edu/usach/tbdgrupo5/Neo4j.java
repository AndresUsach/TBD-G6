package edu.usach.tbdgrupo5;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

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
    public List<Map<String, Object>> nodesList = new ArrayList<>();

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

    public void getPersonNodes()
    {
        StatementResult nodes = session.run("MATCH (a:Person) return a.name as name, a.type as type");

        while(nodes.hasNext())
        {
            //record es un nodo específico
            Record record = nodes.next();
            personNodes.add(mapDouble("type", record.get("type").asString(), "name", record.get("name").asString()));

            //System.out.println(">\nType: " + record.get("type").asString() + "\nName: " + record.get("name").asString());
        }

        for(int i = 0; i< personNodes.size(); i++)
        {
            System.out.println("> personNodes # " + i + ": \n" + personNodes.get(i).get("name"));
        }
    }

    public void getGameNodes()
    {
        StatementResult nodes = session.run("MATCH (a:Game) return a.name as name");
        while(nodes.hasNext())
        {
            Record record = nodes.next();
            gameNodes.add(mapSingle("name", record.get("name").asString()));
        }

        for(int i = 0; i< gameNodes.size(); i++)
        {
            System.out.println("> gameNodes #" + i +": \n" + gameNodes.get(i).get("name"));
        }

    }

    public void getNodes()
    {
        //Label Person
        StatementResult nodes = session.run("MATCH (a:Person) return a.name as name");
        while(nodes.hasNext())
        {
            Record record = nodes.next();
            nodesList.add(mapDouble("name", record.get("name").asString(), "label", "person"));
        }

        //Label Game
        nodes = session.run("MATCH (a:Game) return a.name as name");
        while(nodes.hasNext())
        {
            Record record = nodes.next();
            nodesList.add(mapDouble("name", record.get("name").asString(), "label", "game"));
        }

        for(int i = 0; i< nodesList.size(); i++)
        {
            System.out.println("> nodeList #" + i +":" + nodesList.get(i).get("name") + " " + nodesList.get(i).get("label"));
        }
    }

    public void getPlaysRel()
    {
        int pIndex = -1;
        int gIndex = -1;
        StatementResult rel = session.run("MATCH (a:Person)-[r:Plays]->(b:Game) RETURN a.name as personName, r, b.name as gameName");
        while(rel.hasNext())
        {
            Record record = rel.next();

            for(int i = 0; i< personNodes.size(); i++)
            {
                if(personNodes.get(i).get("name").equals(record.get("personName").asString()))
                {
                    pIndex = i;
                    break;
                }
            }

            for(int i = 0; i< gameNodes.size(); i++)
            {
                if(gameNodes.get(i).get("name").equals(record.get("gameName").asString()))
                {
                    gIndex = i;
                    break;
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

    public void getPlaysRelSingleList()
    {
        int pIndex = -1;
        int gIndex = -1;
        StatementResult rel = session.run("MATCH (a:Person)-[r:Plays]->(b:Game) RETURN a.name as personName, r, b.name as gameName");
        while(rel.hasNext())
        {
            Record record = rel.next();

            for(int i = 0; i< nodesList.size(); i++)
            {
                if(nodesList.get(i).get("name").equals(record.get("personName").asString()))
                {
                    pIndex = i;
                    for(int j = 0; j<nodesList.size(); j++)
                    {
                        if(nodesList.get(j).get("name").equals(record.get("gameName").asString()))
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
        return mapDouble("nodes", nodesList, "links", playsRel);
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


    public void crearEjemplo() {
        /*
            Crea nodos con el label Person y los atributos name y type
        */
        /*
        session.run("CREATE (a:Person {name:'Chris', type:'STARS'})");
        session.run("CREATE (a:Person {name:'Jill', type:'STARS'})");
        session.run("CREATE (a:Person {name:'Leon', type:'Police'})");
        session.run("CREATE (a:Person {name:'Claire', type:'Sister'})");
        session.run("CREATE (a:Person {name:'Wesker', type:'Traitor'})");
        session.run("CREATE (a:Person {name:'Sherry', type:'Girl'})");
        session.run("CREATE (a:Person {name:'Steve', type:'Guy'})");
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
        /*
        session.run("CREATE (a:Game {name:'RE1'})");
        session.run("CREATE (a:Game {name:'RE2'})");
        session.run("CREATE (a:Game {name:'RE3'})");
        session.run("CREATE (a:Game {name:'RECV'})");
        session.run("CREATE (a:Game {name:'RE4'})");
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
        /*
        session.run("match (a:Person) where a.name='Chris' "
                + "  match (b:Game) where b.name='RE1' "
                + "  create (a)-[r:Plays {Role:'Main male character'}]->(b)");

        session.run("match (a:Person) where a.name='Jill' "
                + "  match (b:Game) where b.name='RE1' "
                + "  create (a)-[r:Plays {Role:'Main female character'}]->(b)");

        session.run("match (a:Person) where a.name='Wesker' "
                + "  match (b:Game) where b.name='RE1' "
                + "  create (a)-[r:Plays {Role:'Villain'}]->(b)");

        session.run("match (a:Person) where a.name='Leon' "
                + "  match (b:Game) where b.name='RE2' "
                + "  create (a)-[r:Plays {Role:'Main male character'}]->(b)");

        session.run("match (a:Person) where a.name='Claire' "
                + "  match (b:Game) where b.name='RE2' "
                + "  create (a)-[r:Plays {Role:'Main female character'}]->(b)");

        session.run("match (a:Person) where a.name='Sherry' "
                + "  match (b:Game) where b.name='RE2' "
                + "  create (a)-[r:Plays {Role:'Girl character'}]->(b)");

        session.run("match (a:Person) where a.name='Jill' "
                + "  match (b:Game) where b.name='RE3' "
                + "  create (a)-[r:Plays {Role:'Main character'}]->(b)");

        session.run("match (a:Person) where a.name='Claire' "
                + "  match (b:Game) where b.name='RECV' "
                + "  create (a)-[r:Plays {Role:'Main character'}]->(b)");

        session.run("match (a:Person) where a.name='Steve' "
                + "  match (b:Game) where b.name='RECV' "
                + "  create (a)-[r:Plays {Role:'Secondary character'}]->(b)");

        session.run("match (a:Person) where a.name='Chris' "
                + "  match (b:Game) where b.name='RECV' "
                + "  create (a)-[r:Plays {Role:'Secondary character'}]->(b)");

        session.run("match (a:Person) where a.name='Wesker' "
                + "  match (b:Game) where b.name='RECV' "
                + "  create (a)-[r:Plays {Role:'Villain'}]->(b)");

        session.run("match (a:Person) where a.name='Leon' "
                + "  match (b:Game) where b.name='RE4' "
                + "  create (a)-[r:Plays {Role:'Main character'}]->(b)");

        session.run("match (a:Person) where a.name='Wesker' "
                + "  match (b:Game) where b.name='RE4' "
                + "  create (a)-[r:Plays {Role:'Hidden character'}]->(b)");
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