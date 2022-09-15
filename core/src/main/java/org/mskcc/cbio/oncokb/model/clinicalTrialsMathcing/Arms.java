package org.mskcc.cbio.oncokb.model.clinicalTrialsMathcing;

import java.util.List;

/**
 * Created by Yifu Yao on 2020-09-08
 */
public class Arms {
    private String armDescription;
    private List<Drug> drugs;

    public String getArmDescription() {
        return armDescription;
    }

    public void setArmDescription(String armDescription) {
        this.armDescription = armDescription;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
    
}
