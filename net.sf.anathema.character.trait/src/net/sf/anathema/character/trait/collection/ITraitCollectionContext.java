package net.sf.anathema.character.trait.collection;

import java.util.List;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.validator.IValidator;

public interface ITraitCollectionContext {

  public IExperience getExperience();

  public ITraitCollectionModel getCollection();

  public ITraitGroup[] getTraitGroups();

  public String getActiveImageId();

  public List<IValidator> getValidators(String traitId);
}