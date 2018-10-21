package com.wissal.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"","/","/index"})
    public String getIndex(){
        System.out.println("#######################dfgdf####,;,;,;##hhhhhhhhhhhzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        return "index";
    }
}
