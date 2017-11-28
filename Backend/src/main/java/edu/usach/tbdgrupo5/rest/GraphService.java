package edu.usach.tbdgrupo5.rest;

import edu.usach.tbdgrupo5.Neo4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/graph")
public class GraphService {

    private Neo4j neo;
    private Map<String, Object> grafo;
    private Map<String, Object> mas;

    @RequestMapping( method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> mostrarGrafo() throws SQLException {
        neo = new Neo4j();
        neo.connect("bolt://localhost", "neo4j", "root");

        this.grafo = neo.mostrarGrafo();

        neo.disconnect();

        return this.grafo;
    }

    @RequestMapping( value = "/crearGrafo" ,method = RequestMethod.GET)
    @ResponseBody
    public void crearGrafo() throws SQLException {
        neo = new Neo4j();
        neo.connect("bolt://localhost", "neo4j", "root");

        neo.crearGrafo();

        neo.disconnect();
    }

    @RequestMapping( value = "/ejemplo" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> graph() {
        neo = new Neo4j();
        neo.connect("bolt://localhost", "neo4j", "root");

        neo.deleteAll();
        neo.crearEjemplo();

        neo.getNodes();
        neo.getPlaysRelSingleList();

        this.grafo = neo.makeGraphFormat();

        neo.disconnect();
        return this.grafo;
    }

    @RequestMapping( value = "/masInfluyente" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> masInfluyente() {
        neo = new Neo4j();
        neo.connect("bolt://localhost", "neo4j", "root");
        this.mas = neo.tweetMasInfluyente();
        neo.disconnect();
        return this.mas;
    }

}
