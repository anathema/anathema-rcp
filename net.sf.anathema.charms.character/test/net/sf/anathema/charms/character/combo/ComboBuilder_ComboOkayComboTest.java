package net.sf.anathema.charms.character.combo;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Test;

public class ComboBuilder_ComboOkayComboTest extends AbstractComboBuilderTestCase {

  @Test
  public void doesNotAllowExtraActionCharmWithExtraActionCharmInCombo() throws Exception {
    combo.charms.add(setCharmDataForId("comboExtraAction", createComboOkCharm("ExtraAction")));
    ICharmId charmId = setCharmDataForId("surplusExtraAction", createComboOkCharm("ExtraAction"));
    assertThat(builder.isValid(charmId), is(false));
  }

  @Test
  public void doesNotAllowSimpleCharmWithSimpleCharmInCombo() throws Exception {
    combo.charms.add(setCharmDataForId("comboSimple", createComboOkCharm("Simple")));
    ICharmId charmId = setCharmDataForId("surplusSimple", createComboOkCharm("Simple"));
    assertThat(builder.isValid(charmId), is(false));
  }

  @Test
  public void doesNotAllowSimpleCharmWithExtraActionAndSimpleCharmInCombo() throws Exception {
    combo.charms.add(setCharmDataForId("comboSimple", createComboOkCharm("Simple")));
    combo.charms.add(setCharmDataForId("comboExtraAction", createComboOkCharm("ExtraAction")));
    ICharmId charmId = setCharmDataForId("surplusSimple", createComboOkCharm("Simple"));
    assertThat(builder.isValid(charmId), is(false));
  }

  @Test
  public void doesNotAllowExtraActionCharmWithExtraActionAndSimpleCharmInCombo() throws Exception {
    combo.charms.add(setCharmDataForId("firstCombo", createComboOkCharm("Simple")));
    combo.charms.add(setCharmDataForId("secondCombo", createComboOkCharm("ExtraAction")));
    ICharmId charmId = setCharmDataForId("surplusExtraAction", createComboOkCharm("ExtraAction"));
    assertThat(builder.isValid(charmId), is(false));
  }
}