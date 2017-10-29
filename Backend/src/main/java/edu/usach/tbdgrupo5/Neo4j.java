package edu.usach.tbdgrupo5;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

public class Neo4j
{

    /*
        Correr de la siguiente forma:

		Neo4j neo = new Neo4j();
		neo.connect("bolt://localhost", "neo4j", "root");
		neo.deleteAll();
		neo.neoEjemplo();
		neo.disconnect();
    */

    private Driver driver;
    private Session session;

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


    public void neoEjemplo()
    {
        /*
            Crea un nodo con el label Person y los atributos name y title
        */
        session.run( "CREATE (a:Person {name:'Arthur', title:'King'})");
        session.run( "CREATE (a:Person {name:'Lancelot', title:'Sir'})");
        session.run( "CREATE (a:Person {name:'Merlin', title:'Wizard'})");

        /*
            Toma todos los nodos con label Person
        */
        StatementResult result = session.run( "MATCH (a:Person) return a.name as name, a.title as title");

        /*
            Imprime todos los resultados
        */
        while ( result.hasNext() )
        {
            Record record = result.next();
            System.out.println( record.get( "title" ).asString() + " " + record.get("name").asString() );
        }

        /*
            Establece relaciones entre los nodos.
            Primero los busca y luego define la relación
        */
        session.run("match (a:Person) where a.name='Lancelot' "
                + "  match (b:Person) where b.name='Arthur' "
                + "  create (a)-[r:Loyal {reason:'He love Arthur in secret :3'}]->(b)");

        session.run("match (a:Person) where a.name='Merlin'"
                + "match (b:Person) where b.name='Arthur'"
                + "create (a)-[r:Advise]->(b)");

    }

}