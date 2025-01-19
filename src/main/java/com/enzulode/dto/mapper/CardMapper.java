package com.enzulode.dto.mapper;

import com.enzulode.dao.entity.Card;
import com.enzulode.dto.CreateCardDto;
import com.enzulode.dto.ReadCardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {

  @Mapping(target = "id", ignore = true)
  Card toEntity(CreateCardDto dto);

  ReadCardDto toDto(Card card);
}
