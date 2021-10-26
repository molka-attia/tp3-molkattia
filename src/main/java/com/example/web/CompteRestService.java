package com.example.web;



import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.CompteRepository;
import com.example.entities.Compte;

@Component
@Path("/Banque")
public class CompteRestService {

	@Autowired
	private CompteRepository compteRepository;
	@GET
	@Path("/Comptes")
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public List<Compte> getComptes(){
		return compteRepository.findAll();
	}
	
	
	
	@GET
	@Path("/Comptes/{solde}")
		public double conversion(@PathParam(value="solde") double solde){
		return solde/3.2;
	}
	
	
	
	@GET
	@Path("/Comptes/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Optional<Compte> getCompte(@PathParam(value="code") long code){
		//return compteRepository.findById(code);
		//return compteRepository.findById(code).get();
		//Optional<Compte> employee = compteRepository.findById(code);
		  Optional<Compte> compt=compteRepository.findById(code);
		  return compt;   
    
           // return employee.get();
        
	}
	
	@DELETE
	@Path("/Comptes/{code}")
	public Response delete(@PathParam(value="code") long code){
		compteRepository.deleteById(code);
		return Response.status(Response.Status.OK).entity(code).build();
	}
	
	@POST
    @Path("/Comptes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(@RequestParam Compte compte){
		compteRepository.save(compte);
	return Response.status(Response.Status.OK).entity(compte).build();
	
	}
	

	
	
/*	@Path("/Comptes/{code}")
	public void update(@PathParam(value="code") long code,Compte compte){
	Compte compt=compteRepository.getById(code);
	compt.setCode(compte.getCode());
	compt.setDatecreation(compte.getDatecreation());
	compt.setSolde(compte.getSolde());
	compteRepository.save(compt);
	}
	*/
	@PUT
	@Path("/Comptes/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Compte updateCompte(@PathParam(value="code") long code,@RequestParam Compte comp) {
		Optional<Compte> compt=Optional.of(compteRepository.findById(comp.getCode())).orElseThrow(RuntimeException::new);
		//Optional<Compte> compt=compteRepository.findById(comp.getCode());
		return compteRepository.save(comp);
	}
	
}

