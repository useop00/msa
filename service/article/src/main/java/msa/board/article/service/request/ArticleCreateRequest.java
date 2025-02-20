package msa.board.article.service.request;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ArticleCreateRequest {
    private String title;
    private String content;
    private Long writerId;
    private Long boardId;


}
