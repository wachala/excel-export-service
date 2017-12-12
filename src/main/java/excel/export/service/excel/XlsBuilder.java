package excel.export.service.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public class XlsBuilder {
    private List<XlsSheetBuilder> sheets;

    public XlsBuilder withSheet(List<XlsSheetBuilder> sheets) {
        this.sheets = sheets;
        return this;
    }

    public HSSFWorkbook build() {
        HSSFWorkbook workbook = new HSSFWorkbook();

        sheets.forEach(sheet -> sheet.build(workbook));

        return workbook;
    }
}
