package shoponline.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shoponline.models.Category;
import shoponline.models.Uzer;
import shoponline.repository.CategoryRepository;
import shoponline.repository.ProductTypeRepository;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @GetMapping("/")
    public String red() {
        return "index1";
    }

    @GetMapping("/home")
    public String home(Model categories, Model products) {
        categories.addAttribute("categories", categoryRepository.findAll());
        products.addAttribute("product_types", productTypeRepository.findAll());
        return "index1";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/showRequests")
    public String showRequests() {
        return "showRequests";
    }

    @GetMapping("/userExists")
    public String userExists() { return "userExists";}

    @GetMapping("/signin")
    public String signin(Model uzer) {
        uzer.addAttribute("uzer",new Uzer());
        return "signin";
    }

    @GetMapping("/notFound")
    public String notFound() { return "notFound";}

    @GetMapping("/onlyFoundPage")
    public  String onlyFoundPage(){return "onlyFoundPage";}

    @RequestMapping("/admin")
    public String goAdmin(Model model2, Model model3){
        model3.addAttribute("categoria", new Category());
        model2.addAttribute("categories", categoryRepository.findAll());
        return "admin";
    }

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @GetMapping("/resultRequest")
    public String resultReq() {
        return "resultRequest";
    }

    @GetMapping("/alreadyExistsError")
    public String errorMessage1(){
        return "alreadyExistsError";
    }

    @GetMapping("/intermediateRequests")
    public String goPanel(){
        return "intermediateRequests";
    }
}
