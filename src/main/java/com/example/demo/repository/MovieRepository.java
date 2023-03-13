package com.example.demo.repository;

import com.example.demo.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

//    @Query("Select m, mi, avg(coalesce(r.grade, 0)), count(distinct r) from Movie m " +
//            "left outer join MovieImage mi on mi.movie = m " +
//            "left outer join Review r on r.movie = m group by m, mi")
//    Page<Object[]> getListPage(Pageable pageable);

    @Query("Select m, max(mi), avg(coalesce(r.grade, 0)), count(distinct r) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("Select m, mi, avg(coalesce(r.grade, 0)), count(distinct(r)) " +
            " from Movie m left outer join MovieImage mi on mi.movie = m " +
            " left outer join Review r on r.movie = m" +
            " where m.mno = :mno group by m, mi")
    List<Object[]> getMovieWithAll(long mno); // 특정 영화 조회
}
