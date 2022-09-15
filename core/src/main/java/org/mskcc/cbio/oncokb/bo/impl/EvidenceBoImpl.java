package org.mskcc.cbio.oncokb.bo.impl;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.mskcc.cbio.oncokb.bo.EvidenceBo;
import org.mskcc.cbio.oncokb.dao.EvidenceDao;
import org.mskcc.cbio.oncokb.model.*;
import org.mskcc.cbio.oncokb.model.clinicalTrialsMathcing.Tumor;
import org.mskcc.cbio.oncokb.util.CacheUtils;
import org.mskcc.cbio.oncokb.model.TumorType;
import org.mskcc.cbio.oncokb.util.EvidenceUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jgao
 */
public class EvidenceBoImpl extends GenericBoImpl<Evidence, EvidenceDao> implements EvidenceBo {

    @Override
    public List<Evidence> findEvidencesByAlteration(Collection<Alteration> alterations) {
        Set<Evidence> set = new LinkedHashSet<Evidence>();
        Set<Alteration> alterationSet = new HashSet<>(alterations);
        for (Evidence evidence : EvidenceUtils.getAllEvidencesByAlterationsGenes(alterations)) {
            if (!Collections.disjoint(evidence.getAlterations(), alterationSet)) {
                set.add(evidence);
            }
        }
        return new ArrayList<Evidence>(set);
    }

    @Override
    public List<Evidence> findEvidencesByAlteration(Collection<Alteration> alterations, Collection<EvidenceType> evidenceTypes) {
        Set<Evidence> set = new LinkedHashSet<Evidence>();
        Set<Alteration> altsSet = new HashSet<>(alterations);
        for (Evidence evidence : EvidenceUtils.getAllEvidencesByAlterationsGenes(alterations)) {
            if (evidenceTypes.contains(evidence.getEvidenceType()) && !Collections.disjoint(evidence.getAlterations(), altsSet)) {
                set.add(evidence);
            }
        }
        return new ArrayList<Evidence>(set);
    }

    @Override
    public List<Evidence> findEvidencesByAlterationWithLevels(Collection<Alteration> alterations, Collection<EvidenceType> evidenceTypes, Collection<LevelOfEvidence> levelOfEvidences) {
        if (evidenceTypes == null) {
            return findEvidencesByAlteration(alterations, evidenceTypes);
        }
        Set<Evidence> set = new LinkedHashSet<Evidence>();

        for (Evidence evidence : EvidenceUtils.getAllEvidencesByAlterationsGenes(alterations)) {
            if (Sets.intersection(evidence.getAlterations(), new HashSet(alterations)).size() > 0
                && evidenceTypes.contains(evidence.getEvidenceType())
                && levelOfEvidences.contains(evidence.getLevelOfEvidence())) {
                set.add(evidence);
            }
        }
        return new ArrayList<Evidence>(set);
    }

