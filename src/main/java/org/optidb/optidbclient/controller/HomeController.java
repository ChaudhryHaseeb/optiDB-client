package org.optidb.optidbclient.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController
{

    @GetMapping({"/","/home"})
    public String home()
    {
        return "home";
    }

}

