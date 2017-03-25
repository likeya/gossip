package me.yufan.gossip.rest;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import me.yufan.gossip.rest.connector.CommentConnector;
import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.dto.CommentDTO;
import me.yufan.gossip.rest.integration.ResourceTestHelper;
import me.yufan.gossip.rest.response.BaseApiResponse;
import me.yufan.gossip.rest.response.CommentResponse;
import me.yufan.gossip.service.ArticleService;
import me.yufan.gossip.service.AuthorService;
import me.yufan.gossip.service.CommentService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.Response;
import java.util.List;

import static java.util.Collections.emptyList;
import static me.yufan.gossip.utils.RandomArticle.randomArticleDTO;
import static me.yufan.gossip.utils.RandomComment.randomCommentDTO;
import static me.yufan.gossip.utils.RandomEntityGenerator.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentResourceTest extends ResourceTestHelper {

    private CommentService commentService = mock(CommentService.class);
    private ArticleService articleService = mock(ArticleService.class);
    private AuthorService authorService = mock(AuthorService.class);

    private CommentResource commentResource = new CommentResource(commentService, articleService, authorService);

    private CommentConnector commentConnector;

    @Before
    public void setUp() {
        commentConnector = buildConnector(CommentConnector.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void return_a_empty_comment_list_when_have_a_newly_created_article() {
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

    @Test
    @SneakyThrows
    public void integration_test_on_register_a_new_article() {
        final ArticleDTO newArticle = new ArticleDTO()
            .setKey(randomString(20))
            .setName(randomString(10))
            .setUrl(randomUrl());
        BaseApiResponse<List<CommentDTO>> expectResult = new CommentResponse().setResult(emptyList());
        final retrofit2.Response<CommentResponse> response = commentConnector.getCommentList(newArticle).execute();

        assertThat(response.isSuccessful(), is(true));
        assertThat(response.body(), is(expectResult));
    }
}
