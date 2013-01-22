Ext.define('LordsAndKnights.controller.Main', {
	extend: 'Ext.app.Controller',
	requires: ['Ext.data.JsonP', 'Megatherium.communicator.MegatheriumCommunicator'],
	
	serverURL: 'http://spinshare.de:8080/Lords___Knights_Server/',
	
	config: {
		refs: {
			userCreateView: {
				xtype: 'usercreateview',
				selector: 'usercreateview',
				autoCreate: true
			},
			userLoginView: {
				xtype: 'userloginview',
				selector: 'userloginview',
				autoCreate: true
			},
			slideNavigationView: {
				xtype: 'slidenavigationview',
				selector: 'slidenavigationview',
				autoCreate: true
			}
		},
		
		control: {
			userCreateView: {
				switchToUserLogin: 'switchToUserLogin',
				performUserCreate: 'performUserCreate'
			},
			userLoginView: {
				switchToUserCreate: 'switchToUserCreate',
				performUserLogin: 'performUserLogin'
			}
		}
	},
	navigationPanelVisible: false,
	
	showMainContainer: function() {
		Ext.Viewport.animateActiveItem(this.getSlideNavigationView(), {type: 'cover', direction: 'down'});
	},
	
	switchToUserCreate: function() {
		Ext.Viewport.animateActiveItem(this.getUserCreateView(), {type: 'slide', direction: 'left'});
	},
	
	switchToUserLogin: function() {
		Ext.Viewport.animateActiveItem(this.getUserLoginView(), {type: 'slide', direction: 'right'});
	},
	
	performUserLogin: function() {
		var values = this.getUserLoginView().getValues();
		MegatheriumCommunicator.login(values.login, values.password, function(response, success) {
			if (success) {
				Ext.Msg.alert("Anmeldung erfolgreich", "Sie wurden erfolgreich angemeldet.", function() {
					this.showMainContainer();
				}, this);
			} else Ext.Msg.alert("Anmeldung fehlgeschlagen", "Die Kombination aus Benutzername/E-Mail und Passwort ist nicht bekannt.");
		}, this);
	},
	
	performUserCreate: function() {
		var values = this.getUserCreateView().getValues();
		
		// try to create user
		MegatheriumCommunicator.createUser(values.name, values.email, values.password, function(data, success) {
			if (success) {
				// user creation successfull
				Ext.Msg.alert("Registrierung erfolgreich", "Der Benutzeraccount wurde erfolgreich erstellt.", function() {
					this.switchToUserLogin();
				}, this);
			} else {
				// show dissuccess message
				var message = "Unexpected error, please notify us: \"UserCreate->"+data.response.type+"\"";
				switch (data.response.type) {
					case 'noname': message = "Bitte wählen Sie einen Benutzernamen."; break;
					case 'noemail': message = "Bitte wählen Sie eine E-Mail Adresse."; break;
					case 'nopassword': message = "Bitte wählen Sie ein Passwort."; break;
					case 'duplicatename': message = "Dieser Benutzername ist bereits vergeben."; break;
					case 'duplicateemail': message = "Diese E-Mail Adresse ist bereits vergeben."; break;
				}
				Ext.Msg.alert("Registrierung fehlgeschlagen", message);
			}
		}, this);		
	},
	
	/**
	 * Loads the platforms from remote into the local storage.
	 */
	loadPlatformList: function() {
		Ext.getStore("platformStore").add(MegatheriumCommunicator.getPlatformList());
	},
	
	/**
	 * Loads the account list for the current user.
	 */
	loadAccountList: function() {
		
	}
	
});