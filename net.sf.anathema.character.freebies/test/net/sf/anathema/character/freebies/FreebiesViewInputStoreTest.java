package net.sf.anathema.character.freebies;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.freebies.view.FreebiePointInputProvider;
import net.sf.anathema.character.freebies.view.IFreebiePointEntryFactory;
import net.sf.anathema.view.valuelist.IValueEntry;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class FreebiesViewInputStoreTest {

  private FreebiePointInputProvider store;
  private IFreebiePointEntryFactory factory;

  @Before
  public void createStore() throws Exception {
    factory = EasyMock.createMock(IFreebiePointEntryFactory.class);
    store = new FreebiePointInputProvider(factory);
  }

  @Test
  public void returnsNoEntriesForNonCharacterEditorInput() throws Exception {
    assertEquals(0, store.getEntries(CharacterObjectMother.createNonCharacterEditorInput()).size());
  }

  @Test
  public void returnsNoEntriesForNullArgument() throws Exception {
    assertEquals(0, store.getEntries(null).size());
  }

  @Test
  public void requestsInputCreationForCharacterEditorInput() throws Exception {
    ICharacterId id = new DummyCharacterId();
    List<IValueEntry> entries = new ArrayList<IValueEntry>();
    EasyMock.expect(factory.create(id)).andReturn(entries);
    EasyMock.replay(factory);
    assertSame(entries, store.getEntries(CharacterObjectMother.createCharacterEditorInput(id)));
    EasyMock.verify(factory);
  }

  @Test
  public void returnsSameResultsForSuccessiveRequestsWithSameCharacter() throws Exception {
    ICharacterId id = new DummyCharacterId();
    List<IValueEntry> entries = new ArrayList<IValueEntry>();
    List<IValueEntry> moreEntries = new ArrayList<IValueEntry>();
    EasyMock.expect(factory.create(id)).andReturn(entries);
    EasyMock.expect(factory.create(id)).andReturn(moreEntries);
    EasyMock.replay(factory);
    assertSame(entries, store.getEntries(CharacterObjectMother.createCharacterEditorInput(id)));
    assertSame(moreEntries, store.getEntries(CharacterObjectMother.createCharacterEditorInput(id)));
    EasyMock.verify(factory);
  }
}