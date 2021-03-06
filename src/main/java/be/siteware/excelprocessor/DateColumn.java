package be.siteware.excelprocessor;

import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;

public class DateColumn extends NumericConverterColumn<Date> {
	
	private String dateformat;
	private CellStyle style;
	
	public DateColumn(String name, String property, String format) {
		super(name, 
				property, 
				number -> {return DateUtil.getJavaDate(number);}, 
				d -> {return DateUtil.getExcelDate(d);},
				0);
		this.dateformat = format;
	}
		
	@Override
	public CellStyle getStyle(Workbook wb) {
		if (style==null) {
			style = wb.createCellStyle();
			short df = wb.createDataFormat().getFormat(dateformat);
			style.setDataFormat(df);
		}
		return style;
	}

}
