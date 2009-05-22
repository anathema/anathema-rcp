package net.sf.anathema.character.points.calculation;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;

public class ExperienceCharacterFactory implements IExperienceCharacterFactory {

  private final IModelCollection collection;
  private final Iterable<IPointConfiguration> configurations;

  public ExperienceCharacterFactory(IModelCollection collection, Iterable<IPointConfiguration> configurations) {
    this.collection = collection;
    this.configurations = configurations;
  }

  @Override
  public IExperienceCharacter create(ICharacterId id) {
    IModelContainer container = new ModelContainer(collection, id);
    return new ExperienceCharacter(container, id, configurations);
  }
}