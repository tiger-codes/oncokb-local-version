package org.mskcc.cbio.oncokb.model;


import java.util.ArrayList;
import java.util.List;


/**
 * TumorType generated by hbm2java
 */
public class EvidenceQueryRes implements java.io.Serializable {
    private String id; //Optional, This id is passed from request. The identifier used to distinguish the query
    private Query query;
    private Gene gene;
    private Alteration exactMatchedAlteration;
    private TumorType exactMatchedTumorType;
    private List<Alteration> alterations = new ArrayList<>();
    private List<Alteration> alleles = new ArrayList<>();
    private List<TumorType> tumorTypes = new ArrayList<>();
    private List<Evidence> evidences = new ArrayList<>();
    private List<LevelOfEvidence> levelOfEvidences = new ArrayList<>();

    public EvidenceQueryRes() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public Alteration getExactMatchedAlteration() {
        return exactMatchedAlteration;
    }

    public void setExactMatchedAlteration(Alteration exactMatchedAlteration) {
        this.exactMatchedAlteration = exactMatchedAlteration;
    }

    public TumorType getExactMatchedTumorType() {
        return exactMatchedTumorType;
    }

    public void setExactMatchedTumorType(TumorType exactMatchedTumorType) {
        this.exactMatchedTumorType = exactMatchedTumorType;
    }

    public List<Alteration> getAlterations() {
        return alterations;
    }

    public void setAlterations(List<Alteration> alterations) {
        if (alterations != null) {
            this.alterations = alterations;
        } else {
            this.alterations = new ArrayList<>();
        }
    }

    public List<Alteration> getAlleles() {
        return alleles;
    }

    public void setAlleles(List<Alteration> alleles) {
        if (alleles != null) {
            this.alleles = alleles;
        } else {
            this.alleles = new ArrayList<>();
        }
    }

    public List<TumorType> getOncoTreeTypes() {
        return tumorTypes;
    }

    public void setOncoTreeTypes(List<TumorType> cancerTypes) {
        if (cancerTypes != null) {
            this.tumorTypes = cancerTypes;
        } else {
            this.tumorTypes = new ArrayList<>();
        }
    }

    public List<Evidence> getEvidences() {
        return evidences;
    }

    public void setEvidences(List<Evidence> evidences) {
        if (evidences != null) {
            this.evidences = evidences;
        } else {
            this.evidences = new ArrayList<>();
        }
    }

    public List<LevelOfEvidence> getLevelOfEvidences() {
        return levelOfEvidences;
    }

    public void setLevelOfEvidences(List<LevelOfEvidence> levelOfEvidences) {
        if (levelOfEvidences != null) {
            this.levelOfEvidences = levelOfEvidences;
        } else {
            this.levelOfEvidences = new ArrayList<>();
        }
    }
}

