package com.mobilab.exchangeconversionrateservice.Base.Repository;

import com.mobilab.exchangeconversionrateservice.Base.Entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    Optional<T> findByIdAndDeleted(Long aLong, Boolean deleted);
}
