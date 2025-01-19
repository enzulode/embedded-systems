package com.enzulode.dto;

import java.util.UUID;

public record ReadCardDto(
    Long id,
    UUID cardId,
    String username
) {}
