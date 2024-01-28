package com.psi.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.psi.model.dto.PurchaseDto;
import com.psi.model.dto.PurchaseItemDto;
import com.psi.model.po.Purchase;
import com.psi.model.po.PurchaseItem;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    @Mapping(source = "purchaseItems", target = "purchaseItems")
    PurchaseDto purchaseToPurchaseDto(Purchase purchase);

    // 如果需要，可以为PurchaseItem到PurchaseItemDto的转换添加更多的映射方法
    PurchaseItemDto purchaseItemToPurchaseItemDto(PurchaseItem purchaseItem);
}
