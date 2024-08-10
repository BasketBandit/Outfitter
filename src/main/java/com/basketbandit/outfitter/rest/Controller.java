package com.basketbandit.outfitter.rest;

import com.basketbandit.outfitter.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/wardrobe")
    public ModelAndView wardrobe() {
        ModelAndView mv = new ModelAndView("wardrobe/wardrobe");
        mv.addObject("wardrobe", Application.wardrobe);
        return mv;
    }

    @GetMapping("/outfits")
    public ModelAndView outfits() {
        return new ModelAndView("outfits");
    }
}
