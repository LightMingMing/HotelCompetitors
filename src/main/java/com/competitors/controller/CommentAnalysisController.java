package com.competitors.controller;

import com.competitors.support.DictionaryControl;
import com.competitors.entity.CommentAnalysis;
import com.competitors.repository.CommentAnalysisRepository;
import com.khazix.core.support.ResultPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/commentAnalysis")
public class CommentAnalysisController {

    private final static Logger logger = LoggerFactory.getLogger(CommentAnalysisController.class);
    private final static int PAGE_SIZE = 15;

    @Autowired
    private CommentAnalysisRepository commentAnalysisRepository;
    @Autowired
    private DictionaryControl dictionaryControl;

    @GetMapping
    public String list() {
        return "forward:/commentAnalysis/1";
    }

    @GetMapping("/{page}")
    public String list(@PathVariable int page, Model model) {
        logger.info("[评论分析列表] 第{}页", page);

        ResultPage<CommentAnalysis> resultPage = new ResultPage<>();
        resultPage.setPageNo(page);
        resultPage.setPageSize(PAGE_SIZE);
        resultPage.setControllerName("commentAnalysis");

        commentAnalysisRepository.list(resultPage);

        Map<String, String> dictionary = dictionaryControl.getDictionary("analysisTarget").getItemsAsMap();
        for (CommentAnalysis commentAnalysis : resultPage.getResult())
            commentAnalysis.setAnalysisTargetName(dictionary.get(commentAnalysis.getAnalysisTarget() + ""));

        model.addAttribute("resultPage", resultPage);
        return "/commentAnalysis_list";
    }

}
