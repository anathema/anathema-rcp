package net.sf.anathema.character.points;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.fake.DummyModelCollection;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.configuration.internal.IPointConfigurationProvider;
import net.sf.anathema.character.points.configuration.internal.PointConfiguration;
import net.sf.anathema.character.points.view.PointValueEntryFactoryFactory;
import net.sf.anathema.view.valuelist.IValueEntry;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class PointViewInputFactoryTest {

  private final class StaticValuePointHandler extends UnconfiguredExecutableExtension implements IPointHandler {
    private final int points;

    public StaticValuePointHandler(int points) {
      this.points = points;
    }

    @Override
    public int getPoints(ICharacterId characterId) {
      return points;
    }
  }

  private void assertPointEntryCreated(
      String expectedDisplayName,
      int expectedValue,
      int staticEntries,
      List<IValueEntry> entries) {
    assertEquals(1 + staticEntries, entries.size());
    int entryIndex = staticEntries;
    assertEquals(expectedDisplayName, entries.get(entryIndex).getDisplayName());
    assertEquals(String.valueOf(expectedValue), entries.get(entryIndex).getValue());
    EasyMock.verify(pointConfigurationProvider);
  }

  private List<IPointConfiguration> createPointConfigurations(String expectedDisplayName, int expectedValue) {
    PointConfiguration pointConfiguration = new PointConfiguration(expectedDisplayName);
    pointConfiguration.addHandler(new StaticValuePointHandler(expectedValue));
    return Collections.singletonList((IPointConfiguration) pointConfiguration);
  }

  private PointValueEntryFactoryFactory factory;
  private IPointConfigurationProvider pointConfigurationProvider;

  @Before
  public void createFactory() throws Exception {
    this.pointConfigurationProvider = EasyMock.createMock(IPointConfigurationProvider.class);
    ICharacterTemplateProvider templateProvider = EasyMock.createNiceMock(ICharacterTemplateProvider.class);
    this.factory = new PointValueEntryFactoryFactory(
        new DummyModelCollection(),
        pointConfigurationProvider,
        templateProvider);
  }

  @Test
  public void experiencedConfigurationsUsedForExperienced() throws Exception {
    String expectedDisplayName = "Has�nt�mlich Lieb�s"; //$NON-NLS-1$
    int expectedValue = 5;
    EasyMock.expect(pointConfigurationProvider.getExperiencePointConfigurations(null)).andReturn(
        createPointConfigurations(expectedDisplayName, expectedValue));
    EasyMock.replay(pointConfigurationProvider);
    assertPointEntryCreated(expectedDisplayName, expectedValue, 2, factory.create(null, true).createEntries());
  }

  @Test
  public void bonusPointConfigurationsUsedForUnexperienced() throws Exception {
    String expectedDisplayName = "Has�nt�mlich Lieb�s"; //$NON-NLS-1$
    int expectedValue = 5;
    EasyMock.expect(pointConfigurationProvider.getBonusPointConfigurations(null)).andReturn(
        createPointConfigurations(expectedDisplayName, expectedValue));
    EasyMock.replay(pointConfigurationProvider);
    assertPointEntryCreated(expectedDisplayName, expectedValue, 0, factory.create(null, false).createEntries());
  }
}