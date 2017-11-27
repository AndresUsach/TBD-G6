package edu.usach.tbdgrupo5.rest;

import edu.usach.tbdgrupo5.entities.Artista;
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
import java.util.ArrayList;
import java.util.List;

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
	public List<Genero> getAllUsers()
	{

		List<Genero> generoList = new ArrayList<Genero>();

		double totalPositivos = 0.0;
		double totalNegativos = 0.0;

		double max = 0;

		Iterable<Genero> generos = generoRepository.findAll();

		for(Genero genero:generos)
		{
			if(max < genero.getComentariosNegativos())
			{
				max = genero.getComentariosNegativos();
			}
			if(max < genero.getComentariosPositivos())
			{
				max = genero.getComentariosPositivos();
			}
			totalPositivos = totalPositivos + genero.getComentariosPositivos();
			totalNegativos = totalNegativos + genero.getComentariosNegativos();
		}
		for(Genero genero:generos)
		{
			genero.setComentariosPositivos( this.roundTwoDecimals(( genero.getComentariosPositivos() * 100.0 / (totalNegativos + totalPositivos) )) );
			genero.setComentariosNegativos( this.roundTwoDecimals((-genero.getComentariosNegativos() * 100.0 / (totalNegativos + totalPositivos) )) );
		}
		for(Genero genero:generos)
		{
			generoList.add(genero);
		}

		Genero falso = new Genero();

		falso.setNombre("Margen");
		falso.setComentariosNegativos(this.roundTwoDecimals(( -max * 100.0 / (totalNegativos + totalPositivos) )));
		falso.setComentariosPositivos(this.roundTwoDecimals(( max * 100.0 / (totalNegativos + totalPositivos) )));
		falso.setComentariosNeutros(1);

		generoList.add(falso);
		return generoList;

		/*
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
		*/
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
