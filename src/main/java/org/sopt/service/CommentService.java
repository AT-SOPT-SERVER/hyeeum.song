package org.sopt.service;

import org.sopt.domain.Comment;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.exception.*;
import org.sopt.repository.CommentRepository;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.sopt.validator.ContentValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.sopt.constant.LimitConstant.COMMENT_CONTENT_LENGTH_LIMIT;

@Service
public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public void createComment(final Long userId, final Long postId, final String content) {
        findUserById(userId);
        validateComment(content);

        User user = findUserById(userId);
        Post post = findPostById(postId);
        Comment comment = new Comment(content, user, post);

        commentRepository.save(comment);
    }

    public void deleteComment(final Long userId, final Long postId, final Long commentId) {
        findUserById(userId);
        findPostById(postId);

        Comment comment = findCommentById(commentId);

        boolean isCommentOwner = Objects.equals(comment.getUser().getId(), userId);
        if (!isCommentOwner) throw new NotUserErrorException();

        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void updateComment(final Long userId, final Long postId, final Long commentId, final String content) {
        findUserById(userId);
        findPostById(postId);

        Comment comment = findCommentById(commentId);

        boolean isCommentOwner = Objects.equals(comment.getUser().getId(), userId);
        if (!isCommentOwner) throw new NotUserErrorException();

        comment.updateContent(content);
    }

    @Transactional
    public void likeComment(final Long userId, final Long postId, final Long commentId) {
        findUserById(userId);
        findPostById(postId);

        Comment comment = findCommentById(commentId);

        if (comment.getIsLiked()) throw new CommentLikeDuplicatedException();

        comment.like();
    }

    @Transactional
    public void unlikeComment(final Long userId, final Long postId, final Long commentId) {
        findUserById(userId);
        findPostById(postId);

        Comment comment = findCommentById(commentId);

        boolean isCommentOwner = Objects.equals(comment.getUser().getId(), userId);
        if (!isCommentOwner) throw new NotUserErrorException();

        comment.unlike();
    }

    public User findUserById(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public Post findPostById(final long id) {
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
    }

    public Comment findCommentById(final long id) {
        return commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
    }

    public void validateComment(final String content) {
        if (ContentValidator.isContentBlank(content)) throw new ContentBlankException();
        if (ContentValidator.isContentExceedsLength(content, COMMENT_CONTENT_LENGTH_LIMIT))
            throw new ContentLengthException(COMMENT_CONTENT_LENGTH_LIMIT);
    }
}
