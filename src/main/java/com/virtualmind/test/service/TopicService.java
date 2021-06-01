package com.virtualmind.test.service;

import com.virtualmind.test.dto.TopicCreationDTO;
import com.virtualmind.test.model.Topic;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class TopicService {

    @Autowired
    private EntityManager entityManager;


    @Transactional
    public Topic create(TopicCreationDTO dto) throws Exception {
        if (dto == null || dto.getName() == null || dto.getName().isEmpty()) throw new Exception("Invalid topic name");
        Topic topic = Topic.builder().name(dto.getName()).build();
        entityManager.persist(topic);
        return topic;
    }

    @Transactional
    public Topic update(Integer id, String name) throws Exception {
        Topic topicDB = findById(id);
        topicDB.setName(name);
        return topicDB;
    }

    public Topic findById(Integer id) throws Exception {
        Topic topic = entityManager.find(Topic.class, id);
        if (topic == null) throw new NotFoundException("Topic not found");
        return topic;
    }
}
