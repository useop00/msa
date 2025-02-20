package msa.board.article.repository;

import lombok.extern.slf4j.Slf4j;
import msa.board.article.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void findAll() throws Exception {
        //given
        List<Article> articles = articleRepository.findAll(1L, 1499978L, 30L);

        log.info("articles.size = {}", articles.size());

        for (Article article : articles) {
            log.info("article = {}", article);
        }
    }

    @Test
    void count() throws Exception {
        //given
        Long count = articleRepository.count(1L, 10000L);

        System.out.println("count = " + count);
    }

}