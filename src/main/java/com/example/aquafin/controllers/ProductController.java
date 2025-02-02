package com.example.aquafin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.aquafin.models.Product;
import com.example.aquafin.services.ProductService;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/super-admin/add-product")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public String getAddProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }
    

    @PostMapping("/super-admin/add-product")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public String addProduct(@ModelAttribute("product") Product product) {
        try {
            productService.addProduct(product);
            return "redirect:/super-admin/view-products";  // Changed redirect
        } catch (Exception e) {
            return "redirect:/super-admin/add-product?error";
        }
    }

    @GetMapping("/super-admin/view-products")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public String viewProducts(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        return "view-products";
    }

    @GetMapping("/super-admin/update-product/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public String getEditProductPage(@PathVariable Long id, Model model) {  // Added @PathVariable
        try {
            Product product = productService.getProductById(id);
            model.addAttribute("product", product);
            return "update-product";
        } catch (Exception e) {
            return "redirect:/super-admin/view-products?error";
        }
    }

    @PostMapping("/super-admin/update-product/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public String updateProduct(@PathVariable Long id,@ModelAttribute Product product,Model model){
        try {
            product.setId(id);
            productService.updateProduct(product);
            return "redirect:/super-admin/view-products";
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update product");
            model.addAttribute("product", product);
            return "update-product";
        }
    }
}
