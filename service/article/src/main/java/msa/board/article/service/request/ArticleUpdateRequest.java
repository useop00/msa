package msa.board.article.service.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ArticleUpdateRequest {
    private String title;
    private String content;
}
