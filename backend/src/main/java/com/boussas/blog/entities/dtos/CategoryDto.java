package com.boussas.blog.entities.dtos;

import java.util.UUID;

public record CategoryDto(
        UUID id,
        String name,
        long postCount
                          ) {
}
