Ext.define('Megatherium.view.UserLogin', {
    extend: 'Ext.form.Panel',
	requires: ['Ext.TitleBar', 'Ext.field.Text', 'Ext.field.Password'],
	alias: 'widget.userloginview',

    config: {
		scrollable: 'vertical',
		items: [
			{
				xtype: 'titlebar',
				title: 'Anmelden',
				docked: 'top'
			},
			{
                xtype: 'textfield',
                label: 'Login (E-Mail/Benutzername)',
                labelAlign: 'top',
                name: 'login',
				id: 'login',
				value: 'SargTeX'
            },
            {
                xtype: 'passwordfield',
                label: 'Passwort',
                labelAlign: 'top',
                name: 'password',
				id: 'password',
				value: 'thebospeler'
            },
            {
                xtype: 'button',
                itemId: 'submit',
                margin: '10 0 0 0',
                ui: 'confirm',
                text: 'Anmelden'
            },
            {
                xtype: 'button',
                itemId: 'register',
                ui: 'action',
                text: 'Registrieren'
            }
		],
		
        id: 'loginForm',
        listeners: [
			{
				delegate: '#submit',
				event: 'tap',
				fn: 'onSubmit'
			},
			{
				delegate: '#register',
				event: 'tap',
				fn: 'onRegisterTap'
			}
        ]
    },
	
	onSubmit: function(button, e, options) {
		this.fireEvent('performUserLogin', this);
	},
	
	onRegisterTap: function(button, e, options) {
		this.fireEvent('switchToUserCreate', this);
	}

});