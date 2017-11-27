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

import java.lang.Math;
import java.text.DecimalFormat;

import edu.usach.tbdgrupo5.entities.Genero;
import edu.usach.tbdgrupo5.repository.GeneroRepository;

@CrossOrigin(origins = "http://localhost:8085")
@RestController  
@RequestMapping("/generos")
public class GeneroService {
	
	@Autowired
	private GeneroRepository generoRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Genero> getAllUsers()
	{
		double totalPositivos = 0.0;
		double totalNegativos = 0.0;

		Iterable<Genero> generos = generoRepository.findAll();

		for(Genero genero:generos)
		{
			totalPositivos = totalPositivos + genero.getComentariosPositivos();
			totalNegativos = totalNegativos + genero.getComentariosNegativos();
		}
		for(Genero genero:generos)
		{
			genero.setComentariosPositivos( this.roundTwoDecimals(( genero.getComentariosPositivos() * 100.0 / (totalNegativos + totalPositivos) )) );
			genero.setComentariosNegativos( this.roundTwoDecimals((-genero.getComentariosNegativos() * 100.0 / (totalNegativos + totalPositivos) )) );
		}
		return generos;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Genero findOne(@PathVariable("id") Integer id) {
		return generoRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Genero create(@RequestBody Genero resource) {
	     return generoRepository.save(resource);
	}

	public double roundTwoDecimals(double d)
	{
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d).replace(',', '.'));
	}


}
