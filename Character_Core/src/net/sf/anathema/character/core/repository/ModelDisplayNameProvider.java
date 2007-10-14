package net.sf.anathema.character.core.repository;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;

public class ModelDisplayNameProvider implements IDisplayNameProvider {

  private final IDisplayNameProvider personalizer;
  private final String baseName;

  public ModelDisplayNameProvider(String baseName, IDisplayNameProvider personalizer) {
    this.baseName = baseName;
    this.personalizer = personalizer;
  }

  @Override
  public String getDisplayName() {
    return baseName + " - " + personalizer.getDisplayName(); //$NON-NLS-1$
  }
}