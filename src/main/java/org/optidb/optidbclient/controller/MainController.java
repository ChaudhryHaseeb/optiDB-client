package org.optidb.optidbclient.controller;

import org.optidb.optidbclient.model.Platform;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @GetMapping({"/", "/liste"})
    public String liste(Model model) {
        List<Platform> platforms = new ArrayList<Platform>();
        platforms.add(new Platform("Hadoop"));
        platforms.add(new Platform("Spark"));
        platforms.add(new Platform("Talend"));
        platforms.add(new Platform("Cassandra"));

        model.addAttribute("liste", platforms);
        return "listePlateformes";
    }
}