package com.example.filecreator.components;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LoginPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Label labelError = new Label();
	public TextField userNameField = new TextField();
	public PasswordField passwordField = new PasswordField();
	public Button loginButton = new Button("Login");

	
	
	public LoginPanel(){
		setCaption("LOGIN");
		setWidth("350px");
		labelError.addStyleName(ValoTheme.LABEL_TINY);
        labelError.addStyleName(ValoTheme.LABEL_FAILURE);
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(new MarginInfo(true, true, true, true));
    	FormLayout formlayout = new FormLayout();
    	formlayout.setSpacing(true);
    	formlayout.setMargin(false);
        userNameField.addStyleName(ValoTheme.TEXTFIELD_TINY);
        userNameField.setCaption("USERNAME");
        userNameField.setRequired(true);
        userNameField.setSizeFull();
        passwordField.addStyleName(ValoTheme.TEXTFIELD_TINY);
        passwordField.setCaption("PASSWORD");
        passwordField.setRequired(true);
        passwordField.setSizeFull();
        loginButton.addStyleName(ValoTheme.BUTTON_TINY);
        loginButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        formlayout.addComponent(userNameField);
        formlayout.addComponent(passwordField);
        layout.addComponent(formlayout);
        layout.addComponent(loginButton);
        layout.addComponent(labelError);
        labelError.setVisible(false);
        layout.setComponentAlignment(formlayout, Alignment.TOP_CENTER);
        layout.setComponentAlignment(loginButton, Alignment.BOTTOM_RIGHT);

        loginButton.setClickShortcut(KeyCode.ENTER);
       
        setContent(layout);
      
    }
}
