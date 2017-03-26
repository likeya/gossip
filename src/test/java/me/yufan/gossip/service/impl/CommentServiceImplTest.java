package me.yufan.gossip.service.impl;

import me.yufan.gossip.converter.entity.CommentConverter;
import me.yufan.gossip.mybatis.mapper.CommentMapper;
import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.dto.CommentDTO;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static me.yufan.gossip.utils.RandomArticle.randomArticleDTO;
import static me.yufan.gossip.utils.RandomComment.randomCommentDTO;
import static me.yufan.gossip.utils.RandomEntityGenerator.randomId;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {

    private CommentMapper commentMapper = mock(CommentMapper.class);

    private CommentConverter commentConverter = new CommentConverter();

    private CommentServiceImpl commentService = new CommentServiceImpl(commentMapper, commentConverter);

    @Test
    public void get_a_empty_comment_list_if_article_is_null() {
        final List<CommentDTO> comments = commentService.getCommentsByArticle(null);
        assertThat(comments, hasSize(0));
    }

    @Test
    public void get_a_list_from_specified_article_id() throws Exception {
        final Long articleId = randomId();
        final ArticleDTO article = randomArticleDTO().setId(articleId);
        when(commentMapper.getCommentsByArticleId(articleId)).thenReturn(newArrayList(randomCommentDTO(), randomCommentDTO()));

        final List<CommentDTO> comments = commentService.getCommentsByArticle(article);
        assertThat(comments, hasSize(2));
        verify(commentMapper, only()).getCommentsByArticleId(articleId);
    }
}
