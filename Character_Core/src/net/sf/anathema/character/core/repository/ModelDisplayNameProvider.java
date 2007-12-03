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

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ModelDisplayNameProvider)) {
      return false;
    }
    ModelDisplayNameProvider provider = (ModelDisplayNameProvider) obj;
    return getDisplayName().equals(provider.getDisplayName());
  }

  @Override
  public int hashCode() {
    return baseName.hashCode() + 3 * personalizer.hashCode();
  }
}