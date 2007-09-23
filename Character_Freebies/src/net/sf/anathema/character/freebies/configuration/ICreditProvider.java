package net.sf.anathema.character.freebies.configuration;

import org.eclipse.core.runtime.IExecutableExtension;

public interface ICreditProvider extends IExecutableExtension {

  public int getCredit(String templateId);
}