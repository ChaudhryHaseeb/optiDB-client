package org.optidb.optidbclient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    List<Platform> listAllPlaform() {
        List<Platform> platforms = new ArrayList<Platform>();
        platforms.add(new Platform("Hadoop"));
        platforms.add(new Platform("Spark"));
        return platforms;
    }
}