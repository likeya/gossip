package me.yufan.gossip.rest;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.dto.CommentDTO;
import me.yufan.gossip.rest.response.BaseApiResponse;
import me.yufan.gossip.rest.response.CommentResponse;
import me.yufan.gossip.service.ArticleService;
import me.yufan.gossip.service.AuthorService;
import me.yufan.gossip.service.CommentService;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * The main comment api for gossip
 */
@Path("/api")
@Singleton
// TODO support UA identity
// TODO add rate limit
// TODO add spam check
public class CommentResource implements BaseResource {

    private final CommentService commentService;

    private final ArticleService articleService;

    private final AuthorService authorService;

    @Inject
    public CommentResource(CommentService commentService, ArticleService articleService, AuthorService authorService) {
        this.commentService = commentService;
        this.articleService = articleService;
        this.authorService = authorService;
    }

    @GET
    @Path("/comments")
    @Produces(APPLICATION_JSON)
    public Response loadComment(@Valid final ArticleDTO articleDTO) {
        ArticleDTO article = articleService.getOrRegisterArticle(articleDTO);
        List<CommentDTO> comments = commentService.getCommentsByArticleId(article);

        return Response.ok(new CommentResponse().body(comments, articleDTO)).build();
    }

    @POST
    @Produces(APPLICATION_JSON)
    public Response comment(@Valid final CommentDTO comment) {
        // TODO Check the existed article
        articleService.getOrRegisterArticle(null);


        // TODO auto create article
        // TODO auto insert comment data
        // TODO auto author indexes
        return Response.ok(BaseApiResponse.ok()).build();
    }
}
