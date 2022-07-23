package com.itudy.api.domain.community.controller;

import com.itudy.api.domain.common.dto.PageDTO;
import com.itudy.api.domain.common.dto.ResponseWrapper;
import com.itudy.api.domain.community.dto.*;
import com.itudy.api.domain.community.entity.CommunityBoardVO;
import com.itudy.api.domain.community.entity.CommunityCommentVO;
import com.itudy.api.domain.community.entity.CommunityPostVO;
import com.itudy.api.domain.community.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class CommunityController {

    private final CommunityBoardFindService communityBoardFindService;
    private final CommunityPostFindService communityPostFindService;
    private final CommunityCommentFindService communityCommentFindService;
    private final CommunityPostUpdateService communityPostUpdateService;
    private final CommunityCommentUpdateService communityCommentUpdateService;


    @GetMapping(path = "/community/boards")
    public ResponseEntity<ResponseWrapper<List<CommunityBoardDTO>>> getBoardList(

    ) {
        List<CommunityBoardVO> boardList = communityBoardFindService.findAll();
        List<CommunityBoardDTO> dtos = boardList.stream().map(
                CommunityBoardDTO::fromEntity
        ).toList();

        ResponseWrapper<List<CommunityBoardDTO>> data = new ResponseWrapper<>(dtos,
                "community board list",
                HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(path = "/community/boards/{board-idx}/posts")
    public ResponseEntity<ResponseWrapper<PageDTO<CommunityPostDTO>>> getPostByBoard(
            @PathVariable(value = "board-idx") Long boardIdx,
            @PageableDefault(size = 10, direction = Sort.Direction.ASC) Pageable pageable
    ) {

        CommunityBoardVO board = communityBoardFindService.findByIdx(boardIdx);
        Page<CommunityPostVO> page = communityPostFindService.findByBoard(board, pageable);
        List<CommunityPostDTO> dtos = page.stream().map(CommunityPostDTO::fromEntity).toList();

        PageDTO<CommunityPostDTO> pageDTO = PageDTO.<CommunityPostDTO>builder()
                .contents(dtos)
                .currentPage(pageable.getPageNumber())
                .totalPage(page.getTotalPages() - 1)
                .build();

        ResponseWrapper<PageDTO<CommunityPostDTO>> data = new ResponseWrapper<>(pageDTO,
                "community post list",
                HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(path = "/community/boards/posts/{post-idx}")
    public ResponseEntity<ResponseWrapper<CommunityPostDTO>> getPostByIdx(
            @PathVariable(value = "post-idx") Long postIdx
    ) {
        CommunityPostVO post = communityPostFindService.findByIdx(postIdx);
        CommunityPostDTO dto = CommunityPostDTO.fromEntity(post);

        ResponseWrapper<CommunityPostDTO> data = new ResponseWrapper<>(dto,
                "post information",
                HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(path = "/community/boards/{board-idx}/posts")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<String>> savePost(
            @PathVariable(value = "board-idx") Long boardIdx,
            @RequestBody @Validated CreatePostRequest request
    ){
        Long save = communityPostUpdateService.save(request.getTitle(), request.getContent(), boardIdx);

        ResponseWrapper<String> data = new ResponseWrapper<>("포스트 등록에 성공하였습니다.",
                "post save success", HttpStatus.CREATED.value());

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PostMapping(path = "/community/boards/posts/{post-idx}/comments")
    public ResponseEntity<ResponseWrapper<String>> saveComment(
            @PathVariable(value = "post-idx") Long postIdx,
            @RequestBody @Validated CreateCommentRequest request
    ){
        Long save = communityCommentUpdateService.save(request.getContent(), postIdx, request.getUpperIdx());

        ResponseWrapper<String> data = new ResponseWrapper<>("댓글 등록에 성공하였습니다.",
                "comment save success", HttpStatus.CREATED.value());

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping(path = "/community/boards/posts/{post-idx}/comments")
    public ResponseEntity<ResponseWrapper<PageDTO<CommunityCommentDTO>>> getCommentsByPost(
            @PathVariable(value = "post-idx") Long postIdx,
            @PageableDefault(size = 10, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        CommunityPostVO post = communityPostFindService.findByIdx(postIdx);
        Page<CommunityCommentVO> page = communityCommentFindService.findByPost(post, pageable);
        List<CommunityCommentDTO> dtos = page.stream().map(CommunityCommentDTO::fromEntity).toList();

        PageDTO<CommunityCommentDTO> pageDTO = PageDTO.<CommunityCommentDTO>builder()
                .contents(dtos)
                .currentPage(pageable.getPageNumber())
                .totalPage(page.getTotalPages() - 1)
                .build();

        ResponseWrapper<PageDTO<CommunityCommentDTO>> data = new ResponseWrapper<>(pageDTO,
                "community post comment list",
                HttpStatus.OK.value());

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}




















