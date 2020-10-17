package com.exampleTruLaLa.TruLaLa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ControllerDelete {

    private final RepositoryGoods repositoryGoods;
    private final StorageService storageService;

    private Goods product;

    public ControllerDelete(RepositoryGoods repositoryGoods, StorageService storageService) {
        this.repositoryGoods = repositoryGoods;
        this.storageService=storageService;
    }

    @RequestMapping("/{id7}")
    public   String methodGetForPost(
            @PathVariable (value = "id7") String id4,
            String id, Model model) throws Exception {

//        System.out.println(id);
//        System.out.println(id4);

        product=repositoryGoods.getOne(Long.parseLong(id));

        model.addAttribute("productToChange", product);

        return "changeProduct";
    }

//    @GetMapping("/changeProducta")
//    public String getPage(){
//
//        return "changeProduct";
//    }

    @PostMapping("/changeProduct")

    public String changeProductPost(@RequestParam String name,
                                    @RequestParam(value = "price") Float price,
                                    @RequestParam MultipartFile file, Model model)
    {

        product.setName(name);

        product.setPrice(price);





        if(file.isEmpty()) {
            System.out.println(product.toString() + "file is empty");

            model.addAttribute("electro", repositoryGoods.findAll()); //storageService.loadAll());//.map(

            repositoryGoods.save(product);
            return "index";
        }
        product.setImagePath(file.getOriginalFilename());


        repositoryGoods.save(product);

        System.out.println( product.toString() + file.getOriginalFilename());


        try {
            storageService.store(file);
        } catch (Exception e) {
            e.printStackTrace();
        }


        model.addAttribute("electro", repositoryGoods.findAll());


        return "index";
    }

    @PostMapping("/deleteProduct")

    public String deleteProductPost(Model model){

        repositoryGoods.delete(product);
        model.addAttribute("electro", repositoryGoods.findAll());

        return "index";
    }

}
