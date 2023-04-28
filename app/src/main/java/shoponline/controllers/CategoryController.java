package shoponline.controllers;

import shoponline.models.Category;
import shoponline.models.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shoponline.repository.CategoryRepository;
import shoponline.repository.ProductTypeRepository;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;


    @RequestMapping("/add/")
    public String createCategory(@ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/result";
    }

    @RequestMapping("/showAll")
    public String showAll(Model all){
        return "";

    }

    @RequestMapping("/showAllFromCategory/{id}")
    public String showCategory(@PathVariable(name="id") long id, Model category,Model productsInCategory,Model productType, Model categories){
        categories.addAttribute("categories", categoryRepository.findAll());
        Optional<Category> thisCategory= categoryRepository.findById(id);
        category.addAttribute("category", thisCategory.get());
        productsInCategory.addAttribute("products", thisCategory.get().getProductList());
        productType.addAttribute("productType", new ProductType());
        return "showAllFromCategory";
    }

    @RequestMapping("/remove/product/{product}")
    public String removeProductType(@PathVariable(name= "product") long id){
        ProductType productType = productTypeRepository.findById(id);
        Optional<Category> category = categoryRepository.findById(productType.getCategory().getId());
        category.get().getProductList().remove(productType);
        categoryRepository.save(category.get());
        return "redirect:/result";
    }
}
