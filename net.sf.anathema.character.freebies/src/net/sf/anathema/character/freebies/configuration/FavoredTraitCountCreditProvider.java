package net.sf.anathema.character.freebies.configuration;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;

public class FavoredTraitCountCreditProvider extends UnconfiguredExecutableExtension implements ICreditProvider {

  private final ITraitCollectionTemplateProvider provider;

  public FavoredTraitCountCreditProvider(ITraitCollectionTemplateProvider provider) {
    this.provider = provider;
  }

  @Override
  public Integer getCredit(String templateId) {
    int favorizationCount = provider.getTraitTemplate(templateId).getFavorizationTemplate().getAllowedFavored();
    if (favorizationCount == 0) {
      return null;
    }
    return favorizationCount;
  }
}