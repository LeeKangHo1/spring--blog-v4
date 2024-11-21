package com.example.blog._core.aop;

import com.example.blog._core.error.ex.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component // IoC에 올려. @Aspect 안에 포함되어 있지 않다.
@Aspect
public class MyValidationAspect {

    // 행위, PostMapping 실행 전 실행
//    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)") // 포인트컷 자리
//    public void validationCheck(JoinPoint jp) {
//        Object[] args = jp.getArgs();
//
//        for (Object arg : args) {
//            if (arg instanceof Errors) {
//                Errors errors = (Errors) arg;
//
//                if (errors.hasErrors()) {
//                    String errMsg = errors.getFieldErrors().get(0).getField()
//                            + " " + errors.getFieldErrors().get(0).getDefaultMessage();
//                    throw new Exception400(errMsg);
//                }
//            }
//        }
//    }

    // PostMapping 실행 전 후로 실행
    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)") // 포인트컷 자리
    public Object validationCheck(ProceedingJoinPoint jp) throws Throwable {
        // JoinPoint : 해당 메서드의 모든 정보가 있다.
        Object[] args = jp.getArgs(); // 매개변수 전부 다

        // @Valid BoardRequest.SaveDTO saveDTO, Errors errors
        for (Object arg : args) {
            // 매개변수 2개 중 Errors를 찾는다.
            // 포스트매핑이라도 매개변수에 Errors가 없으면 validation 체크 안한다.
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;

                // Controller에 있던 부가 로직 내용을 여기로
                if (errors.hasErrors()) {
                    String errMsg = errors.getFieldErrors().get(0).getField()
                            + " " + errors.getFieldErrors().get(0).getDefaultMessage();
                    throw new Exception400(errMsg);
                    // alert로 경고 메세지 날리고 확인 눌리면 history.back()실행
                }
            }
        }
        System.out.println("직전");
        Object ob = jp.proceed(); // Controller 메서드 실행
        System.out.println("직후");
        return ob; // Controller 메서드 반환값을 디스패쳐 서블릿에 전달
    }

}
