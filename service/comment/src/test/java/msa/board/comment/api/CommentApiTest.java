package msa.board.comment.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import msa.board.comment.service.request.CommentCreateRequest;
import msa.board.comment.service.response.CommentPageResponse;
import msa.board.comment.service.response.CommentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

class CommentApiTest {
    RestClient restClient = RestClient.create("http://localhost:9001");

    @Test
    void create() {
        CommentResponse response1 = createComment(new CommentCreateRequest(1L, "content1", null, 1L));
        CommentResponse response2 = createComment(new CommentCreateRequest(1L, "content2", response1.getCommentId(), 1L));
        CommentResponse response3 = createComment(new CommentCreateRequest(1L, "content3", response1.getCommentId(), 1L));

        System.out.printf("commentId=%s%n", response1.getCommentId());
        System.out.printf("\tcommentId=%s%n", response2.getCommentId());
        System.out.printf("\tcommentId=%s%n", response3.getCommentId());

        /**
         commentId=150437428358991872
         commentId=150437428770033664
         commentId=150437428900057088
         */
    }

    @Test
    void read() throws Exception {
        CommentResponse response = restClient.get()
                .uri("/v1/comments/{commentId}", 150430328117460992L)
                .retrieve()
                .body(CommentResponse.class);

        System.out.println("comment = " + response);
    }

    @Test
    void delete() throws Exception {
        restClient.delete()
                .uri("/v1/comments/{commentId}", 150437428900057088L)
                .retrieve()
                .body(Void.class);
    }

    @Test
    void readAll() throws Exception {
        //given
        CommentPageResponse response = restClient.get()
                .uri("/v1/comments?articleId=1&page=1000&pageSize=10")
                .retrieve()
                .body(CommentPageResponse.class);

        System.out.println(response.getCommentCount());
        for (CommentResponse comment : response.getComments()) {
            if (!comment.getCommentId().equals(comment.getParentCommentId())) {
                System.out.println("\t");
            }
            System.out.println(comment.getCommentId());
        }
    }

    CommentResponse createComment(CommentCreateRequest request) {
        return restClient.post()
                .uri("/v1/comments")
                .body(request)
                .retrieve()
                .body(CommentResponse.class);
    }

    @Getter
    @AllArgsConstructor
    public static class CommentCreateRequest {
        private Long articleId;
        private String content;
        private Long parentCommentId;
        private Long writerId;
    }
}
