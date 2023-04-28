package shoponline.controllers;

import shoponline.models.Category;
import shoponline.models.Product;
import shoponline.models.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shoponline.repository.CategoryRepository;
import shoponline.repository.ProductTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @RequestMapping("/add/{categoryid}")
    public String createProductType(@ModelAttribute ProductType productType, @PathVariable(name= "categoryid") long id){
        Optional<Category> catTemp= categoryRepository.findById(id);
        productType.setCategory(catTemp.get());
        productTypeRepository.save(productType);
        return "redirect:/result";
    }

    @RequestMapping("/showall")
    public String showAll(Model product1, Model categories){
        categories.addAttribute("categories", categoryRepository.findAll());
        product1.addAttribute("product_type",productTypeRepository.findAll());
        return "products";
    }

    @RequestMapping("/productPage/{productid}")
    public String showProductDetails(@PathVariable(name="productid") long productId, Model thisProduct, Model newProduct){
        thisProduct.addAttribute("Product",productTypeRepository.findById(productId));
        newProduct.addAttribute("addThis", new Product());
        return "productPage";
    }

    @RequestMapping("/searchProduct")
    public  String searchProduct(@RequestParam(value="search") String search, Model model, Model categories){
        categories.addAttribute("categories", categoryRepository.findAll());
        List<ProductType> todos = productTypeRepository.findAll();
        List<ProductType> temporary = new ArrayList<ProductType>();
        for(ProductType t : todos) {
            if(hasSub(t.getName(),search)){
                temporary.add(t);
            }
        }
        if(temporary.size()>0){
            model.addAttribute("search",temporary);
            return "onlyFoundPage";
        }
        return "notFound";
    }

    public boolean hasSub(String name, String letter){
        String nam1 = name.toLowerCase();
        return nam1.contains(letter);
    }


}
