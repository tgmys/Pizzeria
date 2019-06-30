/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Pizza.Controler;


import com.example.Pizza.DateBase.JpaUtils2;
import com.example.Pizza.Ingredient;
import com.example.Pizza.Pizza;

import com.example.Pizza.Repozytory.RepozytoryZamowienia;
import com.example.Pizza.Zamowienia;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.data.HasValue;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Registration;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.InlineDateTimeField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Tomasz
 */
@StyleSheet("styles.css")
@SpringUI
public class Start extends UI{
 
    @Autowired
    RepozytoryZamowienia report;
    public static int zcena(List <Ingredient> lista)
    {int cen= lista.stream().mapToInt(i -> i.getPrice()).sum();
        return cen;
    }
    @Override
    protected void init(VaadinRequest request){
        VerticalLayout pionowo = new VerticalLayout();
        HorizontalLayout poziomo = new HorizontalLayout();
      HorizontalLayout poziomo1 = new HorizontalLayout();
    VerticalLayout pionowo1 = new VerticalLayout();
        
         Image logo1=new Image();
        logo1.setSource(new ThemeResource("logo1.jpg"));
      
        Image logo=new Image();
        logo.setSource(new ThemeResource("logo.gif"));
        logo.setWidth("200");
        logo.setHeight("150");
        
        Image logo2=new Image();
        logo2.setSource(new ThemeResource("logo2.gif"));
        logo2.setWidth("400");
        logo2.setHeight("350");
        
         InlineDateTimeField kal = new InlineDateTimeField();
        kal.setValue(LocalDateTime.now());
        kal.setLocale(Locale.GERMAN);
        kal.setResolution(DateTimeResolution.MINUTE);
        kal.addValueChangeListener(event ->
        kal.getValue().toString());    
 

 
        
           Label sample = new Label(
                "<H2><B>Możesz stworzyć autorską pizzze wcisnij tutaj :"
                        + "\n"
                        + "\n<a href='Wlasna'>"
                        + "Kreator</a> </B></H2>",ContentMode.HTML);
        sample.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);
        
         Label naglowek = new Label("<H2><B>Zamów pizze z listy</B></H2>",
              ContentMode.HTML);
        List<Pizza> listaPizz = Arrays.stream(Pizza.values()).collect(Collectors.toList());
        ComboBox wyborPizzy = new ComboBox();
        wyborPizzy.setItems(listaPizz);
        Label wybranaPizza = new Label();
        Label cenaPizzy = new Label();
        
         wyborPizzy.addValueChangeListener((HasValue.ValueChangeEvent event) -> {
            Pizza wybrana = (Pizza) wyborPizzy.getValue();
            List<Ingredient> skladniki = wybrana.getIngredients();
          int  cena = wybrana.getIngredients().stream().mapToInt(i -> i.getPrice()).sum();
            wybranaPizza.setValue("Składniki: " + wybrana.getIngredients().toString());
            cenaPizzy.setValue("Cena: " + cena);
            
        });
        
         Ingredient[] t=Ingredient.values();
        List<String> li=new ArrayList();
        for(Ingredient i:t)
        {
            li.add(i.toString());
        }

       
      
       
        TextField nazwa = new TextField();
        nazwa.setPlaceholder("Podaj nazwisko");
        
        Button przycisk = new Button("ZAMOW");
        przycisk.addClickListener(e -> {
        try{
         
          
           String nazwa1 = nazwa.getValue();
            Zamowienia z=new Zamowienia();
            
         Pizza wybrana = (Pizza) wyborPizzy.getValue();
            List<Ingredient> skladniki = wybrana.getIngredients();
          int  cena = wybrana.getIngredients().stream().mapToInt(i -> i.getPrice()).sum();
           String pom2 ="";
           for(int i=0; i<skladniki.size();i++)
               pom2=pom2+","+skladniki.get(i);
          
            String data=kal.getValue().toString();
            z.setCena(cena);
            z.setSkladniki(pom2);
            z.setNazwisko(nazwa1);
            z.setData_za(data);
          //  z.setData_zam(data1);
//           z.setData((Data) data);
            report.save(z);
            //łączenie z baza
                SessionFactory factory = JpaUtils2.getSessionFactory();
                Session session = factory.openSession();
                session.beginTransaction();
                session.save(z);
                session.getTransaction().commit();
                session.close();
                factory.close();
                      
           Notification.show("Zlozono zamowienie", Notification.Type.HUMANIZED_MESSAGE);
        }
            catch(Exception ex) {
                    Notification.show("uzupełnij wszystkie pola", Notification.Type.ERROR_MESSAGE);
                    }
        });

        poziomo.addComponents(logo1,logo);
        pionowo1.addComponents(sample,kal,nazwa,przycisk);
        poziomo1.addComponents(pionowo1, logo2);
        pionowo.addComponents(poziomo,naglowek, wyborPizzy, wybranaPizza,cenaPizzy,poziomo1);
       poziomo1.setComponentAlignment(logo2, Alignment.TOP_RIGHT);
        poziomo.setComponentAlignment(logo1, Alignment.MIDDLE_LEFT);
        poziomo.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        setContent(pionowo);
        
        setStyleName("/styles.css");
        addStyleName("/styles.css");
        pionowo.setStyleName("/styles.css");
        pionowo.addStyleName("/styles.css");
    }
    
}
