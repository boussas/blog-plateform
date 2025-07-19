package com.boussas.blog.services;

import com.boussas.blog.entities.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
@Service
public interface TagService {
    List<Tag> getAllTags();
    List<Tag> createTags(Set<String> tagNames );
    void deleteTag(UUID id);
}
