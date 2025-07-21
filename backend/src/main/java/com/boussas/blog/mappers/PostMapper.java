package com.boussas.blog.mappers;

import com.boussas.blog.entities.dtos.CreatePostRequest;
import com.boussas.blog.entities.dtos.UpdatePostRequest;
import com.boussas.blog.entities.dtos.CreatePostRequestDto;
import com.boussas.blog.entities.dtos.PostDto;
import com.boussas.blog.entities.dtos.UpdatePostRequestDto;
import com.boussas.blog.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "status", source = "status")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);

}
