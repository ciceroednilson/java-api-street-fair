package br.com.ciceroednilson.feira.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StreetFairEntity {

    private Integer id;
    private Long longitude;
    private Long latitude;
    private Long setCens;
    private Long areAp;
    private int codDist;
    private String district;
    private int codSubPrefecture;
    private String subPrefecture;
    private String regionFive;
    private String regionEight;
    private String fairName;
    private String register;
    private String streetName;
    private String number;
    private String neighborhood;
    private String referencePoint;
}
