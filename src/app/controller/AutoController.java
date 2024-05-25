package app.controller;

import app.domain.Auto;
import app.service.AutoService;
import org.springframework.stereotype.Controller;

@Controller
public class AutoController {

    private AutoService autoService;


    public AutoController(AutoService autoService) {
        this.autoService = autoService;
    }

    public Auto getAuto(Long id) {
        return autoService.getAuto(id);
    }

}
