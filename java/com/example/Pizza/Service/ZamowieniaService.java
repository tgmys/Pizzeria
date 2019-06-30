/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pizza.Service;

import com.example.Pizza.DateBase.JpaUtils2;
import com.example.Pizza.Repozytory.RepozytoryZamowienia;
import com.example.Pizza.Zamowienia;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomasz
 */
@Slf4j
@Service
public class ZamowieniaService {
    @Autowired
    RepozytoryZamowienia repozytory;
    
    @PostConstruct    
    public void wypisz2(){
        SessionFactory factory = JpaUtils2.getSessionFactory();
        Session session = factory.openSession();
        List<Zamowienia> lista = session.createQuery("From Zamowienia").list();
        session.close();
        factory.close();
        for(Zamowienia i:lista){
            repozytory.save(i);
        }
    }
}
