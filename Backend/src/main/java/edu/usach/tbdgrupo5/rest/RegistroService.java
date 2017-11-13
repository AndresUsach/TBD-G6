package edu.usach.tbdgrupo5.rest;

import edu.usach.tbdgrupo5.Time;
import edu.usach.tbdgrupo5.entities.Artista;
import edu.usach.tbdgrupo5.entities.Registro;
import edu.usach.tbdgrupo5.repository.ArtistaRepository;
import edu.usach.tbdgrupo5.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
@RequestMapping("/registro")
public class RegistroService
{
    private Time time;

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Registro> getAll() {
        return registroRepository.findAll();
    }

    @RequestMapping( value = "/archivar" ,method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String create()
    {
        Iterable<Artista> artistas = artistaRepository.findAll();

        this.time = Time.getInstance();

        for(Artista artista:artistas)
        {
            Registro registro = new Registro();
            registro.setNombre(artista.getNombre());
            registro.setFecha(this.time.getCurrentDate());
            registro.setComentariosNegativos(artista.getComentariosNegativos());
            registro.setComentariosNeutros(artista.getComentariosNeutros());
            registro.setComentariosPositivos(artista.getComentariosPositivos());
            registroRepository.save(registro);
        }
        return "Created";
    }

    @RequestMapping( value = "/artista/{nombre}" ,method = RequestMethod.GET)
    @ResponseBody
    public List<Registro> getRegistroArtista(@PathVariable("nombre") String nombre)
    {
        List<Registro> list = new ArrayList<Registro>();
        Iterable<Registro> registros = registroRepository.findAll();

        for(Registro registro:registros)
        {
            if(registro.getNombre().equals(nombre))
            {
                list.add(registro);
            }
        }
        return list;
    }

}
