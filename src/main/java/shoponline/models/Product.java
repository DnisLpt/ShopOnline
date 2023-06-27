package shoponline.models;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    long id;
    int quantity;
    ProductType productType;
    Request request;

    public Product(){

    }

    public Product(int quantity, ProductType productType, Request request){
        this.quantity=quantity;
        this.productType=productType;
        this.request=request;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name= "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "productTypeName")
    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="request", referencedColumnName="id")
    public Request getRequest(){
        return request;
    }

    public void setRequest(Request request){
        this.request= request;
    }
}
