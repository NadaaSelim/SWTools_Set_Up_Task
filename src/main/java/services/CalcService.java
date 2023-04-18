package services;



import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;


import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Calculation;


@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api")
public class CalcService{
	
	
	@PersistenceContext(unitName = "Calc_Task")
	private EntityManager em;
	
	@POST
	@Path("calc")
	public JsonObject performCalc(InputStream input){
		
		
		Calculation c = new Calculation();
		em.persist(c);
		
		 // Create a JsonReader object to read the JSON input from the InputStream
        JsonReader reader = Json.createReader(new InputStreamReader(input));

        // Parse the JSON input to a JsonObject
        JsonObject jsonInput = reader.readObject();
        
        c.setNumber1(Integer.parseInt(jsonInput.getString("number1")));
        c.setNumber2(Integer.parseInt(jsonInput.getString("number2")));
        c.setOperation(jsonInput.getString("operation"));
        
        c.perform();
        
        // Create an instance of JsonObjectBuilder and add property/value
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder().add("Result", c.getResult());
        // Create a JSON object with the builder
        JsonObject jsonOutput = objectBuilder.build();
        
        return jsonOutput;
	}
	
	@GET
	@Path("calculations")
	public List<JsonObject> getAllCalcs() {
		
		TypedQuery<Calculation> q = em.createQuery("select c from Calculation c", Calculation.class);
		List<Calculation> calcList = q.getResultList();
		List<JsonObject> jsonList =  new ArrayList<JsonObject> ();
		
		 for (Calculation c : calcList) {
			 JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
					 .add("number1", c.getNumber1())
					 .add("number2", c.getNumber2())
					 .add("operation", c.getOperation());
			 JsonObject jsonOutput = objectBuilder.build();
			 jsonList.add(jsonOutput);
		 }
        
		return jsonList;
		
	}

}