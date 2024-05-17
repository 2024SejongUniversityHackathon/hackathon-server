package badukegg.hackathon.hackathon.common;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuccessController {

    @GetMapping("/api/success")
    public String success(){
        return "success";
    }

    @GetMapping("/health-check")
    public ResponseEntity<Void> index() {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
