package in.ashok.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashok.entity.Product;
import in.ashok.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository repo;
	
	
	@GetMapping("/edit")
	public String editProduct(@RequestParam("pid") Integer pid, Model model)
	{
		Optional<Product> id = repo.findById(pid);
		if(id.isPresent())
		{
			Product product = id.get();
			model.addAttribute("product",product);
			
		}
		return "index";
		
	}
	
  /*==================This methods is Delete the product items.=====================*/
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("pid")  Integer pid,Model model)
	{
		
		repo.deleteById(pid);
		model.addAttribute("msg","Product Deleted");
		model.addAttribute("products",repo.findAll());
		return "data";
	}
	
	 /*==================This methods is get the product items.=====================*/
	
	@GetMapping("/products")
	public String getProduct(Model model)
	{
		List<Product> list = repo.findAll();
		model.addAttribute("products",list);
		return "data";
	}
	
	 /*==================This methods is add the product items.=====================*/

	@PostMapping("/product")
	public String saveProduct(@Validated @ModelAttribute("product") Product p, BindingResult result, Model model) {
		if(result.hasErrors())
		{
			return "index";
		}
		p = repo.save(p);
		if (p.getPid() != null) {
			model.addAttribute("msg", "Product Saved...");
		}
		return "index";
	}
	
	 /*==================This methods is load the product form.=====================*/

	@GetMapping("/add")
	public String loadForm(Model model) {
		model.addAttribute("product", new Product());
		return "index";
	}
	
	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("product", new Product());
		return "home";
	}

}
