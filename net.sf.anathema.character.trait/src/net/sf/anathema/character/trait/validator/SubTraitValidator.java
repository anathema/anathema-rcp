package net.sf.anathema.character.trait.validator;

import java.util.List;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.CreationModelTransformer;
import net.sf.anathema.character.trait.collection.ExperiencedModelTransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.interactive.IIntValueModel;

public class SubTraitValidator implements IValidator {

  private final ITraitCollectionModel traitCollection;
  private final IBasicTrait trait;
  private final IExperience experience;
  private final ExperiencedModelTransformer experienceTransformer = new ExperiencedModelTransformer();
  private final CreationModelTransformer creationTransformer = new CreationModelTransformer();

  public SubTraitValidator(IExperience experience, ITraitCollectionModel traitCollection, IBasicTrait trait) {
    this.experience = experience;
    this.traitCollection = traitCollection;
    this.trait = trait;
  }

  @Override
  public int getValidValue(int value) {
    List<IBasicTrait> subTraits = traitCollection.getSubTraits(trait.getTraitType().getId());
    if (subTraits.isEmpty()) {
      return value;
    }
    ITransformer<IBasicTrait, IIntValueModel> transformer = experience.isExperienced()
        ? experienceTransformer
        : creationTransformer;
    int maximalSubTrait = 0;
    for (IBasicTrait aSubTrait : subTraits) {
      IIntValueModel subTraitValueModel = transformer.transform(aSubTrait);
      maximalSubTrait = Math.max(subTraitValueModel.getValue(), maximalSubTrait);
    }
    return maximalSubTrait;
  }
}