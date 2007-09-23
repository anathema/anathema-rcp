package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.AttributeTemplateProvider;
import net.sf.anathema.character.attributes.model.IAttributeTemplateProvider;
import net.sf.anathema.character.freebies.configuration.ICreditProvider;

public class FavoredCountCreditProvider extends AbstractExecutableExtension implements ICreditProvider {

  private final IAttributeTemplateProvider provider;

  public FavoredCountCreditProvider() {
    this(new AttributeTemplateProvider());
  }

  public FavoredCountCreditProvider(IAttributeTemplateProvider provider) {
    this.provider = provider;
  }

  @Override
  public Integer getCredit(String templateId) {
    int favorizationCount = provider.getAttributeTemplate(templateId).getFavorizationCount();
    if (favorizationCount == 0) {
      return null;
    }
    return favorizationCount;
  }
}