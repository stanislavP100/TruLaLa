package com.exampleTruLaLa.TruLaLa;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

@Autowired
    private RepositoryGoods repositoryGoods;

    @GetMapping ("/")
  public   String cont(Map<String,Object>map )
    {

        JSONObject mainJsonObject= new JSONObject();

        JSONArray sampleArr=new JSONArray();
        System.out.println(
                repositoryGoods.count()       );
       int step=0;

         for (int i=1;i<=repositoryGoods.count()+step;i++)
        {
            Long l=new Long(i);

            if(repositoryGoods.existsById(l)) {

                Optional<Goods> goods = repositoryGoods.findById(l);

                JSONObject sampleObject = new JSONObject();

                sampleObject.put("name", goods.get().getName());
                sampleObject.put("price", goods.get().getPrice());
                sampleObject.put("id", goods.get().getId());

                sampleArr.put(sampleObject);
            }
            else
                step++;

        }




        mainJsonObject.put("all goods", sampleArr);

        map.put("jsonString", mainJsonObject.toString());
        System.out.println(sampleArr.toString());

        return "getPage";
    }

    @GetMapping ("/post")
    public   String methodGetForPost() throws Exception {

        return "index";
    }

    @PostMapping("/post")

    public String methodPost(@RequestParam String name,
                             @RequestParam(value = "price") Float price){
        System.out.println(name+"    "+price);

        Goods goods=new Goods();

        goods.setName(name);

       goods.setPrice(price);



        repositoryGoods.save(goods);



        return "index";
    }

public static String makeJson() throws Exception
{
    JSONObject sampleObject = new JSONObject();
    sampleObject.put("name", "Stackabuser");
    sampleObject.put("age", 35);


    Files.write(Paths.get("jsonFile.json"), sampleObject.toString().getBytes());

    return sampleObject.toString();
}

}
