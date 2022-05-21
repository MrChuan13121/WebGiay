package com.ntc.webgiay.service;

import com.ntc.webgiay.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Page<Comment> findAllByProductId(int productId, Pageable pageable);

    Comment createComment(int productId, int userId, String content);
}
