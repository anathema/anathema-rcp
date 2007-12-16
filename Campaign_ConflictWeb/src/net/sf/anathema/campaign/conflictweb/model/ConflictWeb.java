package net.sf.anathema.campaign.conflictweb.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.lib.control.GenericControl;

import org.eclipse.draw2d.geometry.Point;

public class ConflictWeb {

  private final List<IParty> parties = new ArrayList<IParty>();
  private final GenericControl<IConflictWebListener> listeners = new GenericControl<IConflictWebListener>();

  public ConflictWeb() {
    addParty(new Party(new Point(100, 300)));
  }
  
  public void addParty(final IParty party) {
    parties.add(party);
    listeners.forAllDo(new IClosure<IConflictWebListener>() {
      @Override
      public void execute(IConflictWebListener listener) throws RuntimeException {
        listener.partyAdded(party, parties.size() - 1);
      }
    });
  }

  public void addListener(IConflictWebListener listener) {
    listeners.addListener(listener);
  }

  public List<IParty> getParties() {
    return parties;
  }
}