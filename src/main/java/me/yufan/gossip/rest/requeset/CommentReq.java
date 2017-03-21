package me.yufan.gossip.rest.requeset;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * The main comment data from the blog page
 */
@Data
public class CommentReq implements Serializable {
    private static final long serialVersionUID = -6513873674420367775L;

    private Long replyPostId;

    private String articleKey;

    private String commenterName;

    private String email;

    private String website;

    private String message;

    private Date createTime;
}
