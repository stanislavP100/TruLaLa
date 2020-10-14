package com.exampleTruLaLa.TruLaLa;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfigurer implements WebMvcConfigurer {

    String uploadDirectory2;

    public String getUploadDirectory2() {
        return uploadDirectory2;
    }

    public WebConfigurer(    )
    {
        File f=new File("Tmp");
        f.mkdir();

       this.uploadDirectory2=f.getAbsolutePath();//.replace("TruLaLa/Tmp","ImagesPrikhod");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        try {

            Path path= Paths.get(uploadDirectory2);
            Files.createDirectory(path);

        }
        catch (IOException ioException)
        {}



        System.out.println(uploadDirectory2+"      !!!!!!!!!!!!!!!!!!!!!!!!!!!!");

registry.addResourceHandler("/Tmp/**").addResourceLocations("file:"+ uploadDirectory2+"/");




    }
}
