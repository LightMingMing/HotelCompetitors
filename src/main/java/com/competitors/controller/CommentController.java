package com.competitors.controller;

import com.competitors.domain.CommentResultPage;
import com.competitors.entity.Comment;
import com.competitors.service.CommentService;
import com.khazix.core.support.ResultPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String index() {
        return "forward:/comment/1";
    }

    @GetMapping("/{page}")
    public String list(@PathVariable int page, Model model) {
        ResultPage<Comment> resultPage = new ResultPage<>();
        resultPage.setPageNo(page);
        resultPage.setPageSize(10);
        resultPage.setControllerName("comment");
        commentService.list(resultPage);
        model.addAttribute("resultPage", resultPage);
        return "comment_list";
    }

    @PostMapping
    public String list(CommentResultPage resultPage, Model model) {
        if (resultPage.getEntity() != null)
            logger.info("请求实体:{}", resultPage.getEntity());
        resultPage.setControllerName("comment");
        commentService.list(resultPage);
        model.addAttribute("resultPage", resultPage);
        return "comment_list";
    }

}
