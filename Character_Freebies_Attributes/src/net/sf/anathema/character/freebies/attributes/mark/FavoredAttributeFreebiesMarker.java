package net.sf.anathema.character.freebies.attributes.mark;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.resource.IModelMarker;
import net.sf.anathema.character.freebies.attributes.FavoredAttributeFreebiesHandler;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

import org.eclipse.core.resources.IMarker;
import org.eclipse.osgi.util.NLS;

public class FavoredAttributeFreebiesMarker implements IModelMarker {

  private static final String UNSPENT_FREEBIES_MARKER_ID = "net.sf.anathema.markers.unspent.attribute.freebies"; //$NON-NLS-1$
  private final FavoredAttributeFreebiesHandler handler;
  private final ICreditManager manager;
  private final ICharacterId characterId;

  public FavoredAttributeFreebiesMarker(
      IModelCollection collection,
      ICreditManager creditManager,
      ICharacterId character) {
    this.handler = new FavoredAttributeFreebiesHandler(collection, creditManager);
    this.manager = creditManager;
    this.characterId = character;
  }

  @Override
  public String getMarkerId() {
    return UNSPENT_FREEBIES_MARKER_ID;
  }

  @Override
  public boolean isActive(IMarker[] markers) {
    int credit = manager.getCredit(characterId, handler.getCreditId());
    return credit > handler.getPoints(characterId, credit);
  }

  @Override
  public String getDescription(String characterName) {
    return NLS.bind(Messages.FavoredAttributeFreebiesMarker_UnspentFreebies, characterName);
  }

}
