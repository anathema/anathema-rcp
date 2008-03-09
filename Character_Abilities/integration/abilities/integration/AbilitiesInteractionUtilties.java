package abilities.integration;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.groupeditor.TraitCollectionEditorInput;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

public class AbilitiesInteractionUtilties {

  private static final class DummyProvider implements IDisplayNameProvider {
    @Override
    public String getDisplayName() {
      return "Horst"; //$NON-NLS-1$
    }
  }

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

  public static List<IDisplayTraitGroup<IInteractiveTrait>> createDisplayAttributeGroups(final IFolder folder)
      throws PersistenceException,
      CoreException,
      ExtensionException {
    String modelId = IAbilitiesPluginConstants.MODEL_ID;
    ModelExtensionPoint extensionPoint = new ModelExtensionPoint();
    IModelDisplayConfiguration displayConfiguration = extensionPoint.getDisplayConfiguration(modelId);
    return ((TraitCollectionEditorInput) displayConfiguration.createEditorInput(
        folder,
        new DummyProvider())).createDisplayGroups();
  }
}