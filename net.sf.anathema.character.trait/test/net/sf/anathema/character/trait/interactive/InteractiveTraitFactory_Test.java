package net.sf.anathema.character.trait.interactive;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;
import net.sf.anathema.character.trait.groupeditor.NullFavorizationInteraction;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class InteractiveTraitFactory_Test {

  private static final int MAXIMUM = 17;
  private InteractiveTraitFactory factory;
  private Identificate traitType;
  private IEditorInputConfiguration configuration;

  @Before
  public void createFactory() throws Exception {
    traitType = new Identificate("Michael");
    factory = new InteractiveTraitFactory(
        null,
        new BasicTrait(traitType),
        new DummyExperience(),
        new ArrayList<IValidator>());
  }

  @Test
  public void createsTraitWithMaximumValueFromConfiguration() throws Exception {
    createInputConfigurationWithMaximum();
    IInteractiveTrait interactiveTrait = factory.create(configuration, new NullFavorizationInteraction());
    assertThat(interactiveTrait.getMaximalValue(), is(MAXIMUM));
  }

  private void createInputConfigurationWithMaximum() {
    configuration = createMock(IEditorInputConfiguration.class);
    expect(configuration.getTraitMaximum(traitType)).andStubReturn(MAXIMUM);
    replay(configuration);
  }
}