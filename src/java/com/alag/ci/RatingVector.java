package com.alag.ci;

import java.util.List;
import java.util.Map;

public interface RatingVector {
    public enum OwnerType {ITEM, USER};
    public List<RatingMagnitude> getRatingMagnitude() ;
    public Map<Long, RatingMagnitude> getRatingMagnitudeMap();
    public double getCorrelation(RatingVector other);
    public double getItemAdjustedCosineSimilarity(RatingVector other);
    public OwnerType getOwnerType();
}
