package net.sf.anathema.character.caste.dependency;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Collection;

import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.trait.model.IMainTraitModelProvider;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

public class MainModelDependencyProvider_Test {

  private MainModelDependencyProvider provider;

  @Before
  public void createProvider() throws Exception {
    IMainTraitModelProvider mainTraits = EasyMock.createNiceMock(IMainTraitModelProvider.class);
    EasyMock.expect(mainTraits.getFor("Lunar")).andReturn("Attributes"); //$NON-NLS-1$ //$NON-NLS-2$
    EasyMock.expect(mainTraits.getFor("Solar")).andReturn("Abilities"); //$NON-NLS-1$ //$NON-NLS-2$
    EasyMock.replay(mainTraits);
    provider = new MainModelDependencyProvider(mainTraits);
  }

  
  @Test
  public void returnsCasteModelForLunarAttributes() throws Exception {
    Collection<String> ids = provider.getNeededIds("Lunar", "Attributes"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(ids, JUnitMatchers.hasItems(ICasteModel.ID));
  }

  @Test
  public void returnsEmptyListForSolarAttributes() throws Exception {
    Collection<String> ids = provider.getNeededIds("Solar", "Attributes"); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(ids.size(), is(0));
  }
}