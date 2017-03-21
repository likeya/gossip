package me.yufan.gossip.rest;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.yufan.gossip.rest.requeset.CommentReq;
import me.yufan.gossip.rest.response.BaseApiResponse;
import me.yufan.gossip.service.ArticleService;
import me.yufan.gossip.service.AuthorService;
import me.yufan.gossip.service.CommentService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response comment(CommentReq comment) {
        // TODO auto create article
        // TODO auto insert comment data
        // TODO auto author indexes
        return Response.ok(BaseApiResponse.ok()).build();
    }
}
