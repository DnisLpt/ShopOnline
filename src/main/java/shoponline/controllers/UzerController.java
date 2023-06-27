package shoponline.controllers;

import shoponline.models.Uzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import shoponline.repository.UzerRepository;

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

    @RequestMapping("/display/")
    public String findAllUzers(Model model){
        model.addAttribute("users",uzerRepository.findAll());
        return "allUsers";
    }

}
