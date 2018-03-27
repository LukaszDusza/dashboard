package com.dfsp.dashboard.controllers.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TemplateController {
    @GetMapping("/email")
    public String hello(Model model,
                        @RequestParam(value="name", required=false, defaultValue="...") String name,
                        @RequestParam(value="type", required=false, defaultValue="...") String type,
                        @RequestParam(value="amount", required=false, defaultValue="...") String amount,
                        @RequestParam(value="company", required=false, defaultValue="...") String company
    )
    {
        model.addAttribute("name", name);
        model.addAttribute("type", type);
        model.addAttribute("amount", amount);
        model.addAttribute("company", company);

        return "email";
    }

    @GetMapping("/pzu")
    public String emailPzu() {
        return "emails/pzu";
    }

}
