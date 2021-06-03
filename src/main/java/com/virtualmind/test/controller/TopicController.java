package com.virtualmind.test.controller;

import com.virtualmind.test.dto.TopicCreationDTO;
import com.virtualmind.test.dto.TopicUpdateDTO;
import com.virtualmind.test.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/topic")
public class TopicController {

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

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody TopicUpdateDTO dto) {
        try {
            topicService.updateTopic(id, dto);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
