package hello;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api")
public class HelloController {

	@Autowired
	PersonRepository personRepo;
	
	@Autowired
	HelloProperties props;
	
    @RequestMapping("/greetings")
    public String index() {
    	return "Greetings from Spring Boot! ";
    }
    
    @RequestMapping(value = "/salutaBilbo", method = RequestMethod.GET)
    public String salutaBilbo() {
    	Person person;
    	List<Person> persons = personRepo.findByLastName("Baggins");
    	if (persons.isEmpty())
			person = personRepo.save(new Person("Bilbo","Baggins"));
    	else
			person = persons.get(0);
        
    	return "Greetings from Spring Boot! " + person.toString();
    }

    @RequestMapping(value = "/provaPost", method = RequestMethod.POST)
    @ResponseBody
    public Person provaPost(@RequestBody Person p) {
    	List<Person> persons = personRepo.findByLastName(p.getLastName());
    	if(persons.isEmpty())
    		return null;
    	return persons.get(0);
    }
    
    @RequestMapping(value = "/provaProp", method = RequestMethod.GET)
	public String provaProp(@RequestParam String name) {
		return props.getGreeting() + name;
	}


}