package badukegg.hackathon.hackathon.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuccessController {

    @GetMapping("/api/success")
    public String success(){
        return "success";
    }
}
