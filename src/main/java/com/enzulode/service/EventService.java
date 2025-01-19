package com.enzulode.service;

import com.enzulode.dto.ReadEventDto;

import java.util.List;

public interface EventService {

  List<ReadEventDto> findAll();
}
