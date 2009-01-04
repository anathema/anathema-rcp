package net.sf.anathema.character.caste.dependency;

import java.util.Collection;
import java.util.Collections;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.model.IDependencyProvider;
import net.sf.anathema.character.trait.model.IMainTraitModelProvider;
import net.sf.anathema.character.trait.model.MainTraitModelProvider;

public class MainModelDependencyProvider extends AbstractExecutableExtension implements IDependencyProvider {

  private final IMainTraitModelProvider mainTraits;

  public MainModelDependencyProvider() {
    this(new MainTraitModelProvider());
  }

  public MainModelDependencyProvider(IMainTraitModelProvider mainTraitModelProvider) {
    this.mainTraits = mainTraitModelProvider;
  }

  @Override
  public Collection<String> getNeededIds(String characterTypeId, String modelId) {
    String mainTraitModel = mainTraits.getFor(characterTypeId);
    if (mainTraitModel.equals(modelId)) {
      return Collections.singleton(ICasteModel.ID);
    }
    return Collections.emptyList();
  }
}