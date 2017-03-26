package me.yufan.gossip.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import me.yufan.gossip.rest.support.Pagination;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import static org.hibernate.validator.constraints.SafeHtml.WhiteListType.SIMPLE_TEXT;

/**
 * The main comment data from the blog page
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CommentDTO extends Pagination {
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

    @Null(message = "No commenter's type required")
    private Integer type;

    // The main body of comments
    @NotNull
    @SafeHtml(whitelistType = SIMPLE_TEXT, message = "Illegal contents, only support <b>, <em>, <i>, <strong>, <u>")
    private String message;
}
