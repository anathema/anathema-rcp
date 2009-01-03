package net.sf.anathema.charms.xml;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

public class XmlCharm_DbRootCharmTest {

  private IXmlCharm xmlCharm;

  @Before
  public void createArcheryWithoutCharmPrerequisite() throws Exception {
    String xml = "<charm id=\"Dragon-Blooded.DragonGracedArrow\" exalt=\"Dragon-Blooded\" group=\"Archery\">" //$NON-NLS-1$
        + " <prerequisite/>" //$NON-NLS-1$
        + "</charm>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    xmlCharm = new XmlCharm(rootElement);

  }

  @Test
  public void concatenatesExaltAndGroupLowerCaseForTreePart() throws Exception {
    assertThat(xmlCharm.getTreePart(), is("dragon-blooded.archery")); //$NON-NLS-1$
  }

  @Test
  public void usesTreePartAsIdForTreeDto() throws Exception {
    assertThat(xmlCharm.getTreeDto().id, is(xmlCharm.getTreePart()));
  }

  @Test
  public void usesTreePartAsNameForTreeDto() throws Exception {
    assertThat(xmlCharm.getTreeDto().name, is(xmlCharm.getTreePart()));
  }

  @Test
  public void usesDbCharacterTypeForTreeDto() throws Exception {
    assertThat(xmlCharm.getTreeDto().characterType, is("net.sf.anathema.character.type.db")); //$NON-NLS-1$
  }

  @Test
  public void usesGroupAsPrimaryTrait() throws Exception {
    assertThat(xmlCharm.getTreeDto().primaryTrait, is("Archery")); //$NON-NLS-1$
  }

  @Test
  public void addsOneRootPrerequisteWithPrimaryTrait() throws Exception {
    Set<CharmPrerequisite> set = new HashSet<CharmPrerequisite>();
    xmlCharm.addPrerequisites(set);
    assertThat(set.size(), is(1));
    CharmPrerequisite prerequisite = set.iterator().next();
    assertThat(prerequisite.getSource(), is(nullValue()));
    assertThat(prerequisite.getDestination().getPrimaryTrait(), is("Archery")); //$NON-NLS-1$
    assertThat(prerequisite.getDestination().getIdPattern(), is("Dragon-Blooded.DragonGracedArrow")); //$NON-NLS-1$
  }
}