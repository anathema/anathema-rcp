package net.sf.anathema.character.freebies.attributes.mark;

import net.sf.anathema.character.attributes.model.AttributesTemplateProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.resource.IModelMarker;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.freebies.attributes.FavoredAttributeCountHandler;
import net.sf.anathema.character.freebies.configuration.AbstractFavoredTraitCountHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;

import org.eclipse.core.resources.IMarker;
import org.eclipse.osgi.util.NLS;

public class FavoredAttributePicksMarker implements IModelMarker {

  private static final String UNSPENT_PICKS_MARKER_ID = "net.sf.anathema.markers.unspent.attribute.picks"; //$NON-NLS-1$
  private final ITraitCollectionTemplateProvider provider = new AttributesTemplateProvider();
  private final AbstractFavoredTraitCountHandler handler = new FavoredAttributeCountHandler();
  private final ICharacterId characterId;
  private final String templateId;

  public FavoredAttributePicksMarker(ICharacterId character) {
    this.characterId = character;
    this.templateId = new CharacterTemplateProvider().getTemplate(characterId).getId();
  }

  @Override
  public String getMarkerId() {
    return UNSPENT_PICKS_MARKER_ID;
  }

  @Override
  public boolean isActive(IMarker[] markers) {
    IFavorizationTemplate favorizationTemplate = provider.getTraitTemplate(templateId).getFavorizationTemplate();
    return favorizationTemplate.getFavorizationCount() > handler.getPoints(characterId, 0);
  }

  @Override
  public String getDescription(String characterName) {
    return NLS.bind(Messages.FavoredAttributePicksMarker_UnspentPicks, characterName);
  }
}