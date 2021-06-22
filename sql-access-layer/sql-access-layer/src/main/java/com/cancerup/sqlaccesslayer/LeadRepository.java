package com.cancerup.sqlaccesslayer;

import com.cancerup.sqlaccesslayer.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    @Query("select u from Lead where u.userId = :userId")
    Optional<List<Lead>> findAllByUserId(@Param("userId") long userId);

    @Transactional
    long deleteByLeadId (long leadId);
}
