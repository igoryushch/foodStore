/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.dao;

import java.util.Calendar;
import java.util.List;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository("reportDao")
public class ReportDaoImpl  extends GenericDaoImpl<ReportEntry> implements ReportDao{

    @Override
    public List<ReportEntry> getReportData(Calendar startDate, Calendar endDate) {
        TypedQuery<ReportEntry> query = em.createQuery(
                "SELECT new ua.foodstore.dao.ReportEntry(oi.position.name, oi.position.category.name,SUM(oi.quantity),SUM(oi.amount)) from OrderItem oi Where oi.order.orderDate BETWEEN :sDate AND :eDate GROUP BY oi.position.name,oi.position.category.name",
                ReportEntry.class);

        query.setParameter("sDate", startDate, TemporalType.TIMESTAMP);
        query.setParameter("eDate", endDate, TemporalType.TIMESTAMP);
        List<ReportEntry> resList = query.getResultList();
        return resList;
    }

    @Override
    public List<ReportEntryCategory> getReportDataByCategory(Calendar startDate, Calendar endDate) {
        TypedQuery<ReportEntryCategory> query = em.createQuery(
                "SELECT new ua.foodstore.dao.ReportEntryCategory(oi.position.category.name,SUM(oi.quantity),SUM(oi.amount)) from OrderItem oi Where oi.order.orderDate BETWEEN :sDate AND :eDate GROUP BY oi.position.category.name",
                ReportEntryCategory.class);

        query.setParameter("sDate", startDate, TemporalType.TIMESTAMP);
        query.setParameter("eDate", endDate, TemporalType.TIMESTAMP);
        List<ReportEntryCategory> resList = query.getResultList();
        return resList;
    }
    
}
