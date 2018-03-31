package com.dfsp.dashboard.controllers;

import com.dfsp.dashboard.entities.Market;
import com.dfsp.dashboard.repositories.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/api/market")
public class MarketController {

    @Autowired
    MarketRepository marketRepository;

    @GetMapping("/all")
    public List<Market> getMarket() {

        return marketRepository.findAll();
    }

    @GetMapping("/all/response")
    public ResponseEntity<List<Market>> getMarketResponse() {
        List<Market> result =  marketRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public Market getMarketById(@PathVariable("id") Long id) {
        Optional<Market> optionalMarket = marketRepository.findById(id);
        return optionalMarket.get();
    }

    @GetMapping("/year/{year}")
    public List<Market> getMarketByYear(@PathVariable("year") Long year) {
        return marketRepository.findByYear(year);
    }

    @GetMapping("/company/{companyShortName}")
    public List<Market> getMarketByCompanyShortName(@PathVariable("companyShortName") String companyShortName) {
        return marketRepository.findByCompanyShortName(companyShortName);
    }

    @PostMapping("/add")
    public Market addNewMarket(@ModelAttribute Market market) {
        Market result = new Market(
                market.getCompanyName(),
                market.getYear(),
                market.getFinancialResult(),
                market.getShortName()
        );
        return marketRepository.save(result);
    }

    @PutMapping("update/{id}")
    public String updateMarket(@ModelAttribute Market market, @PathVariable("id") Long id){
        Optional<Market> optionalMarket = marketRepository.findById(id);
        optionalMarket.ifPresent(result -> {
            result.setCompanyName(market.getCompanyName());
            result.setFinancialResult(market.getFinancialResult());
            result.setShortName(market.getShortName());
            result.setYear(market.getYear());
        });
        return "market id: " + id + "updated!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMarket(@PathVariable("id") Long id) {
        marketRepository.deleteById(id);
        return "market id: " + id + " deleted!";
    }
}
