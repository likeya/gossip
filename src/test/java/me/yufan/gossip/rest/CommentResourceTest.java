package me.yufan.gossip.rest;

import me.yufan.gossip.converter.ArticleConverter;
import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.response.CommentResponse;
import me.yufan.gossip.service.ArticleService;
import me.yufan.gossip.service.AuthorService;
import me.yufan.gossip.service.CommentService;
import me.yufan.gossip.utils.RandomArticle;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentResourceTest {

    private CommentService commentService = mock(CommentService.class);
    private ArticleService articleService = mock(ArticleService.class);
    private AuthorService authorService = mock(AuthorService.class);
    private ArticleConverter articleConverter = new ArticleConverter();

    private CommentResource commentResource = new CommentResource(commentService, articleService, authorService);

    @Test
    public void return_a_empty_comment_list_when_have_a_newly_created_article() throws Exception {
        final ArticleDTO articleDTO = RandomArticle.randomArticleDTO();
        Mockito.when(articleService.getArticleByUniqueKey(articleDTO.getKey())).thenReturn(null);

        final Response response = commentResource.loadComment(articleDTO);
        final Object entity = response.getEntity();

        assertThat(entity.getClass(), typeCompatibleWith(CommentResponse.class));
        assertThat(((CommentResponse) entity).getResult(), hasSize(0));
    }
}
