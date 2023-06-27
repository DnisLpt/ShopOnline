package shoponline.controllers;

import shoponline.models.Request;
import shoponline.models.Uzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shoponline.repository.RequestRepository;
import shoponline.repository.UzerRepository;

import java.util.List;

@Controller
@RequestMapping("/request")
public class RequestController {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    UzerRepository userRepository;


    @RequestMapping("/showAllRequests")
    public String showRequests(Model pastRequests, Model basket, Model basketConfirm){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Uzer user = userRepository.findByUsername(authentication.getName());
        List<Request> pastRequest=requestRepository.findByUser(user);

        for(Request request : pastRequest){
            if(!request.isConfirmed()){
                basket.addAttribute("basket", request);
                basketConfirm.addAttribute("showBasket", true);
                pastRequest.remove(request);
                break;
            }
        }

        if(!basket.containsAttribute("basket")){
            Request noBasket= new Request();
            basket.addAttribute("basket", noBasket);
            basketConfirm.addAttribute("showBasket", false);
        }

        pastRequests.addAttribute("pastRequests",pastRequest);

        return "showRequests";
    }

    @RequestMapping("/addBasket")
    public String addNewBasket(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Uzer user = userRepository.findByUsername(authentication.getName());

        if(requestRepository.findByUserAndConfirmed(user, false)!= null){
            return "alreadyExistsError";
        }

        Request basket= new Request();
        basket.setAsUserBasket(user);

        requestRepository.save(basket);

        return "redirect:/resultRequest";
    }

    @RequestMapping("/finishRequest/{requestId}")
    public String finishCurrRequest(@PathVariable(name="requestId") long id){
        Request target= requestRepository.findById(id);
        target.setConfirmed(true);
        requestRepository.save(target);
        return "redirect:/resultRequest";

    }




}
