package com.example.taskManagementSystem.services.impl;

import com.example.taskManagementSystem.domain.dto.requests.CommentCreateRequest;
import com.example.taskManagementSystem.domain.dto.requests.CommentUpdateRequest;
import com.example.taskManagementSystem.domain.entities.CommentEntity;
import com.example.taskManagementSystem.domain.entities.TaskEntity;
import com.example.taskManagementSystem.domain.entities.UserEntity;
import com.example.taskManagementSystem.repositories.CommentRepository;
import com.example.taskManagementSystem.repositories.TaskRepository;
import com.example.taskManagementSystem.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;


    @Override
    public CommentEntity createComment(UserEntity user, CommentCreateRequest commentRequest) {
        TaskEntity taskEntity = taskRepository.findById(commentRequest.getTaskId()).orElseThrow();
        CommentEntity comment = CommentEntity.builder()
                .task(taskEntity)
                .content(commentRequest.getContent())
                .author(user)
                .createdDate(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }

    @Override
    public Optional<CommentEntity> updateComment(CommentUpdateRequest commentUpdateRequest) {
        CommentEntity savedComment = commentRepository.findById(commentUpdateRequest.getCommentId()).orElseThrow();
        savedComment.setContent(commentUpdateRequest.getContent());
        commentRepository.saveAndFlush(savedComment);
        return Optional.of(savedComment);
    }

    @Override
    public Optional<CommentEntity> getCommentById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<CommentEntity> getAllCommentsByTaskId(long id) {
        TaskEntity task = taskRepository.findById(id).orElseThrow();
        return commentRepository.findAllByTask(task);
    }

    @Override
    public void deleteCommentById(long id) {
        commentRepository.deleteCommentEntityByCommentId(id);
    }
}
