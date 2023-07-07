package com.codelab.sequencechecker.repository;

import com.codelab.sequencechecker.model.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeqCheckerRepository extends JpaRepository<Sequence,Long> {

    Optional<Sequence> findSequenceByTag(Long tag);
    Optional<Sequence> findTagBySequence(String seq);


}
