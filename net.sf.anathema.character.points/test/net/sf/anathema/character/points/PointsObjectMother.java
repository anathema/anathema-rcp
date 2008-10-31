package net.sf.anathema.character.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.configuration.internal.IPointConfigurationProvider;

import org.easymock.EasyMock;

public class PointsObjectMother {

  public static IPointConfiguration createPointConfiguration(ICharacterId characterId, int value) {
    IPointConfiguration pointConfiguration = EasyMock.createMock(IPointConfiguration.class);
    EasyMock.expect(pointConfiguration.getPoints(characterId)).andReturn(value);
    EasyMock.replay(pointConfiguration);
    return pointConfiguration;
  }

  public static IPointConfigurationProvider createPointConfigurationProvider(
      ICharacterTemplate characterTemplate,
      IPointConfiguration... pointConfigurations) {
    IPointConfigurationProvider pointConfigurationProvider = EasyMock.createMock(IPointConfigurationProvider.class);
    EasyMock.expect(pointConfigurationProvider.getBonusPointConfigurations(characterTemplate)).andReturn(
        pointConfigurations).anyTimes();
    EasyMock.replay(pointConfigurationProvider);
    return pointConfigurationProvider;
  }
}