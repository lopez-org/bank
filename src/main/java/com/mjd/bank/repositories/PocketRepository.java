package com.mjd.bank.repositories;

import com.mjd.bank.entities.Pocket;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PocketRepository extends JpaRepository<Pocket, Long> {
  List<Pocket> findAllByAccount_Number(Long accountId);
  List<Pocket> findAllByAccount_Owner_Id(Long ownerId);
}
