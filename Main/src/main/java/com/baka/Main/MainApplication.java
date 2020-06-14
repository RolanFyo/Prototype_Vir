package com.baka.Main;


import com.baka.Main.UI.FormVMRam;
import com.baka.Main.data.ProductEntity;
import com.baka.Main.data.ProductJdbcRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.util.List;

@SpringBootApplication
public class MainApplication extends JFrame implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    @Autowired
    ProductJdbcRepo rep;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);

    }


    @Override
    public void run(String... args) {
        List<ProductEntity> testList = rep.findAll();
        testList.forEach(x -> log.info(String.valueOf(x)));
        new FormVMRam(testList, rep);
    }

//    public Form(List<ProductEntity> entityList) throws HeadlessException {
//        this.entityList = entityList;
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//    }
}

