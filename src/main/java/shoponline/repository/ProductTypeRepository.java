package shoponline.repository;

import shoponline.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    public ProductType findByName(String productTypeName);
    public List<ProductType> findAllByName(String productTypeName);
    public ProductType findById(long productTypeId);
}
