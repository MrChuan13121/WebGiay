package com.ntc.webgiay.service.impl;

import com.ntc.webgiay.model.Comment;
import com.ntc.webgiay.model.Product;
import com.ntc.webgiay.model.User;
import com.ntc.webgiay.repository.CommentRepository;
import com.ntc.webgiay.repository.ProductRepository;
import com.ntc.webgiay.repository.UserRepository;
import com.ntc.webgiay.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<Comment> findAllByProductId(int productId , Pageable pageable){
        Page<Comment> comments = commentRepository.findAllByProductId(productId,pageable);
        return comments;
    }

    @Override
    public Comment createComment(int productId, int userId, String content){
        Comment comment = new Comment();
        Product product = productRepository.getById(productId);
        User user = userRepository.getById(userId);
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        comment.setProduct(product);
        comment.setUser(user);
        comment.setContent(content);

        commentRepository.save(comment);
        return comment;
    }
}
