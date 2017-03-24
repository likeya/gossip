package me.yufan.gossip.rest;

import com.google.common.collect.Lists;
import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.dto.CommentDTO;
import me.yufan.gossip.rest.response.BaseApiResponse;
import me.yufan.gossip.service.ArticleService;
import me.yufan.gossip.service.AuthorService;
import me.yufan.gossip.service.CommentService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;
import java.util.List;

import static java.util.Collections.emptyList;
import static me.yufan.gossip.utils.RandomArticle.randomArticleDTO;
import static me.yufan.gossip.utils.RandomComment.randomCommentDTO;
import static me.yufan.gossip.utils.RandomEntityGenerator.randomId;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentResourceTest {

    private CommentService commentService = mock(CommentService.class);
    private ArticleService articleService = mock(ArticleService.class);
    private AuthorService authorService = mock(AuthorService.class);

    private CommentResource commentResource = new CommentResource(commentService, articleService, authorService);

    @Test
    @SuppressWarnings("unchecked")
    public void return_a_empty_comment_list_when_have_a_newly_created_article() throws Exception {
        final ArticleDTO articleDTO = randomArticleDTO().setId(randomId());
        when(articleService.getOrRegisterArticle(articleDTO)).thenReturn(articleDTO);
        when(commentService.getCommentsByArticle(articleDTO)).thenReturn(emptyList());

        final Response response = commentResource.loadComment(articleDTO);
        final Object entity = response.getEntity();

        verify(articleService, only()).getOrRegisterArticle(articleDTO);
        verify(commentService, only()).getCommentsByArticle(any(ArticleDTO.class));

        assertThat(entity.getClass(), typeCompatibleWith(BaseApiResponse.class));
        final BaseApiResponse<List<CommentDTO>> res = (BaseApiResponse<List<CommentDTO>>) entity;
        assertThat(res.getResult(), hasSize(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void return_a_comment_list_when_the_article_and_comments_exist() {
        final ArticleDTO articleDTO = randomArticleDTO().setId(randomId());
        when(articleService.getOrRegisterArticle(articleDTO)).thenReturn(articleDTO);
        when(commentService.getCommentsByArticle(any(ArticleDTO.class))).thenReturn(Lists.newArrayList(randomCommentDTO()));

        final Response response = commentResource.loadComment(articleDTO);
        final Object entity = response.getEntity();

        assertThat(entity.getClass(), typeCompatibleWith(BaseApiResponse.class));
        final BaseApiResponse<List<CommentDTO>> res = (BaseApiResponse<List<CommentDTO>>) entity;
        assertThat(res.getResult(), hasSize(1));
    }
}
