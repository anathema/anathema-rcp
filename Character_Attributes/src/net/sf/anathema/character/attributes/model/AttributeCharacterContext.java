package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

import org.eclipse.core.resources.IFolder;

public class AttributeCharacterContext implements IAttributeCharacterContext {

  private final IFolder characterFolder;
  private final IModelProvider modelProvider;
  private final AttributeTemplate template = new AttributeTemplate();

  public AttributeCharacterContext(IModelProvider modelProvider, IFolder characterFolder) {
    this.modelProvider = modelProvider;
    this.characterFolder = characterFolder;
  }

  @Override
  public ITraitTemplate getTraitTemplate() {
    return template.getTraitTemplate();
  }

  @Override
  public IExperience getExperience() {
    return (IExperience) getModel(IExperience.MODEL_ID);
  }

  private Object getModel(String modelId) {
    return modelProvider.getModel(new ModelIdentifier(characterFolder, modelId));
  }

  @Override
  public IAttributes getAttributes() {
    return (IAttributes) getModel(IAttributes.MODEL_ID);
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    return template.getGroups();
  }
}