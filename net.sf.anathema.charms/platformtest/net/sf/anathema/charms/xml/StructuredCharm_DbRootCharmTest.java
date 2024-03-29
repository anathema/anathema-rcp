package net.sf.anathema.charms.xml;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.xml.structure.IStructuredCharm;
import net.sf.anathema.charms.xml.structure.StructuredCharm;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

public class StructuredCharm_DbRootCharmTest {

  private IStructuredCharm xmlCharm;

  @Before
  public void createArcheryWithoutCharmPrerequisite() throws Exception {
    String xml = "<charm id=\"Dragon-Blooded.DragonGracedArrow\" exalt=\"Dragon-Blooded\" group=\"Archery\">" //$NON-NLS-1$
        + " <prerequisite/>" //$NON-NLS-1$
        + "</charm>"; //$NON-NLS-1$
    Element rootElement = DocumentUtilities.read(xml).getRootElement();
    xmlCharm = new StructuredCharm(rootElement);

  }

  @Test
  public void concatenatesExaltAndGroupLowerCaseForTreePart() throws Exception {
    assertThat(xmlCharm.getTreePart(), is("dragon-blooded.archery")); //$NON-NLS-1$
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