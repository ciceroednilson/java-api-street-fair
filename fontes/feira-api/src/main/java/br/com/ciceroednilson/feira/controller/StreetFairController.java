package br.com.ciceroednilson.feira.controller;

import br.com.ciceroednilson.feira.domain.StreetFairDomain;
import br.com.ciceroednilson.feira.service.StreetFairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(StreetFairController.ROUTE)
@RestController
public class StreetFairController {
    public static final String ROUTE = "/fair";

    @Autowired
    private StreetFairService streetFairService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@Valid @RequestBody final StreetFairDomain streetFairDomain) {
        streetFairService.register(streetFairDomain);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delete(@PathVariable("id") final Integer id) {
        streetFairService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity update(@Valid @RequestBody final StreetFairDomain streetFairDomain, @PathVariable("id") final Integer id) {
        streetFairService.update(streetFairDomain, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<List<StreetFairDomain>> find(
            @RequestParam(name = "district", required = false) final String district,
            @RequestParam(name = "region_five", required = false) final String regionFive,
            @RequestParam(name = "fair_name", required = false) final String fairName,
            @RequestParam(name = "neighborhood", required = false) final String neighborhood) {
       final List<StreetFairDomain> list = streetFairService.find(district, regionFive, fairName, neighborhood);
       return ResponseEntity.ok(list);
    }
}
