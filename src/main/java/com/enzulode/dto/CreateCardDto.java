package com.enzulode.dto;

import java.util.UUID;

public record CreateCardDto(
    UUID cardId,
    String username
) {}
