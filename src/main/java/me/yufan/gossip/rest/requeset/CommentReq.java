package me.yufan.gossip.rest.requeset;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The main comment data from the blog page
 */
@Data
public class CommentReq implements Serializable {
    private static final long serialVersionUID = -6513873674420367775L;

    // The parent comment id
    @Digits(integer = 0, fraction = Integer.MAX_VALUE, message = "Wrong reply comment, should be positive")
    private Long replyPostId;

    // The unique identity for article
    @NotNull(message = "Couldn't find the article, refresh the page")
    private String articleKey;

    // Commenter name
    @NotNull(message = "Commenter name should not be empty")
    private String name;

    // Commenter email
    @NotNull(message = "Email shouldn't be empty")
    @Email(message = "Illegal email account, wrong format")
    private String email;

    // It's website
    @URL(regexp = "^http[s]?://.*", message = "Illegal website link, wrong format")
    private String website;

    // The main body of comments
    private String message;
}
