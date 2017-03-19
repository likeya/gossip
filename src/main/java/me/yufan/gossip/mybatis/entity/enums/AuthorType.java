package me.yufan.gossip.mybatis.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Getter
@AllArgsConstructor
public enum AuthorType {

    // Gossip administrator, have the all function for admin gossip
    ADMINISTRATOR(1000),
    // Blog writer, could only operate the comments except registed normal user's privilege
    WRITER(2),
    // Modify his or her own comments, change profile, change avatar
    NORMAL_USER(1),
    // Auto created after first comment
    GUEST(0);

    private final Integer code;

    private static volatile Map<Integer, AuthorType> typeMap;

    public static AuthorType convert(@NonNull Integer code) {
        if (typeMap == null) {
            synchronized (AuthorType.class) {
                typeMap = Arrays.stream(values()).collect(toMap(AuthorType::getCode, authorType -> authorType));
            }
        }
        return typeMap.get(code);
    }
}
