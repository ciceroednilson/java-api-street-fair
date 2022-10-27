package br.com.ciceroednilson.feira.service;

import br.com.ciceroednilson.feira.domain.StreetFairDomain;

import java.util.List;

public interface StreetFairService {

    void register(StreetFairDomain streetFairDomain);
    void delete(Integer id);
    void update(StreetFairDomain streetFairDomain, Integer id);
    List<StreetFairDomain> find(String district, String regionFive, String fairName, String neighborhood);
}
