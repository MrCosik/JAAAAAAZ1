package pl.edu.pjwstk.jaz;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AverageController {
    public AverageResult getAverage(@RequestParam(value = "numbers",required = false) String numbers){

        return new AverageResult("aAAAAAAAAAAAAA");
    }
}
