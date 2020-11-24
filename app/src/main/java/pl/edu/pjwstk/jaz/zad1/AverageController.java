package pl.edu.pjwstk.jaz.zad1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;



@RestController
public class AverageController {

    @GetMapping("average")
    public AverageResult getAverage(@RequestParam(value = "numbers",required = false) String numbers){
        if(numbers == null || numbers.equals("")) {
            return new AverageResult("Please put parameters.");
        }

        String[] array = numbers.split(",");
        double sum = 0;

        for(String element : array){
            sum += Integer.parseInt(element);
        }

        return new AverageResult("Average equal: " + BigDecimal.valueOf(sum / array.length).setScale(2, RoundingMode.FLOOR).stripTrailingZeros());
    }
}
