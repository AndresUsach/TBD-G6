package edu.usach.tbdgrupo5.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import edu.usach.tbdgrupo5.entities.Usuario;
import edu.usach.tbdgrupo5.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:8085")
@RestController  
@RequestMapping("/usuarios")
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Usuario> getAllUsers() {
		return usuarioRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Usuario findOne(@PathVariable("id") Integer id) {
		return usuarioRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Usuario create(@RequestBody Usuario resource) {
	     return usuarioRepository.save(resource);
	}
	 
}