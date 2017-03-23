package me.yufan.gossip.rest.connector;

import me.yufan.gossip.rest.dto.ArticleDTO;
import me.yufan.gossip.rest.response.CommentResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface CommentConnector {

    @GET
    Call<CommentResponse> getCommentList(@Body ArticleDTO article);
}
