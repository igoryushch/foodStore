/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.foodstore.services;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ua.foodstore.dao.ReportEntryCategory;
import ua.foodstore.managedbeans.CategoryListBean;
import ua.foodstore.managedbeans.MenuCategoryBean;

import java.sql.Date;
import java.util.List;

//import java.util.GregorianCalendar;
//import ua.foodstore.dao.ReportEntry;
//import ua.foodstore.entities.Dbuser;
//import ua.foodstore.entities.MenuCategory;
//import ua.foodstore.entities.MenuPosition;
//import ua.foodstore.entities.PurchaseOrder;

/**
 *
 * @author r858c041
 */
public class TestMain {
    public static void main(String[] args) {
        GenericApplicationContext cntx = new GenericXmlApplicationContext("applicationContext.xml");
        //cntx.refresh();
        
//        UserService users = cntx.getBean("userService", UserService.class);
//        
//        List<Dbuser> usersList = users.findAll();
//        for(Dbuser us : usersList)
//        {
//            System.out.println(us);
//        } 
        
        ReportService mps = cntx.getBean("reportService", ReportService.class);
        
        List<ReportEntryCategory> mpList = mps.getReportByCategory(new Date(112, 11, 1), new Date(113, 11, 1));
        for(ReportEntryCategory mcat : mpList)
        {
            System.out.println(mcat);
        }

        CategoryListBean categoryListBean = cntx.getBean("categoryListBean", CategoryListBean.class);
        List<MenuCategoryBean> categories = categoryListBean.getCategories();
        for (MenuCategoryBean mcb : categories)
        {
            System.out.println(mcb.getName());
        }

//        MenuCategoryService mcategoryService = cntx.getBean("menuCategoryService", MenuCategoryService.class);
//        List<MenuCategory> categoriesList = mcategoryService.findAll();
//        for ( MenuCategory mc : categoriesList )
//        {
//            System.out.println(mc.getName());
//        }
    }
}
