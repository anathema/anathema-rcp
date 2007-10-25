package net.sf.anathema.character.freebies.attributes.mark;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.attributes.FavoredAttributeFreebiesHandler;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

import org.eclipse.osgi.util.NLS;

public class FavoredAttributeFreebiesMarker implements IModelMarker {

  private static final String UNSPENT_FREEBIES_MARKER_ID = "net.sf.anathema.markers.unspent.attribute.freebies"; //$NON-NLS-1$
  private final FavoredAttributeFreebiesHandler handler = new FavoredAttributeFreebiesHandler();
  private final ICreditManager manager = new CreditManager();
  private final ICharacterId characterId;

  public FavoredAttributeFreebiesMarker(ICharacterId character) {
    this.characterId = character;
  }

  @Override
  public String getMarkerId() {
    return UNSPENT_FREEBIES_MARKER_ID;
  }

  @Override
  public boolean isActive() {
    int credit = manager.getCredit(characterId, handler.getCreditId());
    return credit > handler.getPoints(characterId, credit);
  }

  @Override
  public String getDescription(String characterName) {
    return NLS.bind(Messages.FavoredAttributeFreebiesMarker_UnspentFreebies, characterName);
  }

}