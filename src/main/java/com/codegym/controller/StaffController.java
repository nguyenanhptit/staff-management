package com.codegym.controller;

import com.codegym.model.Group;
import com.codegym.model.Staff;
import com.codegym.service.GroupService;
import com.codegym.service.StaffService;
import com.codegym.service.impl.GroupServiceImpl;
import com.codegym.service.impl.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private GroupService groupService;

    @ModelAttribute("groups")
    public Page<Group> groups(Pageable pageable) {
        return groupService.findAll(pageable);
    }

    @RequestMapping(value = "/staffs", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam("search") Optional<String> search, @PageableDefault(value = 5) Pageable pageable) {
        ModelAndView mv = new ModelAndView();
        Page<Staff> staffs;
        if (search.isPresent()) {
            staffs = staffService.findByName(search.get(), pageable);
        } else {

            staffs = staffService.findAll(pageable);
        }
        mv.setViewName("staff/list");
        mv.addObject("staffs", staffs);
        return mv;
    }

    @RequestMapping(value = "/create-staff", method = RequestMethod.GET)
    public ModelAndView create(Staff staff) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("staff/create");
        mv.addObject("staff", staff);
        return mv;
    }

    @RequestMapping(value = "/save-staff", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("staff") Staff staff) {
        ModelAndView mv = new ModelAndView();
        staffService.save(staff);
        mv.setViewName("staff/create");
        mv.addObject("staff", staff);
        mv.addObject("msg", "Create Successfully!!");
        return mv;
    }


    @RequestMapping(value = "/edit-staff/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        Optional<Staff> staff = staffService.findById(id);
        mv.setViewName("staff/edit");
        mv.addObject("staff", staff);
        return mv;

    }

    @RequestMapping(value = "/update-staff", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("note") Staff staff) {
        ModelAndView mv = new ModelAndView();
        staffService.save(staff);
        mv.setViewName("staff/edit");
        mv.addObject("staff", staff);
        mv.addObject("msg", "update successfully !!!");
        return mv;
    }


    @RequestMapping(value = "/delete-staff/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") Long id, @ModelAttribute("note") Optional<Staff> staff) {
        ModelAndView mv = new ModelAndView();
        staff = staffService.findById(id);
        staffService.delete(staff.get().getId());
        mv.addObject("staff", staff);
        mv.addObject("msg", "delete successfully !!!");
        return new ModelAndView("redirect:/staffs");

    }
}
