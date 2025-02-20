package msa.board.comment.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import msa.board.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
@ToString
public class CommentResponse {

    private final Long commentId;
    private final String content;
    private final Long parentCommentId;
    private final Long articleId;
    private final Long writerId;
    private final Boolean deleted;
    private final LocalDateTime createdAt;

    @Builder
    private CommentResponse(Long commentId, String content, Long parentCommentId, Long articleId, Long writerId, Boolean deleted, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.articleId = articleId;
        this.writerId = writerId;
        this.deleted = deleted;
        this.createdAt = createdAt;
    }

    public static CommentResponse of(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .parentCommentId(comment.getParentCommentId())
                .articleId(comment.getArticleId())
                .writerId(comment.getWriterId())
                .deleted(comment.getDeleted())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
