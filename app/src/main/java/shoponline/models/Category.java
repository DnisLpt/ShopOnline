package shoponline.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "category")
public class Category {

    long id;
    String categoryName;
    List<ProductType> productList;

    public Category(){

    }

    public Category(String categoryName, List<ProductType> productList){
        this.categoryName = categoryName;
        this.productList=productList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Column(name = "categoryName")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @OneToMany(mappedBy= "category",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<ProductType> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductType> productList) {
        this.productList = productList;
    }
}
