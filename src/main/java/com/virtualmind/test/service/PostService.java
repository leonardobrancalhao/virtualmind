package com.virtualmind.test.service;

import com.virtualmind.test.dto.PostCreationDTO;
import com.virtualmind.test.dto.PostViewDTO;
import com.virtualmind.test.model.Post;
import com.virtualmind.test.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    private int pageSize = 25; // In real life would be configurable
    public List<PostViewDTO> listPostTitlesAndTopics(int pageNumber) {
        List<Post> posts = entityManager
                .createQuery("SELECT p FROM post p inner join fetch p.topic as t")
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        List<PostViewDTO> result = new ArrayList(posts.size());

        for (Post post : posts) {
            PostViewDTO postDto = new PostViewDTO();
            postDto.setId(post.getId());
            postDto.setTitle(post.getTitle());
            postDto.setTopicName(post.getTopic().getName());
            result.add(postDto);
        }

        return result;
    }

}
