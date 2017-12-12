package excel.export.service.excel;

public class HeaderFactory {

    public static Object[] getParkingLotHeader() {
        return new Object[]{
                "Id",
                "Location",
                "Address",
                "Pricing",
                "Security",
                "Restrictions",
                "Additional Information",
                "Google Street View Link",
                "Capacity"
        };
    }
}
