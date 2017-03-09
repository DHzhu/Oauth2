/**
 * 
 */
package com.security;


import org.apache.log4j.Logger;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;


/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月9日
 */
public class CustomSecurityManager extends DefaultWebSecurityManager{
	
	private static Logger log = Logger.getLogger(CustomSecurityManager.class);
	
	@Override
    protected SubjectContext resolveSession(SubjectContext context) {
        if (context.resolveSession() != null) {
            log.debug("Context already contains a session.  Returning.");
            return context;
        }
        try {
            //Context couldn't resolve it directly, let's see if we can since we have direct access to 
            //the session manager:
            Session session = resolveContextSession(context);
            if (session != null) {
                context.setSession(session);
            }
        } catch (InvalidSessionException e) {
            log.debug("Resolved SubjectContext context session is invalid.  Ignoring and creating an anonymous " +
                    "(session-less) Subject instance.");
            log.info(e.getMessage());
        }
        return context;
    }
}
