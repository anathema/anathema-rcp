package net.sf.anathema.character.spiritualtraits.model;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.validator.DummyCharacter;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class SubTraitGroupFactory_Test {

  private static final String SELECTED_SUB_GROUP = "selectedSubGroup";
  private ICharacter character;
  private List<IDisplayTraitGroup<IDisplayTrait>> allGroups;
  private SubTraitGroupFactory groupFactory;

  @Before
  public void createFactory() throws Exception {
    character = new DummyCharacter();
    allGroups = new ArrayList<IDisplayTraitGroup<IDisplayTrait>>();
    final IDisplayGroupFactory factory = createMock(IDisplayGroupFactory.class);
    expect(factory.createDisplayTraitGroups(character)).andReturn(allGroups);
    replay(factory);
    groupFactory = new SubTraitGroupFactory(factory, SELECTED_SUB_GROUP);
  }

  @Test
  public void createsNoGroupsForNoGroups() throws Exception {
    final List<IDisplayTraitGroup<IDisplayTrait>> groups = groupFactory.createDisplayTraitGroups(character);
    assertThat(groups.size(), is(0));
  }

  @Test
  public void createsNoGroupsForOtherGroups() throws Exception {
    allGroups.add(createGroup("otherGroupName"));
    final List<IDisplayTraitGroup<IDisplayTrait>> groups = groupFactory.createDisplayTraitGroups(character);
    assertThat(groups.size(), is(0));
  }

  @Test
  public void createsGroupWithCorrectName() throws Exception {
    final IDisplayTraitGroup<IDisplayTrait> expectedGroup = createGroup(SELECTED_SUB_GROUP);
    allGroups.add(expectedGroup);
    allGroups.add(createGroup("otherGroupName"));
    final List<IDisplayTraitGroup<IDisplayTrait>> groups = groupFactory.createDisplayTraitGroups(character);
    assertThat(groups.size(), is(1));
    assertThat(groups.get(0), is(sameInstance(expectedGroup)));
  }

  @SuppressWarnings("unchecked")
  private IDisplayTraitGroup<IDisplayTrait> createGroup(final String groupName) {
    final IDisplayTraitGroup<IDisplayTrait> group = createMock(IDisplayTraitGroup.class);
    expect(group.getId()).andReturn(groupName);
    replay(group);
    return group;
  }
}