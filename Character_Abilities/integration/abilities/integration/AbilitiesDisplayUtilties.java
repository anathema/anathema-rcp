package abilities.integration;

import java.util.List;

import net.sf.anathema.character.abilities.template.AbilitiesTemplateProvider;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTypeProvider;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.core.type.CharacterTypeProvider;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.display.DisplayTraitGroupTransformer;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.groupeditor.FavorizationHandler;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
import net.sf.anathema.lib.collection.CollectionUtilities;

public class AbilitiesDisplayUtilties {

  public static final class ModelContainer implements IModelContainer {
    private final ICharacterId characterId;

    public ModelContainer(ICharacterId characterId) {
      this.characterId = characterId;
    }

    @Override
    public IModel getModel(String id) {
      return ModelCache.getInstance().getModel(new ModelIdentifier(characterId, id));
    }
  }

  public static List<IDisplayTraitGroup<IDisplayTrait>> createDisplayAttributeGroups(final ICharacterId characterId) {
    ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(characterId);
    String modelId = IAbilitiesPluginConstants.MODEL_ID;
    ITraitCollectionTemplate modelTemplate = new AbilitiesTemplateProvider().getTraitTemplate(template.getId());
    IModelContainer modelContainer = new ModelContainer(characterId);
    ICharacterTypeProvider typeProvider = new CharacterTypeProvider(characterId, new CharacterTypeFinder());
    TraitCollectionContext context = new TraitCollectionContext(modelContainer, typeProvider, modelId, modelTemplate, modelTemplate);
    IFavorizationHandler favorizationHandler = new FavorizationHandler(modelContainer, modelTemplate, modelId);
    TraitGroup[] traitGroups = context.getTraitGroups();
    return CollectionUtilities.transform(traitGroups, new DisplayTraitGroupTransformer(context, favorizationHandler));
  }
}