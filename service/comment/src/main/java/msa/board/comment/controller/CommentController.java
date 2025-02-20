package msa.board.comment.controller;

import lombok.RequiredArgsConstructor;
import msa.board.comment.service.CommentService;
import msa.board.comment.service.request.CommentCreateRequest;
import msa.board.comment.service.response.CommentPageResponse;
import msa.board.comment.service.response.CommentResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/v1/comments/{commentId}")
    public CommentResponse read(@PathVariable("commentId") Long commentId) {
        return commentService.read(commentId);
    }

    @PostMapping("/v1/comments")
    public CommentResponse create(@RequestBody CommentCreateRequest request) {
        return commentService.create(request);
    }

    @DeleteMapping("/v1/comments/{commentId}")
    public void delete(@PathVariable("commentId") Long commentId) {
        commentService.delete(commentId);
    }

    @GetMapping("/v1/comments")
    public CommentPageResponse readAll(
            @RequestParam("articleId") Long articleId,
            @RequestParam("page") Long page,
            @RequestParam("pageSize") Long pageSize)
    {
        return commentService.readAll(articleId, page, pageSize);
    }
}
