package com.gmail.olgabovkaniuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LogStatisticController {

    @GetMapping(value = "/log-statistic-form")
    public String logStatisticForm(Model model) {
        return "logstatistic";
    }

}
