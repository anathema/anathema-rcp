package net.sf.anathema.character.freebies.abilities;

import net.sf.anathema.character.abilities.template.AbilitiesTemplateProvider;
import net.sf.anathema.character.freebies.configuration.FavoredTraitCountCreditProvider;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;

public class FavoredCountCreditProvider extends FavoredTraitCountCreditProvider {

  public FavoredCountCreditProvider() {
    this(new AbilitiesTemplateProvider());
  }

  public FavoredCountCreditProvider(ITraitCollectionTemplateProvider provider) {
    super(provider);
  }
}