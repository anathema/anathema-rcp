package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.IAttributeConstants;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.IPointHandler;

public class FreebiesBonusPointReducer extends AbstractExecutableExtension implements IPointHandler {

  private final IModelProvider modelProvider;

  public FreebiesBonusPointReducer() {
    this(new ModelCache());
  }

  public FreebiesBonusPointReducer(IModelProvider modelProvider) {
    this.modelProvider = modelProvider;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    if (characterId == null) {
      return 0;
    }
    int freebiesSpent = new PrimaryAttributeFreebies(modelProvider).getPoints(characterId, 3);
    freebiesSpent += new SecondaryAttributeFreebies(modelProvider).getPoints(characterId, 3); 
    freebiesSpent += new TertiaryAttributeFreebies(modelProvider).getPoints(characterId, 3); 
    return -freebiesSpent  * IAttributeConstants.BONUS_POINT_COST;
  }
}