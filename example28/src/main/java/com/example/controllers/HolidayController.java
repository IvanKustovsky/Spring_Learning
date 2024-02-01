package com.example.controllers;


import com.example.model.Holiday;
import com.example.repository.HolidayRepository;
import com.example.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayController {
    @Autowired
    private HolidayRepository holidayRepository;
    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model){
        if(null != display && display.equals("all")){
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        } else if (null != display && display.equals("festival")) {
            model.addAttribute("festival", true);
        } else if (null != display && display.equals("federal")) {
            model.addAttribute("federal", true);
        }
        List<Holiday> holidays = holidayRepository.findAllHolidays();

        Holiday.Type[] types = Holiday.Type.values(); // getting valid types of holiday
        for(Holiday.Type type: types){  // populate model.addAttribute("FESTIVAL", holidaysList with Type==FESTIVAL)
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).
                            collect(Collectors.toList())));
        }

        return "holidays";
    }
}
