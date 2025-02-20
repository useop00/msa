package msa.board.comment.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kuke.board.common.snowflake.Snowflake;
import msa.board.comment.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class DataInitializer {

    @PersistenceContext
    EntityManager em;

    @Autowired
    TransactionTemplate transactionTemplate;

    Snowflake snowflake = new Snowflake();
    CountDownLatch latch = new CountDownLatch(EXECUTE_COUNT);

    static final int BULK_INSERT_SIZE = 2000;
    static final int EXECUTE_COUNT = 6000;

    @Test
    void initialize() throws Exception {
        //given
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.submit(() -> {
                insert();
                latch.countDown();
                System.out.println("latch.getCount() = " + latch.getCount());
            });
        }
        latch.await();
        executorService.shutdown();
    }

    private void insert() {
        transactionTemplate.executeWithoutResult(status -> {
            Comment prev = null;
            for (int i = 0; i < BULK_INSERT_SIZE; i++) {
                Comment comment = Comment.create(
                        snowflake.nextId(),
                        "content" + i,
                        i % 2 == 0 ? null : prev.getCommentId(),
                        1L,
                        1L
                );
                prev = comment;
                em.persist(comment);
            }
        });
    }

}
