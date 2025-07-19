package com.boussas.blog.entities.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CreateTagsRequest {
    @NotEmpty
    @Size(min = 1, max = 10)
    private  Set<String> names;

}
