package com.zifrasports.zifra.sports.controllers;


import com.zifrasports.zifra.sports.dao.ProductDAO;
import com.zifrasports.zifra.sports.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller("")
public class ProductController {

    private static String uploadPath = "/home/pc/Downloads/zifra-sports/src/main/resources/static/uploads/";

    @Autowired
    ProductDAO dao;

    @GetMapping("")
    private String openIndex(Model model) {

        model.addAttribute("product", dao.findAll());

        return "index";

    }

    @GetMapping("/cadastrar-produto")
    private String openCadastrarProduto(Product product) {
        return "cadastrar-produto.html";
    }

    @PostMapping("/cadastrar-produto")
    private String saveProduct(Product product, @RequestParam("file")MultipartFile image) {

        dao.save(product);

        try {
            if(!image.isEmpty()) {
                byte[] bytes = image.getBytes();
                Path path = Paths.get(uploadPath+String.valueOf(product.getId())+image.getOriginalFilename());
                Files.write(path, bytes);

                product.setImage(String.valueOf(String.valueOf(product.getId())+image.getOriginalFilename()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        dao.save(product);

        return "cadastrar-produto.html";

    }

}
