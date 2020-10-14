package com.exampleTruLaLa.TruLaLa;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.ServletContextResource;
import sun.misc.IOUtils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ControllerImage {


    private  String imagePath;

    @GetMapping(value = "/get-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte [] getImageWithMediaType(@RequestParam String image) throws IOException {

        System.out.println(image);
        WebConfigurer wc=new
                WebConfigurer();

        String str=wc.getUploadDirectory2();
        //   final InputStream in = getClass().getResourceAsStream("/static/persik.jpeg");
        //   !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        /////// http://localhost:8080/get-image?im=bol.jpeg
      //  System.out.println(Base64.encodeBase64URLSafeString( Files.readAllBytes(Paths.get(str +"/" + image))));
        return  Files.readAllBytes(Paths.get(str +"/" + image));//!!! Rabotae!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //   System.out.println(in);
        // return IOUtils.toByteArray(in);
    }


//
//    @GetMapping("/image")
//
//    public   String methodGetForImage(Model model) throws Exception {
//
//        System.out.println("Method get");
//        model.addAttribute("imagePath", imagePath);
//
//        return "getImage";
//    }

// @RequestMapping("/image/{img}")
//    public String methodGetFo(
//             @PathVariable(value = "img") String image4,
//            String img, Model model) throws Exception {
//
//     System.out.println("Requesr "+ image4+"   " +  img);
//imagePath=img;
//
//        model.addAttribute("imagePath", img);
//
//        return "redirect:/image";
//    }
//
// @RequestMapping("/image/{img}")
//    public String methodGetFo(
//             @PathVariable(value = "img") String image4,
//            String img, Model model) throws Exception {
//
//     System.out.println("Requesr "+ image4+"   " +  img);
//imagePath=img;
//
//
//String path="/root/Images/persik.jpeg";
//     Path pa= Paths.get(path);
//
//     byte []data= Files.readAllBytes(pa);
//
//            model.addAttribute("imageByte", data);
//     System.out.println(data.length);
//
//     return "redirect:/image";
//    }


}
