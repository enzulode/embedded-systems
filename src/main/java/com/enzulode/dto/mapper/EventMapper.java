package com.enzulode.dto.mapper;

import com.enzulode.dao.entity.Event;
import com.enzulode.dto.ReadEventDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {

  ReadEventDto toDto(Event event);
}
