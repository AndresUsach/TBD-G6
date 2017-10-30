package edu.usach.tbdgrupo5.rest;

import edu.usach.tbdgrupo5.entities.Artista;
import edu.usach.tbdgrupo5.entities.Pais;
import edu.usach.tbdgrupo5.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/paises")
public class PaisService
{
    @Autowired
    private PaisRepository paisRepository;

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
}
