package com.virtualmind.test.service;

import com.virtualmind.test.dto.PostCreationDTO;
import com.virtualmind.test.model.Post;
import com.virtualmind.test.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class PostService {

    @Autowired
    @Lazy
    private TopicService topicService;

    @Autowired
    private EntityManager entityManager;


    @Transactional
    public Post saveOrUpdate(PostCreationDTO dto) throws Exception {
        Topic topic = topicService.findById(dto.getTopicId());

        Post post = Post.builder()
                .topic(topic)
                .title(dto.getTitle())
                .text(dto.getText())
                .build();

        entityManager.persist(post);

        return post;
    }

}
