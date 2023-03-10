package com.example.demo.controller;

import com.example.demo.dto.ReplyDTO;
import com.example.demo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService; // 자동주입을 위해 final

    //댓글 출력
    @GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno){
        log.info("bno : " + bno);
        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }

    //댓글 작성
    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
        log.info(replyDTO);
        Long rno = replyService.register(replyDTO);
        return new ResponseEntity<>(rno,HttpStatus.OK);
    }

    //댓글 삭제
    @DeleteMapping("/{rno}")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
        log.info("RNO : " + rno);
        replyService.remove(rno);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    //댓글 수정
    @PutMapping("/{rno}")
    public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO){
        log.info(replyDTO);
        replyService.modify(replyDTO);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}