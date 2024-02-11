package com.example.controllers;


import com.example.model.EazyClass;
import com.example.model.Person;
import com.example.repository.EazyClassRepository;
import com.example.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EazyClassRepository eazyClassRepository;

    @RequestMapping(value = "/displayClasses")
    public ModelAndView displayClassesPage() {
        List<EazyClass> eazyClasses = eazyClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("eazyClass", new EazyClass());
        modelAndView.addObject("eazyClasses", eazyClasses);
        return modelAndView;
    }

    @RequestMapping(value = "/displayCourses")
    public ModelAndView displayCoursesPage() {
        return new ModelAndView("courses");
    }

    @PostMapping(value = "/addNewClass")
    public ModelAndView addNewClass(@ModelAttribute("eazyClass") EazyClass eazyClass) {
        eazyClassRepository.save(eazyClass);
        return new ModelAndView("redirect:/admin/displayClasses");
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(@RequestParam(name = "classId") int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error){
        String errorMessage;
        ModelAndView modelAndView = new ModelAndView("students");
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(classId);
        modelAndView.addObject("person", new Person());
        if(eazyClass.isPresent()){
            modelAndView.addObject("eazyClass", eazyClass.get());
            session.setAttribute("eazyClass", eazyClass.get());
        }
        if(error != null) {
            errorMessage = "Invalid Email entered";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(@RequestParam int id){
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(id);
        if (eazyClass.isPresent()) {
            for (Person person : eazyClass.get().getPersons()){
                person.setEazyClass(null);
                personRepository.save(person);
            }
            eazyClassRepository.deleteById(id);
        }
        return new ModelAndView("redirect:/admin/displayClasses");
    }

    @PostMapping(value = "/addStudent")
    public ModelAndView addStudent(@ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity == null || !(personEntity.getPersonId() > 0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId() +
                    "&error=true");
        } else {
            personEntity.setEazyClass(eazyClass);
            personRepository.save(personEntity);
            eazyClass.getPersons().add(personEntity);
            eazyClassRepository.save(eazyClass);
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId());
        }
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(@RequestParam int personId, HttpSession session){
        EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
        Optional<Person> person = personRepository.findById(personId);
        if(person.isPresent()){
            person.get().setEazyClass(null);
            eazyClass.getPersons().remove(person.get());
            EazyClass eazyClassSaved = eazyClassRepository.save(eazyClass);
            session.setAttribute("eazyClass", eazyClassSaved);
        }
        return new ModelAndView("redirect:/admin/displayStudents?classId=" + eazyClass.getClassId());
    }
}
