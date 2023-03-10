package com.zensar.pm.panel.export;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.zensar.pm.panel.dto.PanelAvailabilityListDTO;
import com.zensar.pm.panel.entity.PanelAvailabilityEntity;

public class FileExporter {
	public void exportToCSV(List<PanelAvailabilityListDTO> fileExportCSV,HttpServletResponse response, String csvName) throws IOException{

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String timeStamp = dateFormat.format(new Date());
        String fileName = csvName+timeStamp+".csv";

        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue ="attachment; filename ="+fileName;
        response.setHeader(headerKey, headerValue);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Panel_ID","Panel_Name","Panel_Email","Panel_Contact","Panel_Role","Type","Date","Time","Status"};
        String[] fieldMapping= {"panelId","panelName","email","contact","role","interviewType","date","slotTime","availabilityStatus"};

        csvWriter.writeHeader(csvHeader);

        for(PanelAvailabilityListDTO p :fileExportCSV)
        {

            csvWriter.write(p,fieldMapping);
        }

        csvWriter.close();
    }

}
