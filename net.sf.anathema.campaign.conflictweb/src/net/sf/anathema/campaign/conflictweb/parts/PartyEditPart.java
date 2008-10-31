package net.sf.anathema.campaign.conflictweb.parts;

import net.sf.anathema.campaign.conflictweb.model.IParty;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public class PartyEditPart extends AbstractGraphicalEditPart {

  private static final Dimension SIZE = new Dimension(80, 40);

  @Override
  protected IFigure createFigure() {
    IParty party = (IParty) getModel();
    Ellipse ellipse = new Ellipse();
    ellipse.setBounds(new Rectangle(party.getPosition(), SIZE));
    return ellipse;
  }

  @Override
  protected void createEditPolicies() {
    // nothing to do
  }
}