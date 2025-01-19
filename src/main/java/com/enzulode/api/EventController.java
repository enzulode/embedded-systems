package com.enzulode.api;

import com.enzulode.dto.ReadEventDto;
import com.enzulode.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

  private final EventService eventService;

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public List<ReadEventDto> getEvents() {
    return eventService.findAll();
  }
}
