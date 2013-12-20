package ua.foodstore.managedbeans;

import org.primefaces.model.chart.PieChartModel;
import ua.foodstore.dao.ReportEntry;
import ua.foodstore.dao.ReportEntryCategory;
import ua.foodstore.services.ReportService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.Date;
import java.util.List;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "report", eager = true)
@SessionScoped
public class ReportBean
{
    @ManagedProperty( value = "#{reportService}" )
    private ReportService reportService;
    private Date dateStart;
    private Date dateEnd;
    private boolean reportRendered = false;
    private PieChartModel pieModel;
    private List<ReportEntry> sales;
    
    public ReportBean()
    {
    }

    public Date getDateStart()
    {
        return dateStart;
    }

    public void setDateStart( Date dateStart )
    {
        this.dateStart = dateStart;
    }

    public Date getDateEnd()
    {
        return dateEnd;
    }

    public void setDateEnd( Date dateEnd )
    {
        this.dateEnd = dateEnd;
    }

    public boolean isReportRendered()
    {
        return reportRendered;
    }

    public void setReportRendered( boolean reportRendered )
    {
        this.reportRendered = reportRendered;
    }
    
    public PieChartModel getPieModel() {  
        return pieModel;  
    }  

    public List<ReportEntry> getSales()
    {
        return sales;
    }

    public void setSales( List<ReportEntry> sales )
    {
        this.sales = sales;
    }

    public ReportService getReportService()
    {
        return reportService;
    }

    public void setReportService( ReportService reportService )
    {
        this.reportService = reportService;
    }

    public void setPieModel( PieChartModel pieModel )
    {
        this.pieModel = pieModel;
    }

    private void createPieModel() {
        
        pieModel = new PieChartModel();  
  
        List<ReportEntryCategory> reportData = reportService.getReportByCategory(dateStart, dateEnd);
        
        for (ReportEntryCategory cat : reportData)
        {
            pieModel.set(cat.getMenuCategoryName(),cat.getOrderSum().doubleValue());
        }
        
//        pieModel.set("Salads", 500);  
//        pieModel.set("Appetizers", 200);  
//        pieModel.set("Pizzas", 800);  
//        pieModel.set("Desserts", 300); 
//        pieModel.set("Drinks", 720);
    } 
    
    public String createReportByCategory()
    {
        createPieModel();
        fillSalesData();
        reportRendered = true;
        return "reports";
    }
    
    private void fillSalesData()
    {
        sales = reportService.getSalesReport(dateStart, dateEnd);
//        sales = new ArrayList<ReportEntry>();
//        sales.add( new ReportEntry( "Caeser Salad", "Salads", 5, new BigDecimal( 66.5 )));
//        sales.add( new ReportEntry( "Avocado Salad", "Salads", 3, new BigDecimal( 33.8 )));
//        sales.add( new ReportEntry( "Pizza 4 season", "Pizzas", 12, new BigDecimal( 99.9 )));
//        sales.add( new ReportEntry( "Pizza Margherita", "Pizzas", 18, new BigDecimal( 138.01 )));
//        sales.add( new ReportEntry( "Coffee", "Drinks", 35, new BigDecimal( 289.44 )));
    }
    
    public int getSalesCount()
    {
        int total = 0;  
  
        for(ReportEntry sale : sales) {  
            total += sale.getOrderCount();  
        }  
  
        return total; 
    }
    
    public double getProfit()
    {
        double total = 0.0;  
  
        for(ReportEntry sale : sales) {  
            total += sale.getOrderSum().doubleValue();  
        }  
  
        return total; 
    }
    
//    public String createSalesReport()
//    {
//        fillSalesData();
//        reportRendered = true;
//        return "reports";
//    }
}
