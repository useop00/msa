package msa.board.article.service.response;

import lombok.Builder;
import lombok.Getter;
import msa.board.article.entity.Article;

import java.time.LocalDateTime;

@Getter
public class ArticleResponse {
    private final Long articleId;
    private final String title;
    private final String content;
    private final Long boardId; // shard Key
    private final Long writerId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Builder
    private ArticleResponse(Long articleId, String title, String content, Long boardId, Long writerId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.writerId = writerId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ArticleResponse of(Article article) {
        return ArticleResponse.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .content(article.getContent())
                .boardId(article.getBoardId())
                .writerId(article.getWriterId())
                .createdAt(article.getCreatedAt())
                .modifiedAt(article.getModifiedAt())
                .build();
    }
}
