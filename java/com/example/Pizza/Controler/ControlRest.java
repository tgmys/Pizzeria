/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pizza.Controler;

import com.example.Pizza.Repozytory.RepozytoryZamowienia;
import com.example.Pizza.Zamowienia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Tomasz
 */
@RestController
@RequestMapping("/zamowienia/")
public class ControlRest {
    
    @Autowired
    RepozytoryZamowienia repo;
    
     @GetMapping("spis")
    public List<Zamowienia> czolg(){
      return repo.findAll();
    }
    
    
}
