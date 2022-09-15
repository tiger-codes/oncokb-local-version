/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.cbio.oncokb.bo;

import org.mskcc.cbio.oncokb.model.*;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jgao
 */
public interface AlterationBo extends GenericBo<Alteration> {

    /**
     * Get set of alterations by entrez gene Ids.
     *
     * @param genes
     * @return
     */
    List<Alteration> findAlterationsByGene(Collection<Gene> genes);


    Alteration findAlteration(Gene gene, AlterationType alterationType, String alteration);

    Alteration findAlteration(Gene gene, AlterationType alterationType, ReferenceGenome referenceGenome, String alteration);

    Alteration findAlteration(Gene gene, AlterationType alterationType, ReferenceGenome referenceGenome, String alteration, String name);

    /**
     * @param gene
     * @param alterationType
     * @param alteration
     * @param name
     * @return
     */
    Alteration findAlterationFromDao(Gene gene, AlterationType alterationType, ReferenceGenome referenceGenome, String alteration, String name);

    Alteration findExactlyMatchedAlteration(ReferenceGenome referenceGenome, Alteration alteration, Set<Alteration> fullAlterations);
    /**
     * @param gene
     * @param consequence
     * @param start
     * @param end
     * @return
     */
    List<Alteration> findRelevantOverlapAlterations(Gene gene, ReferenceGenome referenceGenome, VariantConsequence consequence, int start, int end, String proteinChange, Set<Alteration> alterations);

    /**
     * @param gene
     * @param consequence
     * @param start
     * @param end
     * @return
     */
    List<Alteration> findMutationsByConsequenceAndPositionOnSamePosition(Gene gene, ReferenceGenome referenceGenome, VariantConsequence consequence, int start, int end, String referenceResidue, Collection<Alteration> alterations);

    /**
     * @param alteration
     * @return
     */
    LinkedHashSet<Alteration> findRelevantAlterations(ReferenceGenome referenceGenome, Alteration alteration, boolean includeAlternativeAllele);
    /**
     * @param alteration
     * @return
     */
    LinkedHashSet<Alteration> findRelevantAlterationsForCategoricalAlt(ReferenceGenome referenceGenome, Alteration alteration, Set<Alteration> alterations);

    /**
     * @param alteration
     * @return
     */
    LinkedHashSet<Alteration> findRelevantAlterations(ReferenceGenome referenceGenome, Alteration alteration, Set<Alteration> alterations, boolean includeAlternativeAllele);

    void deleteMutationsWithoutEvidenceAssociatedByGene(Gene gene);
}
