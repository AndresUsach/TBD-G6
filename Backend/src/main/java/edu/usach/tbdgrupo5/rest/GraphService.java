package edu.usach.tbdgrupo5.rest;

import edu.usach.tbdgrupo5.Neo4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/graph")
public class GraphService {

    private Neo4j neo;
    private Map<String, Object> graphFormat;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> graph() {
        neo = new Neo4j();
        neo.connect("bolt://localhost", "neo4j", "root");

        neo.deleteAll();
        neo.crearEjemplo();

        neo.getNodes();
        neo.getPlaysRelSingleList();

        this.graphFormat = neo.makeGraphFormat();

        neo.disconnect();
        return this.graphFormat;
    }
}
