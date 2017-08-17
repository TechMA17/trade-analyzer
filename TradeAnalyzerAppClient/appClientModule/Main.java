import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import com.intelligenceoverflow.ejb.ExtractorRemote;

public class Main {
	@EJB
	private static ExtractorRemote er;
	public static void main(String[] args) {
		System.out.println("TEST");
		
		try {
			// Create Properties for JNDI InitialContext.
			Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY,
					org.jboss.naming.remote.client.InitialContextFactory.class.getName());
			prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			prop.put(Context.PROVIDER_URL, "remote://localhost:8080");
			prop.put("jboss.naming.client.ejb.context", true);
			System.out.println(prop);
			
			
			// Create the JNDI InitialContext.
			Context context = new InitialContext(prop);
			NamingEnumeration<NameClassPair> list = context.list("");
			while (list.hasMore()) {
				System.out.println(list.next().getName());
			}
			extractLogDataBean(context);
		} catch (NamingException ex) {
			System.out.println("NamingException: " + ex.getMessage());
		}
	}
	
	public static void extractLogDataBean(Context context) {
		try {
			// Look-up the bean.
			ExtractorRemote er = (ExtractorRemote) context
					.lookup("TradeAnalyzer/TradeAnalyzerEJB/Extractor!com.intelligenceoverflow.ejb.ExtractorRemote");
			// Invoke business methods on the bean.
			er.extractLines(null);
			
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}


}