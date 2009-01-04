package net.sf.anathema.character.core.model;

import java.util.Collection;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IDependencyProvider extends IExecutableExtension {

  Collection<String> getNeededIds(String characterTypeId, String modelId);
}