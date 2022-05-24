package com.ntc.webgiay.controller;


import com.ntc.webgiay.model.*;
import com.ntc.webgiay.repository.ProductSizeRepository;
import com.ntc.webgiay.repository.RolesRepository;
import com.ntc.webgiay.repository.SizeRepository;
import com.ntc.webgiay.repository.UserRepository;
import com.ntc.webgiay.security.SecurityUtils;
import com.ntc.webgiay.service.*;

import com.sun.security.auth.UserPrincipal;
import org.apache.catalina.Authenticator;
import org.apache.catalina.connector.Request;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.persistence.PostRemove;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	@Autowired
	RolesRepository rolesRepository;

	@Autowired
	ProductSizeRepository productSizeRepository;

	@Autowired
	CommentService commentService;

	@Autowired
	SizeRepository sizeRepository;

	UserPrincipal userPrincipal;

    @GetMapping("/")
	public String homePage(Model model){
    	//Lấy các thương hiệu
    	List<Brand> brandsReputation = brandService.getBrandReputation();
		model.addAttribute("listBrandsReputation",brandsReputation);

		//Lấy tát cả các sản phẩm
		List<Product> products = productService.findAll();
		model.addAttribute("listProduct",products);

		//Lấy 3 sản phẩm mới nhất
		List<Product> newProductsBanner = productService.getListNewProducts(3);
		model.addAttribute("newProducts",newProductsBanner);

		//Lấy 8 sản phẩm mới nhất
		List<Product> newProducts = productService.getListNewProducts(8);
		model.addAttribute("listNewProduct", newProducts);

		//Lấy 8 sản phẩm ngẫu nhiên
		List<Product> randomProducts = productService.getRandomListProduct(8);
		model.addAttribute("listRandomProduct",randomProducts);

		return "index";
	}

	
	@PostMapping("/process_register")
	public String processRegister(User user ) {
    	if( userRepo.findByEmail(user.getEmail()) != null){
			return "redirect:/login";
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setStatus(false);
		Set<Roles> roles = new HashSet<>();
		roles.add(rolesRepository.getById(1));
		user.setRoles(roles);
		userRepo.save(user);
		
		return "redirect:/login";
	}
	@GetMapping("/logout")
	public  String logout(HttpSession session){
    	session.removeAttribute("user");
    	return "redirect:/login";
	}

	//Đăng nhập
	@GetMapping("/login")
	public String showLoginPage(Model model, @RequestParam("error") Optional<String> error){
		model.addAttribute("user", new User());
		if(error.isPresent()) {
			model.addAttribute("error", error);
		}

		return "login";
	}

	//Trang thông tin sản phẩm
	@GetMapping("/{id}")
	public String getDetailProduct(Model model, @PathVariable int id, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){

		//Lấy các thương hiệu
		List<Brand> brandsReputation = brandService.getBrandReputation();
		model.addAttribute("listBrandsReputation",brandsReputation);

		//Lấy thông tin sản phẩm
		Product product;
		try {
			product = productService.getDetailProductById(id);
		} catch (Exception ex){
			return "error/404";
		}

		//Phân trang comment
		int currentPage = page.orElse(1);
		int sizePage = size.orElse(4);
		Pageable pageable = PageRequest.of(currentPage - 1,sizePage);
		Page<Comment> listComment = commentService.findAllByProductId(id,pageable);
		model.addAttribute("product",product);
		model.addAttribute("listCommnet",listComment);

		int totalPage = listComment.getTotalPages();

		if( totalPage > 0 ){
			int start = Math.max(1,currentPage-2);
			int end = Math.min(currentPage + 2, totalPage);
			if( totalPage > 5 ){
				if( end == totalPage){
					start = end - 5;
				}else if(start == 1){
					end = start + 5;
				}
			}
			List<Integer> pagenummber = IntStream.rangeClosed(start,end)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumber", pagenummber);
		}
		//Lấy size có sẵn
		List<Product_size> listSizeByProduct = productSizeRepository.findAllByProductId(id);
		List<Product_size> listSize = new ArrayList<>();
		for (var item : listSizeByProduct
		) {
			if(item.getQuantity() > 0){
				listSize.add(item);
			}

		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getPrincipal() != "anonymousUser")
		{
			User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
			model.addAttribute("user",user);
		}
		model.addAttribute("listSize",listSize);

		return "single-product";
	}

	@PostMapping("/comment")
	public String sendComment(@RequestParam("message") String comment, @RequestParam("productId") int productId){
		User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
		commentService.createComment(productId,user.getId(),comment);


    	return "redirect:/"+productId;
	}

	@GetMapping("/404")
	public String errorPage(){
    	return "error/404";
	}

}
