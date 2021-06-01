package com.virtualmind.test.rest;

import com.virtualmind.test.dto.TopicCreationDTO;
import com.virtualmind.test.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/topic")
public class TopicResource {

    @Autowired
    private TopicService topicService;


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TopicCreationDTO dto) {
        try {
            topicService.create(dto);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
