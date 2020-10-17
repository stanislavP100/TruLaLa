package com.exampleTruLaLa.TruLaLa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControllerPost {

    private final StorageService storageService;
    @Autowired
    public ControllerPost(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private RepositoryGoods repositoryGoods;

    @Autowired
    private RepositoryElektroTools repositoryElektroTools;

    @GetMapping("/pos")
    public   String methodGetForPost(Model model) throws Exception {

        model.addAttribute("electro", repositoryGoods.findAll());

        return "index";
    }

    @PostMapping("/post")

    public String methodPost(@RequestParam(defaultValue = "no name") String name,
                             @RequestParam(value = "price", defaultValue = "0.00f") Float price,
                             @RequestParam(value = "category", defaultValue = "goods") String category,
                             @RequestParam MultipartFile file,Model model) throws Exception {

        System.out.println(name+"    "+price+"    "+category);


        if(category.equals("goods")) {
    Goods goods = new Goods();

    goods.setName(name);

    goods.setPrice(price);

            if(file.isEmpty()){

                goods.setImagePath("bol.jpeg");
            }
            else
                goods.setImagePath(file.getOriginalFilename());


    repositoryGoods.save(goods);
}

        if(category.equals("electroTools")) {
            ElectroTools electroTools = new ElectroTools();

            electroTools.setName(name);

            electroTools.setPrice(price);

            if(file.isEmpty()){
                electroTools.setImagePath("index.jpeg");
            }
            else
                electroTools.setImagePath(file.getOriginalFilename());

            repositoryElektroTools.save(electroTools);
        }

        model.addAttribute("electro", repositoryGoods.findAll());

        if (file.isEmpty())

            return "index";


            storageService.store(file);



        return "index";
    }


}
