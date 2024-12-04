package com.capgemini.wsb.fitnesstracker.training.api;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

@Getter
@Setter
public class TrainingDto {

    @Nullable
    private Long id;

    private UserDto user;

    private Date startTime;

    private Date endTime;

    private ActivityType activityType;

    private double distance;

    private double averageSpeed;

    public TrainingDto(
            final Long id,
            final UserDto user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}
