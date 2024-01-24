package com.example.services;

import com.example.interfaces.Speakers;
import com.example.interfaces.Tyres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class VehicleServices {
    /*The @Scope annotation with BeanDefinition.SCOPE_PROTOTYPE indicates that this bean should be of prototype scope.
    In prototype scope, a new instance of the bean is created each time it is requested from the Spring IoC container.
    This allows for a unique instance of the 'VehicleServices' bean to be created for each injection point or lookup,
   providing a fresh state for each usage.*/

    @Autowired
    private Speakers speakers;
    private Tyres tyres;
    public VehicleServices(){
        System.out.println("VehicleServices object is created");
    }

    public void playMusic() {
        String music = speakers.makeSound();
        System.out.println(music);
    }

    public void moveVehicle() {
        String status = tyres.rotate();
        System.out.println(status);
    }

    public Speakers getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Speakers speakers) {
        this.speakers = speakers;
    }

    public Tyres getTyres() {
        return tyres;
    }

    @Autowired
    public void setTyres(Tyres tyres) {
        this.tyres = tyres;
    }
}
