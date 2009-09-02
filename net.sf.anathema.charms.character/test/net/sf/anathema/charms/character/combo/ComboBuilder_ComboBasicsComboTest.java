package net.sf.anathema.charms.character.combo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Test;

public class ComboBuilder_ComboBasicsComboTest extends AbstractComboBuilderTestCase {

  @Test
  public void doesNotAllowNonReflexiveComboBasicWithNonReflexiveComboBasicPresent() throws Exception {
    combo.charms.add(setCharmDataForId("firstComboBasic", createComboBasicCharm("Supplemental")));
    ICharmId charmId = setCharmDataForId("secondComboBasic", createComboBasicCharm("Supplemental"));
    assertThat(builder.isValid(charmId), is(false));
  }

  @Test
  public void doesAllowReflexiveComboCharmWithNonReflexiveComboBasicPresent() throws Exception {
    combo.charms.add(setCharmDataForId("firstComboBasic", createComboBasicCharm("Supplemental")));
    ICharmId charmId = setCharmDataForId("secondComboBasic", createComboBasicCharm("Reflexive"));
    assertThat(builder.isValid(charmId), is(true));
  }

  @Test
  public void doesNotAllowReflexiveNonComboCharmWithComboBasicCombo() throws Exception {
    combo.charms.add(setCharmDataForId("firstComboBasic", createComboBasicCharm("Supplemental")));
    ICharmId charmId = setCharmDataForId("secondComboBasic", createCharmData("Reflexive"));
    assertThat(builder.isValid(charmId), is(false));
  }

  @Test
  public void doesAllowNonReflexiveComboBasicCharmWithReflexiveComboBasicInCombo() throws Exception {
    combo.charms.add(setCharmDataForId("firstComboBasic", createComboBasicCharm("Reflexive")));
    ICharmId charmId = setCharmDataForId("secondComboBasic", createComboBasicCharm("Supplemental"));
    assertThat(builder.isValid(charmId), is(true));
  }

  @Test
  public void doesAllowNonReflexiveComboOkyCharmWithReflexiveComboBasicInCombo() throws Exception {
    combo.charms.add(setCharmDataForId("firstComboBasic", createComboBasicCharm("Reflexive")));
    ICharmId charmId = setCharmDataForId("secondComboBasic", createComboOkCharm("Supplemental"));
    assertThat(builder.isValid(charmId), is(true));
  }

  @Test
  public void doesNotAllowNonReflexiveComboBasicCharmWithNonReflexiveComboOkCharm() throws Exception {
    combo.charms.add(setCharmDataForId("firstComboBasic", createComboOkCharm("Supplemental")));
    ICharmId charmId = setCharmDataForId("secondComboBasic", createComboBasicCharm("Supplemental"));
    assertThat(builder.isValid(charmId), is(false));
  }
}