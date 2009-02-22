package net.sf.anathema.character.trait.display;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.FavorizationInteraction;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.lib.collection.CollectionUtilities;

public abstract class AbstractTraitDisplayGroupFactory extends AbstractExecutableExtension implements
    IDisplayGroupFactory {

  private final String modelId;
  private final ITraitCollectionTemplateProvider templateProvider;

  protected AbstractTraitDisplayGroupFactory(String modelId, ITraitCollectionTemplateProvider templateProvider) {
    this.modelId = modelId;
    this.templateProvider = templateProvider;
  }

  public List<IDisplayTraitGroup<IDisplayTrait>> createDisplayTraitGroups(ICharacter character) {
    TraitCollectionContext context = createCollectionContext(character);
    return transformGroupsToDisplayGroups(character, context);
  }

  private List<IDisplayTraitGroup<IDisplayTrait>> transformGroupsToDisplayGroups(
      ICharacter character,
      TraitCollectionContext context) {
    TraitGroup[] traitGroups = context.getTraitGroups();
    IFavorizationInteraction favorizationHandler = createFavorizationInteraction(character);
    DisplayTraitGroupTransformer transformer = new DisplayTraitGroupTransformer(context, favorizationHandler);
    return CollectionUtilities.transform(traitGroups, transformer);
  }

  private IFavorizationInteraction createFavorizationInteraction(ICharacter character) {
    String templateId = character.getTemplate().getId();
    ITraitCollectionTemplate attributesTemplate = templateProvider.getTraitTemplate(templateId);
    IFavorizationTemplate favorizationTemplate = attributesTemplate.getFavorizationTemplate();
    IFavorizationInteraction favorizationHandler = new FavorizationInteraction(character, favorizationTemplate, modelId);
    return favorizationHandler;
  }

  private TraitCollectionContext createCollectionContext(ICharacter character) {
    return new TraitCollectionContext(character, modelId, createGroupTemplate(character));
  }

  protected abstract ITraitGroupTemplate createGroupTemplate(ICharacter character);
}