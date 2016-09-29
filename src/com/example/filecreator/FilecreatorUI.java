package com.example.filecreator;

import javax.servlet.annotation.WebServlet;

import com.example.filecreator.components.LoginPanel;
import com.example.filecreator.components.MainPanel;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("filecreator")
public class FilecreatorUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FilecreatorUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
	
		setSizeFull();
		final VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(true);
		setContent(layout);
		final MainPanel mainPanel = new MainPanel();
		
		
		final LoginPanel loginPanel = new LoginPanel();
		
		layout.addComponent(loginPanel);
		layout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		

	
		
		loginPanel.loginButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				String user =  loginPanel.userNameField.getValue();
				String password = loginPanel.passwordField.getValue();
				if(user.equalsIgnoreCase("user") && password.equalsIgnoreCase("1234")){
					layout.removeAllComponents();
					layout.addComponent(mainPanel);
					layout.setComponentAlignment(mainPanel, Alignment.MIDDLE_CENTER);
				}else{
					loginPanel.labelError.setVisible(true);
					loginPanel.labelError.setValue("Incorrect Credentials");
				}
			}
		});
	}

}