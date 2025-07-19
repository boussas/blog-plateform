package com.boussas.blog.mappers;

import com.boussas.blog.entities.Post;
import com.boussas.blog.entities.PostStatus;
import com.boussas.blog.entities.Tag;
import com.boussas.blog.entities.dtos.CategoryDto;
import com.boussas.blog.entities.dtos.TagResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper  {
    @Mapping(target = "postCount",source = "posts",qualifiedByName = "CalculatePostCount")
    TagResponse toTagResponse(Tag tag);
    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts) {
        if(posts == null || posts.isEmpty()) return 0;
        return (int) posts.
                stream().
                filter(post-> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
