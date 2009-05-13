package net.sf.anathema.character.points;

import java.util.Collections;
import java.util.List;

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
      IPointConfiguration pointConfiguration) {
    IPointConfigurationProvider pointConfigurationProvider = EasyMock.createMock(IPointConfigurationProvider.class);
    List<IPointConfiguration> singletonList = Collections.singletonList(pointConfiguration);
    EasyMock.expect(pointConfigurationProvider.getBonusPointConfigurations(characterTemplate)).andReturn(
        singletonList).anyTimes();
    EasyMock.replay(pointConfigurationProvider);
    return pointConfigurationProvider;
  }
}