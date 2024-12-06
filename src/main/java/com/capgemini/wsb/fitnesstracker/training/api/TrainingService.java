package com.capgemini.wsb.fitnesstracker.training.api;

public interface TrainingService {


    Training createTraining(Training training);

    Training updateTraining(Long id, TrainingSimpleDto trainingSimpleDto);

    void deleteTraining(Long id);

}
