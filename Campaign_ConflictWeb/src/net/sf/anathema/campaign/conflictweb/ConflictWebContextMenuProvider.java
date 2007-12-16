/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Elias Volanakis and others.  * All rights reserved. This program and the accompanying
 * materials  * are made available under the terms of the Eclipse Public License v1.0  * which accompanies this
 * distribution, and is available at  * http://www.eclipse.org/legal/epl-v10.html  *  * Contributors:  *    Elias
 * Volanakis - initial API and implementation  
 **********************************************************************************************************************/
package net.sf.anathema.campaign.conflictweb;

import net.sf.anathema.campaign.conflictweb.model.ConflictWeb;
import net.sf.anathema.campaign.conflictweb.model.Party;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;

/**
 * Provides context menu actions for the ShapesEditor.
 * 
 * @author Elias Volanakis
 */
class ConflictWebContextMenuProvider extends ContextMenuProvider {

  /** The editor's action registry. */
  private ActionRegistry actionRegistry;

  /**
   * Instantiate a new menu context provider for the specified EditPartViewer and ActionRegistry.
   * 
   * @param viewer the editor's graphical viewer
   * @param registry the editor's action registry
   * @throws IllegalArgumentException if registry is <tt>null</tt>.
   */
  public ConflictWebContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
    super(viewer);
    if (registry == null) {
      throw new IllegalArgumentException();
    }
    actionRegistry = registry;
  }

  /**
   * Called when the context menu is about to show. Actions, whose state is enabled, will appear in the context menu.
   * 
   * @see org.eclipse.gef.ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
   */
  @Override
  public void buildContextMenu(IMenuManager menu) {
    GEFActionConstants.addStandardActionGroups(menu);
    menu.appendToGroup(GEFActionConstants.GROUP_EDIT, new Action("Add party") {
      @Override
      public void run() {
        ConflictWeb web = (ConflictWeb) ((GraphicalViewer) getViewer()).getContents().getModel();
        web.addParty(new Party(new Point(0, 0)));
      }
    });
  }
}