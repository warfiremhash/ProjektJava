package com.capgemini.wsb.fitnesstracker.training.api;

import lombok.Getter;
import lombok.Setter;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;

@Setter
@Getter
public class TrainingSimpleDto {
    private Long userId;

    private Date startTime;

    private Date endTime;

    private ActivityType activityType;


    private double distance;


    private double averageSpeed;

    public TrainingSimpleDto(
            final long userId,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

}
