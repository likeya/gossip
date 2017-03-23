package me.yufan.gossip.mybatis.mapper;

import me.yufan.gossip.mybatis.entity.Comment;
import me.yufan.gossip.mybatis.mapper.base.MyBatisTestHelper;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class CommentMapperTest extends MyBatisTestHelper {

    private CommentMapper commentMapper = injector.getInstance(CommentMapper.class);

    @Test
    public void get_a_existed_comment_list_from_the_giving_article_id() {
        final List<Comment> comments = commentMapper.getCommentsByArticleId(888888L);

        assertThat(comments, hasSize(3));
    }
}
