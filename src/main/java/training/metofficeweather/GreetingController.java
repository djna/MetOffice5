package training.metofficeweather;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import training.metofficeweather.data.GreetingData;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        String [] fakeLocations = { "aaa", "bbb"};
        model.addAttribute("locations", fakeLocations);
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(
            value = "/weatherForLocation",
            method= RequestMethod.GET,
            consumes = MediaType.ALL_VALUE
    )
    ModelAndView weatherForLocation(@RequestParam Map<String, String> body) {
        System.out.println("just:" + body);
        return new ModelAndView("greetingInfo", "location", body) ;
    }

    /* POST form data, deserialise to Map - OK */
    @RequestMapping(
            value = "/weatherForLocation",
            method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    ModelAndView dataFromPost( @RequestBody MultiValueMap requestData ) {
        System.out.println("just:" + requestData);
        return new ModelAndView("greetingInfo", "location", requestData) ;
    }

    /* POST form data, deserialise to DTO object - fails */
    @RequestMapping(
            value = "/dtoWeatherForLocation",
            method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    ModelAndView dtoFromPost(   @RequestBody GreetingData requestData ) {
        System.out.println("just:" + requestData);
        return new ModelAndView("greetingInfo", "location", requestData) ;
    }

    /* POST JSO, deserialise to DTO object - OK */
    @RequestMapping(
            value = "/jsonWeatherForLocation",
            method= RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ModelAndView jsonDataFromPost(@RequestBody GreetingData requestData ) {
        System.out.println("just:" + requestData);
        return new ModelAndView("greetingInfo", "location", requestData) ;
    }

}
