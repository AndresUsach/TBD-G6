package edu.usach.tbdgrupo5.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.usach.tbdgrupo5.entities.Artista;
import edu.usach.tbdgrupo5.entities.Genero;
import edu.usach.tbdgrupo5.repository.ArtistaRepository;
import edu.usach.tbdgrupo5.repository.GeneroRepository;

@CrossOrigin(origins = "http://localhost:8085")
@RestController  
@RequestMapping("/artistas")
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Autowired
	private GeneroRepository generoRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Artista> getAllUsers() {
		return artistaRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Artista findOne(@PathVariable("id") Integer id) {
		return artistaRepository.findOne(id);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) {
		artistaRepository.delete(id);
		return ;
	}
	
	@RequestMapping(value = "/{idgenero}/genero", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Artista create(@PathVariable("idegenero") Integer idgenero,@RequestBody Artista resource) {
		Genero genero = generoRepository.findOne(idgenero);
		resource.setGenero(genero);
	    return artistaRepository.save(resource);
	}
	 
}
