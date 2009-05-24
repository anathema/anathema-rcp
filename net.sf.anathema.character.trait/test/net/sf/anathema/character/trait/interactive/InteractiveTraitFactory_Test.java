package net.sf.anathema.character.trait.interactive;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
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

  @Before
  public void createFactory() throws Exception {
    traitType = new Identificate("Michael");
    IEditorInputConfiguration configuration = createMock(IEditorInputConfiguration.class);
    expect(configuration.getTraitMaximum(traitType)).andStubReturn(MAXIMUM);
    replay(configuration);
    factory = new InteractiveTraitFactory(null, new Experience(), configuration, new NullFavorizationInteraction());
  }

  @Test
  public void createsTraitWithMaximumValueFromConfiguration() throws Exception {
    IBasicTrait basicTrait = new BasicTrait(traitType);
    ArrayList<IValidator> validators = new ArrayList<IValidator>();
    IInteractiveTrait interactiveTrait = factory.create(basicTrait, validators);
    assertThat(interactiveTrait.getMaximalValue(), is(MAXIMUM));
  }
}