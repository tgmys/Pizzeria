/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pizza.Repozytory;

import com.example.Pizza.Zamowienia;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tomasz
 */
public interface RepozytoryZamowienia extends JpaRepository<Zamowienia, Integer> {
    
}
