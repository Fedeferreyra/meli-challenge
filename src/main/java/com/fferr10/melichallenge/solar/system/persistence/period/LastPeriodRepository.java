package com.fferr10.melichallenge.solar.system.persistence.period;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LastPeriodRepository extends JpaRepository<LastPeriod, Integer> {

    Optional<LastPeriod> getByLastUpdated(Integer lastUpdated);
}
