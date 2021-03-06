package com.advocacy.advocacysystem.infrastructure.repository;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseModel> extends JpaRepository<T, Long> {
}
