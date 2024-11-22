package com.example.blog.board;

import com.example.blog._core.util.Resp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    // 메인화면이자 전체 글 조회
    // 객체를 반환하면 JSON으로 바꿔서 보내준다.
    @GetMapping("/api")
    public Resp<?> list() {
        // Resp<?> extends Object -> 와일드 카드
        List<BoardResponse.ReadDTO> boardList = boardService.게시글목록보기();

        return Resp.ok(boardList);
        // return Resp.ok(boardService.게시글목록보기()); 1줄도 가능
    }

    // 글 상세보기 만들기
    @GetMapping("/api/board/{id}")
    public Resp<?> detail(@PathVariable("id") Integer id) {
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        return Resp.ok(boardDetail);
    }

    // 글 작성 폼으로 이동 -> 클라이언트에서 처리 가능 -> 필요없는 메서드
//    @GetMapping("/board/save-form")
//    public String saveForm() {
//        return "save-form";
//    }

    // 글 작성
    @PostMapping("/api/board") // api인데 insert한다 (post니까). save 없어도 된다.
    public Resp<?> save(@Valid @RequestBody BoardRequest.SaveDTO saveDTO, Errors errors) {
        boardService.게시글쓰기(saveDTO);
        return Resp.ok(null); // 돌려줄 내용은 없다.
    }

    // 업데이트 폼으로 이동 -> 그림은 프론트에서 이동가능
//    @GetMapping("/board/{id}/update-form")
//    public String updateForm(@PathVariable("id") Integer id, Model model) {
//        BoardResponse.UpdateFormDTO updateFormDTO = boardService.게시글수정화면보기(id);
//        model.addAttribute("model", updateFormDTO);
//        return "update-form";
//    }

    // 글 업데이트
    @PutMapping("/api/board/{id}")
    public Resp<?> updateBoard(@PathVariable("id") Integer id, @Valid @RequestBody BoardRequest.UpdateDTO updateDTO, Errors errors) {
        // 주소에 걸린 id는 무조건 where절에 들어가는 변수다. DTO에 id가 있으면 안된다.
        boardService.게시글수정하기(id, updateDTO);
        return Resp.ok(null);
    }

    // 글 삭제
    @DeleteMapping("/api/board/{id}") // 주소에 동사(delete)는 적지 않는다.
    public Resp<?> delete(@PathVariable Integer id) {
        // body 받는 method는 post, put 2개밖에 없다.
        boardService.게시글삭제(id);
        return Resp.ok(null);
    }
}
