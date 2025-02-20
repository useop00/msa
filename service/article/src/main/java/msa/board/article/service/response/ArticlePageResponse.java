package msa.board.article.service.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticlePageResponse {

    private List<ArticleResponse> articles;

    private Long articleCount;

    @Builder
    private ArticlePageResponse(List<ArticleResponse> articles, Long articleCount) {
        this.articles = articles;
        this.articleCount = articleCount;
    }

    public static ArticlePageResponse of(List<ArticleResponse> articles, Long articleCount) {
        return ArticlePageResponse.builder()
                .articles(articles)
                .articleCount(articleCount)
                .build();
    }
}
