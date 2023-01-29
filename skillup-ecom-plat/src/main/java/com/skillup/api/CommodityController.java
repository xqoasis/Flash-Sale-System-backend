package com.skillup.api;

import com.skillup.api.dto.in.CommodityInDto;
import com.skillup.api.dto.out.CommodityOutDto;
import com.skillup.api.util.ResponseUtil;
import com.skillup.domain.commodity.CommodityDomain;
import com.skillup.domain.commodity.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @PostMapping()
    public ResponseEntity<CommodityOutDto> createNewCommodity(@Validated @RequestBody CommodityInDto commodityInDto){
        CommodityDomain commodityDomain =  createCommodityDomain(commodityInDto);
        CommodityDomain savedCommodityDomain = null;
        try{
            savedCommodityDomain = commodityService.registerCommodity(commodityDomain);
        }catch (Exception e){
//            return ResponseEntity.status(ResponseUtil.BAD_REQUEST).body(SkillResponse.builder()
//                    .msg(ResponseUtil.USER_EXISTS)
//                    .build());
        }

        CommodityOutDto commodityOutDto = createCommodityOutDto(savedCommodityDomain);
        return ResponseEntity.status(ResponseUtil.SUCCESS).body(commodityOutDto);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CommodityOutDto> getCommodityById(@PathVariable("id") String id){
        CommodityDomain commodityDomain = commodityService.getCommodityById(id);
        if (Objects.nonNull(commodityDomain)){
            return ResponseEntity.status(ResponseUtil.SUCCESS)
                    .body(createCommodityOutDto(commodityDomain));
        }
        return null;
    }

    private CommodityDomain createCommodityDomain(CommodityInDto commodityInDto){
        return CommodityDomain.builder()
                .commodityId(UUID.randomUUID().toString())
                .commodityName(commodityInDto.getCommodityName())
                .description(commodityInDto.getDescription())
                .price(commodityInDto.getPrice())
                .imageUrl(commodityInDto.getImageUrl())
                .build();
    }
    private CommodityOutDto createCommodityOutDto(CommodityDomain commodityDomain){
        return CommodityOutDto.builder()
                .commodityId(commodityDomain.getCommodityId())
                .commodityName(commodityDomain.getCommodityName())
                .description(commodityDomain.getDescription())
                .price(commodityDomain.getPrice())
                .imageUrl(commodityDomain.getImageUrl())
                .build();
    }
}
