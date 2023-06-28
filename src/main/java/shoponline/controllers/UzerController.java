package shoponline.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import shoponline.models.Uzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import shoponline.repository.UzerRepository;

import java.util.List;

@Controller
@RequestMapping("/uzer")
public class UzerController {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UzerRepository uzerRepository;


    @RequestMapping("/add/")
    public String createUzer(@ModelAttribute Uzer uzer){
        Uzer test = uzerRepository.findByUsername(uzer.getUsername());
        if(test == null) {
            uzer.setPassword(passwordEncoder.encode(uzer.getPassword()));
            uzer.setRole(Uzer.Role.USER);
            uzerRepository.save(uzer);
            return "redirect:/home";
        }
        return "redirect:/userExists";

    }
/*
    @GetMapping("/display/")
    public List<Uzer> findAllUzers(){
        //model.addAttribute("users",uzerRepository.findAll());
        //return "allUsers";
        return uzerRepository.findAll();
    }*/
    @GetMapping("/display")
    public ResponseEntity<List<Uzer>> findAllUzers() {
        List<Uzer> users = uzerRepository.findAll();
        return ResponseEntity.ok(users);
    }

}
