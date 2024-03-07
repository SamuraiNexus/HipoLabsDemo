package com.mikeshen.countrydemo1.service;

import com.mikeshen.countrydemo1.pojo.Hipo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public interface HipoService {
    public Hipo[] getHiposWithouParams();
    public List<Hipo> getFilteredHipos(List<String> countries) throws ExecutionException, InterruptedException;
}
