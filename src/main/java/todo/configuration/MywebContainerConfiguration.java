package todo.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MywebContainerConfiguration  extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
	
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
	
		return new Class[]{MySpringContainerConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[]{"/"};
	}

}
