/**
 * 
 */
package com.alag.ci.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alag.ci.RatingMagnitude;
import com.alag.ci.RatingVector;

/**
 * @author salag1
 *
 */
public class RatingVectorImpl implements RatingVector {
    private List<RatingMagnitude> magnitudes = null;
    private Map<Long, RatingMagnitude> magnitudeMap = null;
    private OwnerType ownerType = null;
    
    public RatingVectorImpl(OwnerType ownerType, List<RatingMagnitude> magnitudes) {
        this.magnitudes = magnitudes;
        this.ownerType = ownerType;
        this.magnitudeMap = new HashMap<Long, RatingMagnitude>();
        
        for (RatingMagnitude rm : this.magnitudes) {
            this.magnitudeMap.put(rm.getOwnerId(), rm);
        }
    }
    
    public Map<Long, RatingMagnitude> getRatingMagnitudeMap() {
        return this.magnitudeMap;
    }
    
    /* (non-Javadoc)
     * @see com.alag.ci.RatingVector#getAdjustedCosineSimilarity(com.alag.ci.RatingVector)
     */
    public double getItemAdjustedCosineSimilarity(RatingVector other) {
        double sim = 0.;
        if (other != null) {
            Map<Long, RatingMagnitude> otherMap = other.getRatingMagnitudeMap();
            //We need to consider only terms with the same id
            Map<Long, TwoRatingMagnitudes> mergedMap = new HashMap<Long, TwoRatingMagnitudes>();
            for (Long id: otherMap.keySet()) {
                RatingMagnitude ratingMagnitudeSelf = this.magnitudeMap.get(id);
                if (ratingMagnitudeSelf != null) {
                    RatingMagnitude otherRatingMagnitude = otherMap.get(id);
                    mergedMap.put(id, new TwoRatingMagnitudes(ratingMagnitudeSelf,otherRatingMagnitude));
                }
            }
            double sumDot = 0.;
            double sumSelfSqd = 0.;
            double sumOtherSqd = 0.;
            for (TwoRatingMagnitudes trm: mergedMap.values()) {
                double selfMagn = trm.getSelf().getMagnitude();
                double otherMagn = trm.getOther().getMagnitude();
                RatingMagnitude selfRatingMagnitude = trm.getSelf();
                RatingMagnitude otherRatingMagnitude = trm.getOther();               
                sumDot += (selfMagn - selfRatingMagnitude.getOwnerRatingAverage())*
                        (otherMagn - otherRatingMagnitude.getOwnerRatingAverage());
                sumSelfSqd += (selfMagn - selfRatingMagnitude.getOwnerRatingAverage())*(selfMagn - selfRatingMagnitude.getOwnerRatingAverage());
                sumOtherSqd += (otherMagn - otherRatingMagnitude.getOwnerRatingAverage())*
                    (otherMagn - otherRatingMagnitude.getOwnerRatingAverage());
            }
            //Check to make sure denominator is not zero
            if (sumSelfSqd*sumOtherSqd > 0.) {
                sim = sumDot/Math.sqrt(sumSelfSqd*sumOtherSqd);
            }
        }
        return sim;
    }

    /* (non-Javadoc)
     * @see com.alag.ci.RatingVector#getCorrelation(com.alag.ci.RatingVector)
     */
    public double getCorrelation(RatingVector other) {
        double corr = 0.;
        if (other != null) {
            Map<Long, RatingMagnitude> otherMap = other.getRatingMagnitudeMap();
            //We need to consider only terms with the same id
            double selfAverage = 0.;
            double otherAverage = 0.;
            Map<Long, TwoRatingMagnitudes> mergedMap = new HashMap<Long, TwoRatingMagnitudes>();
            for (Long id: otherMap.keySet()) {
                RatingMagnitude ratingMagnitudeSelf = this.magnitudeMap.get(id);
                if (ratingMagnitudeSelf != null) {
                    RatingMagnitude otherRatingMagnitude = otherMap.get(id);
                    mergedMap.put(id, new TwoRatingMagnitudes(ratingMagnitudeSelf,otherRatingMagnitude));
                    selfAverage += ratingMagnitudeSelf.getMagnitude();
                    otherAverage += otherRatingMagnitude.getMagnitude();
                }
            }
            selfAverage = selfAverage/(double) mergedMap.size();
            otherAverage =  otherAverage/(double) mergedMap.size();
           
            double sumDot = 0.;
            double sumSelfSqd = 0.;
            double sumOtherSqd = 0.;
            for (TwoRatingMagnitudes trm: mergedMap.values()) {
                double selfMagn = trm.getSelf().getMagnitude();
                double otherMagn = trm.getOther().getMagnitude();
                sumDot += (selfMagn - selfAverage)*(otherMagn - otherAverage);
                sumSelfSqd += (selfMagn - selfAverage)*(selfMagn - selfAverage);
                sumOtherSqd += (otherMagn - otherAverage)*(otherMagn - otherAverage);
            }
            //Check to make sure denominator is not zero
            if (sumSelfSqd*sumOtherSqd > 0.) {
                corr = sumDot/Math.sqrt(sumSelfSqd*sumOtherSqd);
            }
        }
        return corr;
    }

    /* 
     * @see com.alag.ci.RatingVector#getOwnerType()
     */
    public OwnerType getOwnerType() {
        return this.ownerType;
    }

    /* (non-Javadoc)
     * @see com.alag.ci.RatingVector#getRatingMagnitude()
     */
    public List<RatingMagnitude> getRatingMagnitude() {
        return this.magnitudes;
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(RatingMagnitude rm: this.magnitudes) {
            sb.append("[" + rm + "] ");
        }
        return sb.toString();
    }
    
    private class TwoRatingMagnitudes {
        private RatingMagnitude self = null;
        private RatingMagnitude other = null;
        
        TwoRatingMagnitudes(RatingMagnitude self, RatingMagnitude other) {
            this.self = self;
            this.other = other;
        }

        RatingMagnitude getOther() {
            return other;
        }

        RatingMagnitude getSelf() {
            return self;
        }

    }
}
