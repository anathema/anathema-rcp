package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.character.attributes.model.AttributeTemplateProvider;
import net.sf.anathema.character.freebies.configuration.FavoredTraitCountCreditProvider;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;

public class FavoredCountCreditProvider extends FavoredTraitCountCreditProvider {

  public FavoredCountCreditProvider() {
    this(new AttributeTemplateProvider());
  }

  public FavoredCountCreditProvider(ITraitCollectionTemplateProvider provider) {
    super(provider);
  }
}