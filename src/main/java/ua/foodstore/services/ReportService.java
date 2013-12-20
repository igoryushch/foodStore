/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.services;

import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ua.foodstore.dao.ReportEntry;
import ua.foodstore.dao.ReportEntryCategory;

@Transactional(readOnly = true)
public interface ReportService {
    
    public List<ReportEntry> getSalesReport(Date sDate, Date eDate);
    
    public List<ReportEntryCategory> getReportByCategory(Date sDate, Date eDate);
    
}
