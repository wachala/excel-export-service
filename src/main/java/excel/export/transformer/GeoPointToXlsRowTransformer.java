package excel.export.transformer;

import excel.export.model.GeoPoint;

public class GeoPointToXlsRowTransformer implements Transformer<GeoPoint, Object[]>{
    @Override
    public Object[] transform(GeoPoint source) {
        return new Object[0];
    }
}
