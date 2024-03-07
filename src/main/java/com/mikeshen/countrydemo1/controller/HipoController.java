package com.mikeshen.countrydemo1.controller;


import com.mikeshen.countrydemo1.pojo.Hipo;
import com.mikeshen.countrydemo1.service.HipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/hipolab")
public class HipoController {

    private final HipoService hipoService;
    public HipoController(@Autowired HipoService hipoService){
        this.hipoService = hipoService;
    }
    @GetMapping
    public ResponseEntity<?> getHipoLabs(@RequestParam(value = "country", required = false)  List<String> params) throws ExecutionException, InterruptedException {
        if (params != null && !params.isEmpty()) {
            System.out.println(params);
            List<Hipo> hipo = hipoService.getFilteredHipos(params);
            return new ResponseEntity<>(hipo,HttpStatus.OK);
        } else {
            Hipo[] hipo = hipoService.getHiposWithouParams();
            return new ResponseEntity<>(hipo, HttpStatus.OK);
        }
    }
}
