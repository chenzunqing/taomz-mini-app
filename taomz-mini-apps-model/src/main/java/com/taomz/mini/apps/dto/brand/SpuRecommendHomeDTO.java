package com.taomz.mini.apps.dto.brand;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SpuRecommendHomeDTO {
    private String type;
    private List<SpuRecDTO> spuRecDTOList = new ArrayList<>();
}

@Data
class SpuRecDTO {
    private Integer id ;
    private String mainImg;
    private String spuName;
}
