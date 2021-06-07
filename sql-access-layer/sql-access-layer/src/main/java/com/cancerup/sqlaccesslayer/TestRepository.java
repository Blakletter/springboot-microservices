package com.cancerup.sqlaccesslayer;

import com.cancerup.sqlaccesslayer.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

}
