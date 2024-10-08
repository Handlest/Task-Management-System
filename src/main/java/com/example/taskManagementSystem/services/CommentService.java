package com.example.taskManagementSystem.services;

import com.example.taskManagementSystem.domain.dto.requests.CommentCreateRequest;
import com.example.taskManagementSystem.domain.dto.requests.CommentUpdateRequest;
import com.example.taskManagementSystem.domain.entities.CommentEntity;
import com.example.taskManagementSystem.domain.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    /**
     * Создаёт новый комментарий
     *
     * @param user           Автор комментария
     * @param commentRequest Поля, получаемые при запросе
     * @return {@link CommentEntity}, сохранённый в базе данных
     * @see CommentCreateRequest
     */
    CommentEntity createComment(UserEntity user, CommentCreateRequest commentRequest);

    /**
     * Обновляет комментарий. Может быть выполнено только владельцем комментария
     *
     * @param commentUpdateRequest Поля, получаемые при запросе
     * @return Обновлённый {@link CommentEntity}, сохранённый в базе данных
     * @see CommentUpdateRequest
     */
    CommentEntity updateComment(CommentUpdateRequest commentUpdateRequest);

    /**
     * Получает комментарий из базы данных по его id
     *
     * @param id id запрашиваемого комментария
     * @return {@link CommentEntity}, сохранённый в базе данных
     */
    CommentEntity getCommentById(long id);

    /**
     * Получает список комментариев из базы данных по id задачи, к которой они относятся
     *
     * @param id id задачи, которой принадлежат комментарии
     * @return Список {@link CommentEntity}, принадлежащих задаче с указанным id
     */
    List<CommentEntity> getAllCommentsByTaskId(long id);

    /**
     * Удаляет комментарий по его id. Если комментарий не найден, то ошибка NotFound не будет вызвана
     *
     * @param id id комментария
     */
    void deleteCommentById(long id);
}