    @Override
    public List<Evidence> findEvidencesByAlteration(Collection<Alteration> alterations, Collection<EvidenceType> evidenceTypes, TumorType matchedTumorType,  List<TumorType> relevantTumorTypes) {
        if (relevantTumorTypes == null) {
            if (evidenceTypes == null) {
                return findEvidencesByAlteration(alterations);
            }
            return findEvidencesByAlteration(alterations, evidenceTypes);
        }

        Set<Evidence> alterationEvidences = new HashSet<>(findEvidencesByAlteration(alterations, evidenceTypes));
        Set<Evidence> evidences = new LinkedHashSet<>();
        for (TumorType relevantTumorType : relevantTumorTypes) {
            for (Evidence evidence : alterationEvidences) {
                boolean hasJointOnSubtype = false;
                if (evidence.getRelevantCancerTypes().isEmpty()) {
                    hasJointOnSubtype = evidence.getCancerTypes().contains(relevantTumorType);
                } else {
                    hasJointOnSubtype = matchedTumorType != null && evidence.getRelevantCancerTypes().contains(matchedTumorType);
                }
                if (hasJointOnSubtype) {
                    evidences.add(evidence);
                } else if (evidence.getRelevantCancerTypes().isEmpty()) {
                    // we also like to check whether the evidence is assigned to a main type. Only check if the evidence relevant cancer type is empty.
                    // If the evidence has the relevant cancer type curated, disjoin in the previous step should capture it
                    Set<TumorType> evidenceMainTypes = evidence.getCancerTypes().stream().filter(cancerType -> StringUtils.isEmpty(cancerType.getCode()) && !StringUtils.isEmpty(cancerType.getMainType())).collect(Collectors.toSet());
                    if (!evidenceMainTypes.isEmpty()) {
                        if (evidenceMainTypes.stream().map(tumorType -> tumorType.getMainType()).collect(Collectors.toSet()).contains(relevantTumorType.getMainType())) {
                            evidences.add(evidence);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(evidences);
    }

    @Override
    public List<Evidence> findEvidencesByAlteration(Collection<Alteration> alterations, Collection<EvidenceType> evidenceTypes, TumorType matchedTumorType, List<TumorType> tumorTypes, Collection<LevelOfEvidence> levelOfEvidences) {
        return findEvidencesByAlteration(alterations, evidenceTypes, matchedTumorType, tumorTypes).stream().filter(evidence -> levelOfEvidences.contains(evidence.getLevelOfEvidence())).collect(Collectors.toList());
    }

    @Override
    public List<Evidence> findEvidencesByGene(Collection<Gene> genes) {
        Set<Evidence> set = new LinkedHashSet<Evidence>();
        for (Gene gene : genes) {
            set.addAll(CacheUtils.getEvidences(gene));
        }
        return new ArrayList<Evidence>(set);
    }

    @Override
    public List<Evidence> findEvidencesByGeneFromDB(Collection<Gene> genes) {
        Set<Evidence> set = new LinkedHashSet<>();
        for (Gene gene : genes) {
            set.addAll(getDao().findEvidencesByGene(gene));
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<Evidence> findEvidencesByGene(Collection<Gene> genes, Collection<EvidenceType> evidenceTypes) {
        Set<Evidence> set = new LinkedHashSet<Evidence>();
        for (Gene gene : genes) {
            for (Evidence evidence : CacheUtils.getEvidences(gene)) {
                if (evidenceTypes.contains(evidence.getEvidenceType())) {
                    set.add(evidence);
                }
            }
        }
        return new ArrayList<Evidence>(set);
    }

    @Override
    public List<Evidence> findEvidencesByGene(Collection<Gene> genes, Collection<EvidenceType> evidenceTypes, Collection<TumorType> tumorTypes) {
        Set<Evidence> set = new LinkedHashSet<Evidence>();
        for (Gene gene : genes) {
            set.addAll(CacheUtils.getEvidences(gene).stream().filter(evidence -> evidenceTypes.contains(evidence.getEvidenceType()) && !Collections.disjoint(evidence.getRelevantCancerTypes().isEmpty() ? evidence.getCancerTypes() : evidence.getRelevantCancerTypes(), tumorTypes)).collect(Collectors.toList()));
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<Evidence> findEvidencesByIds(List<Integer> ids) {
        List<Evidence> evidences = new ArrayList<>();
        for (Evidence evidence : CacheUtils.getAllEvidences()) {
            if (ids.contains(evidence.getId())) {
                evidences.add(evidence);
            }
            if (evidences.size() == ids.size()) {
                break;
            }
        }
        return evidences;
    }

    @Override
    public List<Drug> findDrugsByAlterations(Collection<Alteration> alterations) {
        List<Evidence> evidences = new ArrayList<Evidence>();

        Set<EvidenceType> evidenceTypes = new HashSet<>();
        evidenceTypes.add(EvidenceType.STANDARD_THERAPEUTIC_IMPLICATIONS_FOR_DRUG_SENSITIVITY);
        evidenceTypes.add(EvidenceType.INVESTIGATIONAL_THERAPEUTIC_IMPLICATIONS_DRUG_SENSITIVITY);
        evidences = findEvidencesByAlteration(alterations, evidenceTypes);


        Set<Drug> set = new HashSet<Drug>();
        for (Evidence ev : evidences) {
            for (Treatment t : ev.getTreatments()) {
                set.addAll(t.getDrugs());
            }
        }
        return new ArrayList<Drug>(set);
    }

    @Override
    public List<Object> findTumorTypesWithEvidencesForAlterations(List<Alteration> alterations) {
        return getDao().findTumorTypesWithEvidencesForAlterations(alterations);
    }

    @Override
    public List<Object> findCancerTypesWithEvidencesForAlterations(List<Alteration> alterations) {
        return getDao().findCancerTypesWithEvidencesForAlterations(alterations);
    }

    @Override
    public List<Object> findSubtypesWithEvidencesForAlterations(List<Alteration> alterations) {
        return getDao().findSubtypesWithEvidencesForAlterations(alterations);
    }

    @Override
    public List<Evidence> findEvidenceByUUIDs(List<String> uuids) {
        return new ArrayList<>(CacheUtils.getEvidencesByUUIDs(new HashSet<>(uuids)));
    }
}
