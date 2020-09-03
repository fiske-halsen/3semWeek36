/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import entity.Animal;
import java.util.Random;
import javax.persistence.Query;
import javax.ws.rs.PathParam;

@Path("animal_db")
public class AnimalFromDB {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    public AnimalFromDB() {
    }

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }
    
    
@Path("animalbyid/{id}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimal(@PathParam("id") int id) {
     EntityManager em = emf.createEntityManager();
        
    
    TypedQuery query = em.createQuery("SELECT a FROM Animal a WHERE a.id = '"+ id +"'", Animal.class);
    
    Animal animal = (Animal)query.getSingleResult();
    
    return GSON.toJson(animal);
    
  //Hvis den kaldes med .../animalbyid/2  vil id nu være lig 2.
  //Den værdi kan I så benytte til at slå op i databasen med em.find(

    
    
}
   
@Path("animalbytype/{type}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getAnimalType(@PathParam("type") String type) {
     EntityManager em = emf.createEntityManager();
        
    
    TypedQuery query = em.createQuery("SELECT a FROM Animal a WHERE a.type = '"+ type +"'", Animal.class);
    
    Animal animal = (Animal)query.getSingleResult();
    
    return GSON.toJson(animal);
    
  //Hvis den kaldes med .../animalbyid/2  vil id nu være lig 2.
  //Den værdi kan I så benytte til at slå op i databasen med em.find(
     
}

@Path("random_animal")
@GET
@Produces(MediaType.APPLICATION_JSON)
public String getRandomAnimal() {
    EntityManager em = emf.createEntityManager();
    
    Query countQuery = em.createQuery("Select count(a) FROM Animal a");
    
   long count =  (long)countQuery.getSingleResult();
   
    System.out.println(count);
    
    Random random = new Random();
    
    int randomNumber = random.nextInt((int) count);
    
    Query selectQuery = em.createQuery("SELECT a FROM Animal a where a.id = '"+ randomNumber +"'", Animal.class );
    
    Animal animal = (Animal)selectQuery.getSingleResult();
   
    return GSON.toJson(animal);
  
      
  //Hvis den kaldes med .../animalbyid/2  vil id nu være lig 2.
  //Den værdi kan I så benytte til at slå op i databasen med em.find(
     
}



    

}
