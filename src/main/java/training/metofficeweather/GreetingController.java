package training.metofficeweather;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training.metofficeweather.data.DataHolder;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {

    private String[] titleArray = { "AAA", "BBB", "CCC"};
    private DataHolder dh1 = new DataHolder(3,7);
    private DataHolder dh2 = new DataHolder(102,88);
    private List<DataHolder> myHolders = new ArrayList<>();


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("titles", titleArray);
        myHolders.add(dh1);
        myHolders.add(dh2);
        model.addAttribute("dataHolders", myHolders);
        return "greeting";
    }

}


