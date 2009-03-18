package net.sf.anathema.character.spiritualtraits;

import java.util.Collection;
import java.util.Collections;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.model.IDependencyProvider;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;

public class WillpowerDependencyProvider extends UnconfiguredExecutableExtension implements IDependencyProvider {

  @Override
  public Collection<String> getNeededIds(String characterTypeId, String modelId) {
    if (modelId.equals(IPluginConstants.MODEL_ID)) {
      return Collections.singletonList(modelId);
    }
    return Collections.emptyList();
  }
}