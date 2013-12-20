
package ua.foodstore.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpSession;


@ManagedBean
@SessionScoped
public class LogoutActionListener implements ActionListener{

    @Override
    public void processAction(ActionEvent ae) throws AbortProcessingException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
    }

    public LogoutActionListener() {
    }
}
