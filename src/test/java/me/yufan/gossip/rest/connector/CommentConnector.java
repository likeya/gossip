package me.yufan.gossip.rest.connector;

import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.dto.CommentDTO;
import me.yufan.gossip.rest.response.CommentResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * The retrofit interface for gossip integration test
 */
public interface CommentConnector {

    @POST("/api/comments")
    Call<CommentResponse> getCommentList(@Body ArticleDTO article);

    @POST("/api/comments/add")
    Call<Void> postComment(@Body CommentDTO comment);
}
