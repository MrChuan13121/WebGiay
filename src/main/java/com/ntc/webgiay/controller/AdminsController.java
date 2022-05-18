package com.ntc.webgiay.controller;

import com.ntc.webgiay.model.Order;
import com.ntc.webgiay.model.OrderDetail;
import com.ntc.webgiay.repository.*;
import com.ntc.webgiay.model.*;
import com.ntc.webgiay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class AdminsController {

    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    BrandRepository brandRepository;

    @GetMapping("/admin")
    public String adminPage(){
        return "redirect:admin/index";
    }

    @GetMapping("/admin/index")
    public String adminHome(Model model){
        model.addAttribute("totalProduct",productRepository.countProduct());
        model.addAttribute("sumPrice", orderRepository.sumPrice());
        model.addAttribute("countUser",userRepository.countUser());
        model.addAttribute("countOrder", orderRepository.countOrder());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/index";
    }


    /////////////////////////////Brand
    @GetMapping("/admin/brands")
    public String adminBrands(Model model){
        model.addAttribute("listBrand", brandService.findAll());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/brand/brands";
    }

    @GetMapping("/admin/createBrand")
    public String adminCreateBrand(Model model){
        model.addAttribute("newBrand", new Brand());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/brand/createBrand";
    }

    @PostMapping("/admin/brand/add")
    public String createBrand(@ModelAttribute("brand") Brand brand, @RequestParam("thumbnailUrl") MultipartFile multipartFile) throws IOException {

        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        brand.setThumbnail("~/img/brand/" +filename);
        brandService.save(brand);
        String uploadDir = "./src/main/resources/static/img/brand/";
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try{
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);

        }catch(IOException e){
            throw new IOException("Không thể tải file: "+filename);
        }
        return "redirect:/admin/brands";
    }

    @GetMapping("/admin/brand/update/{id}")
    public String updateBrand(Model model, @PathVariable("id") Integer id){
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        model.addAttribute("brand",brandService.getById(id));
        return "/admin/brand/updateBrand";
    }

    @PostMapping("/admin/brand/update")
    public String updateBrand(@RequestParam("id") Integer id, @RequestParam("nameBrand") String nameBrand, @RequestParam("description") String description, @RequestParam("thumbnailUrl") MultipartFile multipartFile ) throws IOException {
        Brand brand = brandService.getById(id);
        brand.setNameBrand(nameBrand);
        brand.setDescription(description);
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        brand.setThumbnail("~/img/brand/"+filename);
        brandService.save(brand);
        String uploadDir = "./src/main/resources/static/img/brand/";
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try{
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            throw new IOException("Không thể tải file: "+filename);
        }
        return "redirect:/admin/brands";

    }

    @GetMapping("/admin/brand/delete/{id}")
    public String deleteBrand(@PathVariable("id") Integer id){
        brandService.deleteBrand(id);
        return "redirect:/admin/brands";
    }
    //////////////////////////Category
    @GetMapping("/admin/categories")
    public String adminCategories(Model model){
        model.addAttribute("listCategory", categoryService.findAll());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/category/categories";
    }


    @GetMapping("/admin/createCategory")
    public String adminCreateCategory(Model model){
        model.addAttribute("listBrand",brandService.findAll());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/category/createCategory";
    }

    @PostMapping("/admin/category/add")
    public String adminAddCategory(@RequestParam("category") String category, @RequestParam("brand") int brandId ){
        categoryService.createCategory(category,brandId);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/category/update/{id}")
    public String updateCategory(Model model,@PathVariable("id") Integer id){
        Category category = categoryService.getById(id);
        model.addAttribute("category",category);
        model.addAttribute("listBrand",brandService.findAll());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/category/updateCategory";
    }

    @PostMapping("/admin/category/update")
    public String updateCategory(@RequestParam("id") Integer id, @RequestParam("category") String category, @RequestParam("brand") int brandId){
        categoryService.update(id,category,brandId);
        return "redirect:/admin/categories";
    }

    @GetMapping("admin/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id){
        categoryRepository.delete(categoryRepository.getById(id));
        return "redirect:/admin/categories";
    }


    /////////////////////////Order
    @GetMapping("/admin/orders")
    public String adminOrder(Model model){
        model.addAttribute("listOrder",orderService.findAll());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/order/orders";
    }

    @PostMapping("/accept")
    public String acceptOrder(@RequestParam("orderId") int id){
        Order order = orderService.getById(id);
        order.setStatus(true);
        orderRepository.save(order);
        List<OrderDetail> listOrderDetail = orderDetailRepository.findAllByOrderId(id);
        for (var orderDetail: listOrderDetail
             ) {
            Size size = sizeRepository.getByName(orderDetail.getSize());
            Product product = productService.getDetailProductById(orderDetail.getProduct().getId());
            Product_size product_size = productSizeRepository.findByProductIdAndSizeId(orderDetail.getProduct().getId(),size.getId());
            product_size.setQuantity(product_size.getQuantity() - orderDetail.getQuantity());
            product.setQuantity(product.getQuantity() - orderDetail.getQuantity());
            if(product.getQuantity() == 0){
                product.setStatus(false);
                Category category = product.getCategory();
                Boolean a = false;
                for (var p : category.getProducts()
                     ) {
                    if(p.getQuantity() != 0 ){
                        a = true;
                        break;
                    }
                }
                if( a == false){
                    category.setStatus(false);
                    categoryRepository.save(category);
                    Brand brand = category.getBrand();
                    Boolean b = false;
                    for (var c: brand.getCategories()
                         ) {
                        if( c.isStatus() == true){
                            b = true;
                            break;
                        }
                    }
                    if( b == false){
                        brand.setStatus(false);
                        brandRepository.save(brand);
                    }
                }
            }
            productRepository.save(product);
            productSizeRepository.save(product_size);

        }
        return "redirect:admin/orders";
    }

    @GetMapping("/admin/orderDetail/{id}")
    public String getOrderDetail(Model model,@PathVariable("id") int id){
        Order order = orderService.getById(id);
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(order.getId());

        model.addAttribute("order",order);
        model.addAttribute("listOrderDetail",orderDetailList);
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/order/orderDetail";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id){
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(id);
        for (var orderDetail: orderDetailList
             ) {
            orderDetailRepository.deleteById(orderDetail.getId());
        }
        orderRepository.deleteById(id);
        return "redirect:/admin/orders";
    }

    //////////////////////////Product

    @GetMapping("/admin/products")
    public String adminProducts(Model model){
        model.addAttribute("listProduct",productService.findAll());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/product/products";
    }

    @GetMapping("/admin/createProduct")
    public String adminCreateProduct(Model model){
        model.addAttribute("newProduct",new Product());
        model.addAttribute("listCategory",categoryRepository.findAll());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/product/createProduct";
    }

    @PostMapping("/admin/createProduct")
    public String adminCreateProduct(@RequestParam("name") String name,
                                     @RequestParam("categoryId") Integer categoryId,
                                     @RequestParam("price") float price,
                                     @RequestParam("description") String description,
                                     @RequestParam("thumbnailUrl") MultipartFile multipartFile) throws IOException {
        Product product = new Product();
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setName(name);
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product.setPrice(price);
        product.setDescription(description);
        Category category = categoryRepository.getById(categoryId);
        product.setCategory(category);
        category.setStatus(true);
        Brand brand = category.getBrand();
        brand.setStatus(true);
        product.setThumbnail("~/img/product/"+filename);
        productRepository.save(product);
        categoryRepository.save(category);
        brandRepository.save(brand);
        String uploadDir = "./src/main/resources/static/img/product/";
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try{
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Không thể tải file: "+filename);
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(Model model,@PathVariable("id") Integer id){
        Product product = productService.getDetailProductById(id);
        model.addAttribute("product",product);
        model.addAttribute("listCategory",categoryRepository.findAll());
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        return "admin/product/updateProduct";
    }
    @PostMapping("/admin/product/update")
    public String adminCreateProduct(@RequestParam("id") Integer id,
                                     @RequestParam("name") String name,
                                     @RequestParam("categoryId") Integer categoryId,
                                     @RequestParam("price") float price,
                                     @RequestParam("description") String description,
                                     @RequestParam("thumbnailUrl") MultipartFile multipartFile) throws IOException {
        Product product = productService.getDetailProductById(id);
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setName(name);
        product.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        product.setPrice(price);
        product.setDescription(description);
        Category category = categoryRepository.getById(categoryId);
        product.setCategory(category);
        product.setThumbnail("~/img/product/"+filename);
        productRepository.save(product);
        String uploadDir = "./src/main/resources/static/img/product/";
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try{
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Không thể tải file: "+filename);
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id ){
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/{id}")
    public String productDetail(@PathVariable("id") Integer id){
        return "admin/product/productDetail";
    }

    ////////////////////////User
    @GetMapping("/admin/users")
    public String adminUsers(Model model){
        model.addAttribute("countOrderWait", orderRepository.countDonHangCho());
        List<Integer> listAdminId = userService.findAllAdminId();
        List<User> listUser = userService.findAll();
        for( var item : listAdminId){
            User user = userService.getById(item);
            listUser.remove(user);
        }
        model.addAttribute("listUser",listUser);
        return "admin/user/users";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }





}
