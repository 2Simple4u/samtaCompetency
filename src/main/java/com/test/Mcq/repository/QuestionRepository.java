package com.test.Mcq.repository;

import com.test.Mcq.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questions,Long> {

}
