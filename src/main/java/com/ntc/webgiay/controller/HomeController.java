package com.ntc.webgiay.controller;

import com.ntc.webgiay.entity.Brand;
import com.ntc.webgiay.entity.Category;
import com.ntc.webgiay.entity.Product;
import com.ntc.webgiay.entity.User;
import com.ntc.webgiay.repository.UserRepository;
import com.ntc.webgiay.service.BrandService;
import com.ntc.webgiay.service.CategoryService;
import com.ntc.webgiay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepo;


	@Autowired
	ProductService productService;

	@Autowired
	BrandService brandService;

	@Autowired
	CategoryService categoryService;

    @GetMapping("")
	public String homePage(Model model){
    	List<Brand> brandsReputation = brandService.getBrandReputation();
		model.addAttribute("listBrandsReputation",brandsReputation);

		List<Product> products = productService.getAllProduct();
		model.addAttribute("listProduct",products);

		//Lấy 3 sản phẩm mới nhất
		List<Product> newProducts = productService.getListNewProducts();
		model.addAttribute("newProducts",newProducts);
//		int into = 1 ;
//		List<String> categories = categoryService.getListCategoryOfBrand(into);
//		model.addAttribute("listCategoryOfBrand",categories);


		return "index";
	}

	//Đăng ký
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	//Đăng nhập
	@GetMapping("/login")
	public String showLoginPage(Model model){
		model.addAttribute("user", new User());
		return "login";
	}

	//Trang thông tin sản phẩm
	@GetMapping("/{id}")
	public String getDetailProduct(Model model, @PathVariable int id){
		//Lấy thông tin sản phẩm
		Product product;
		try {
			product = productService.getDetailProductById(id);
		} catch (Exception ex){
			return "error/404";
		}
		model.addAttribute("product",product);
		return "single-product";
	}

}
