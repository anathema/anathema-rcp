package net.sf.anathema.charms.character.fake;

import java.util.List;

import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.tree.ITreeProvider;
import net.sf.anathema.charms.tree.TreeDto;
import net.sf.anathema.lib.collection.MultiEntryMap;

@SuppressWarnings("nls")
public class DummyTreeProvider implements ITreeProvider {

  public MultiEntryMap<String, String> genericIdsByCharactertype = new MultiEntryMap<String, String>();

  @Override
  public List<String> getGenericCharms(String typeId) {
    return genericIdsByCharactertype.get(typeId);
  }

  @Override
  public CharmPrerequisite[] getTree(String id) {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public List<String> getTreeList() {
    throw new UnsupportedOperationException("Dummy");
  }

  @Override
  public TreeDto getData(String id) {
    throw new UnsupportedOperationException("Dummy");
  }
}