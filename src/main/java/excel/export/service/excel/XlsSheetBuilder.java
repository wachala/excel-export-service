package excel.export.service.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public class XlsSheetBuilder {
    private String name;
    private List<XlsTableBuilder> tables;

    public XlsSheetBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public XlsSheetBuilder withTable(List<XlsTableBuilder> tables) {
        this.tables = tables;
        return this;
    }

    public void build(HSSFWorkbook workbook) {
        HSSFSheet sheet = workbook.createSheet(name);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);

        tables.forEach(table -> table.build(sheet));
    }
}
