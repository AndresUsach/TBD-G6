package edu.usach.tbdgrupo5.rest;

import edu.usach.tbdgrupo5.entities.Artista;
import edu.usach.tbdgrupo5.entities.Pais;
import edu.usach.tbdgrupo5.repository.ArtistaRepository;
import edu.usach.tbdgrupo5.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/paises")
public class PaisService
{
    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Pais> getAllUsers() {
        return paisRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public  Pais findOne(@PathVariable("id") Integer id) {
        return paisRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Pais create(@RequestBody Pais resource) {
        return paisRepository.save(resource);
    }

    @RequestMapping(value = "/{id}/artista", method = RequestMethod.GET)
    public Artista findArtistaByCountry(@PathVariable("id") Integer id)
    {
        Pais pais = paisRepository.findOne(id);
        return pais.getArtista();
    }

    @RequestMapping(value = "/mostrarMapa", method = RequestMethod.GET)
    public Map<String, Object> mostrarMapa()
    {
        Map<String, Object> inside1 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(1).getNombre());
        Map<String, Object> inside2 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(2).getNombre());
        Map<String, Object> inside3 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(3).getNombre());
        Map<String, Object> inside4 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(4).getNombre());
        Map<String, Object> inside5 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(5).getNombre());
        Map<String, Object> inside6 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(6).getNombre());
        Map<String, Object> inside7 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(7).getNombre());
        Map<String, Object> inside8 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(8).getNombre());
        Map<String, Object> inside9 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(9).getNombre());
        Map<String, Object> inside10 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(10).getNombre());
        Map<String, Object> inside11 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(11).getNombre());
        Map<String, Object> inside12 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(12).getNombre());
        Map<String, Object> inside13 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(13).getNombre());
        Map<String, Object> inside14 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(14).getNombre());
        Map<String, Object> inside15 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(15).getNombre());
        Map<String, Object> inside16 = mapDouble("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(16).getNombre());

        Map<String, Object> inside17 = mapDouble("fillKey", "SurAmerica", "artista", "unknown");
        Map<String, Object> inside18 = mapDouble("fillKey", "SurAmerica", "artista", "unknown");

        return mapCountry(inside1,inside2,inside3,inside4,inside5,inside6,inside7,inside8,inside9,
                inside10,inside11,inside12,inside13,inside14,inside15,inside16,inside17,inside18);
    }

    private Map<String, Object> mapDouble(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }

    private Map<String, Object> mapCountry(Object value1, Object value2,
                                           Object value3, Object value4,
                                           Object value5, Object value6,
                                           Object value7, Object value8,
                                           Object value9, Object value10,
                                           Object value11, Object value12,
                                           Object value13, Object value14,
                                           Object value15, Object value16,
                                           Object value17, Object value18)
    {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put("ARG", value1);
        result.put("BOL", value2);
        result.put("CHL", value3);
        result.put("COL", value4);
        result.put("CRI", value5);
        result.put("ECU", value6);
        result.put("GTM", value7);
        result.put("HND", value8);
        result.put("MEX", value9);
        result.put("NIC", value10);
        result.put("PAN", value11);
        result.put("PRY", value12);
        result.put("PER", value13);
        result.put("SLV", value14);
        result.put("URY", value15);
        result.put("VEN", value16);

        result.put("PRI", value17);
        result.put("CUB", value18);
        return result;
    }

}
