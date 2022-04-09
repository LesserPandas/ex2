package com.ex2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ItemController {

    @GetMapping(value = "/admin/item/new")
    public ModelAndView itemForm(ModelAndView mav) {
        mav.setViewName("item/itemForm");
        return mav;
    }
}
