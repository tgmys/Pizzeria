/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pizza.Controler;


import com.example.Pizza.DateBase.JpaUtils2;
import com.example.Pizza.Ingredient;

import com.example.Pizza.Repozytory.RepozytoryZamowienia;
import com.example.Pizza.Zamowienia;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Tomasz
 */
@SpringUI(path = "/Wlasna")
public class Wlasna extends UI{
   @Autowired
    RepozytoryZamowienia report;
    @Override
    protected void init(VaadinRequest request){
       Label cen=new Label(); 
        VerticalLayout pionowo = new VerticalLayout();
        HorizontalLayout poziomo=new HorizontalLayout();
                 Image logo=new Image();
        logo.setSource(new ThemeResource("zdj.gif"));
        
        Link gotowe = new Link("Powrot",new ExternalResource("/ "));
         Ingredient[] t=Ingredient.values();
        List<String> li=new ArrayList();
        System.out.print(t);
        for(Ingredient i:t)
        {
            li.add(i.toString());
        }
        
        TwinColSelect tcs=new TwinColSelect<>(null, li);

    TextField adres = new TextField();
        adres.setPlaceholder("Podaj adres");
        TextField godzina = new TextField();
        godzina.setPlaceholder("Podaj godzina");
        TextField nazwa = new TextField();
        nazwa.setPlaceholder("Podaj nazwisko");
        Button przycisk = new Button("ZAMOW");
        przycisk.addClickListener(e -> {
        try{
         
           String godzina1 = godzina.getValue();
           String adres1 = adres.getValue();
           String nazwa1 = nazwa.getValue();
            Zamowienia z=new Zamowienia();
             
             Set<String> set=tcs.getValue();
    
                    String pom="";
                    for(String s:set)
                    {
                       pom=pom+","+s;        
                    }   
                Ingredient[] tabSkladnikow=Ingredient.values();
                
                List<Ingredient> listaSkladnikow=new ArrayList<>();
                for(String s:set)
                {
                    for(Ingredient i:tabSkladnikow)
                    {
                        if(i.getName().equals(s))
                        {
                            listaSkladnikow.add(i);
                            
                            break;
                        }
                    }
                }
                
                int cena=listaSkladnikow.stream().mapToInt(i -> i.getPrice()).sum();
               // Label cen=new Label("Cena: "+cena);
                   // System.out.println("Cena: "+cena);
                if(!(adres1.equals("")||pom.equals("")||nazwa1.equals("")||godzina1.equals(""))){
           
            z.setCena(cena);         
            z.setSkladniki(pom);
            z.setData_za(godzina1);
            z.setNazwisko(nazwa1);
           
            report.save(z);
            //łączenie z baza
                SessionFactory factory = JpaUtils2.getSessionFactory();
                org.hibernate.Session session = factory.openSession();
                session.beginTransaction();
                session.save(z);
                session.getTransaction().commit();
                session.close();
                factory.close();
           
           Notification.show("Zlozono zamowienie", Notification.Type.HUMANIZED_MESSAGE);
                }else{
                    Notification.show("uzupelnij wszystkie pola", Notification.Type.ERROR_MESSAGE);}
                    
        }
            catch(Exception ex) {
                    Notification.show(ex.toString(), Notification.Type.ERROR_MESSAGE);
                    }
        });
       
                
                
        
        tcs.addValueChangeListener(event -> {
             
                Notification.show("Value changed:", String.valueOf(event.getValue()),
                Notification.Type.TRAY_NOTIFICATION);
                Set<String> set=tcs.getValue();
        Ingredient[] tabSkladnikow=Ingredient.values();
                
                List<Ingredient> listaSkladnikow=new ArrayList<>();
                for(String s:set)
                {
                    for(Ingredient i:tabSkladnikow)
                    {
                        if(i.getName().equals(s))
                        {
                            listaSkladnikow.add(i);
                            
                            break;
                        }
                    }
                }int cena=listaSkladnikow.stream().mapToInt(i -> i.getPrice()).sum();
              cen.setValue("Cena: "+String.valueOf(cena));
               
                
                
                });
        poziomo.addComponents(tcs, logo);
       pionowo.addComponents(poziomo,cen,adres,godzina,nazwa,przycisk,gotowe);
    //  pionowo.setComponentAlignment(logo, Alignment.TOP_CENTER);
        setContent(pionowo);
        
        
    }
    
}
