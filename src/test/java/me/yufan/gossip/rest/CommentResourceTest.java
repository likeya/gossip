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
import static me.yufan.gossip.utils.RandomEntityGenerator.randomId;
import static me.yufan.gossip.utils.RandomEntityGenerator.randomString;
import static org.hamcrest.Matchers.*;
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
        ArticleDTO articleDTO = randomArticleDTO().setId(randomId());
        when(articleService.getOrRegisterArticle(articleDTO)).thenReturn(articleDTO);
        when(commentService.getCommentsByArticle(articleDTO)).thenReturn(emptyList());

        Response response = commentResource.loadComment(articleDTO);
        Object entity = response.getEntity();

        verify(articleService, only()).getOrRegisterArticle(articleDTO);
        verify(commentService, only()).getCommentsByArticle(any(ArticleDTO.class));

        assertThat(entity.getClass(), typeCompatibleWith(BaseApiResponse.class));
        BaseApiResponse<List<CommentDTO>> res = (BaseApiResponse<List<CommentDTO>>) entity;
        assertThat(res.getResult(), hasSize(0));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void return_a_comment_list_when_the_article_and_comments_exist() {
        ArticleDTO articleDTO = randomArticleDTO().setId(randomId());
        when(articleService.getOrRegisterArticle(articleDTO)).thenReturn(articleDTO);
        when(commentService.getCommentsByArticle(any(ArticleDTO.class))).thenReturn(Lists.newArrayList(randomCommentDTO()));

        Response response = commentResource.loadComment(articleDTO);
        Object entity = response.getEntity();

        assertThat(entity.getClass(), typeCompatibleWith(BaseApiResponse.class));
        BaseApiResponse<List<CommentDTO>> res = (BaseApiResponse<List<CommentDTO>>) entity;
        assertThat(res.getResult(), hasSize(1));
    }

    @Test
    @SneakyThrows
    public void integration_test_on_register_a_new_article() {
        ArticleDTO newArticle = randomArticleDTO();
        BaseApiResponse<List<CommentDTO>> expectResult = new CommentResponse().setResult(emptyList());
        retrofit2.Response<CommentResponse> response = commentConnector.getCommentList(newArticle).execute();

        assertThat(response.isSuccessful(), is(true));
        assertThat(response.body(), is(expectResult));
    }

    @Test
    @SneakyThrows
    public void the_id_parameter_should_not_be_the_request_args() {
        ArticleDTO newArticle = randomArticleDTO().setId(randomId());
        retrofit2.Response<CommentResponse> response = commentConnector.getCommentList(newArticle).execute();
        constraintAssert(response, "Using key as the article's identity");
    }

    @Test
    @SneakyThrows
    public void the_key_or_name_should_not_be_empty() {
        ArticleDTO emptyKey = randomArticleDTO().setKey("");
        retrofit2.Response<CommentResponse> response1 = commentConnector.getCommentList(emptyKey).execute();
        constraintAssert(response1, "Need article identity");

        ArticleDTO nullKey = randomArticleDTO().setKey(null);
        retrofit2.Response<CommentResponse> response2 = commentConnector.getCommentList(nullKey).execute();
        constraintAssert(response2, "Need article identity");

        ArticleDTO emptyName = randomArticleDTO().setName("");
        retrofit2.Response<CommentResponse> response3 = commentConnector.getCommentList(emptyName).execute();
        constraintAssert(response3, "Empty article name");

        ArticleDTO nullName = randomArticleDTO().setName(null);
        retrofit2.Response<CommentResponse> response4 = commentConnector.getCommentList(nullName).execute();
        constraintAssert(response4, "Empty article name");
    }

    @Test
    @SneakyThrows
    public void the_article_url_should_a_valid_format() {
        ArticleDTO wrongUrl = randomArticleDTO().setUrl(randomString(20));
        retrofit2.Response<CommentResponse> response = commentConnector.getCommentList(wrongUrl).execute();
        constraintAssert(response, "Illegal article link");
    }

    @Test
    @SneakyThrows
    public void avoid_the_xss_in_comments_loading_api() {
        ArticleDTO xssArticle = randomArticleDTO().setKey("<p>some_text</p>");
        retrofit2.Response<CommentResponse> response = commentConnector.getCommentList(xssArticle).execute();
        constraintAssert(response, "No html tags, only support plain text");
    }

    @SneakyThrows
    private void constraintAssert(retrofit2.Response<CommentResponse> response, String errorMsg) {
        assertThat(response.isSuccessful(), is(false));
        assertThat(response.code(), is(400));

        String errorResponse = response.errorBody().string();
        assertThat(errorResponse, containsString(errorMsg));
    }
}
