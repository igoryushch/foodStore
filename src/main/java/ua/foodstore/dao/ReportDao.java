/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author r858c041
 */
public interface ReportDao  extends GenericDao<ReportEntry>{
    public List<ReportEntry> getReportData(Calendar startDate, Calendar endDate);
    public List<ReportEntryCategory> getReportDataByCategory(Calendar startDate, Calendar endDate);
}
