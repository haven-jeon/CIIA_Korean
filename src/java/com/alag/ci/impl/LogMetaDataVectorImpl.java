package com.alag.ci.impl;

import java.util.List;

import com.alag.ci.TagMagnitude;

public class LogMetaDataVectorImpl extends MetaDataVectorImpl {
    /**
     * Constructor
     * 
     * @param tagMagnitudeList
     */
   public LogMetaDataVectorImpl(List<TagMagnitude> tagMagnitudeList) {
        super(tagMagnitudeList);
    }

    /**
     * Normalize the vector
     * 
     * @param tmList
     * @return the normalized list
     */
    protected List<TagMagnitude> normalize(List<TagMagnitude> tmList) {
        for (TagMagnitude tm : tmList) {
            double v = tm.getMagnitude();
            double nv = 0.;
            if (v > 0.) {
                nv = 1. + Math.log10(1. + Math.log10(v));
            }
            tm.setMagnitude(nv);
        }
        return super.normalize(tmList);
    }
}
