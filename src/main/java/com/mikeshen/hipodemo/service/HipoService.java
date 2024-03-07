package com.mikeshen.hipodemo.service;

import com.mikeshen.hipodemo.pojo.Hipo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public interface HipoService {
    public Hipo[] getHiposWithouParams();
    public List<Hipo> getFilteredHipos(List<String> countries) throws ExecutionException, InterruptedException;
}
