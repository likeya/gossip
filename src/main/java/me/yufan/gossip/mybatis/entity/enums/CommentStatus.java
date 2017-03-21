package me.yufan.gossip.mybatis.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Comment status for gossip
 */
@Getter
@AllArgsConstructor
public enum CommentStatus {

    // the comment which regard as a useless
    SPAM(-1),
    // the comment which waiting for a agreement
    WAITING(0),
    // the normal comment
    APPROVED(1);

    private final Integer status;

    private static volatile Map<Integer, CommentStatus> statusMap =
            Arrays.stream(values()).collect(toMap(CommentStatus::getStatus, commentStatus -> commentStatus));

    public static CommentStatus convert(String statusCode) {
        return statusCode == null ? null : statusMap.get(Integer.valueOf(statusCode));
    }
}
