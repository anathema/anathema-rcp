package anathema_rcp;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of the actions added to a workbench window.
 * Each window will be populated with new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

  // Actions - important to allocate these only in makeActions, and then use them
  // in the fill methods. This ensures that the actions aren't recreated when
  // fillActionBars is called with FILL_PROXY.
  private IWorkbenchAction exitAction;
  private List<IAction> toolbarActions = new ArrayList<IAction>();

  public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
    super(configurer);
  }

  @Override
  protected void makeActions(final IWorkbenchWindow window) {
    // Creates the actions and registers them.
    // Registering is needed to ensure that key bindings work.
    // The corresponding commands keybindings are defined in the plugin.xml file.
    // Registering also provides automatic disposal of the actions when the window is closed.
    exitAction = ActionFactory.QUIT.create(window);
    register(exitAction);
    createActions(window);
  }

  private void createActions(IWorkbenchWindow workbenchWindows) {
    IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint("Anathema.MainToolbar"); //$NON-NLS-1$
    for (IExtension extension : extensionPoint.getExtensions()) {
      for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {
        try {
          IWindowAction action = (IWindowAction) configurationElement.createExecutableExtension("actionClass"); //$NON-NLS-1$
          action.setWorkbenchWindow(workbenchWindows);
          toolbarActions.add(action);
        }
        catch (CoreException e) {
          e.printStackTrace();
        }
      }
    }
  }

  @Override
  protected void fillMenuBar(IMenuManager menuBar) {
    MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
    menuBar.add(fileMenu);
    fileMenu.add(exitAction);
    MenuManager grrrMenuu = new MenuManager("&Grrr", "Grrr");
    menuBar.add(grrrMenuu);
    addActions(grrrMenuu);
  }

  @Override
  protected void fillCoolBar(ICoolBarManager coolBar) {
    IToolBarManager toolBarManager = new ToolBarManager();
    coolBar.add(toolBarManager);
    addActions(toolBarManager);
  }

  private void addActions(IContributionManager manager) {
    for (IAction action : toolbarActions) {
      manager.add(action);
    }
  }
}