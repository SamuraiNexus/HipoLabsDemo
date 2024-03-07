package com.mikeshen.hipodemo.service.impl;

import com.mikeshen.hipodemo.pojo.Hipo;
import com.mikeshen.hipodemo.service.HipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Service
public class HipoServiceImpl implements HipoService {


    private final RestTemplate restTemplate;
    private String baseUrl = "http://universities.hipolabs.com/search";
    @Autowired
    public HipoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Hipo[] getHiposWithouParams() {
        Hipo[] hipoLabs = getByOneQuery(baseUrl, Hipo[].class);
        return hipoLabs;
    }

    @Override
    public List<Hipo> getFilteredHipos(List<String> countries) {
     // return getBySingleThread(countries);
        return getByCompletableFuture(countries);
    }

    public List<Hipo> getBySingleThread(List<String> countries) {
        List<Hipo> result = new ArrayList<>();
        for (String country: countries) {
            Hipo[] hipoLabs= getByOneQuery(baseUrl + "?country=" + country, Hipo[].class);
            result.addAll(Arrays.asList(hipoLabs));
        }
        return result;
    }

    public Hipo[] getByOneQuery(String url, Class<?> type) {
        Hipo[] hipoLabs= restTemplate.getForObject(url, Hipo[].class);
        return hipoLabs;
    }
    public List<Hipo> getByCompletableFuture(List<String> countries) {
        List<Hipo[]> futureResult = new ArrayList<>();

        List<CompletableFuture<Hipo[]>> completableFutures = countries.stream().map(country -> {
            String url = baseUrl + "?country=" + country;
            return new CompletableFuture().supplyAsync(() -> getByOneQuery(url, Hipo[].class));
        }).collect(Collectors.toList());
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).join();
        futureResult = completableFutures.stream().map((future) -> {
            try {
                return future.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        List<Hipo> finalResult = futureResult
                .stream()
                .flatMap(hipos->Arrays.stream(hipos))
                .collect(Collectors.toList()); ;
        return finalResult;
    }
}
