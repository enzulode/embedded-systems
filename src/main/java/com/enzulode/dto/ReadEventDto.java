package com.enzulode.dto;

import com.enzulode.dto.util.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReadEventDto(
    Long id,
    UUID cardId,
    String username,
    EventType type,
    LocalDateTime at
) {}
