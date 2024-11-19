package com.example.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@RequiredArgsConstructor // 필드와 @NonNull 애노테이션이 붙은 필드에 대해 생성자를 자동으로 생성해줍니다
@Controller // IOC에 집어 넣는다.
public class BoardController {
    // 책임: 외부 클라이언트에게 요청을 받으면 응답을 해주는 객체
    // 유일하게 클라이언트에게 노출된 클래스 -> API / service, repository는 노출 x

    private final BoardService boardService;

    // 글 업데이트
    @PostMapping("/board/{id}/update-form")
    public String updateBoard(@PathVariable("id") int id, BoardRequest.UpdateDTO updateDTO) {
        boardService.게시글수정하기(id, updateDTO);
        return "redirect:/board/" + id; // 상세 글 보기로 이동
    }

    // 업데이트 폼으로 이동
    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, Model model) {
        BoardResponse.UpdateFormDTO updateFormDTO = boardService.게시글수정화면보기(id);
        model.addAttribute("model", updateFormDTO);
        return "update-form";
    }

    // 글 삭제
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id) {
        boardService.게시글삭제(id);
        return "redirect:/";
    }

    // 글 작성 폼으로 이동
    @GetMapping("/board/save-form")
    public String saveForm() {
        return "save-form";
    }

    // 글 작성
    @PostMapping("/board/save-form")
    public void save(BoardRequest.SaveDTO saveDTO, HttpServletResponse response) {
        // Board로 받으면 id랑 createdAt는 null이 된다. 필요한 값만 받자
        // x-www...는 클래스로 받을 수 있다. @RequestParam 안 써도 된다.

        boardService.게시글쓰기(saveDTO);

        response.setStatus(302); // header 302
        response.setHeader("Location", "/");

//        return "redirect:/";
    }

    // 메인화면이자 전체 글 조회
    @GetMapping("/") // invoke로 실행하니까 메서드 이름이 중요하지 않다.
    public String list(Model model) { // DS 디스패쳐 서블릿 (request객체를 model이라는 객체로 랩핑해서 전달해준다)
        List<BoardResponse.ReadDTO> boardList = boardService.게시글목록보기();
        // key 이름은 일부러 model로 (여러 개 담을거면 models)
        model.addAttribute("models", boardList);

        return "list";
    }
    /**
     * 쿼리스트링(where절) : /board?title=바다
     * 패스변수(where절) : /board/1
     */
    // 글 상세보기 만들기
    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        model.addAttribute("model", boardDetail);
        return "detail";
    }
}
