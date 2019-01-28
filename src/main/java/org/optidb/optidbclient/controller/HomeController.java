package org.optidb.optidbclient.controller;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

@Controller
public class HomeController
{

    @GetMapping({"/","/home"})
    public String home()
    {
        return "home";
    }

}

