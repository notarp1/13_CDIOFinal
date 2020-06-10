package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloService {
    @GET
    public String getHello(){
        return "Hello";
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public String postHello(String name){
        return "Hello " + name;

    }

}
