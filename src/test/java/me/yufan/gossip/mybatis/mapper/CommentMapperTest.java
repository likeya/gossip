package me.yufan.gossip.mybatis.mapper;

import lombok.extern.slf4j.Slf4j;
import me.yufan.gossip.mybatis.entity.Comment;
import me.yufan.gossip.mybatis.mapper.base.MyBatisTestHelper;
import me.yufan.gossip.rest.dto.CommentDTO;
import org.junit.Test;

import java.util.List;

import static me.yufan.gossip.utils.RandomComment.randomRawComment;
import static me.yufan.gossip.utils.RandomEntityGenerator.randomString;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@Slf4j
public class CommentMapperTest extends MyBatisTestHelper {

    private CommentMapper commentMapper = injector.getInstance(CommentMapper.class);

    @Test
    public void get_a_existed_comment_list_from_the_giving_article_id() {
        final List<CommentDTO> comments = commentMapper.getCommentsByArticleId(888888L);

        assertThat(comments, hasSize(3));
    }

    @Test
    public void select_a_existed_comment() {
        final Comment comment = commentMapper.queryOne(111111L);
        assertThat(comment, notNullValue());
        assertThat(comment.getArticleId(), is(888888L));
    }

    @Test
    public void insert_a_random_comment_but_the_author_and_article_not_existed() {
        try {
            final Comment rawComment = randomRawComment();
            commentMapper.insert(rawComment);
            fail("Should be some foreign key error");
        } catch (Exception e) {// NOSONAR
            log.error(e.getMessage());
            assertThat(e.getMessage(), containsString("Error updating database"));
        }
    }

    @Test
    public void insert_a_comment_to_existed_article_and_author() {
        Comment comment = new Comment()
            .setArticleId(888888L)
            .setAuthorId(888888L)
            .setMessage(randomString(400));
        commentMapper.insert(comment);
        assertThat(comment.getId(), notNullValue());
    }
}
