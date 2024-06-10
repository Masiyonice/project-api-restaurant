package com.menuchallenge.challenge08.repository;

import com.menuchallenge.challenge08.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MenuRepo extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu>
{

}
