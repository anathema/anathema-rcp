package net.sf.anathema.campaign.conflictweb.parts;

import java.util.List;

import net.sf.anathema.campaign.conflictweb.model.ConflictWeb;
import net.sf.anathema.campaign.conflictweb.model.IConflictWebListener;
import net.sf.anathema.campaign.conflictweb.model.IParty;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public class ConflictWebEditPart extends AbstractGraphicalEditPart {

  private IConflictWebListener listener = new IConflictWebListener() {
    @Override
    public void partyAdded(IParty party, int index) {
      PartyEditPart editPart = new PartyEditPart();
      editPart.setModel(party);
      addChild(editPart, index);
    }
  };

  @Override
  public void setModel(Object model) {
    ConflictWeb conflictWeb = (ConflictWeb) model;
    super.setModel(model);
    conflictWeb.addListener(listener);
  }

  @Override
  protected IFigure createFigure() {
    Figure newFigure = new FreeformLayer();
    newFigure.setBorder(new MarginBorder(3));
    newFigure.setLayoutManager(new FreeformLayout());
    return newFigure;
  }

  @Override
  protected List< ? > getModelChildren() {
    return ((ConflictWeb) getModel()).getParties();
  }

  @Override
  protected void createEditPolicies() {
    // nothing to do
  }
}