package com.alag.ci.impl;

import com.alag.ci.RatingMagnitude;

public class RatingMagnitudeImpl implements RatingMagnitude {
    private Double magnitude = null;
    private Long ownerId = null;
    private Double ownerRatingAverage = null;
    
    public RatingMagnitudeImpl(long ownerId, double magnitude, 
           double ownerRatingAverage) {
        this.magnitude = magnitude;
        this.ownerId = ownerId;
        this.ownerRatingAverage = ownerRatingAverage;
    }
    
    public RatingMagnitudeImpl(long ownerId, double magnitude) {
        this.magnitude = magnitude;
        this.ownerId = ownerId;
     }

    public double getMagnitude() {
        return this.magnitude;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public double getOwnerRatingAverage() {
        return this.ownerRatingAverage;
    }
    
    public String toString() {
        return " " + this.ownerId + "," + 
            this.magnitude + ", " + this.ownerRatingAverage;
    }

}
