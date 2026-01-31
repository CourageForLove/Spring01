package com.ChIoe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TestThController {
    @GetMapping ("TestTh")
    public String Test01(Model model){
        model.addAttribute("Love","Love秋怡");
        model.addAttribute("score",90);
        List<String> list = new ArrayList<String>(Arrays.asList("Love英","Love晓","Love秋怡"));
        model.addAttribute("MyLove",list);
        return "TestTh";
    }
}
