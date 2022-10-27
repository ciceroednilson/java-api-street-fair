package br.com.ciceroednilson.feira.mapper;

import br.com.ciceroednilson.feira.domain.StreetFairDomain;
import br.com.ciceroednilson.feira.repository.entity.StreetFairEntity;

import java.util.ArrayList;
import java.util.List;

public class StreetFairMapper {

    public static StreetFairEntity toStreetFairEntity(final StreetFairDomain domain) {
        return StreetFairEntity
                .builder()
                .longitude(domain.getLongitude())
                .latitude(domain.getLatitude())
                .setCens(domain.getSetCens())
                .areAp(domain.getAreAp())
                .codDist(domain.getCodDist())
                .district(domain.getDistrict())
                .codSubPrefecture(domain.getCodSubPrefecture())
                .subPrefecture(domain.getSubPrefecture())
                .regionFive(domain.getRegionFive())
                .regionEight(domain.getRegionEight())
                .fairName(domain.getFairName())
                .register(domain.getRegister())
                .streetName(domain.getStreetName())
                .number(domain.getNumber())
                .neighborhood(domain.getNeighborhood())
                .referencePoint(domain.getReferencePoint())
                .build();
    }

    public static List<StreetFairDomain> toListOfStreetFairDomain(final List<StreetFairEntity> listEntity) {
        final List<StreetFairDomain> listDomain = new ArrayList<>();
        listEntity.forEach(entity -> {
            listDomain.add(buildStreetFairDomain(entity));
        });
        return listDomain;
    }

    private static StreetFairDomain buildStreetFairDomain(final StreetFairEntity entity) {
        return StreetFairDomain
                .builder()
                .id(entity.getId())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .setCens(entity.getSetCens())
                .areAp(entity.getAreAp())
                .codDist(entity.getCodDist())
                .district(entity.getDistrict())
                .codSubPrefecture(entity.getCodSubPrefecture())
                .subPrefecture(entity.getSubPrefecture())
                .regionFive(entity.getRegionFive())
                .regionEight(entity.getRegionEight())
                .fairName(entity.getFairName())
                .register(entity.getRegister())
                .streetName(entity.getStreetName())
                .number(entity.getNumber())
                .neighborhood(entity.getNeighborhood())
                .referencePoint(entity.getReferencePoint())
                .build();
    }
}
