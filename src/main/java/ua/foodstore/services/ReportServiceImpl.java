/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.services;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.foodstore.dao.ReportDao;
import ua.foodstore.dao.ReportEntry;
import ua.foodstore.dao.ReportEntryCategory;

@Transactional(readOnly = true)
@Service("reportService")
public class ReportServiceImpl implements ReportService{

    @Autowired
    private ReportDao dao;
            
    @Override
    public List<ReportEntry> getSalesReport(Date sDate, Date eDate) {
        
        GregorianCalendar startDate = new GregorianCalendar();
        startDate.setTime(sDate);
        
        GregorianCalendar endDate = new GregorianCalendar();
        endDate.setTime(eDate);
        
        return dao.getReportData(startDate, endDate);
    }

    @Override
    public List<ReportEntryCategory> getReportByCategory(Date sDate, Date eDate) {
        
        GregorianCalendar startDate = new GregorianCalendar();
        startDate.setTime(sDate);
        
        GregorianCalendar endDate = new GregorianCalendar();
        endDate.setTime(eDate);
        
        return dao.getReportDataByCategory(startDate, endDate);
    }
    
}
