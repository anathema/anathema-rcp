package net.sf.anathema.character.freebies;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.model.ICharacterId;
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
    assertEquals(0, store.getEntries(CharacterObjectMother.createNonCharacterEditorInput()).length);
  }

  @Test
  public void returnsNoEntriesForNullArgument() throws Exception {
    assertEquals(0, store.getEntries(null).length);
  }

  @Test
  public void requestsInputCreationForCharacterEditorInput() throws Exception {
    ICharacterId id = new DummyCharacterId();
    IValueEntry[] entries = new IValueEntry[1];
    EasyMock.expect(factory.create(id)).andReturn(entries);
    EasyMock.replay(factory);
    assertSame(entries, store.getEntries(CharacterObjectMother.createCharacterEditorInput(id)));
    EasyMock.verify(factory);
  }

  @Test
  public void returnsSameResultsForSuccessiveRequestsWithSameCharacter() throws Exception {
    ICharacterId id = new DummyCharacterId();
    IValueEntry[] entries = new IValueEntry[1];
    IValueEntry[] moreEntries = new IValueEntry[1];
    EasyMock.expect(factory.create(id)).andReturn(entries);
    EasyMock.expect(factory.create(id)).andReturn(moreEntries);
    EasyMock.replay(factory);
    assertSame(entries, store.getEntries(CharacterObjectMother.createCharacterEditorInput(id)));
    assertSame(moreEntries, store.getEntries(CharacterObjectMother.createCharacterEditorInput(id)));
    EasyMock.verify(factory);
  }
}