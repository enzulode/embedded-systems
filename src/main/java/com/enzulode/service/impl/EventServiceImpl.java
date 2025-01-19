package com.enzulode.service.impl;

import com.enzulode.dao.repository.EventRepository;
import com.enzulode.dto.ReadEventDto;
import com.enzulode.dto.mapper.EventMapper;
import com.enzulode.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventMapper mapper;
  private final EventRepository repository;

  @Override
  public List<ReadEventDto> findAll() {
    var events = repository.findAll();
    return events.stream().map(mapper::toDto).toList();
  }
}
