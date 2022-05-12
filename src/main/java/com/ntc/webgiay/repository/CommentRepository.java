package com.ntc.webgiay.repository;

import com.ntc.webgiay.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM dbshopgiay.comment WHERE product_id = ?1 ORDER BY created_at DESC")
    Page<Comment> findAllByProductId(int productId, Pageable pageable);
}
