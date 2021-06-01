package com.virtualmind.test.rest;

import com.virtualmind.test.dto.PostCreationDTO;
import com.virtualmind.test.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostResource {

    @Autowired
    private PostService postService;


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PostCreationDTO dto) {
        try {
            postService.saveOrUpdate(dto);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
