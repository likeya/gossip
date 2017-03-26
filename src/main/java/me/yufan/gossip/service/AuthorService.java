package me.yufan.gossip.service;

import me.yufan.gossip.rest.dto.AuthorDTO;
import me.yufan.gossip.rest.dto.CommentDTO;

public interface AuthorService {

    AuthorDTO getOrRegisterAuthor(CommentDTO comment);
}
