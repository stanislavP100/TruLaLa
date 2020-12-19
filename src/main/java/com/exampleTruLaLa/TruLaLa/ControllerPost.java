package com.exampleTruLaLa.TruLaLa;


import com.exampleTruLaLa.TruLaLa.Entity.*;
import com.exampleTruLaLa.TruLaLa.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static com.exampleTruLaLa.TruLaLa.Entity.BudivelniSumish.getClassName;

@Controller
public class ControllerPost {

    private final StorageService storageService;
    private final RepositoryBudivelniSumish repositoryBudivelniSumish;
    private  final RepositoryGoods repositoryGoods;
    private final RepositorySadGorod repositorySadGorod;
    private  final  RepositorySantehnika repositorySantehnika;
    private  final  RepositoryInstrumenty repositoryInstrumenty;
    private final RepositoryElectroinstrument repositoryElectroinstrument;
    @Autowired
    public ControllerPost(StorageService storageService,
                          RepositoryBudivelniSumish repositoryBudivelniSumish,
                          RepositoryElectroinstrument repositoryElectroinstrument,
                          RepositoryInstrumenty repositoryInstrumenty,
                          RepositorySantehnika repositorySantehnika,
                          RepositorySadGorod repositorySadGorod,
                          RepositoryGoods repositoryGoods) {
        this.storageService = storageService;
        this.repositoryBudivelniSumish=repositoryBudivelniSumish;
        this.repositoryGoods = repositoryGoods;
        this.repositoryElectroinstrument=repositoryElectroinstrument;
        this.repositorySadGorod=repositorySadGorod;
        this.repositorySantehnika=repositorySantehnika;
        this.repositoryInstrumenty=repositoryInstrumenty;
    }



    @GetMapping("/pos")
    public   String methodGetForPost(Model model) throws Exception {

        model.addAttribute("electro", repositoryGoods.findAll());

        return "index";
    }

    @PostMapping("/post")

    public String methodPost(@RequestParam(defaultValue = "no name") String name,
                             @RequestParam(value = "price", defaultValue = "0.00f") Float price,
                             @RequestParam(value = "category", defaultValue = "VseDlyaRemontu") String category,
                             @RequestParam MultipartFile file,Model model) throws Exception {

        System.out.println(name+"    "+price+"    "+category);

        BudivelniSumish budsum=new BudivelniSumish();
        Electroinstrument eli=new Electroinstrument();
        Santehnika san=new Santehnika();
        Instrumenty ins=new Instrumenty();
        SadGorod sg=new SadGorod();
        Goods goods=new Goods();

        switch (category){
            case  "BudivelniSumish": budsum.setName(name);
                                     budsum.setPrice(price);
                                   if(file.isEmpty()){
                                      budsum.setImagePath("bol.jpeg"); }
                                   else
                                      budsum.setImagePath(file.getOriginalFilename());
                                    repositoryBudivelniSumish.save(budsum);
                                    break;
            case  "Electroinstrument": eli.setName(name);
                                       eli.setPrice(price);
                                     if(file.isEmpty()){
                                        eli.setImagePath("bol.jpeg"); }
                                     else
                                        eli.setImagePath(file.getOriginalFilename());
                                     repositoryElectroinstrument.save(eli);
                                     break;
            case  "Santehnika":  san.setName(name);
                                 san.setPrice(price);
                                 if(file.isEmpty()){
                                     san.setImagePath("bol.jpeg"); }
                                 else
                                     san.setImagePath(file.getOriginalFilename());
                                 repositorySantehnika.save(san);
                                 break;

            case  "Instrumenty":    ins.setName(name);
                                    ins.setPrice(price);
                                    if(file.isEmpty()){
                                        ins.setImagePath("bol.jpeg"); }
                                    else
                                        ins.setImagePath(file.getOriginalFilename());
                                    repositoryInstrumenty.save(ins);
                                    break;

            case  "VseDlyaRemontu":     goods.setName(name);
                                        goods.setPrice(price);
                                        if(file.isEmpty()){
                                            goods.setImagePath("bol.jpeg"); }
                                        else
                                            goods.setImagePath(file.getOriginalFilename());
                                        repositoryGoods.save(goods);
                                        break;

            case  "SadGorod":   sg.setName(name);
                                sg.setPrice(price);
                                if(file.isEmpty()){
                                    sg.setImagePath("bol.jpeg"); }
                                else
                                    sg.setImagePath(file.getOriginalFilename());
                                repositorySadGorod.save(sg);
                                break;
        }


//
//        if(category.equals("VseDlyaRemontu")) {
//    Goods goods = new Goods();
//            System.out.println(Goods.getClassName());
//    goods.setName(name);
//
//    goods.setPrice(price);
//
//
//            if(file.isEmpty()){
//
//                goods.setImagePath("bol.jpeg");
//            }
//            else
//                goods.setImagePath(file.getOriginalFilename());
//
//
//    repositoryGoods.save(goods);
//
//}

        model.addAttribute("electro", repositoryGoods.findAll());

        if (file.isEmpty())

            return "index";


            storageService.store(file);



        return "index";
    }


}
