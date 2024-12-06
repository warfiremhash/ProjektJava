package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {

        return trainingRepository.findById(trainingId);
        //Throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public List<Training> findAllTrainings(){
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> findAllTrainingsByUserId(final Long id) {
        return trainingRepository.findByUserId(id);
    }

    @Override
    public List<Training> findAllCompletedTrainings(final Date data){
        return trainingRepository.findCompletedTrainings(data);
    }

    @Override
    public List<Training> findAllTrainingsByActivityType(ActivityType activityType){
        return trainingRepository.findByActivityType(activityType);
    }

    @Override
    public Training createTraining(final Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(final Long id, final TrainingSimpleDto trainingSimpleDto) {
        Optional <Training> existingTrainingOpt = trainingRepository.findById(id);

        if (existingTrainingOpt.isEmpty()) {
            throw new TrainingNotFoundException(id);
        }
        Training existingTraining = existingTrainingOpt.get();

        if (trainingSimpleDto.getStartTime() != null) {
            existingTraining.setStartTime(trainingSimpleDto.getStartTime());
        }
        if (trainingSimpleDto.getEndTime() != null) {
            existingTraining.setEndTime(trainingSimpleDto.getEndTime());
        }
        if (trainingSimpleDto.getActivityType() != null) {
            existingTraining.setActivityType(trainingSimpleDto.getActivityType());
        }
        if (trainingSimpleDto.getDistance() != 0) {
            existingTraining.setDistance(trainingSimpleDto.getDistance());
        }
        if (trainingSimpleDto.getAverageSpeed() != 0) {
            existingTraining.setAverageSpeed(trainingSimpleDto.getAverageSpeed());
        }
        return trainingRepository.save(existingTraining);
    }


    @Override
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }

}
