package me.yufan.gossip.mybatis.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Comment status for gossip
 */
@Getter
@Slf4j
@AllArgsConstructor
public enum CommentStatus {

    // the comment which regard as a useless
    SPAM(-1),
    // the comment which waiting for a agreement
    WAITING(0),
    // the normal comment
    APPROVED(1);

    private final Integer status;

    private static volatile Map<Integer, CommentStatus> statusMap;

    public static CommentStatus convert(String statusCode) {
        if (statusMap == null) {
            log.debug("First call CommentStatus convert method, initialize statusMap");
            synchronized (CommentStatus.class) {
                statusMap = Arrays.stream(values()).collect(toMap(CommentStatus::getStatus, commentStatus -> commentStatus));
            }
        }
        return statusCode == null ? null : statusMap.get(Integer.valueOf(statusCode));
    }
}
