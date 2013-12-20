package ua.foodstore.services;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import ua.foodstore.managedbeans.UserBean;

public class AccessControlPhaseListener implements PhaseListener
{

    private List<String> allowedPages = Arrays.asList(
                                            "/base.xhtml",
                                            "/cart.xhtml",
                                            "/checkout.xhtml",
                                            "/accepted.xhtml",
                                            "/login.xhtml" );

    @Override
    public void afterPhase( PhaseEvent pe )
    {
        //throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void beforePhase( PhaseEvent pe )
    {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        if ( !allowedPages.contains( viewId) )
        {
            Application app = context.getApplication();
            UserBean user = (UserBean) app.evaluateExpressionGet( context, "#{user}", UserBean.class );
            if ( user.getRoleName()== null )
            {
                ViewHandler viewHandler = app.getViewHandler();
                UIViewRoot viewRoot = viewHandler.createView( context, "/login.xhtml" );
                context.setViewRoot( viewRoot );
            }
        }
    }

    @Override
    public PhaseId getPhaseId()
    {
        return PhaseId.RENDER_RESPONSE;
    }
    
}
