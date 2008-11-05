package net.sf.anathema.map.view.gisterm;

import java.io.IOException;

import de.disy.gis.core.geometry.BaseGeometryType;
import de.disy.gis.core.geometry.IBaseGeometryTypeVisitor;
import de.disy.gis.gisterm.geometry.validation.GeometryValidationException;
import de.disy.gis.gisterm.map.layer.feature.IFeatureLayer;
import de.disy.gis.gisterm.map.layer.feature.IFeatureLayerAndThemeLayerId;
import de.disy.gis.gisterm.map.layer.feature.IFeatureProxy;
import de.disy.gis.gisterm.map.layer.feature.attribute.IAttributeValuePairList;
import de.disy.gis.gisterm.map.layer.feature.attribute.ILayerAttributeList;
import de.disy.gis.gisterm.pro.edit.layer.AbstractFeatureLayerEditStrategy;
import de.disy.gis.gisterm.pro.edit.layer.FeatureSaveException;
import de.disy.gis.gisterm.pro.edit.layer.TransactionFailedException;
import de.disy.gis.gisterm.pro.mode.edit.EditGeometryType;

import com.vividsolutions.jts.geom.Geometry;

import net.sf.anathema.map.view.feature.LayerAttributeList;

public class Db4OEditStrategy extends AbstractFeatureLayerEditStrategy {

  @Override
  public void applyAttributeChanges(
      final IFeatureLayerAndThemeLayerId arg0,
      final IFeatureProxy arg1,
      final IAttributeValuePairList arg2) throws IOException, FeatureSaveException {
    // TODO Auto-generated method stub
  }

  @Override
  public void applyGeometryChanges(
      final IFeatureLayerAndThemeLayerId arg0,
      final IFeatureProxy arg1,
      final Geometry arg2) throws IOException, GeometryValidationException {
    // TODO Auto-generated method stub
  }

  @Override
  public void commitTransaction(final IFeatureLayer featureLayer) throws TransactionFailedException {
    // TODO Auto-generated method stub
  }

  @Override
  public EditGeometryType getEditGeometryType(final IFeatureLayer featureLayer) {
    class EditGeometryTypeCalculator implements IBaseGeometryTypeVisitor {

      private EditGeometryType type;

      @Override
      public void visitPolygon(final BaseGeometryType visitedGeometryType) {
        type = EditGeometryType.MULTI_POLYGON;
      }

      @Override
      public void visitPoint(final BaseGeometryType visitedGeometryType) {
        type = EditGeometryType.MULTI_POINT;
      }

      @Override
      public void visitLine(final BaseGeometryType visitedGeometryType) {
        type = EditGeometryType.MULTI_LINE;
      }
    }
    final EditGeometryTypeCalculator calculator = new EditGeometryTypeCalculator();
    featureLayer.getBaseGeometryType().accept(calculator);
    return calculator.type;
  }

  @Override
  public ILayerAttributeList getEditableAttributes(final IFeatureLayer featureLayer)
      throws IOException {
    return new LayerAttributeList();
  }

  @Override
  public void insertNew(
      final IFeatureLayerAndThemeLayerId arg0,
      final Geometry arg1,
      final IAttributeValuePairList arg2)
      throws IOException,
      GeometryValidationException,
      FeatureSaveException {
    // TODO Auto-generated method stub
  }

  @Override
  public boolean isApplicable(final IFeatureLayer arg0) {
    return true;
  }

  @Override
  public void remove(final IFeatureLayerAndThemeLayerId arg0, final IFeatureProxy[] arg1)
      throws IOException {
    // TODO Auto-generated method stub
  }

  @Override
  public void rollbackTransaction(final IFeatureLayer arg0) throws TransactionFailedException {
    // TODO Auto-generated method stub
  }

  @Override
  public void startTransaction(final IFeatureLayer arg0) throws TransactionFailedException {
    // TODO Auto-generated method stub
  }
}