package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    ReviewRepository reviewRepository;

//    다대일 처리 멤버
//    @Test
//    public void insertMembers(){
//        IntStream.rangeClosed(1,100).forEach(i -> {
//            Member member = Member.builder()
//                    .email("user"+i+"@naver.com")
//                    .password("1111")
//                    .nickname("user"+i)
//                    .build();
//
//            memberRepository.save(member);
//        });
//    }


//    다대다 처리 멤버
    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1, 100).forEach(i ->{

            Member member = Member.builder()
                    .email("r" + i + "@naver.com")
                    .password("1111")
                    .nickname("reviewer" + i)
                    .build();

            memberRepository.save(member);
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){
        //FK를 가지는 Review쪽을 먼저 삭제하지 않았고, 트랜잭션 관련 처리가 없기 때문에 오류 발생 따라서 삭제하는 단계를
        //FK 쪽을 먼저 삭제하도록 수정하고 메서드의 선언부에 @Transactional과 @Commit을 추가

        Long mid = 1L; // Member의 mid
        Member member = Member.builder().mid(mid).build();

        // 기존
        // memberRepository.deleteById(mid);
        // reviewRepository.deleteByMember(member);

        // 순서 주의
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}