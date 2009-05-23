package net.sf.anathema.charms.character.special;

import static java.text.MessageFormat.*;
import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.properties.Evaluation;
import net.sf.anathema.charms.character.CharmCharacterPlugin;
import net.sf.anathema.charms.tree.ICharmId;

public class VirtualCharms implements IVirtualCharms {

  private static final String TAG_EVALUATION = "evaluation"; //$NON-NLS-1$

  private final class CharmIdFormatter implements ITransformer<String, String> {
    private final String primaryTrait;

    private CharmIdFormatter(String primaryTrait) {
      this.primaryTrait = primaryTrait;
    }

    @Override
    public String transform(String pattern) {
      return format(pattern, primaryTrait);
    }
  }

  static final String ATTRIB_CHARM_ID_PATTERN = "charmIdPattern"; //$NON-NLS-1$
  static final String TAG_VIRTUAL_CHARM = "virtualCharm"; //$NON-NLS-1$
  private final EclipseExtensionPoint extensionPoint;

  public VirtualCharms() {
    this.extensionPoint = new EclipseExtensionPoint(CharmCharacterPlugin.PLUGIN_ID, "specialcharms"); //$NON-NLS-1$
  }

  public boolean isVirtual(final String pattern) {
    IExtensionElement virtualElement = getElementForPattern(pattern);
    return virtualElement != null;
  }

  private IExtensionElement getElementForPattern(final String pattern) {
    IsVirtualCharmWithIdPattern predicate = new IsVirtualCharmWithIdPattern(pattern);
    return extensionPoint.getFirst(predicate);
  }

  public boolean isVirtual(ICharmId charmId) {
    return isVirtual(charmId.getIdPattern());
  }

  @Override
  public boolean isFullfilled(final ICharmId charmId, ICharacter character) {
    IExtensionElement element = getElementForPattern(charmId.getIdPattern());
    String primaryTrait = charmId.getPrimaryTrait();
    IExtensionElement evaluationElement = element.getElement(TAG_EVALUATION);
    CharmIdFormatter propertyValueTransformer = new CharmIdFormatter(primaryTrait);
    Evaluation evaluation = new Evaluation();
    IPredicate<ICharacter> predicate = evaluation.create(evaluationElement, propertyValueTransformer);
    return predicate.evaluate(character);
  }
}