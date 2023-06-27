package shoponline.controllers;

import shoponline.models.Product;
import shoponline.models.ProductType;
import shoponline.models.Request;
import shoponline.models.Uzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shoponline.repository.ProductRepository;
import shoponline.repository.ProductTypeRepository;
import shoponline.repository.RequestRepository;
import shoponline.repository.UzerRepository;

import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private UzerRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @RequestMapping("/addToBasket/{producttypeid}")
    public String addToBasket(@ModelAttribute Product product, @PathVariable long producttypeid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Uzer user = userRepository.findByUsername(authentication.getName());
        Request basket= requestRepository.findByUserAndConfirmed(user,false);
        ProductType productType= productTypeRepository.findById(producttypeid);

        List<Product> productList= basket.getProductsInRequest();
        productList.add(product);
        basket.setProductsInRequest(productList);

        product.setRequest(basket);
        product.setProductType(productType);

        productRepository.save(product);

        return "redirect:/resultRequest";
    }
}
