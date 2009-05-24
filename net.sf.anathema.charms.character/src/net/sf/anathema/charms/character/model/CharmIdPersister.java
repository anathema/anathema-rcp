package net.sf.anathema.charms.character.model;

import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.dom4j.Element;

public class CharmIdPersister {
  private static final String TAG_ID = "id"; //$NON-NLS-1$
  private static final String TAG_TRAIT = "trait"; //$NON-NLS-1$

  public CharmId load(Element charmElement) {
    String idPattern = charmElement.element(TAG_ID).getText();
    String trait = charmElement.element(TAG_TRAIT).getText();
    return new CharmId(idPattern, trait);
  }

  public void save(Element charmElement, ICharmId charmId) {
    charmElement.addElement(TAG_ID).addText(charmId.getIdPattern());
    charmElement.addElement(TAG_TRAIT).addText(charmId.getPrimaryTrait());
  }
}