package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingSimpleDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.UserServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingControler {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper serviceMapper;
    private final UserServiceImpl userService;

    @GetMapping("")
    public List<TrainingDto> getTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(serviceMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable long id) {
        return trainingService.findAllTrainingsByUserId(id)
                .stream()
                .map(serviceMapper::toDto)
                .toList();
    }

    @GetMapping("finished/{date}")
    public List<TrainingDto> getFinishedTrainings(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return trainingService.findAllCompletedTrainings(date)
                .stream()
                .map(serviceMapper::toDto)
                .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getTrainingsByActivityType(@RequestParam String activityType) {
        ActivityType type = ActivityType.valueOf(activityType.toUpperCase());
        return trainingService.findAllTrainingsByActivityType(type)
                .stream()
                .map(serviceMapper::toDto)
                .toList();
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingSimpleDto trainingSimpleDto) {
        Optional <User> user = userService.getUser(trainingSimpleDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException(trainingSimpleDto.getUserId());
        }


        Training training = serviceMapper.toEntity(trainingSimpleDto, user.get());
        Training createdTraining = trainingService.createTraining(training);
        return serviceMapper.toDto(createdTraining);
    }


    @PutMapping("/{id}")
    public TrainingDto updateTraining(@PathVariable long id, @RequestBody TrainingSimpleDto trainingSimpleDto) {

        Training updatedTraining = trainingService.updateTraining(id, trainingSimpleDto);
        return serviceMapper.toDto(updatedTraining);

    }

    @DeleteMapping("/{id}")
    public void deleteTraining(@PathVariable long id) {
        trainingService.deleteTraining(id);
    }

}
