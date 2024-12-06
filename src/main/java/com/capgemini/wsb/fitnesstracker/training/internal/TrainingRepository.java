package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    default List<Training> findByUserId(Long id) {
        return findAll().stream()
                .filter(training -> training.getUser().getId().equals(id))
                .toList();
    }


    default List<Training> findCompletedTrainings(Date data) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(data))
                .toList();
    }

    default List<Training> findByActivityType(ActivityType activityType) {
        return findAll().stream()
                .filter(training -> training.getActivityType() == activityType)
                .toList();
    }
}
