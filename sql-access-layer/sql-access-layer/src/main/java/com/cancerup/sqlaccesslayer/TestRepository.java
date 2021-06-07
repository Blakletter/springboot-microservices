package com.cancerup.sqlaccesslayer;

import com.cancerup.sqlaccesslayer.controller.Test;
import com.cancerup.sqlaccesslayer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Long> {

}
