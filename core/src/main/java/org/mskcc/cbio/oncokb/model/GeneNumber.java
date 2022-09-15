package org.mskcc.cbio.oncokb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-05-08T23:17:19.384Z")
public class GeneNumber {

    private Gene gene = null;
    private Integer alteration = null;
    private Integer tumorType = null;
    private String highestSensitiveLevel = null;
    private String highestResistanceLevel = null;
    private String highestDiagnosticImplicationLevel = null;
    private String highestPrognosticImplicationLevel = null;

    /**
     **/
    @ApiModelProperty(value = "")
    @JsonProperty("gene")
    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    /**
     **/
    @ApiModelProperty(value = "")
    @JsonProperty("alteration")
    public Integer getAlteration() {
        return alteration;
    }

    public void setAlteration(Integer alteration) {
        this.alteration = alteration;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    @JsonProperty("tumorType")
    public Integer getTumorType() {
        return tumorType;
    }

    public void setTumorType(Integer tumorType) {
        this.tumorType = tumorType;
    }


    public String getHighestSensitiveLevel() {
        return highestSensitiveLevel;
    }

    public void setHighestSensitiveLevel(String highestSensitiveLevel) {
        this.highestSensitiveLevel = highestSensitiveLevel;
    }

    public String getHighestResistanceLevel() {
        return highestResistanceLevel;
    }

    public void setHighestResistanceLevel(String highestResistanceLevel) {
        this.highestResistanceLevel = highestResistanceLevel;
    }

    public String getHighestDiagnosticImplicationLevel() {
        return highestDiagnosticImplicationLevel;
    }

    public void setHighestDiagnosticImplicationLevel(String highestDiagnosticImplicationLevel) {
        this.highestDiagnosticImplicationLevel = highestDiagnosticImplicationLevel;
    }

    public String getHighestPrognosticImplicationLevel() {
        return highestPrognosticImplicationLevel;
    }

    public void setHighestPrognosticImplicationLevel(String highestPrognosticImplicationLevel) {
        this.highestPrognosticImplicationLevel = highestPrognosticImplicationLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneNumber)) return false;
        GeneNumber that = (GeneNumber) o;
        return Objects.equals(getGene(), that.getGene()) &&
            Objects.equals(getAlteration(), that.getAlteration()) &&
            Objects.equals(getTumorType(), that.getTumorType()) &&
            Objects.equals(getHighestSensitiveLevel(), that.getHighestSensitiveLevel()) &&
            Objects.equals(getHighestResistanceLevel(), that.getHighestResistanceLevel()) &&
            Objects.equals(getHighestDiagnosticImplicationLevel(), that.getHighestDiagnosticImplicationLevel()) &&
            Objects.equals(getHighestPrognosticImplicationLevel(), that.getHighestPrognosticImplicationLevel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGene(), getAlteration(), getTumorType(), getHighestSensitiveLevel(), getHighestResistanceLevel(), getHighestDiagnosticImplicationLevel(), getHighestPrognosticImplicationLevel());
    }

    @Override
    public String toString() {
        return "GeneNumber{" +
            "gene=" + gene +
            ", alteration=" + alteration +
            ", tumorType=" + tumorType +
            ", highestSensitiveLevel='" + highestSensitiveLevel + '\'' +
            ", highestResistanceLevel='" + highestResistanceLevel + '\'' +
            ", highestDiagnosticImplicationLevel='" + highestDiagnosticImplicationLevel + '\'' +
            ", highestPrognosticImplicationLevel='" + highestPrognosticImplicationLevel + '\'' +
            '}';
    }
}
