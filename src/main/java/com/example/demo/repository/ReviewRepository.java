package com.example.demo.repository;

import com.example.demo.entity.Member;
import com.example.demo.entity.Movie;
import com.example.demo.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //1. @Query를 이용해서 조인 처리하여 해결
    //2. @EntityGraph를 이용해서 Review 객체를 가져올 때 Member 객체를 로딩하는 방법으로 해결
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("delete from Review mr where mr.member = :member")
    void deleteByMember(Member member);
}