package edu.usach.tbdgrupo5.rest;

import java.io.FileWriter;
import java.text.DecimalFormat;

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
import edu.usach.tbdgrupo5.entities.Pais;
import edu.usach.tbdgrupo5.entities.Usuario;
import edu.usach.tbdgrupo5.repository.UsuarioRepository;
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
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Artista> getAllArtists() {

		double totalPositivos = 0.0;
		double totalNegativos = 0.0;

		Iterable<Artista> artistas = artistaRepository.findAll();
		for(Artista artista:artistas)
		{
			totalPositivos = totalPositivos + artista.getComentariosPositivos();
			totalNegativos = totalNegativos + artista.getComentariosNegativos();
		}
		for(Artista artista:artistas)
		{
			artista.setComentariosPositivos( this.roundTwoDecimals(( artista.getComentariosPositivos() * 100.0 / (totalNegativos + totalPositivos) )) );
			artista.setComentariosNegativos( this.roundTwoDecimals((-artista.getComentariosNegativos() * 100.0 / (totalNegativos + totalPositivos) )) );
		}
		return artistas;
	}
	public void updateKeyWords(){
		Iterable<Artista> artistas = getAllArtists();
		
		try{
			FileWriter fichero = new FileWriter("words.dat");
			FileWriter ficheroverificador = new FileWriter("verificador.txt");
			ficheroverificador.write("true");
			for(Artista artista:artistas){
				fichero.write(artista.getNombre()+ "\n");
			}
			
			fichero.close();
			ficheroverificador.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	@RequestMapping(value = "/restart", method = RequestMethod.GET)
	@ResponseBody
	public  void restart() {
		updateKeyWords();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Artista findOne(@PathVariable("id") Integer id) {
		
		return artistaRepository.findOne(id);
	}
	@RequestMapping(value = "/{id}/{idGenero}/{idUsuario}", method = RequestMethod.PUT)
	@ResponseBody
	public void update(@PathVariable("id") Integer id, @PathVariable("idGenero") Integer idGenero, @PathVariable("idUsuario") Integer idUsuario, @RequestBody Artista resource) {
		Artista artista = artistaRepository.findOne(id);
		Genero genero = generoRepository.findOne(idGenero);
		Usuario usuario = usuarioRepository.findOne(idUsuario);
		artista.setComentariosNegativos(0);
		artista.setComentariosPositivos(0);
		artista.setComentariosNeutros(0);
		artista.setDescripcion(resource.getDescripcion());
		artista.setNombre(resource.getNombre());
		artista.setUsuario(usuario);
		artista.setGenero(genero);
		artistaRepository.save(artista);
	}
	
	@RequestMapping(value = "/{idgenero}/{idusuario}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Artista create(@PathVariable("idgenero") Integer idgenero, @PathVariable("idusuario") Integer idusuario,@RequestBody Artista resource) {
		Genero genero = generoRepository.findOne(idgenero);
		Usuario usuario = usuarioRepository.findOne(idusuario);
		resource.setUsuario(usuario);
		resource.setGenero(genero);
	    return artistaRepository.save(resource);
	}

	public double roundTwoDecimals(double d)
	{
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d).replace(',', '.'));
	}
	 
}
