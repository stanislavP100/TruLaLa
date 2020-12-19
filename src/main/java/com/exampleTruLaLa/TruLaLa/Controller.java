package com.exampleTruLaLa.TruLaLa;

import com.exampleTruLaLa.TruLaLa.Entity.Goods;
import com.exampleTruLaLa.TruLaLa.Repository.RepositoryGoods;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class Controller {

@Autowired
    private RepositoryGoods repositoryGoods;

    @GetMapping ("/")
  public   String cont(Map<String,Object>map )
    {

        JSONObject mainJsonObject= new JSONObject();

        JSONArray sampleArr=new JSONArray();
        JSONArray electroToolsArr=new JSONArray();


       int step=0;
       int stepForElectroTools=0;

         for (int i=1;i<=repositoryGoods.count()+step;i++)
        {
            Long l=new Long(i);

            if(repositoryGoods.existsById(l)) {

                Optional<Goods> goods = repositoryGoods.findById(l);

                JSONObject sampleObject = new JSONObject();

                sampleObject.put("name", goods.get().getName());
                sampleObject.put("price", goods.get().getPrice());
                sampleObject.put("id", goods.get().getId());
                sampleObject.put("image", goods.get().getImagePath());

                sampleArr.put(sampleObject);
            }
            else
                step++;

        }

//
//        for (int i=1;i<=repositoryElektroTools.count()+stepForElectroTools;i++)
//        {
//            Long l=new Long(i);
//
//            if(repositoryElektroTools.existsById(l)) {
//
//                Optional<ElectroTools> goods = repositoryElektroTools.findById(l);
//
//                JSONObject sampleObject = new JSONObject();
//
//                sampleObject.put("name", goods.get().getName());
//                sampleObject.put("price", goods.get().getPrice());
//                sampleObject.put("id", goods.get().getId());
//                sampleObject.put("image", goods.get().getImagePath());
//
//                electroToolsArr.put(sampleObject);
//            }
//            else
//                stepForElectroTools++;
//
//        }

        System.out.println(sampleArr.toString());

        mainJsonObject.put("all goods", sampleArr);
        mainJsonObject.put("electroTools", electroToolsArr);

        map.put("jsonString", mainJsonObject.toString());
        System.out.println(mainJsonObject.toString());




        return mainJsonObject.toString();
    }




}
