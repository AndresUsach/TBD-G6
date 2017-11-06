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
        Map<String, Object> inside1 = mapTriple("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(1).getNombre(), "cantidadPositivos", this.findArtistaByCountry(1).getComentariosPositivos() );
        Map<String, Object> inside2 = mapTriple("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(2).getNombre(), "cantidadPositivos", this.findArtistaByCountry(2).getComentariosPositivos());
        Map<String, Object> inside3 = mapTriple("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(3).getNombre(), "cantidadPositivos", this.findArtistaByCountry(3).getComentariosPositivos());
        Map<String, Object> inside4 = mapTriple("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(4).getNombre(), "cantidadPositivos", this.findArtistaByCountry(4).getComentariosPositivos());
        Map<String, Object> inside5 = mapTriple("fillKey", "CentroAmerica", "artista", this.findArtistaByCountry(5).getNombre(), "cantidadPositivos", this.findArtistaByCountry(5).getComentariosPositivos());
        Map<String, Object> inside6 = mapTriple("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(6).getNombre(), "cantidadPositivos", this.findArtistaByCountry(6).getComentariosPositivos());
        Map<String, Object> inside7 = mapTriple("fillKey", "CentroAmerica", "artista", this.findArtistaByCountry(7).getNombre(), "cantidadPositivos", this.findArtistaByCountry(7).getComentariosPositivos());
        Map<String, Object> inside8 = mapTriple("fillKey", "CentroAmerica", "artista", this.findArtistaByCountry(8).getNombre(), "cantidadPositivos", this.findArtistaByCountry(8).getComentariosPositivos());
        Map<String, Object> inside9 = mapTriple("fillKey", "NorteAmerica", "artista", this.findArtistaByCountry(9).getNombre(), "cantidadPositivos", this.findArtistaByCountry(9).getComentariosPositivos());
        Map<String, Object> inside10 = mapTriple("fillKey", "CentroAmerica", "artista", this.findArtistaByCountry(10).getNombre(), "cantidadPositivos", this.findArtistaByCountry(10).getComentariosPositivos());
        Map<String, Object> inside11 = mapTriple("fillKey", "CentroAmerica", "artista", this.findArtistaByCountry(11).getNombre(), "cantidadPositivos", this.findArtistaByCountry(11).getComentariosPositivos());
        Map<String, Object> inside12 = mapTriple("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(12).getNombre(), "cantidadPositivos", this.findArtistaByCountry(12).getComentariosPositivos());
        Map<String, Object> inside13 = mapTriple("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(13).getNombre(), "cantidadPositivos", this.findArtistaByCountry(13).getComentariosPositivos());
        Map<String, Object> inside14 = mapTriple("fillKey", "CentroAmerica", "artista", this.findArtistaByCountry(14).getNombre(), "cantidadPositivos", this.findArtistaByCountry(14).getComentariosPositivos());
        Map<String, Object> inside15 = mapTriple("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(15).getNombre(), "cantidadPositivos", this.findArtistaByCountry(15).getComentariosPositivos());
        Map<String, Object> inside16 = mapTriple("fillKey", "SurAmerica", "artista", this.findArtistaByCountry(16).getNombre(), "cantidadPositivos", this.findArtistaByCountry(16).getComentariosPositivos());

        Map<String, Object> inside17 = mapTriple("fillKey", "CentroAmerica", "artista", "unknown", "cantidadPositivos", 0);
        Map<String, Object> inside18 = mapTriple("fillKey", "CentroAmerica", "artista", "unknown", "cantidadPositivos", 0);

        return mapCountry(inside1,inside2,inside3,inside4,inside5,inside6,inside7,inside8,inside9,
                inside10,inside11,inside12,inside13,inside14,inside15,inside16,inside17,inside18);
    }

    private Map<String, Object> mapTriple(String key1, Object value1, String key2, Object value2, String key3, Object value3) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
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
