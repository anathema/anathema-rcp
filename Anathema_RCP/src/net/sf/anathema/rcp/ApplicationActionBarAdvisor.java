package net.sf.anathema.rcp;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

  private IContributionItem viewList;

  public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
    super(configurer);
  }

  @Override
  protected void makeActions(final IWorkbenchWindow window) {
    this.viewList = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
    register(ActionFactory.CLOSE.create(window));
    register(ActionFactory.CLOSE_ALL.create(window));
    register(ActionFactory.SAVE.create(window));
    register(ActionFactory.SAVE_ALL.create(window));
    register(ActionFactory.QUIT.create(window));
  }

  @Override
  protected void fillMenuBar(IMenuManager menuBar) {
    MenuManager viewMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_viewMenuName, "viewList");
    menuBar.add(viewMenu);
    viewMenu.add(viewList);
  }
}