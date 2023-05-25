package Ph28001.ASM.Repository;

import Ph28001.ASM.Entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Product findOneByProductId(Integer id);

    //    @Modifying
//  public Product deleteByProductId(int id);
    public Product findAllByProductName(String productName);
    public List<Product> findAllByOrderByProductIdDesc(Pageable pageable);

}
