package com.competitors.service;

import com.competitors.entity.Comment;
import com.khazix.core.support.Page;

public interface CommentService {
    Page<Comment> list(Page<Comment> page);
}
