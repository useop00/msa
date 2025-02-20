package msa.board.article.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import msa.board.article.service.request.ArticleCreateRequest;
import msa.board.article.service.request.ArticleUpdateRequest;
import msa.board.article.service.response.ArticlePageResponse;
import msa.board.article.service.response.ArticleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleApiTest {
    RestClient restClient = RestClient.create("http://localhost:9000");

    @Test
    void createTest() throws Exception {
        ArticleResponse response = create(new ArticleCreateRequest(
                "title",
                "content",
                1L,
                1L
        ));
        //given

        //when & //then
        System.out.println("response = " + response.getArticleId());
    }

    @Test
    void readTest() throws Exception {
        //given
        ArticleResponse response = read(142007617632993280L);

        System.out.println("response = " + response);
    }

    @Test
    void update() throws Exception {
        update(142007617632993280L);
        ArticleResponse response = read(142007617632993280L);

        System.out.println("response = " + response.getTitle());
        System.out.println("response = " + response.getContent());
    }

    @Test
    void delete() throws Exception {
        restClient.delete()
                .uri("/v1/articles/142007617632993280")
                .retrieve()
                .body(Void.class);
    }

    @Test
    void readAll() throws Exception {
        //given
        ArticlePageResponse response = restClient.get()
                .uri("/v1/articles?boardId=1&page=50000&pageSize=30")
                .retrieve()
                .body(ArticlePageResponse.class);

        System.out.println("response.getArticleCount() = " + response.getArticleCount());

        for (ArticleResponse article : response.getArticles()) {
            System.out.println("article.getArticleId() = " + article.getArticleId());
        }
    }

    void update(Long articleId) {
        restClient.put()
                .uri("/v1/articles/" + articleId)
                .body(new ArticleUpdateRequest("title2", "content2"))
                .retrieve()
                .body(ArticleResponse.class);
    }

    ArticleResponse read(Long articleId) {
        return restClient.get()
                .uri("/v1/articles/" + articleId)
                .retrieve()
                .body(ArticleResponse.class);
    }

    ArticleResponse create(ArticleCreateRequest request) {
        return restClient.post()
                .uri("/v1/articles")
                .body(request)
                .retrieve()
                .body(ArticleResponse.class);

    }

    @Getter
    @AllArgsConstructor
    static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long writerId;
        private Long boardId;
    }

    @Getter
    @AllArgsConstructor
    static class ArticleUpdateRequest {
        private String title;
        private String content;
    }


}
