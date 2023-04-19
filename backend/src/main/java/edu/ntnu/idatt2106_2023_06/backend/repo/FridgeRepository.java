package edu.ntnu.idatt2106_2023_06.backend.repo;

import edu.ntnu.idatt2106_2023_06.backend.model.Fridge;
import edu.ntnu.idatt2106_2023_06.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**

 This repository provides CRUD operations for the Fridge entity.

 It extends JpaRepository and JpaSpecificationExecutor interfaces.

 JpaRepository provides basic CRUD operations while JpaSpecificationExecutor provides
 search functionality using specifications.
 */
@Repository
public interface FridgeRepository extends JpaRepository<Fridge, Long>, JpaSpecificationExecutor<Fridge> {

    @Modifying
    @Transactional
    @Query(value = "CREATE TRIGGER fridge_member_deleted AFTER DELETE ON fridge_members FOR EACH ROW " +
            "BEGIN " +
            "IF OLD.super_user = 1 THEN " +
            "IF NOT EXISTS(SELECT 1 FROM fridge_members WHERE fridge_id = OLD.fridge_id AND super_user = 1) THEN " +
            "DELETE FROM fridge WHERE fridge.fridge_id = OLD.fridge_id; " +
            "END IF; " +
            "END IF; " +
            "END; ", nativeQuery = true)
    void createTrigger();

    @Modifying
    @Transactional
    @Query(value = "DROP TRIGGER IF EXISTS fridge_member_deleted", nativeQuery = true)
    void dropTrigger();

}