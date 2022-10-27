package br.com.ciceroednilson.feira.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StreetFairDomain {

    @JsonIgnore
    private static final String MSG_IS_MANDATORY = " is mandatory!";

    @JsonIgnore
    private static final String MSG_INVALID_VALUE = " invalid value!";

    @JsonIgnore
    private static final String MSG_INVALID_SIZE = " invalid size!";

    private Integer id;

    @Max(message = MSG_INVALID_VALUE, value = Long.MAX_VALUE)
    @Min(message = MSG_INVALID_VALUE, value = Long.MIN_VALUE)
    private Long longitude;

    @Max(message = MSG_INVALID_VALUE, value = Long.MAX_VALUE)
    @Min(message = MSG_INVALID_VALUE, value = Long.MIN_VALUE)
    private Long latitude;

    @Max(message = MSG_INVALID_VALUE, value = Long.MAX_VALUE)
    @Min(message = MSG_INVALID_VALUE, value = Long.MIN_VALUE)
    private Long setCens;

    @Max(message = MSG_INVALID_VALUE, value = Long.MAX_VALUE)
    @Min(message = MSG_INVALID_VALUE, value = Long.MIN_VALUE)
    private Long areAp;

    @Max(message = MSG_INVALID_VALUE, value = 255)
    @Min(message = MSG_INVALID_VALUE, value = 1L)
    private Integer codDist;

    @Size(min = 1, max = 250, message = MSG_INVALID_SIZE)
    @NotBlank(message = MSG_IS_MANDATORY)
    private String district;

    @Max(message = MSG_INVALID_VALUE, value = 255)
    @Min(message = MSG_INVALID_VALUE, value = 1L)
    private Integer codSubPrefecture;

    @Size(min = 1, max = 250, message = MSG_INVALID_SIZE)
    @NotBlank(message = MSG_IS_MANDATORY)
    private String subPrefecture;

    @Size(min = 1, max = 50, message = MSG_INVALID_SIZE)
    @NotBlank(message = MSG_IS_MANDATORY)
    private String regionFive;

    @Size(min = 1, max = 50)
    @NotBlank(message = MSG_IS_MANDATORY)
    private String regionEight;

    @Size(min = 1, max = 250, message = MSG_INVALID_SIZE)
    @NotBlank(message = MSG_IS_MANDATORY)
    private String fairName;

    @Size(min = 1, max = 6, message = MSG_INVALID_SIZE)
    @NotBlank(message = MSG_IS_MANDATORY)
    private String register;

    @Size(min = 1, max = 250, message = MSG_INVALID_SIZE)
    @NotBlank(message = MSG_IS_MANDATORY)
    private String streetName;

    @Size(min = 1, max = 50, message = MSG_INVALID_SIZE)
    private String number;

    @Size(min = 1, max = 100, message = MSG_INVALID_SIZE)
    @NotBlank(message = MSG_IS_MANDATORY)
    private String neighborhood;

    @Size(min = 1, max = 250, message = MSG_INVALID_SIZE)
    @NotBlank(message = MSG_IS_MANDATORY)
    private String referencePoint;
}
