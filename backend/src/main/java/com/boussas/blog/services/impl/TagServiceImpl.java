package com.boussas.blog.services.impl;

import com.boussas.blog.entities.Tag;
import com.boussas.blog.entities.dtos.TagResponse;
import com.boussas.blog.repositories.TagRepository;
import com.boussas.blog.services.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAllWithPostsCount();
    }

    @Override
    @Transactional
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag> existing = tagRepository.findByNameIn(tagNames);

        Set<String> existingTagNames = existing.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .collect(Collectors.toList());

        if (!newTags.isEmpty()) {
            tagRepository.saveAll(newTags);
        }

        List<Tag> savedTags = new ArrayList<>();
        savedTags.addAll(existing);
        savedTags.addAll(newTags);

        return savedTags;
    }

    @Override
    @Transactional
    public void deleteTag(UUID id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Tag with ID " + id + " not found"));

        if (!tag.getPosts().isEmpty()) {
            throw new IllegalStateException("Cannot delete tag associated with posts");
        }

        tagRepository.deleteById(id);
    }


}
