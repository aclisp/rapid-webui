package com.lingnanedu.classmgmt.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lingnanedu.classmgmt.core.domain.Teacher;


@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String listAllTeachers(Model model) {
        log.debug("About to list all teachers");
        model.addAttribute(new Teacher("The Only Teacher"));
        return "teachers";
    }

}
