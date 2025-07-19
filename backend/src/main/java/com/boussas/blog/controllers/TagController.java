package com.boussas.blog.controllers;

import com.boussas.blog.entities.Tag;
import com.boussas.blog.entities.dtos.CreateTagsRequest;
import com.boussas.blog.entities.dtos.TagResponse;
import com.boussas.blog.mappers.TagMapper;
import com.boussas.blog.repositories.TagRepository;
import com.boussas.blog.services.TagService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;
    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        List<TagResponse> response= (List<TagResponse>) tags.stream().map(tag->tagMapper.toTagResponse(tag));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagsRequest createTagsRequest) {
        List<Tag> saved = tagService.createTags(createTagsRequest.getNames());
        List<TagResponse> created = saved.stream()
                .map(tagMapper::toTagResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<TagResponse> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
