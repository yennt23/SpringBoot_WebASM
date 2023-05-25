package Ph28001.ASM.Controller;

import Ph28001.ASM.Entity.Product;
import Ph28001.ASM.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String listProduct(Model model, @RequestParam(name="page", required=false) String page) {
        int currentPage=1;
        int limit = 2;
        if(page == null) {
            currentPage = 1;
        }
        else {
            currentPage=Integer.parseInt(page);
        }
        Pageable pageable= PageRequest.of(currentPage-1, limit);
        List<Product> productList = productRepository.findAllByOrderByProductIdDesc(pageable);
        model.addAttribute("productList", productList);
        model.addAttribute("page",currentPage);
        model.addAttribute("totalPage", ((int) Math.ceil((productRepository.count()*1.0/limit))));
        return "product";
    }

    @RequestMapping(value = "/newProduct")
    public String newProduct(Map<String, Product> model) {
        Product product = new Product();
        model.put("product", product);
        return "newProduct";
    }

    @RequestMapping(value = "product/saveProduct", method = RequestMethod.POST)
    public String save(Product product) {
        productRepository.save(product);
        return "redirect:/product";
    }

    @RequestMapping("product/edit")
    public ModelAndView editProduct(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("edit_product");
        Product product = productRepository.findOneByProductId(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @RequestMapping("product/delete")
    public String deleteProduct(@RequestParam int id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }
}
