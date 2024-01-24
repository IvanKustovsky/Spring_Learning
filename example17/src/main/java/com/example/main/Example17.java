package com.example.main;

import com.example.config.ProjectConfig;
import com.example.model.Song;
import com.example.services.VehicleServices;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example17 {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        var vehicleServices = context.getBean(VehicleServices.class);

        System.out.println(vehicleServices.getClass());

        Song song = new Song();
        song.setTitle("Pes Patron");
        song.setSingerName("Oleg Swift");

        boolean vehicleStarted = true;

        String moveVehicleStatus = vehicleServices.moveVehicle(vehicleStarted);
        String playMusicStatus = vehicleServices.playMusic(vehicleStarted,song);
        String applyBrakeStatus = vehicleServices.applyBrake(vehicleStarted);
        System.out.println(moveVehicleStatus);
        System.out.println(playMusicStatus);
        System.out.println(applyBrakeStatus);
    }
}
