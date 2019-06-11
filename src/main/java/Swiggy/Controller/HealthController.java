package Swiggy.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController{
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity getHealth(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
