package shoponline.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "request")
public class Request {

    long id;
    float totalPrice;
    List<Product> productsInRequest;
    Uzer user;
    boolean confirmed;

    public Request(){
        productsInRequest=new ArrayList<Product>();
    }

    public Request(float totalPrice , List<Product> productsInRequest ,Uzer user) {
        this.totalPrice = totalPrice;
        this.productsInRequest = productsInRequest;
        this.user= user;
        this.confirmed=false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "totalPrice")
    public float getTotalPrice() {
        float totalPrice=0;
        if(productsInRequest.isEmpty()){
            return totalPrice;
        }
        for (Product product: productsInRequest) {
            totalPrice+=product.getProductType().getPrice() * product.quantity;
        }
        return totalPrice;
    }

    public void setConfirmed(boolean confirmed){
        this.confirmed=confirmed;
    }

    @Column(name= "confirmed")
    public boolean isConfirmed(){
        return this.confirmed;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @OneToMany
    public List<Product> getProductsInRequest() {
        return productsInRequest;
    }

    public void setProductsInRequest(List<Product> productsInRequest) {
        this.productsInRequest = productsInRequest;
    }

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="username")
    public Uzer getUser() {
        return user;
    }

    public void setUser(Uzer user) {
        this.user = user;
    }

    public void setAsUserBasket(Uzer user){
        this.user=user;
        this.totalPrice=0;
        this.confirmed=false;
    }
}
