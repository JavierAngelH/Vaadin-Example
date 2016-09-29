package com.example.filecreator.components;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import com.example.filecreator.FileGenerator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Extension;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainPanel extends Panel {

	private static final long serialVersionUID = 1L;
	public TextField tfFirstName = new TextField();
	public TextField tfSecondName = new TextField();
	public TextField tfThirdName = new TextField();

	final Button downloadFirstButton = new Button();
	final Button downloadSecondButton = new Button();
	final Button downloadThirdButton = new Button();

	public Button button = new Button("Generate");

	public MainPanel() {
		setCaption("GENERATE FILE");
		setWidth("500px");

		downloadFirstButton.setId("downloadFirstButton");
		downloadSecondButton.setId("downloadSecondButton");
		downloadThirdButton.setId("downloadThirdButton");

		downloadFirstButton.setStyleName("invisibleButton");
		downloadSecondButton.setStyleName("invisibleButton");
		downloadThirdButton.setStyleName("invisibleButton");

		VerticalLayout layout = new VerticalLayout();
		layout.addComponents(downloadFirstButton, downloadSecondButton,
				downloadThirdButton);

		layout.setSpacing(false);
		layout.setMargin(new MarginInfo(true, true, true, true));
		FormLayout formlayout = new FormLayout();
		formlayout.setSpacing(true);
		formlayout.setSizeFull();
		tfFirstName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfFirstName.setCaption("FIRST TABLE NAME");
		tfFirstName.setSizeFull();

		tfSecondName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfSecondName.setCaption("SECOND TABLE NAME");
		tfSecondName.setSizeFull();

		tfThirdName.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfThirdName.setCaption("THIRD TABLE NAME");
		tfThirdName.setSizeFull();

		button.addStyleName(ValoTheme.BUTTON_TINY);
		button.addStyleName(ValoTheme.BUTTON_PRIMARY);
		button.setImmediate(true);
		button.setClickShortcut(KeyCode.ENTER);

		formlayout.addComponents(tfFirstName, tfSecondName, tfThirdName);
		formlayout.setComponentAlignment(tfFirstName, Alignment.MIDDLE_CENTER);
		formlayout.setComponentAlignment(tfSecondName, Alignment.MIDDLE_CENTER);
		formlayout.setComponentAlignment(tfThirdName, Alignment.MIDDLE_CENTER);

		layout.addComponent(formlayout);
		layout.addComponent(button);

		layout.setComponentAlignment(formlayout, Alignment.TOP_CENTER);
		layout.setComponentAlignment(button, Alignment.BOTTOM_RIGHT);

		setContent(layout);

		final FileGenerator fileGenerator = new FileGenerator();

		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				String path = "";
				try {

					if (!tfFirstName.isEmpty()) {
						String tableName = tfFirstName.getValue();
						try {
							Collection<Extension> extensionsList = downloadFirstButton
									.getExtensions();
							for (Extension extension : extensionsList) {
								try {
									downloadFirstButton
											.removeExtension(extension);
								} catch (Exception e) {

								}
							}

							path = fileGenerator.generateFile(tableName);
							FileResource res = new FileResource(new File(path));
							FileDownloader fileDownloader = new FileDownloader(
									res);
							fileDownloader.extend(downloadFirstButton);
							Page.getCurrent()
									.getJavaScript()
									.execute(
											"document.getElementById('downloadFirstButton').click();");
						} catch (Exception e) {

						}
					}

					if (!tfSecondName.isEmpty()) {
						try {

							Collection<Extension> extensionsList = downloadSecondButton
									.getExtensions();
							for (Extension extension : extensionsList) {
								try {
									downloadSecondButton
											.removeExtension(extension);
								} catch (Exception e) {

								}
							}

							String tableName = tfSecondName.getValue();
							String result = fileGenerator
									.generateFile(tableName);
							FileResource res = new FileResource(
									new File(result));
							FileDownloader fileDownloader = new FileDownloader(
									res);

							fileDownloader.extend(downloadSecondButton);
							Page.getCurrent()
									.getJavaScript()
									.execute(
											"document.getElementById('downloadSecondButton').click();");

							path = path + ", " + result;
						} catch (Exception e) {

						}
					}

					if (!tfThirdName.isEmpty()) {
						try {

							Collection<Extension> extensionsList = downloadThirdButton
									.getExtensions();
							for (Extension extension : extensionsList) {
								try {
									downloadThirdButton
											.removeExtension(extension);
								} catch (Exception e) {

								}
							}

							String tableName = tfThirdName.getValue();
							String result = fileGenerator
									.generateFile(tableName);
							FileResource res = new FileResource(
									new File(result));

							FileDownloader fileDownloader = new FileDownloader(
									res);

							fileDownloader.extend(downloadThirdButton);
							Page.getCurrent()
									.getJavaScript()
									.execute(
											"document.getElementById('downloadThirdButton').click();");

							path = path + ", " + result;
						} catch (Exception e) {

						}
					}

					if (!path.isEmpty()) {
						Notification.show("File(s) generated succesfully");

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
}
