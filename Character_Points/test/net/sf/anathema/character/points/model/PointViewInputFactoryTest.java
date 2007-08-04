package net.sf.anathema.character.points.model;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.points.IPointConfiguration;
import net.sf.anathema.character.points.IPointConfigurationProvider;
import net.sf.anathema.character.points.IPointEntry;
import net.sf.anathema.character.points.IPointHandler;
import net.sf.anathema.character.points.PointConfiguration;
import net.sf.anathema.character.points.PointViewInputFactory;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class PointViewInputFactoryTest {

  private final class StaticValuePointHandler extends AbstractExecutableExtension implements IPointHandler {
    private final int points;

    public StaticValuePointHandler(int points) {
      this.points = points;
    }

    @Override
    public int getPoints(ICharacterId characterId) {
      return points;
    }
  }

  private void assertPointEntryCreated(String expectedDisplayName, int expectedValue, IPointEntry[] entries) {
    assertEquals(1, entries.length);
    assertEquals(expectedDisplayName, entries[0].getModelDisplayName());
    assertEquals(String.valueOf(expectedValue), entries[0].getExperiencePoints());
    EasyMock.verify(pointConfigurationProvider);
  }

  private IPointConfiguration[] createPointConfigurations(String expectedDisplayName, int expectedValue) {
    return new IPointConfiguration[] { new PointConfiguration(expectedDisplayName, new StaticValuePointHandler(
        expectedValue)) };
  }

  private PointViewInputFactory factory;
  private IPointConfigurationProvider pointConfigurationProvider;

  @Before
  public void createFactory() throws Exception {
    this.pointConfigurationProvider = EasyMock.createMock(IPointConfigurationProvider.class);
    this.factory = new PointViewInputFactory(pointConfigurationProvider, null);
  }

  @Test
  public void experiencedConfigurationsUsedForExperienced() throws Exception {
    String expectedDisplayName = "Hasäntümlich Liebäs"; //$NON-NLS-1$
    int expectedValue = 5;
    EasyMock.expect(pointConfigurationProvider.getExperiencePointConfigurations(null, null)).andReturn(
        createPointConfigurations(expectedDisplayName, expectedValue));
    EasyMock.replay(pointConfigurationProvider);
    assertPointEntryCreated(expectedDisplayName, expectedValue, factory.create(null, true).createEntries());
  }

  @Test
  public void bonusPointConfigurationsUsedForUnexperienced() throws Exception {
    String expectedDisplayName = "Hasäntümlich Liebäs"; //$NON-NLS-1$
    int expectedValue = 5;
    EasyMock.expect(pointConfigurationProvider.getBonusPointConfigurations(null, null)).andReturn(
        createPointConfigurations(expectedDisplayName, expectedValue));
    EasyMock.replay(pointConfigurationProvider);
    assertPointEntryCreated(expectedDisplayName, expectedValue, factory.create(null, false).createEntries());
  }
}