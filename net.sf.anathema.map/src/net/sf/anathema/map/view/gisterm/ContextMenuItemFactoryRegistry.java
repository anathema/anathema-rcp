package net.sf.anathema.map.view.gisterm;

import static org.easymock.EasyMock.*;

import net.disy.commons.core.predicate.AcceptAllPredicate;
import net.disy.commons.core.provider.IProvider;
import net.disy.commons.core.provider.StaticProvider;
import net.disy.commons.swing.action.GroupedMenuItem;

import de.disy.cadenza.core.model.repository.ICadenzaRepositoryModel;
import de.disy.gis.gisterm.map.layer.feature.IFeatureLayer;
import de.disy.gis.gisterm.pro.edit.contextmenu.IAttributeCustomizationFactory;
import de.disy.gis.gisterm.pro.edit.contextmenu.LayerEditLegendContextMenuItemFactory;
import de.disy.gis.gisterm.pro.edit.contextmenu.NullAttributeCustomizationFactory;
import de.disy.gis.gisterm.pro.edit.layer.ILayerEditManager;
import de.disy.gis.gisterm.pro.edit.layer.LayerEditStrategyRegistry;
import de.disy.gisterm.pro.map.popupmenu.IMapSelectionContext;
import de.disy.gisterm.pro.map.popupmenu.IMenuItemFactoryRegistry;
import de.disy.gisterm.pro.map.popupmenu.MenuItemFactoryRegistry;

public class ContextMenuItemFactoryRegistry {

  public static IMenuItemFactoryRegistry create() {
    LayerEditStrategyRegistry strategyRegistry = new LayerEditStrategyRegistry();
    strategyRegistry.addEditStrategy(new Db4OEditStrategy());
    IProvider<ILayerEditManager> editManagementProvider = new StaticProvider<ILayerEditManager>(null);
    IAttributeCustomizationFactory attributeCustomizationFactory = new NullAttributeCustomizationFactory();
    MenuItemFactoryRegistry registry = new MenuItemFactoryRegistry();
    // TODO Mit nächster Version auf schlankes Interface databasecontext umstellen
    ICadenzaRepositoryModel repositoryModel = createMock(ICadenzaRepositoryModel.class);
    registry.registerMenuItemFactory(new LayerEditLegendContextMenuItemFactory(
        strategyRegistry,
        editManagementProvider,
        new AcceptAllPredicate<IFeatureLayer>(),
        attributeCustomizationFactory,
        repositoryModel) {
      @Override
      public GroupedMenuItem[] createMenuItems(IMapSelectionContext context) {
        return super.createMenuItems(context);
      }
    });
    return registry;
  }
}
