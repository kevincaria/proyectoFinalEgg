package com.grupo6.app.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexControlador {

@RequestMapping("/")
    public String inicio(){
    return "index";
}
}
